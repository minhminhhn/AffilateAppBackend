package affilateweb.controller;

import affilateweb.model.requestobject.UpdateInfoAccount;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import affilateweb.dto.AccountDTO;
import affilateweb.model.entities.Account;
import affilateweb.model.requestobject.RegisterRequest;
import affilateweb.repository.AccountRepo;
import affilateweb.service.AccountService;
import affilateweb.util.JwtUtil;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

    private final JwtUtil jwtUtil;
    @Autowired
    AccountService accountService;


//    @GetMapping("/info")
//    public ResponseEntity<?> getAccountInfo() {
//
//        Account account = accountService.getAccount();
//
//        AccountDTO accountInfo = new AccountDTO();
//
//        accountInfo.setUsername(account.getUsername());
//        accountInfo.setRole(account.getRole().getRoleName());
//        accountInfo.setEmail(account.getEmail());
//        accountInfo.setName(account.getName());
//
//        return ResponseEntity.ok(accountInfo);
//    }

    @PutMapping("/update_info")
    public ResponseEntity<?> updateInfoAccount(@RequestBody UpdateInfoAccount updateInfoAccount, HttpServletRequest request) {
        try {
            accountService.updateInfoAccount(updateInfoAccount);
            return ResponseEntity.ok("Account updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/list_account")
    public ResponseEntity<?> getListAccount(HttpServletRequest request) {

        return ResponseEntity.ok(accountService.getListAccount());
    }

    @PostMapping(value = "/create_account", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAccount(@RequestBody RegisterRequest registerRequest) {
        try {
            accountService.createAccount(registerRequest);
            return ResponseEntity.ok("Account created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}