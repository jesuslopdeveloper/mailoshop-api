package com.jesuslopdeveloper.mailoshop.persistencia.mapper;

import com.jesuslopdeveloper.mailoshop.dominio.modelos.dto.CompraDto;
import com.jesuslopdeveloper.mailoshop.persistencia.entidad.Compra;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {IComprasProductoMapper.class})
public interface ICompraMapper {
    @Mapping(source = "estado", target = "estatus")
    CompraDto aCompraDto(Compra compra);

    List<CompraDto> aComprasDto(List<Compra> compras);

    @InheritInverseConfiguration
    @Mapping(target = "cliente", ignore = true)
    Compra aCompra(CompraDto compra);
}