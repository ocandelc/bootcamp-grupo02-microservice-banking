package com.nttdata.bootcamp.banking.service.impl;

import com.nttdata.bootcamp.banking.model.dao.HolderDao;
import com.nttdata.bootcamp.banking.model.document.Holder;
import com.nttdata.bootcamp.banking.service.HolderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class HolderServiceImpl implements HolderService {

    private static final Logger log = LoggerFactory.getLogger(HolderServiceImpl.class);

    @Autowired
    private HolderDao holderDao;

    @Override
    public Mono<Holder> insert(Holder holder) {
        return holderDao.save(holder)
                .doFirst(() -> log.info("Begin Insert Holder"))
                .doOnNext(h -> log.info(h.toString()))
                .doAfterTerminate(() -> log.info("Finish Insert Holder"));
    }

    @Override
    public Mono<Holder> update(Holder holder) {
        return holderDao.findById(holder.getId())
                .doFirst(() -> log.info("Begin Update Holder"))
                .map(h -> holder)
                .flatMap(this.holderDao::save)
                .doOnNext(h -> log.info(h.toString()))
                .doAfterTerminate(() -> log.info("Finish Update Holder"));
    }

    @Override
    public Mono<Void> delete(String id) {
        return holderDao.deleteById(id)
                .doFirst(() -> log.info("Begin Delete Holder"))
                .doOnNext(h -> log.info(h.toString()))
                .doAfterTerminate(() -> log.info("Finish Delete Holder"));
    }

    @Override
    public Mono<Holder> find(String id) {
        return holderDao.findById(id)
                .doFirst(() -> log.info("Begin Find Holder"))
                .doOnNext(h -> log.info(h.toString()))
                .doAfterTerminate(() -> log.info("Finish Find Holder"));
    }

    @Override
    public Flux<Holder> findAll() {
        return holderDao.findAll()
                .doFirst(() -> log.info("Begin FindAll Holder"))
                .doOnNext(h -> log.info(h.toString()))
                .doAfterTerminate(() -> log.info("Finish FindAll Holder"));
    }

}
