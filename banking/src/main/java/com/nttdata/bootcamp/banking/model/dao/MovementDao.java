package com.nttdata.bootcamp.banking.model.dao;

import com.nttdata.bootcamp.banking.model.document.Movement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface MovementDao extends ReactiveMongoRepository<Movement, String> {

    Mono<Movement> findByCode(String code);

}
