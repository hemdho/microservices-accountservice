package com.omniri.assignment.config;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
  private JwtUtils jwtUtils;

	
   
  
  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

 
  
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
    	System.out.println(" Readed heer");
      String jwt = parseJwt(request);
      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        
        Set<Entry<String,Object>> claims=jwtUtils.getClaimFromToken(jwt, Claims::entrySet);
        Entry<String,Object> claim=claims.stream().filter(m->m.getKey().equalsIgnoreCase("authority")).findFirst().orElse(null);
        String authority = null;
        if(claim!=null ) authority=claim.getValue().toString();
        jwtUtils.validateToken(jwt);
        	
        	UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null,
        			List.of(new SimpleGrantedAuthority(authority)));
        	
        	
        		authentication.setDetails(jwt);
        		
        		System.out.println("    ,,,,,,,,,,,,,,,,,,,,,,,,,,");
        		SecurityContextHolder.getContext().setAuthentication(authentication);
        		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        
      }
    } catch (Exception e) {
    	e.printStackTrace();
    	throw e;
     // logger.error("Cannot set user authentication: {}", e);
    }

    filterChain.doFilter(request, response);
  }

  private String parseJwt(HttpServletRequest request) {
    String headerAuth = request.getHeader("Authorization");

    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7, headerAuth.length());
    }

    return null;
  }
 
}
