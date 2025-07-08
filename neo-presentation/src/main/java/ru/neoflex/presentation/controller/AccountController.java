package ru.neoflex.presentation.controller;

import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.presentation.dto.AccountDto;
import ru.neoflex.presentation.dto.CreateAccountRequest;
import ru.neoflex.presentation.service.AccountService;

import java.util.List;

@Validated
@RestController
@RequestMapping("/v1/user")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public AccountDto createAccount(@RequestBody @Valid CreateAccountRequest createAccountRequest) {
        System.out.println("Received request: " + createAccountRequest);
        return accountService.createAccount(createAccountRequest);
    }

    @GetMapping("/{accountId}")
    public AccountDto getAccount(@PathVariable("accountId") long accountId) {
        return accountService.getUser(accountId);
    }

    @DeleteMapping("/{accountId}")
    public void deleteAccount(@PathVariable("accountId") long accountId) {
        accountService.deleteUser(accountId);
    }

    @GetMapping
    public List<AccountDto> getAllAccounts() {
        return accountService.getAllAccounts();
    }
}
