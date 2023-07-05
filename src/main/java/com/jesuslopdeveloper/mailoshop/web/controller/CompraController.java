package com.jesuslopdeveloper.mailoshop.web.controller;

import com.jesuslopdeveloper.mailoshop.dominio.modelos.dto.CompraDto;
import com.jesuslopdeveloper.mailoshop.dominio.modelos.dto.ProductoDto;
import com.jesuslopdeveloper.mailoshop.dominio.servicio.CompraServicio;
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

@Tag(name = "Compras", description = "Servicio CRUD de compras")
@RestController
@RequestMapping("/compras")
public class CompraController {
    private final CompraServicio compraServicio;

    @Autowired
    public CompraController(CompraServicio compraServicio) {
        this.compraServicio = compraServicio;
    }

    @Operation(
            summary = "Obtener todas las compras registradas",
            description = "El servicio devuelve la lista de las compras registradas"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = CompraDto.class)), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/lista")
    public ResponseEntity<List<CompraDto>> ObtenerCompras() {
        return new ResponseEntity<>(compraServicio.ObtenerCompras(), HttpStatus.OK);
    }

    @Operation(
            summary = "Obtener las compras de un cliente",
            description = "El servicio devuelve la lista de las compras realizada por un cliente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(array = @ArraySchema(schema = @Schema(implementation = CompraDto.class)), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<CompraDto>> ObtenerComprasPorIdCliente(
            @Parameter(name = "idCliente", description = "Id del cliente")
            @PathVariable("idCliente") String idCliente
    ) {
        return compraServicio.ObtenerComprasPorIdCliente(idCliente)
                .map(compras -> new ResponseEntity<>(compras, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
            summary = "Obtener una compra por el Id",
            description = "El servicio devuelve una compra buscando por el Id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = CompraDto.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/{idCompra}")
    public ResponseEntity<CompraDto> ObtenerCompraPorId(
            @Parameter(name = "idCompra", description = "Id de la compra")
            @PathVariable("idCompra") int idCompra
    ) {
        return compraServicio.ObtenerCompraPorId(idCompra)
                .map(compra -> new ResponseEntity<>(compra, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
            summary = "Agregar una compra de un cliente",
            description = "El servicio agrega una compra y el detalle de los productos comprados por un cliente"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = CompraDto.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    @PostMapping("/agregar")
    public ResponseEntity<CompraDto> AgregarCompra(@RequestBody CompraDto nuevaCompra) {
        return new ResponseEntity<>(compraServicio.AgregarCompra(nuevaCompra), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Eliminar una compra",
            description = "El servicio elimina una compra por el Id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {
                    @Content(schema = @Schema(implementation = CompraDto.class), mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    @GetMapping("/eliminar/{idCompra}")
    public ResponseEntity<HttpStatusCode> EliminarCompra(
            @Parameter(name = "idCompra", description = "Id de la compra")
            @PathVariable("idCompra") int idCompra
    ) {
        if (compraServicio.EliminarCompra(idCompra)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}