/**
 * Resumen.
 * Objeto                   : AccountServiceImpl.java
 * Descripción              : Clase para los métodos de la implementación de servicio de la cuenta.
 * Fecha de Creación        : 04/08/2022.
 * Proyecto de Creación     : Bootcamp-01.
 * Autor                    : Marvin Castro.
 * ---------------------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                   Fecha             Nombre                  Descripción
 * ---------------------------------------------------------------------------------------------------------------------------
 * Bootcamp-01              05/08/2022        Oscar Candela           Realizar la creación de un método nuevo.
 */

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

/**
 * Clase para los métodos de la implementación de servicio de la cuenta.
 */
@Service
public class AccountServiceImpl implements AccountService {

    /** Declaración de la variable de log */
    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    /** Declaración de la clase dao */
    @Autowired
    private AccountDao accountDao;

    /**
     * Método que realiza la acción insertar datos del document
     * @return Mono retorna el Account, tipo Mono
     */
    @Override
    public Mono<Account> insert(Account account) {
        //Que tipo de cliente -> si crea la cuenta o no
        //Aqui tendriamos que invocar el otro microservicio
        return accountDao.save(account)
                .doFirst(() -> log.info("Begin Insert Account"))
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish Insert Account"));
    }

    /**
     * Método que realiza la acción actualizar datos del document
     * @return Mono retorna el Account, tipo Mono
     */
    @Override
    public Mono<Account> update(Account account) {
        return accountDao.findById(account.getId())
                .doFirst(() -> log.info("Begin Update Account"))
                .map(a -> account)
                .flatMap(this.accountDao::save)
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish Update Account"));
    }

    /**
     * Método que realiza la acción borrar datos del document
     * @return Mono retorna el Void, tipo Mono
     */
    @Override
    public Mono<Void> delete(String id) {
        return accountDao.deleteById(id)
                .doFirst(() -> log.info("Begin Delete Account"))
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish Delete Account"));
    }

    /**
     * Método que realiza la acción buscar datos por id del document
     * @return Mono retorna el Account, tipo String
     */
    @Override
    public Mono<Account> find(String id) {
        return accountDao.findById(id)
                .doFirst(() -> log.info("Begin Find Account"))
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish Find Account"));
    }

    /**
     * Método que realiza la acción buscar datos por código del document
     * @return Mono retorna el Account, tipo String
     */
    @Override
    public Mono<Account> findByAccountNumber(String accountNumber) {
        return accountDao.findByAccountNumber(accountNumber)
                .doFirst(() -> log.info("Begin FindByAccountNumber Account"))
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish FindByAccountNumber Account"));
    }

    /**
     * Método que realiza la acción buscar todos los datos del document
     * @return Mono retorna el Account, tipo String
     */
    @Override
    public Flux<Account> findAll() {
        return accountDao.findAll()
                .doFirst(() -> log.info("Begin FindAll Account"))
                .doOnNext(a -> log.info(a.toString()))
                .doAfterTerminate(() -> log.info("Finish FindAll Account"));
    }

}
