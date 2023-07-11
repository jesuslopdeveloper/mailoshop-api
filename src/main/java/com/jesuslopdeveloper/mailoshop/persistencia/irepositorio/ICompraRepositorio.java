package com.jesuslopdeveloper.mailoshop.persistencia.irepositorio;

import com.jesuslopdeveloper.mailoshop.dominio.modelos.dto.CompraDto;

import java.util.List;
import java.util.Optional;

public interface ICompraRepositorio {
    List<CompraDto> ObtenerCompras();

    Optional<List<CompraDto>> ObtenerComprasPorIdCliente(String idCliente);
    Optional<CompraDto> ObtenerCompraPorId(int idCompra);

    CompraDto AgregarCompra(CompraDto nuevaCompra);

    void EliminarCompra(int idCompra);
}