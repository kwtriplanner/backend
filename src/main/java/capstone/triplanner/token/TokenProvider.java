package capstone.triplanner.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class TokenProvider {

    private Key key;
    private final String secretKey;
    private final long accessTokenExpTime;
    public TokenProvider(@Value("${jwt.secret}") String secretKey,
                         @Value("${jwt.expiration}") long accessTokenExpTime) {
        this.secretKey = secretKey;
        this.accessTokenExpTime = accessTokenExpTime;
    }

    @PostConstruct
    public void init() {
        // Base64로 디코딩된 키를 생성 (한 번만 초기화)
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        System.out.println("Key initialized = " + key);
    }

    public String createToken(String username) {
        Claims claims = Jwts.claims().setSubject(username);
        Date now = new Date();
        Date validity = new Date(now.getTime() + accessTokenExpTime);

        System.out.println("Key in createToken = " + key);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            System.out.println("Key in validateToken = " + key);
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getUsernameByBearer(String tokenWithBearer) {
        String token = tokenWithBearer.replace("Bearer ", "");
        return getUsername(token);
    }

}
