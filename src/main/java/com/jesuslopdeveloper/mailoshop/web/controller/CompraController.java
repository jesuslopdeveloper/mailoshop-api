package com.jesuslopdeveloper.mailoshop.web.controller;

import com.jesuslopdeveloper.mailoshop.dominio.modelos.dto.CompraDto;
import com.jesuslopdeveloper.mailoshop.dominio.servicio.CompraServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class CompraController {
    private final CompraServicio compraServicio;

    @Autowired
    public CompraController(CompraServicio compraServicio) {
        this.compraServicio = compraServicio;
    }

    @GetMapping("/lista")
    public ResponseEntity<List<CompraDto>> ObtenerCompras() {
        return new ResponseEntity<>(compraServicio.ObtenerCompras(), HttpStatus.OK);
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<CompraDto>> ObtenerComprasPorIdCliente(@PathVariable("idCliente") String idCliente) {
        return compraServicio.ObtenerComprasPorIdCliente(idCliente)
                .map(compras -> new ResponseEntity<>(compras, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{idCompra}")
    public ResponseEntity<CompraDto> ObtenerCompraPorId(@PathVariable("idCompra") int idCompra) {
        return compraServicio.ObtenerCompraPorId(idCompra)
                .map(compra -> new ResponseEntity<>(compra, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/agregar")
    public ResponseEntity<CompraDto> AgregarCompra(@RequestBody CompraDto nuevaCompra) {
        return new ResponseEntity<>(compraServicio.AgregarCompra(nuevaCompra), HttpStatus.CREATED);
    }

    @GetMapping("/eliminar/{idCompra}")
    public ResponseEntity<HttpStatusCode> EliminarCompra(@PathVariable("idCompra") int idCompra) {
        if (compraServicio.EliminarCompra(idCompra)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}