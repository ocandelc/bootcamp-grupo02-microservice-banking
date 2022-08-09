package com.nttdata.bootcamp.banking.service.impl;

import com.nttdata.bootcamp.banking.model.dao.MovementTypeDao;
import com.nttdata.bootcamp.banking.model.document.MovementType;
import com.nttdata.bootcamp.banking.service.MovementTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovementTypeServiceImpl implements MovementTypeService {

    private static final Logger log = LoggerFactory.getLogger(MovementTypeServiceImpl.class);

    @Autowired
    private MovementTypeDao movementTypeDao;

    @Override
    public Mono<MovementType> insert(MovementType movementType) {
        return movementTypeDao.save(movementType)
                .doFirst(() -> log.info("Begin Insert MovementType"))
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish Insert MovementType"));
    }

    @Override
    public Mono<MovementType> update(MovementType movementType) {
        return movementTypeDao.findById(movementType.getId())
                .doFirst(() -> log.info("Begin Update MovementType"))
                .map(m -> movementType)
                .flatMap(this.movementTypeDao::save)
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish Update MovementType"));
    }

    @Override
    public Mono<Void> delete(String id) {
        return movementTypeDao.deleteById(id)
                .doFirst(() -> log.info("Begin Delete MovementType"))
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish Delete MovementType"));
    }

    @Override
    public Mono<MovementType> find(String id) {
        return movementTypeDao.findById(id)
                .doFirst(() -> log.info("Begin Find MovementType"))
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish Find MovementType"));
    }

    @Override
    public Mono<MovementType> findByCode(String code) {
        return movementTypeDao.findByCode(code)
                .doFirst(() -> log.info("Begin FindByCode MovementType"))
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish FindByCode MovementType"));
    }

    @Override
    public Flux<MovementType> findAll() {
        return movementTypeDao.findAll()
                .doFirst(() -> log.info("Begin FindAll MovementType"))
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish FindAll MovementType"));
    }

}
