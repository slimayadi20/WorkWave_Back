package com.example.workwave.configuration;


import com.example.workwave.services.JwtService;
import com.example.workwave.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
//retrieve the authorization header then retrive the bearer token then check the username and check the token (exipred,correct informations) then allow th request
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
String header=request.getHeader("Authorization");

String jwtToken=null;
String username=null;
if(header !=null && header.startsWith("Bearer ")){
    //si le header n'est pas null il va commencer par Bearer
    jwtToken=header.substring(7);

    try{
       username= jwtUtil.getUserNameFromToken(jwtToken);

    }catch(IllegalArgumentException e){
System.out.println("Enable to get jwt token");
    }catch(ExpiredJwtException e){
        System.out.println("Jwt Token is expired");

    }

}else{
    System.out.println("Jwt token does not start with Bearer");
}

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = jwtService.loadUserByUsername(username);

            if (jwtUtil.validateToken(jwtToken, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);

    }

}
