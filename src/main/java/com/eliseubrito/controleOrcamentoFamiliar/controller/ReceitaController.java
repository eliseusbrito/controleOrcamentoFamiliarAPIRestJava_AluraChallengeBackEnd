package com.eliseubrito.controleOrcamentoFamiliar.controller;

import com.eliseubrito.controleOrcamentoFamiliar.exception.DescricaoDuplicadaException;
import com.eliseubrito.controleOrcamentoFamiliar.model.Receita;
import com.eliseubrito.controleOrcamentoFamiliar.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1")
public class ReceitaController {

    @Autowired
    ReceitaService receitaService;

    @GetMapping(path = "/receitas")
    public ResponseEntity<Iterable<Receita>> findAll() {
        return ResponseEntity.ok().body(receitaService.findAll());
    }

    @GetMapping(path = "/receitas/{id}")
    public ResponseEntity<Optional<Receita>> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(receitaService.findById(id));
    }

    @GetMapping(path = "/receitas", params = "descricao")
    public ResponseEntity<List<Receita>> findByDescricao(@RequestParam String descricao) {
        return ResponseEntity.ok().body(receitaService.findByDescricao(descricao));
    }

    @PostMapping(path = "/receitas")
    public ResponseEntity<?> postReceita(@RequestBody @Valid Receita receita) throws Exception {
        receita = receitaService.postReceita(receita);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(receita.getId()).toUri();
        return ResponseEntity.created(uri).body(receita);
    }

    @PutMapping(path = "/receitas/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Receita receita) throws DescricaoDuplicadaException {
        return ResponseEntity.ok().body(receitaService.update(id, receita));
    }

    @DeleteMapping(value = "/receitas/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        receitaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
