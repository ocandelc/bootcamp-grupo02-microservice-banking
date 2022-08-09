package com.nttdata.bootcamp.banking.controller;

import com.nttdata.bootcamp.banking.model.document.MovementType;
import com.nttdata.bootcamp.banking.service.MovementTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/movementtypes")
public class MovementTypeController {

    @Autowired
    private MovementTypeService movementTypeService;

    @PostMapping
    public Mono<MovementType> create(@RequestBody MovementType movementType){
        return this.movementTypeService.insert(movementType);
    }

    @PutMapping
    public Mono<MovementType> update(@RequestBody MovementType movementType){
        return this.movementTypeService.update(movementType);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return this.movementTypeService.delete(id);
    }

    @GetMapping("/{id}")
    public Mono<MovementType> find(@PathVariable String id) {
        return this.movementTypeService.find(id);
    }

    @GetMapping("/findByCode/{code}")
    public Mono<MovementType> findByCode(@PathVariable String code) {
        return this.movementTypeService.findByCode(code);
    }

    @GetMapping
    public Flux<MovementType> findAll() {
        return this.movementTypeService.findAll();
    }

}
