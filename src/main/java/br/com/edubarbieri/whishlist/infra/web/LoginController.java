package br.com.edubarbieri.whishlist.infra.web;

import br.com.edubarbieri.whishlist.application.dto.AuthenticationRequest;
import br.com.edubarbieri.whishlist.application.dto.AuthenticationResponse;
import br.com.edubarbieri.whishlist.infra.security.JWTService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    private JWTService jwtService;

    private AuthenticationManager authenticationManager;

    public LoginController(JWTService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<Object> login(@RequestBody AuthenticationRequest request) {
        try {
            var authReq = new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword());
            Authentication authenticate = authenticationManager.authenticate(authReq);
            Object principal = authenticate.getPrincipal();
            final var token = jwtService.generateToken((UserDetails) principal);
            return ResponseEntity.ok(new AuthenticationResponse(token));
        } catch (DisabledException | LockedException | BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or Password");
        }
    }
}
