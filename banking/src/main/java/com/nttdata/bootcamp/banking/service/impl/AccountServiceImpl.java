package com.nttdata.bootcamp.banking.service.impl;

import com.nttdata.bootcamp.banking.model.dao.AccountDao;
import com.nttdata.bootcamp.banking.model.document.Account;
import com.nttdata.bootcamp.banking.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountDao accountDao;

    @Override
    public Mono<Account> insert(Account account) {
        //Que tipo de cliente -> si crea la cuenta o no
        //Aqui tendriamos que invocar el otro microservicio
        return accountDao.save(account)
                .doFirst(() -> log.info("Begin Insert Account"))
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish Insert Account"));
    }

    @Override
    public Mono<Account> update(Account account) {
        return accountDao.findById(account.getId())
                .doFirst(() -> log.info("Begin Update Account"))
                .map(a -> account)
                .flatMap(this.accountDao::save)
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish Update Account"));
    }

    @Override
    public Mono<Void> delete(String id) {
        return accountDao.deleteById(id)
                .doFirst(() -> log.info("Begin Delete Account"))
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish Delete Account"));
    }

    @Override
    public Mono<Account> find(String id) {
        return accountDao.findById(id)
                .doFirst(() -> log.info("Begin Find Account"))
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish Find Account"));
    }

    @Override
    public Mono<Account> findByAccountNumber(String accountNumber) {
        return accountDao.findByAccountNumber(accountNumber)
                .doFirst(() -> log.info("Begin FindByAccountNumber Account"))
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish FindByAccountNumber Account"));
    }

    @Override
    public Flux<Account> findAll() {
        return accountDao.findAll()
                .doFirst(() -> log.info("Begin FindAll Account"))
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish FindAll Account"));
    }

}
