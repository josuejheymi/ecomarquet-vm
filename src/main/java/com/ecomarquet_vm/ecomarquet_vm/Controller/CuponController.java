package com.ecomarquet_vm.ecomarquet_vm.Controller;

import com.ecomarquet_vm.ecomarquet_vm.Assemblers.CuponModelAssembler;
import com.ecomarquet_vm.ecomarquet_vm.Model.Cupon;
import com.ecomarquet_vm.ecomarquet_vm.Service.CuponService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/cupones")
@Tag(name = "Cupones", description = "Operaciones relacionadas con cupones de descuento")
public class CuponController {

    @Autowired
    private CuponService cuponService;

    @Autowired
    private CuponModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Lista todos los cupones",
               description = "Obtiene todos los cupones disponibles en el sistema")
    public ResponseEntity<CollectionModel<EntityModel<Cupon>>> getAll() {
        List<EntityModel<Cupon>> cupones = cuponService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        if (cupones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(
                CollectionModel.of(cupones,
                        linkTo(methodOn(CuponController.class).getAll()).withSelfRel()
                )
        );
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Buscar cupón por ID",
               description = "Busca un cupón específico por su ID")
    public ResponseEntity<EntityModel<Cupon>> getById(@PathVariable String id) {
        Optional<Cupon> cupon = cuponService.findById(id);
        return cupon.map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/codigo/{codigo}")
    @Operation(summary = "Buscar cupón por código",
               description = "Busca un cupón específico por su código único")
    public ResponseEntity<EntityModel<Cupon>> getByCodigo(@PathVariable String codigo) {
        Cupon cupon = cuponService.findByCodigo(codigo);
        if (cupon == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(assembler.toModel(cupon));
    }

    @PostMapping
    @Operation(summary = "Crear nuevo cupón",
               description = "Crea un nuevo cupón con los datos proporcionados")
    public ResponseEntity<EntityModel<Cupon>> create(@RequestBody Cupon cupon) {
        Cupon nuevoCupon = cuponService.save(cupon);
        EntityModel<Cupon> entityModel = assembler.toModel(nuevoCupon);

        return ResponseEntity
                .created(entityModel.getRequiredLink("self").toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cupón",
               description = "Actualiza un cupón existente identificado por su ID")
    public ResponseEntity<EntityModel<Cupon>> update(@PathVariable String id, @RequestBody Cupon cupon) {
        cupon.setId(id);
        Cupon actualizado = cuponService.save(cupon);
        EntityModel<Cupon> entityModel = assembler.toModel(actualizado);
        return ResponseEntity.ok(entityModel);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cupón",
               description = "Elimina un cupón específico por su ID")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        cuponService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/aplicar-descuento/{transaccionId}")
    @Operation(summary = "Aplicar descuento con cupón",
               description = "Aplica el descuento de un cupón a una transacción y devuelve el monto con descuento")
    public ResponseEntity<BigDecimal> aplicarDescuento(@PathVariable String transaccionId) {
        BigDecimal totalConDescuento = cuponService.aplicarDescuento(transaccionId);
        return ResponseEntity.ok(totalConDescuento);
    }

    @GetMapping("/validar/{codigoCupon}")
    @Operation(summary = "Validar cupón",
               description = "Verifica si un cupón es válido según su código")
    public ResponseEntity<Boolean> esCuponValido(@PathVariable String codigoCupon) {
        boolean esValido = cuponService.esCuponValido(codigoCupon);
        return ResponseEntity.ok(esValido);
    }
}
