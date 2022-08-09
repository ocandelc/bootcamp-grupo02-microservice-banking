package com.nttdata.bootcamp.banking.controller;

import com.nttdata.bootcamp.banking.model.document.Account;
import com.nttdata.bootcamp.banking.service.AccountService;
import com.nttdata.bootcamp.banking.util.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    /*@PostMapping
    public Mono<Account> create(@RequestBody Account account){
        return this.accountService.insert(account);
    }*/

    @PostMapping
    public Mono<ResponseEntity<ApiResponse>> createWithResponse(@RequestBody Account account){
        Mono<Account> accountMono = this.accountService.insert(account);
        return accountMono.map(data -> ResponseEntity.ok(ApiResponse.success("Insert Account",
                "Successful result", data)));
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

    @GetMapping("/findByCodeClient/{code}")
    public Flux<Account> findByCodeClient(@PathVariable String code) {
        return this.accountService.findByCodeClient(code);
    }

    @GetMapping
    public Flux<Account> findAll() {
        return this.accountService.findAll();
    }

}
