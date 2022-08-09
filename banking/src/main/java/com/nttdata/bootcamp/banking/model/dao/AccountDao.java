package com.nttdata.bootcamp.banking.model.dao;

import com.nttdata.bootcamp.banking.model.document.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountDao extends ReactiveMongoRepository<Account, String> {

    Mono<Account> findByAccountNumber(String accountNumber);

    Flux<Account> findByCodeClient(String code);

}
