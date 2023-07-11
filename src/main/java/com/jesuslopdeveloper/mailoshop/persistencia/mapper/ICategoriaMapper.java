package com.jesuslopdeveloper.mailoshop.persistencia.mapper;

import com.jesuslopdeveloper.mailoshop.dominio.modelos.dto.CategoriaDto;
import com.jesuslopdeveloper.mailoshop.persistencia.entidad.Categoria;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ICategoriaMapper {
    @Mapping(source = "estado", target = "activo")
    CategoriaDto aCategoriaDto(Categoria categoria);

    @InheritInverseConfiguration
    @Mapping(target = "productos", ignore = true)
    Categoria aCategoria(CategoriaDto categoria);
}
