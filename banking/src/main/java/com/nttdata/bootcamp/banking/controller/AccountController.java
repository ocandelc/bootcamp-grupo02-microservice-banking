package com.nttdata.bootcamp.banking.controller;

import com.nttdata.bootcamp.banking.model.document.Account;
import com.nttdata.bootcamp.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public Mono<Account> create(@RequestBody Account account){
        return this.accountService.insert(account);
    }

    @PutMapping
    public Mono<Account> update(@RequestBody Account account){
        return this.accountService.update(account);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return this.accountService.delete(id);
    }

    @GetMapping("/{id}")
    public Mono<Account> find(@PathVariable String id) {
        return this.accountService.find(id);
    }

    @GetMapping("/findByAccountNumber/{accountNumber}")
    public Mono<Account> findByAccountNumber(@PathVariable String accountNumber) {
        return this.accountService.findByAccountNumber(accountNumber);
    }

    @GetMapping
    public Flux<Account> findAll() {
        return this.accountService.findAll();
    }

}
