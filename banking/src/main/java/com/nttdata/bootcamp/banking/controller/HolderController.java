package com.nttdata.bootcamp.banking.controller;

import com.nttdata.bootcamp.banking.model.document.Holder;
import com.nttdata.bootcamp.banking.service.HolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/holders")
public class HolderController {

    @Autowired
    private HolderService holderService;

    @PostMapping
    public Mono<Holder> create(@RequestBody Holder holder){
        return this.holderService.insert(holder);
    }

    @PutMapping
    public Mono<Holder> update(@RequestBody Holder holder){
        return this.holderService.update(holder);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return this.holderService.delete(id);
    }

    @GetMapping("/{id}")
    public Mono<Holder> find(@PathVariable String id) {
        return this.holderService.find(id);
    }

    @GetMapping
    public Flux<Holder> findAll() {
        return this.holderService.findAll();
    }

}
