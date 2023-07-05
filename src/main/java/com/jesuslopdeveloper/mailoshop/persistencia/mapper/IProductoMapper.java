package com.jesuslopdeveloper.mailoshop.persistencia.mapper;

import com.jesuslopdeveloper.mailoshop.dominio.modelos.dto.ProductoDto;
import com.jesuslopdeveloper.mailoshop.persistencia.entidad.Producto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ICategoriaMapper.class})
public interface IProductoMapper {
    @Mappings({
            @Mapping(source = "precioVenta", target = "precio"),
            @Mapping(source = "cantidadStock", target = "stock"),
            @Mapping(source = "estado", target = "activo"),
            @Mapping(source = "categoria", target = "categoria"),
    })
    ProductoDto aProductoDto(Producto producto);

    List<ProductoDto> aProductosDto(List<Producto> producto);

    @InheritInverseConfiguration
    @Mapping(target = "codigoBarras", ignore = true)
    Producto aProducto(ProductoDto producto);
}