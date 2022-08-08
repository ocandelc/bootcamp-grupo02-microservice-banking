package com.nttdata.bootcamp.banking.model.dao;

import com.nttdata.bootcamp.banking.model.document.MovementType;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface MovementTypeDao extends ReactiveMongoRepository<MovementType, String> {

    Mono<MovementType> findByCode(String code);

}
