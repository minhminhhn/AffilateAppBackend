package affilateweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import affilateweb.model.requestobject.RegisterRequest;
import affilateweb.model.entities.Account;
import affilateweb.model.entities.Role;
import affilateweb.repository.AccountRepo;
import affilateweb.repository.RoleRepo;

import java.util.List;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    private AccountRepo accountRepository;
    @Autowired
    private RoleRepo roleRepository;
    private  PasswordEncoder passwordEncoder;

    public void register(RegisterRequest registerRequest) {
        Account account = new Account();
        account.setUsername(registerRequest.getUsername());
        account.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        account.setName(registerRequest.getName());
        account.setEmail(registerRequest.getEmail());

        int roleId = 2; // Default role for non-authenticated users

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        account.setRole(role);

        accountRepository.save(account);
    }

    public Account getAccount(Authentication authentication) {
        // Lấy đối tượng principal từ authentication
        Object principal = authentication.getPrincipal();

        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername(); // Lấy username từ UserDetails
        } else {
            username = principal.toString(); // Trong trường hợp khác, có thể là chuỗi
        }

        // Sử dụng username để lấy Account
        return (Account) loadUserByUsername(username);
    }

    public Account getAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username =  (String) authentication.getPrincipal();

        return (Account) loadUserByUsername(username);
    }

    public List<Account> getListAccount() {
        return accountRepository.findAll();
    }

    public void createAccount(RegisterRequest registerRequest) {
        Account account = new Account();
        account.setUsername(registerRequest.getUsername());
        account.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        account.setName(registerRequest.getName());
        account.setEmail(registerRequest.getEmail());
        account.setRole(roleRepository.findById(registerRequest.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found")));
        accountRepository.save(account);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return account;
    }
}