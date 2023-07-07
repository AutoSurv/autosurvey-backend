package com.marcosimon.autosurvey.user;

import com.marcosimon.autosurvey.models.UserDto;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.marcosimon.autosurvey.config.DatabaseUserDetailsService;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin
public class JWTController {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private DatabaseUserDetailsService databaseUserDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthRequestJWT authRequestJWT) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestJWT.username(), authRequestJWT.password()));
        if (authentication.isAuthenticated()) {
            String jwt = jwtService.generateToken(authRequestJWT.username());
            UserDto userDto = new UserDto(authRequestJWT.username(), jwt);
            return ResponseEntity.ok(userDto);
        } else {
            throw new UsernameNotFoundException("invalid user request!");
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam("token") String token) {
        try{
            String username = null;
            username = jwtService.extractUsername(token);
            UserDetails userDetails = databaseUserDetailsService.loadUserByUsername(username);
            Boolean isTokenValid = jwtService.validateToken(token, userDetails);
            return ResponseEntity.ok(isTokenValid);

        } catch (ExpiredJwtException e) {
            return ResponseEntity.ok(false);
        }
    }


}
