/**
 * Resumen.
 * Objeto                   : MovementTypeController.java
 * Descripción              : Clase de controladora para invocar a métodos CRUD con rest api.
 * Fecha de Creación        : 04/08/2022.
 * Proyecto de Creación     : Bootcamp-01.
 * Autor                    : Marvin Castro.
 * ---------------------------------------------------------------------------------------------------------------------------
 * Modificaciones
 * Motivo                   Fecha             Nombre                  Descripción
 * ---------------------------------------------------------------------------------------------------------------------------
 * Bootcamp-01              05/08/2022        Oscar Candela           Realizar la creación de un método nuevo.
 */

package com.nttdata.bootcamp.banking.controller;

import com.nttdata.bootcamp.banking.model.document.MovementType;
import com.nttdata.bootcamp.banking.service.MovementTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase de controladora para invocar a métodos CRUD con rest api.
 */
@RestController
@RequestMapping("/api/movementtypes")
public class MovementTypeController {

    /** Declaración de la clase service */
    @Autowired
    private MovementTypeService movementTypeService;

    /**
     * Método que realiza la acción insertar datos del document
     * @return Mono retorna el MovementType, tipo Mono
     */
    @PostMapping
    public Mono<MovementType> create(@RequestBody MovementType movementType){
        return this.movementTypeService.insert(movementType);
    }

    /**
     * Método que realiza la acción actualizar datos del document
     * @return Mono retorna el MovementType, tipo Mono
     */
    @PutMapping
    public Mono<MovementType> update(@RequestBody MovementType movementType){
        return this.movementTypeService.update(movementType);
    }

    /**
     * Método que realiza la acción borrar datos del document
     * @return Mono retorna el Void, tipo Mono
     */
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return this.movementTypeService.delete(id);
    }

    /**
     * Método que realiza la acción buscar datos por id del document
     * @return Mono retorna el MovementType, tipo String
     */
    @GetMapping("/{id}")
    public Mono<MovementType> find(@PathVariable String id) {
        return this.movementTypeService.find(id);
    }

    /**
     * Método que realiza la acción buscar datos por código del document
     * @return Mono retorna el MovementType, tipo String
     */
    @GetMapping("/findByCode/{code}")
    public Mono<MovementType> findByCode(@PathVariable String code) {
        return this.movementTypeService.findByCode(code);
    }

    /**
     * Método que realiza la acción buscar todos los datos del document
     * @return Flux retorna el MovementType, tipo Flux
     */
    @GetMapping
    public Flux<MovementType> findAll() {
        return this.movementTypeService.findAll();
    }

}
