package com.jesuslopdeveloper.mailoshop.dominio.modelos.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductoDto {
    private int idProducto;
    private int idCategoria;
    private String nombre;
    private BigDecimal precio;
    private int stock;
    private boolean activo;
    private CategoriaDto categoria;
}
