package com.eliseubrito.controleOrcamentoFamiliar.controller;

import com.eliseubrito.controleOrcamentoFamiliar.model.Resumo;
import com.eliseubrito.controleOrcamentoFamiliar.service.ResumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class ResumoController {

    @Autowired
    ResumoService resumoService;

    @GetMapping(path = "/resumo/{ano}/{mes}")
    public ResponseEntity<Resumo> findByMes(@PathVariable Integer ano, @PathVariable Integer mes) {
        return ResponseEntity.ok().body(resumoService.findByMes(ano, mes));
    }

}
