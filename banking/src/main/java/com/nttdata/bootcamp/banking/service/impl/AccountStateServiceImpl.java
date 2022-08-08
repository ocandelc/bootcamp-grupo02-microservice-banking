package com.nttdata.bootcamp.banking.service.impl;

import com.nttdata.bootcamp.banking.model.dao.AccountDao;
import com.nttdata.bootcamp.banking.model.dao.AccountStateDao;
import com.nttdata.bootcamp.banking.model.document.Account;
import com.nttdata.bootcamp.banking.model.document.AccountState;
import com.nttdata.bootcamp.banking.service.AccountStateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountStateServiceImpl implements AccountStateService {

    private static final Logger log = LoggerFactory.getLogger(AccountStateServiceImpl.class);

    @Autowired
    private AccountStateDao accountStateDao;

    @Override
    public Mono<AccountState> insert(AccountState accountState) {
        return accountStateDao.save(accountState)
                .doFirst(() -> log.info("Begin Insert AccountState"))
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish Insert AccountState"));
    }

    @Override
    public Mono<AccountState> update(AccountState accountState) {
        return accountStateDao.findById(accountState.getId())
                .doFirst(() -> log.info("Begin Update AccountState"))
                .map(a -> accountState)
                .flatMap(this.accountStateDao::save)
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish Update AccountState"));
    }

    @Override
    public Mono<Void> delete(String id) {
        return accountStateDao.deleteById(id)
                .doFirst(() -> log.info("Begin Delete AccountState"))
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish Delete AccountState"));
    }

    @Override
    public Mono<AccountState> find(String id) {
        return accountStateDao.findById(id)
                .doFirst(() -> log.info("Begin Find AccountState"))
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish Find AccountState"));
    }

    @Override
    public Mono<AccountState> findByCode(String code) {
        return accountStateDao.findByCode(code)
                .doFirst(() -> log.info("Begin FindByCode AccountState"))
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish FindByCode AccountState"));
    }

    @Override
    public Flux<AccountState> findAll() {
        return accountStateDao.findAll()
                .doFirst(() -> log.info("Begin FindAll AccountState"))
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish FindAll AccountState"));
    }

}
