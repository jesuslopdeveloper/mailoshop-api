package com.jesuslopdeveloper.mailoshop.web.controller;

import com.jesuslopdeveloper.mailoshop.dominio.modelos.dto.ProductoDto;
import com.jesuslopdeveloper.mailoshop.dominio.servicio.ProductoServicio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Productos", description = "Servicio CRUD de productos")
@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoServicio productoServicio;

    @Autowired
    public ProductoController(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    @Operation(
            summary = "Obtener los productos",
            description = "El servicio devuelve la lista de los productos"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = ProductoDto.class)), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    @GetMapping("lista")
    public ResponseEntity<List<ProductoDto>> ObtenerProductos() {
        return new ResponseEntity<>(productoServicio.ObtenerProductos(), HttpStatus.OK);
    }

    @Operation(
            summary = "Obtener los productos por una categía",
            description = "El servicio devuelve la lista de los productos de la categoría indicada"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = ProductoDto.class)), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<ProductoDto>> ObtenerPorIdCategoria(
            @Parameter(name = "idCategoria", description = "Id de la categoría del producto")
            @PathVariable("idCategoria") int idCategoria
    ) {
        return productoServicio.ObtenerPorIdCategoria(idCategoria)
                .map(productos -> new ResponseEntity<>(productos, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
            summary = "Obtener los productos escasos",
            description = "El servicio devuelve la lista de los productos escasos que esten en el rango de la cantidad indicada"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = ProductoDto.class)), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/escasos/{cantidad}")
    public ResponseEntity<List<ProductoDto>> ObtenerEscasos(
            @Parameter(name = "cantidad", description = "Cantidad para que se considere que el producto esta en stock bajo")
            @PathVariable("cantidad") int cantidad
    ) {
        return productoServicio.ObtenerEscasos(cantidad)
                .map(productos -> new ResponseEntity<>(productos, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
            summary = "Obtener un producto por el Id",
            description = "El servicio devuelve un producto buscando por el Id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProductoDto.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/{idProducto}")
    public ResponseEntity<ProductoDto> ObtenerPorId(
            @Parameter(name = "idProducto", description = "Id del producto")
            @PathVariable("idProducto") int idProducto
    ) {
        return productoServicio.ObtenerPorId(idProducto)
                .map(producto -> new ResponseEntity<>(producto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
            summary = "Agregar un producto",
            description = "El servicio agrega un producto al catálogo"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = ProductoDto.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    @PostMapping("/agregar")
    public ResponseEntity<ProductoDto> AgregarProducto(@RequestBody ProductoDto nuevoProducto) {
        return new ResponseEntity<>(productoServicio.AgregarProducto(nuevoProducto), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Actualizar un producto",
            description = "El servicio actualiza la información de un producto"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = ProductoDto.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    @PostMapping("/actualizar")
    public ResponseEntity<ProductoDto> ActualizarProducto(@RequestBody ProductoDto producto) {
        return new ResponseEntity<>(productoServicio.ActualizarProducto(producto), HttpStatus.OK);
    }

    @Operation(
            summary = "Eliminar un producto",
            description = "El servicio elimina un producto del catálogo"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    @DeleteMapping("/eliminar/{idProducto}")
    public ResponseEntity<HttpStatusCode> EliminarProducto(
            @Parameter(name = "idProducto", description = "Id del producto")
            @PathVariable("idProducto") int idProducto
    ) {
        if (productoServicio.EliminarProducto(idProducto)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}