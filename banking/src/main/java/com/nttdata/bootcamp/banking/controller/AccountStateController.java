package com.nttdata.bootcamp.banking.controller;

import com.nttdata.bootcamp.banking.model.document.AccountState;
import com.nttdata.bootcamp.banking.service.AccountStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/accountstates")
public class AccountStateController {

    @Autowired
    private AccountStateService accountStateService;

    @PostMapping
    public Mono<AccountState> create(@RequestBody AccountState accountState){
        return this.accountStateService.insert(accountState);
    }

    @PutMapping
    public Mono<AccountState> update(@RequestBody AccountState accountState){
        return this.accountStateService.update(accountState);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return this.accountStateService.delete(id);
    }

    @GetMapping("/{id}")
    public Mono<AccountState> find(@PathVariable String id) {
        return this.accountStateService.find(id);
    }

    @GetMapping("/findByCode/{code}")
    public Mono<AccountState> findByCode(@PathVariable String code) {
        return this.accountStateService.findByCode(code);
    }

    @GetMapping
    public Flux<AccountState> findAll() {
        return this.accountStateService.findAll();
    }

}
