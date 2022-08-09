package com.nttdata.bootcamp.banking.model.dao;

import com.nttdata.bootcamp.banking.model.document.Holder;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface HolderDao extends ReactiveMongoRepository<Holder, String> {

}
