package com.eliseubrito.controleOrcamentoFamiliar.controller;

import com.eliseubrito.controleOrcamentoFamiliar.enuns.Categoria;
import com.eliseubrito.controleOrcamentoFamiliar.exception.DescricaoDuplicadaException;
import com.eliseubrito.controleOrcamentoFamiliar.model.Despesa;
import com.eliseubrito.controleOrcamentoFamiliar.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
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

    @GetMapping(path = "/despesas", params = "descricao")
    public ResponseEntity<List<Despesa>> findByDescricao(@RequestParam String descricao) {
        return ResponseEntity.ok().body(despesaService.findByDescricao(descricao));
    }

    @GetMapping(path = "/despesas/{ano}/{mes}")
    public ResponseEntity<List<Despesa>> findByMes(@PathVariable Integer ano, @PathVariable Integer mes) {
        return ResponseEntity.ok().body(despesaService.findByMes(ano, mes));
    }

    @GetMapping(path = "/despesas-filter")
    public ResponseEntity<List<Despesa>> findDespesasByParameters(@RequestParam Long id, @RequestParam String descricao, @RequestParam Double valor, @RequestParam String data, @RequestParam String categoria) {
        return ResponseEntity.ok().body(despesaService.findDespesasByParameters(id, descricao, valor, data, categoria));
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
