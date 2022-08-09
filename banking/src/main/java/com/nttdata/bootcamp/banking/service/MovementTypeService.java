package com.nttdata.bootcamp.banking.service;

import com.nttdata.bootcamp.banking.model.document.Movement;
import com.nttdata.bootcamp.banking.model.document.MovementType;
import reactor.core.publisher.Mono;

public interface MovementTypeService extends GenericService<MovementType, String> {

    Mono<MovementType> findByCode(String code);

}
