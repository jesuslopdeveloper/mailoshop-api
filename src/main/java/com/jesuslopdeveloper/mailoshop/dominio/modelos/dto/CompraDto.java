package com.jesuslopdeveloper.mailoshop.dominio.modelos.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CompraDto {
    private int idCompra;
    private int idCliente;
    private LocalDateTime fecha;
    private String medioPago;
    private String comentario;
    private String estatus;
    private List<ComprasProductoDto> comprasProductos;
}