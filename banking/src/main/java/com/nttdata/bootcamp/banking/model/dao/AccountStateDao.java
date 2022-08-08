package com.nttdata.bootcamp.banking.model.dao;

import com.nttdata.bootcamp.banking.model.document.Account;
import com.nttdata.bootcamp.banking.model.document.AccountState;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface AccountStateDao extends ReactiveMongoRepository<AccountState, String> {

    Mono<AccountState> findByCode(String code);

}
