package com.nttdata.bootcamp.banking.service;

import com.nttdata.bootcamp.banking.model.document.Account;
import com.nttdata.bootcamp.banking.model.document.Movement;
import reactor.core.publisher.Mono;

public interface MovementService extends GenericService<Movement, String> {

    Mono<Movement> findByCode(String code);

}
