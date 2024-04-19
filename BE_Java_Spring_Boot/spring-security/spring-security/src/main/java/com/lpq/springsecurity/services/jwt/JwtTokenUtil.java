package com.lpq.springsecurity.services.jwt;

import com.lpq.springsecurity.repositories.ITokenRepository;
import com.lpq.springsecurity.services.user.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class); // đối tượng logger để ghi lại thông tin, được sử dụng để ghi lại các thông báo lỗi hoặc cảnh báo trong quá trình xử lý token

    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24h

    @Autowired
    private ITokenRepository tokenRepository;

    @Value("${app.jwt.secret}") // error if do not have value - khóa bí mật được sử dụng để ký và xác minh token
    private String SECRET_KEY;

    public String generateAccessToken(Authentication authentication) {//tạo  một token JWT từ thông tin người dùng đã được xác thực
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrincipal.getEmail()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key key() {// tạo một đối tượng Key từ SECRET_KEY, được sử dụng để ký và xác minh token.
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));
    }

    public boolean validateAccessToken(String token) {//xác thực tính hợp lệ của một token JWT đã cung cấp
        try {
            Jwts.parserBuilder().setSigningKey(key()).build()
                    .parse(token);
            return true;
        } catch (ExpiredJwtException ex) {
            tokenRepository.deleteByToken(token);
            logger.error("Jwt expired", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            logger.error("Token is null, empty or only whitespace", ex.getMessage());
        } catch (MalformedJwtException ex) {
            logger.error("JWT is invalid", ex);
        } catch (UnsupportedJwtException ex) {
            logger.error("JWT is not supported", ex);
        } catch (SignatureException ex) {
            logger.error("Signature validation failed");
        }
        return false;
    }

    public String getEmailFromJwtToken(String token) {// lấy email từ một token JWT đã cung cấp
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

}
