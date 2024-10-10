package affilateweb.controller;

import affilateweb.dto.AccountDTO;
import affilateweb.dto.AuthenticationResponse;
import affilateweb.model.entities.Account;
import affilateweb.model.requestobject.AuthenticationRequest;
import affilateweb.model.requestobject.RegisterRequest;
import affilateweb.service.AccountService;
import affilateweb.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
            String token = jwtUtil.generateToken(authentication);

            Account account = accountService.getAccount(authentication);

            AccountDTO accountInfo = new AccountDTO();

            accountInfo.setUsername(account.getUsername());
            accountInfo.setRole(account.getRole().getRoleName());
            accountInfo.setEmail(account.getEmail());
            accountInfo.setName(account.getName());

            return ResponseEntity.ok(new AuthenticationResponse(token, accountInfo));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            accountService.register(registerRequest);
            return ResponseEntity.ok("Account created successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        accountService.sendResetPasswordEmail(email);
        return ResponseEntity.ok("Reset password email sent.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        accountService.resetPassword(token, newPassword);
        return ResponseEntity.ok("Password has been reset.");
    }

}
