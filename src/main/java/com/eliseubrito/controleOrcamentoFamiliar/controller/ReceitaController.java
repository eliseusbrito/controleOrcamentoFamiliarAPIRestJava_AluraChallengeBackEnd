package com.eliseubrito.controleOrcamentoFamiliar.controller;

import com.eliseubrito.controleOrcamentoFamiliar.model.Receita;
import com.eliseubrito.controleOrcamentoFamiliar.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1")
public class ReceitaController {

    @Autowired
    ReceitaService receitaService;

    @GetMapping(path = "/receitas")
    public ResponseEntity<List<Receita>> findAll() {
        return ResponseEntity.ok().body(receitaService.findAll());
    }

    @PostMapping(path = "/receitas")
    public ResponseEntity<?> postReceita(@RequestBody @Valid Receita receita) {
        receita = receitaService.postReceita(receita);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(receita.getId()).toUri();
        return ResponseEntity.created(uri).body(receita);
    }


}
