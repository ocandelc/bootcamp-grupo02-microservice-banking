package com.nttdata.bootcamp.banking.service.impl;

import com.nttdata.bootcamp.banking.model.dao.MovementDao;
import com.nttdata.bootcamp.banking.model.document.Account;
import com.nttdata.bootcamp.banking.model.document.Movement;
import com.nttdata.bootcamp.banking.model.document.MovementType;
import com.nttdata.bootcamp.banking.model.dto.Product;
import com.nttdata.bootcamp.banking.service.AccountService;
import com.nttdata.bootcamp.banking.service.MovementService;
import com.nttdata.bootcamp.banking.service.MovementTypeService;
import com.nttdata.bootcamp.banking.service.ProductService;
import com.nttdata.bootcamp.banking.util.MathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovementServiceImpl implements MovementService {

    private static final Logger log = LoggerFactory.getLogger(MovementServiceImpl.class);

    @Autowired
    private MovementDao movementDao;
    @Autowired
    private ProductService productService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private MovementTypeService movementTypeService;

    @Override
    public Mono<Movement> insert(Movement movement) {
        Mono<Account> accountMono = accountService.findByAccountNumber(movement.getAccountNumber());
        Mono<MovementType> movementTypeMono = movementTypeService.findByCode(movement.getCodeMovementType());
        Mono.zip(accountMono, movementTypeMono)
                .flatMap(data -> {
                    Account accountResult = data.getT1();
                    MovementType movementTypeResult = data.getT2();
                    movement.setPreviousAmount(accountResult.getAvailableAmount());
                    if(movement.getMovementAmount() > 0) {
                        if (movementTypeResult.getOperationType().equals("I")) {
                            movement.setFinalAmount(MathUtil.sum(movement.getPreviousAmount(),
                                    movement.getMovementAmount()));
                        } else if (movementTypeResult.getOperationType().equals("S")) {
                            if (accountResult.getAvailableAmount() >= movement.getMovementAmount()) {
                                movement.setFinalAmount(MathUtil.rest(movement.getPreviousAmount(),
                                        movement.getMovementAmount()));
                            } else {
                                //Saldo insuficiente
                            }
                        }
                    } else {
                        //Monto debe ser mayor a cero
                    }
                    return movementDao.save(movement);
                });
        return movementDao.save(movement)
                .doFirst(() -> log.info("Begin Insert Movement"))
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish Insert Movement"));
    }

    @Override
    public Mono<Movement> update(Movement movement) {
        return movementDao.findById(movement.getId())
                .doFirst(() -> log.info("Begin Update Movement"))
                .map(m -> movement)
                .flatMap(this.movementDao::save)
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish Update Movement"));
    }

    @Override
    public Mono<Void> delete(String id) {
        return movementDao.deleteById(id)
                .doFirst(() -> log.info("Begin Delete Movement"))
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish Delete Movement"));
    }

    @Override
    public Mono<Movement> find(String id) {
        return movementDao.findById(id)
                .doFirst(() -> log.info("Begin Find Movement"))
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish Find Movement"));
    }

    @Override
    public Mono<Movement> findByCode(String code) {
        return movementDao.findByCode(code)
                .doFirst(() -> log.info("Begin FindByCode Movement"))
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish FindByCode Movement"));
    }

    @Override
    public Flux<Movement> findAll() {
        return movementDao.findAll()
                .doFirst(() -> log.info("Begin FindAll Movement"))
                .doOnNext(m -> log.info(m.toString()))
                .doAfterTerminate(() -> log.info("Finish FindAll Movement"));
    }

}
