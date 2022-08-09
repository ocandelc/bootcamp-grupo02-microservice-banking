package com.nttdata.bootcamp.banking.service;

import com.nttdata.bootcamp.banking.model.dto.Client;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

public interface ClientService {

    Mono<Client> findByCode(String code);

}
