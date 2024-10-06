package affilateweb.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    AccountRepo accountRepo;


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

    @GetMapping("/list_account")
    public ResponseEntity<?> getListAccount(HttpServletRequest request) {

        return ResponseEntity.ok(accountRepo.findAll());
    }
    @GetMapping("/create_account")
    public ResponseEntity<?> createAccount(@RequestBody RegisterRequest registerRequest, HttpServletRequest request) {
        accountService.createAccount(registerRequest);
        return ResponseEntity.ok("Account created successfully");
    }

}