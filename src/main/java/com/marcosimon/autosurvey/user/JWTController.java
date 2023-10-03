package com.marcosimon.autosurvey.user;

import com.marcosimon.autosurvey.config.DatabaseUserDetailsService;
import com.marcosimon.autosurvey.models.user.LoggedUserDto;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("authenticate")
@CrossOrigin(origins = {"https://autosurvey.vercel.app", "http://localhost:3000"})
public class JWTController {

    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final DatabaseUserDetailsService databaseUserDetailsService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<LoggedUserDto> authenticateAndGetToken(@RequestBody AuthRequestJWT authRequestJWT) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestJWT.username(), authRequestJWT.password()));
        if (authentication.isAuthenticated()) {
            UserModel userModel = userService.getUserByName(authRequestJWT.username());
            if (userModel == null) throw new UsernameNotFoundException("invalid user request!");

            return ResponseEntity.ok(new LoggedUserDto(userModel.getUserId(), userModel.getUsername(), userModel.getEmail(), userModel.getRoles(), userModel.getStatus(), jwtService.generateToken(authRequestJWT.username())));
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