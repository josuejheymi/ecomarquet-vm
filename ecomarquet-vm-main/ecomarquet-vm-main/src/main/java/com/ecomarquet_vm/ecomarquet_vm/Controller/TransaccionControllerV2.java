package com.ecomarquet_vm.ecomarquet_vm.Controller;

import com.ecomarquet_vm.ecomarquet_vm.Model.Transaccion;
import com.ecomarquet_vm.ecomarquet_vm.Service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.hateoas.EntityModel;

import com.ecomarquet_vm.ecomarquet_vm.Assemblers.TransaccionModelAssembler;
import org.springframework.hateoas.MediaTypes;

@RestController
@RequestMapping("/api/v2/transacciones")
public class TransaccionControllerV2 {

    @Autowired
    private TransaccionService transaccionService;

    @Autowired
    private TransaccionModelAssembler TransaccionModelAssembler;

    @GetMapping
    public ResponseEntity<List<Transaccion>> getAll() {
        return ResponseEntity.ok(transaccionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaccion> getById(@PathVariable String id) {
        Optional<Transaccion> transaccion = TransaccionService.findById(id);
        return transaccion.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Transaccion> create(@RequestBody Transaccion transaccion){
        Transaccion nueva = TransaccionService.save(transaccion);
        return TransaccionModelAssembler.toModel(nueva);
}}