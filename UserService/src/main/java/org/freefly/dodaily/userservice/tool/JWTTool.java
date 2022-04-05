package org.freefly.dodaily.userservice.tool;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTTool {

    // Do not modify this parameter!
    private final String secret = "dodaily_user";

    private final String CLAIMS_KEY_NAME = "DODAILY_NAME";
    private final String CLAIMS_KEY_DATE = "DODAILY_DATE";

    private Date generateDate;
    // One Day Default
    private int expire = 60 * 60 * 24 * 1000;

    public String generateToken(String name) {
        Map<String, Object> claims = new HashMap<>();
        generateDate = new Date();
        claims.put(CLAIMS_KEY_NAME, name);
        claims.put(CLAIMS_KEY_DATE, generateDate);
        return generate(claims);
    }

    public boolean volidateToken(String token) {
        Claims claims = getClaims(token);
        if (claims == null) {
            return false;
        } else {
            Date expDate = new Date(claims.getExpiration().getTime() + expire);
            Date date = new Date();
            if (expDate.before(date)) {
                return false;
            } else {
                return true;
            }
        }
    }

    private String generate(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(generateDate.getTime() + expire))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
