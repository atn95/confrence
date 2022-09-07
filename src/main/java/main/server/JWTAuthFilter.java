package main.server;

import main.server.account.AccountService;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(JWTAuthFilter.class);

    @Autowired
    AccountService accountService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/api/user/register") || request.getServletPath().equals("/api/user/login")) {
            filterChain.doFilter(request,response);
        } else {
            final String tokenFromRequest = request.getHeader("Authorization");
            String userName = null;
            String encryptedJwtToken = null;
            String jwtToken = null;
            logger.debug("Inside JwtRequestFilter--OncePerRequestFilter");
            // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
            if (tokenFromRequest != null && tokenFromRequest.startsWith("Bearer ")) {
                encryptedJwtToken = tokenFromRequest.substring(7);
                jwtToken = EncryptionUtil.decrypt(encryptedJwtToken);
                try {
                    userName = jwtTokenUtil.getUserNameFromToken(jwtToken);
                } catch (IllegalArgumentException e) {
                    logger.error("Unable to get JWT Token");
                } catch (ExpiredJwtException e) {
                    logger.error("JWT Token has expired");
                }
            } else {
                logger.warn("JWT Token does not begin with Bearer String");
            }

            //  Once we get the token validate it and extract username(principal/subject) from it.
            if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = null;
                try {
                    userDetails = this.accountService.loadUserInfo(userName);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                // if token is valid configure Spring Security to manually set
                // authentication
                if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // After setting the Authentication in the context, we specify
                    // that the current user is authenticated. So it passes the
                    // Spring Security Configurations successfully.
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            filterChain.doFilter(request, response);
        }
    }
}