package com.nttdata.bootcamp.banking.model.dao;

import com.nttdata.bootcamp.banking.model.document.Signer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SignerDao extends ReactiveMongoRepository<Signer, String> {

}
