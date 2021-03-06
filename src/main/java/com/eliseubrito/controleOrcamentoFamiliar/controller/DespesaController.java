package com.eliseubrito.controleOrcamentoFamiliar.controller;

import com.eliseubrito.controleOrcamentoFamiliar.exception.DescricaoDuplicadaException;
import com.eliseubrito.controleOrcamentoFamiliar.model.Despesa;
import com.eliseubrito.controleOrcamentoFamiliar.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
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

    @GetMapping(path = "/despesas/{id}")
    public ResponseEntity<Optional<Despesa>> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(despesaService.findById(id));
    }

    @PostMapping(path = "/despesas")
    public ResponseEntity<?> postDespesa(@RequestBody @Valid Despesa despesa) throws DescricaoDuplicadaException {
        despesa = despesaService.postDespesa(despesa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(despesa.getId()).toUri();
        return ResponseEntity.created(uri).body(despesa);
    }

    @PutMapping(path = "/despesas/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Despesa despesa) throws DescricaoDuplicadaException {
        return ResponseEntity.ok().body(despesaService.update(id, despesa));
    }

    @DeleteMapping(value = "/despesas/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        despesaService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
