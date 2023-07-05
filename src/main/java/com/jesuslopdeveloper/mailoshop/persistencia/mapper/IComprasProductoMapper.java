package com.jesuslopdeveloper.mailoshop.persistencia.mapper;

import com.jesuslopdeveloper.mailoshop.dominio.modelos.dto.ComprasProductoDto;
import com.jesuslopdeveloper.mailoshop.persistencia.entidad.ComprasProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface IComprasProductoMapper {
    @Mappings({
            @Mapping(source = "id.idProducto", target = "idProducto"),
            @Mapping(source = "estado", target = "activo"),
    })
    ComprasProductoDto aComprasProductoDto(ComprasProducto comprasProducto);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "id.idCompra", ignore = true),
            @Mapping(target = "compra", ignore = true),
            @Mapping(target = "producto", ignore = true)
    })
    ComprasProducto aComprasProducto(ComprasProductoDto comprasProducto);
}