package com.nttdata.bootcamp.banking.controller;

import com.nttdata.bootcamp.banking.model.document.Signer;
import com.nttdata.bootcamp.banking.service.SignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/signers")
public class SignerController {

    @Autowired
    private SignerService signerService;

    @PostMapping
    public Mono<Signer> create(@RequestBody Signer signer){
        return this.signerService.insert(signer);
    }

    @PutMapping
    public Mono<Signer> update(@RequestBody Signer signer){
        return this.signerService.update(signer);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return this.signerService.delete(id);
    }

    @GetMapping("/{id}")
    public Mono<Signer> find(@PathVariable String id) {
        return this.signerService.find(id);
    }

    @GetMapping
    public Flux<Signer> findAll() {
        return this.signerService.findAll();
    }

}
