package com.nttdata.bootcamp.banking.service;

import com.nttdata.bootcamp.banking.model.document.Account;
import com.nttdata.bootcamp.banking.model.document.AccountState;
import reactor.core.publisher.Mono;

public interface AccountStateService extends GenericService<AccountState, String> {

    Mono<AccountState> findByCode(String code);

}
