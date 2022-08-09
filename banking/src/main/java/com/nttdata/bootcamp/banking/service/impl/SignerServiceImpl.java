package com.nttdata.bootcamp.banking.service.impl;

import com.nttdata.bootcamp.banking.model.dao.SignerDao;
import com.nttdata.bootcamp.banking.model.document.Signer;
import com.nttdata.bootcamp.banking.service.SignerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SignerServiceImpl implements SignerService {

    private static final Logger log = LoggerFactory.getLogger(SignerServiceImpl.class);

    @Autowired
    private SignerDao signerDao;

    @Override
    public Mono<Signer> insert(Signer signer) {
        return signerDao.save(signer)
                .doFirst(() -> log.info("Begin Insert Signer"))
                .doOnNext(s -> log.info(s.toString()))
                .doAfterTerminate(() -> log.info("Finish Insert Signer"));
    }

    @Override
    public Mono<Signer> update(Signer signer) {
        return signerDao.findById(signer.getId())
                .doFirst(() -> log.info("Begin Update Signer"))
                .map(s -> signer)
                .flatMap(this.signerDao::save)
                .doOnNext(s -> log.info(s.toString()))
                .doAfterTerminate(() -> log.info("Finish Update Signer"));
    }

    @Override
    public Mono<Void> delete(String id) {
        return signerDao.deleteById(id)
                .doFirst(() -> log.info("Begin Delete Signer"))
                .doOnNext(s -> log.info(s.toString()))
                .doAfterTerminate(() -> log.info("Finish Delete Signer"));
    }

    @Override
    public Mono<Signer> find(String id) {
        return signerDao.findById(id)
                .doFirst(() -> log.info("Begin Find Signer"))
                .doOnNext(s -> log.info(s.toString()))
                .doAfterTerminate(() -> log.info("Finish Find Signer"));
    }

    @Override
    public Flux<Signer> findAll() {
        return signerDao.findAll()
                .doFirst(() -> log.info("Begin FindAll Signer"))
                .doOnNext(s -> log.info(s.toString()))
                .doAfterTerminate(() -> log.info("Finish FindAll Signer"));
    }

}
