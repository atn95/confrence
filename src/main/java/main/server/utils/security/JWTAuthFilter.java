package main.server.utils.security;

import main.server.service.AccountService;
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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

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
            Cookie[] cookiesList = request.getCookies();
            String cooks = "";
            if (cookiesList != null) {
                cooks = Arrays.stream(cookiesList).map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(", "));
                System.out.println("Cookies: " + cooks);
//                System.out.println(cooks);
            }
            String[] cookies = cooks.split(", ");
            System.out.println("Sys OUT" + cookies[0].replace("Authorization=",""));
            final String tokenFromRequest = cookies[0].replace("Authorization=","");
            String userName = null;
            String jwtToken;
            logger.debug("Inside JwtRequestFilter--OncePerRequestFilter");
            // JWT Token is in the form "Bearer token". Remove Bearer word and get only the Token
            jwtToken = EncryptionUtil.decrypt(tokenFromRequest);
            try {
                userName = jwtTokenUtil.getUserNameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                logger.error("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                logger.error("JWT Token has expired");
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
