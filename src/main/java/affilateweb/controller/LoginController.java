package affilateweb.controller;

import affilateweb.dto.AccountDTO;
import affilateweb.model.entities.Account;
import affilateweb.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import affilateweb.dto.AuthenticationResponse;
import affilateweb.model.requestobject.AuthenticationRequest;
import affilateweb.util.JwtUtil;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/api/auth/login", produces = MediaType.APPLICATION_JSON_VALUE)
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
}