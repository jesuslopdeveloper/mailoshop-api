package com.jesuslopdeveloper.mailoshop.web.controller;

import com.jesuslopdeveloper.mailoshop.dominio.modelos.dto.ProductoDto;
import com.jesuslopdeveloper.mailoshop.dominio.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoServicio productoServicio;

    @Autowired
    public ProductoController(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    @GetMapping("lista")
    public ResponseEntity<List<ProductoDto>> ObtenerProductos() {
        return new ResponseEntity<>(productoServicio.ObtenerProductos(), HttpStatus.OK);
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<ProductoDto>> ObtenerPorIdCategoria(@PathVariable("idCategoria") int idCategoria) {
        return productoServicio.ObtenerPorIdCategoria(idCategoria)
                .map(productos -> new ResponseEntity<>(productos, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/escasos/{cantidad}")
    public ResponseEntity<List<ProductoDto>> ObtenerEscasos(@PathVariable("cantidad") int cantidad) {
        return productoServicio.ObtenerEscasos(cantidad)
                .map(productos -> new ResponseEntity<>(productos, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{idProducto}")
    public ResponseEntity<ProductoDto> ObtenerPorId(@PathVariable("idProducto") int idProducto) {
        return productoServicio.ObtenerPorId(idProducto)
                .map(producto -> new ResponseEntity<>(producto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/agregar")
    public ResponseEntity<ProductoDto> AgregarProducto(@RequestBody ProductoDto nuevoProducto) {
        return new ResponseEntity<>(productoServicio.AgregarProducto(nuevoProducto), HttpStatus.CREATED);
    }

    @PostMapping("/actualizar")
    public ResponseEntity<ProductoDto> ActualizarProducto(@RequestBody ProductoDto producto) {
        return new ResponseEntity<>(productoServicio.ActualizarProducto(producto), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{idProducto}")
    public ResponseEntity<HttpStatusCode> EliminarProducto(@PathVariable("idProducto") int idProducto) {
        if (productoServicio.EliminarProducto(idProducto)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}