package com.nttdata.bootcamp.banking.controller;

import com.nttdata.bootcamp.banking.model.document.Movement;
import com.nttdata.bootcamp.banking.service.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/movements")
public class MovementController {

    @Autowired
    private MovementService movementService;

    @PostMapping
    public Mono<Movement> create(@RequestBody Movement movement){
        return this.movementService.insert(movement);
    }

    @PutMapping
    public Mono<Movement> update(@RequestBody Movement movement){
        return this.movementService.update(movement);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return this.movementService.delete(id);
    }

    @GetMapping("/{id}")
    public Mono<Movement> find(@PathVariable String id) {
        return this.movementService.find(id);
    }

    @GetMapping("/findByCode/{code}")
    public Mono<Movement> findByCode(@PathVariable String code) {
        return this.movementService.findByCode(code);
    }

    @GetMapping
    public Flux<Movement> findAll() {
        return this.movementService.findAll();
    }

}
