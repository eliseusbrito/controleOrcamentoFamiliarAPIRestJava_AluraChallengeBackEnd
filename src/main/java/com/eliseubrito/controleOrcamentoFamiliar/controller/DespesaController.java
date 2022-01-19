package com.eliseubrito.controleOrcamentoFamiliar.controller;

import com.eliseubrito.controleOrcamentoFamiliar.model.Despesa;
import com.eliseubrito.controleOrcamentoFamiliar.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("v1")
public class DespesaController {

    @Autowired
    DespesaService despesaService;

    @GetMapping(path = "/despesas")
    public ResponseEntity<Iterable<Despesa>> findAll() {
        return ResponseEntity.ok().body(despesaService.findAll());
    }

    @PostMapping(path = "/despesas")
    public ResponseEntity<?> postDespesa(@RequestBody @Valid Despesa despesa) {
        despesa = despesaService.postDespesa(despesa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(despesa.getId()).toUri();
        return ResponseEntity.created(uri).body(despesa);
    }


}
