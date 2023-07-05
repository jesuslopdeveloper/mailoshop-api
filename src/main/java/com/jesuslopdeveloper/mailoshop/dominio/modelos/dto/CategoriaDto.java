package com.jesuslopdeveloper.mailoshop.dominio.modelos.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaDto {
    private int idCategoria;
    private String descripcion;
    private Boolean activo;
}
