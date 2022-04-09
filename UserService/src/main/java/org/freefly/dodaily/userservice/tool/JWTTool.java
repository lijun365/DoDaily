package org.freefly.dodaily.userservice.tool;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTTool {

    // Do not modify this parameter!
    private static final String secret = "DODAILY_USER";

    private static final String CLAIMS_KEY_USERID = "DODAILY_USERID";
    private static final String CLAIMS_KEY_NAME = "DODAILY_NAME";
    private static final String CLAIMS_KEY_DATE = "DODAILY_DATE";

    private static Date generateDate;
    // One Day Default
    private static int expire = 60 * 60 * 24 * 1000;

    public static String generateToken(int userId, String name) {
        Map<String, Object> claims = new HashMap<>();
        generateDate = new Date();
        claims.put(CLAIMS_KEY_USERID, userId);
        claims.put(CLAIMS_KEY_NAME, name);
        claims.put(CLAIMS_KEY_DATE, generateDate);
        return generate(claims);
    }

    // Only volidate the time of token
    public static boolean volidateToken(String token) {
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

    private static String generate(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(generateDate.getTime() + expire))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public static Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }
}
