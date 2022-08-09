package com.nttdata.bootcamp.banking.service;

import com.nttdata.bootcamp.banking.model.document.Account;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountService extends GenericService<Account, String> {

    Mono<Account> findByAccountNumber(String accountNumber);

    Flux<Account> findByCodeClient(String code);

}
