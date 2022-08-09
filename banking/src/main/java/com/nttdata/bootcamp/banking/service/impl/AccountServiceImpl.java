package com.nttdata.bootcamp.banking.service.impl;

import com.nttdata.bootcamp.banking.model.dao.AccountDao;
import com.nttdata.bootcamp.banking.model.document.Account;
import com.nttdata.bootcamp.banking.model.dto.Client;
import com.nttdata.bootcamp.banking.model.dto.Product;
import com.nttdata.bootcamp.banking.service.AccountService;
import com.nttdata.bootcamp.banking.service.ClientService;
import com.nttdata.bootcamp.banking.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountDao accountDao;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ProductService productService;

    @Override
    public Mono<Account> insert(Account account) {
        account.setCodeAccountState("RA");
        return clientService.findByCode(account.getCodeClient()).flatMap(
                clientResult -> {
            if(clientResult.getCodeClientType().equals("PER")) {
                return productService.findByCode(account.getCodeProduct()).flatMap(productResult -> {
                    if(productResult.getCodeProductType().equals("CBA")) {
                        account.setCreditLine(0.00);
                        account.setAvailableAmount(0.00);
                        return findByCodeClient(clientResult.getCode())
                                .filter(a -> a.getCodeProduct().equals(productResult.getCode()))
                                .hasElements()
                                .flatMap(flag -> {
                                    if(!flag) {
                                        return accountDao.save(account);
                                    }else {
                                        return Mono.error(new RuntimeException("Ya existe una cuenta bancaria de ese tipo para ese cliente"));
                                    }
                                });
                    } else if(productResult.getCodeProductType().equals("CRE")) {
                        if(account.getCreditLine() > 0) {
                            account.setAvailableAmount(account.getCreditLine());
                            if(productResult.getCode().equals("CRE-PER")) {
                                return findByCodeClient(clientResult.getCode())
                                        .filter(a -> a.getCodeProduct().equals(productResult.getCode()))
                                        .hasElements()
                                        .flatMap(flag -> {
                                            if(!flag) {
                                                return accountDao.save(account);
                                            }else {
                                                return Mono.error(new RuntimeException("Ya existe un credito de ese tipo para ese cliente"));
                                            }
                                        });
                            } else if(productResult.getCode().equals("CRE-TRJ")) {
                                return accountDao.save(account);
                            } else {
                                return Mono.error(new RuntimeException("Cliente Personal solo puede tener un credito personal o Tarjeta de Credito"));
                            }
                        } else {
                            return Mono.error(new RuntimeException("Dato línea de credito es requerido"));
                        }
                    } else {
                        return Mono.error(new RuntimeException("No existe codigo tipo de producto"));
                    }
                });
            } else if(clientResult.getCodeClientType().equals("EMP")) {
                return productService.findByCode(account.getCodeProduct()).flatMap(productResult -> {
                    if(productResult.getCodeProductType().equals("CBA")) {
                        account.setCreditLine(0.00);
                        account.setAvailableAmount(0.00);
                        if(productResult.getCode().equals("CTA-CRT")) {
                            return accountDao.save(account);
                        } else {
                            return Mono.error(new RuntimeException("Cliente Empresarial Solo puede tener cuentas corrientes"));
                        }
                    } else if(productResult.getCodeProductType().equals("CRE")) {
                        if(account.getCreditLine() > 0){
                            account.setAvailableAmount(account.getCreditLine());
                            if(productResult.getCode().equals("CRE-EMP") ||
                                    productResult.getCode().equals("CRE-TRJ")) {
                                return accountDao.save(account);
                            } else {
                                return Mono.error(new RuntimeException("Cliente Empresarial solo puede tener creditos empresariales y tarjeta de credito"));
                            }
                        } else {
                            return Mono.error(new RuntimeException("Dato línea de credito es requerido"));
                        }
                    } else {
                        return Mono.error(new RuntimeException("No existe codigo tipo de producto"));
                    }
                });
            } else {
                return Mono.error(new RuntimeException("El codigo de tipo cliente no existe"));
            }
        });
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
    public Flux<Account> findByCodeClient(String code) {
        return accountDao.findByCodeClient(code)
                .doFirst(() -> log.info("Begin FindByCodeClient Account"))
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish FindByCodeClient Account"));
    }

    @Override
    public Flux<Account> findAll() {
        return accountDao.findAll()
                .doFirst(() -> log.info("Begin FindAll Account"))
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish FindAll Account"));
    }

}
