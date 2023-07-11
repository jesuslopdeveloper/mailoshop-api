package com.jesuslopdeveloper.mailoshop.dominio.modelos.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ComprasProductoDto {
    private int idProducto;
    private int cantidad;
    private BigDecimal total;
    private boolean activo;
}