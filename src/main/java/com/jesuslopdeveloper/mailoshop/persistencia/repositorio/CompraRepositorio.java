package com.jesuslopdeveloper.mailoshop.persistencia.repositorio;

import com.jesuslopdeveloper.mailoshop.dominio.modelos.dto.CompraDto;
import com.jesuslopdeveloper.mailoshop.persistencia.entidad.Compra;
import com.jesuslopdeveloper.mailoshop.persistencia.iquery.ICompraQuery;
import com.jesuslopdeveloper.mailoshop.persistencia.irepositorio.ICompraRepositorio;
import com.jesuslopdeveloper.mailoshop.persistencia.mapper.ICompraMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepositorio implements ICompraRepositorio {
    private final ICompraQuery compraQuery;
    private final ICompraMapper mapper;

    @Autowired
    public CompraRepositorio(ICompraQuery compraQuery, ICompraMapper mapper) {
        this.compraQuery = compraQuery;
        this.mapper = mapper;
    }

    @Override
    public List<CompraDto> ObtenerCompras() {
        List<Compra> compras = (List<Compra>) compraQuery.findAll();
        return mapper.aComprasDto(compras);
    }

    @Override
    public Optional<List<CompraDto>> ObtenerComprasPorIdCliente(String idCliente) {
        return compraQuery.findByIdClienteOrderByFechaDesc(idCliente).map(compras -> mapper.aComprasDto(compras));
    }

    @Override
    public Optional<CompraDto> ObtenerCompraPorId(int idCompra) {
        return compraQuery.findById(idCompra).map(compra -> mapper.aCompraDto(compra));
    }

    @Override
    public CompraDto AgregarCompra(CompraDto nuevaCompra) {
        Compra compra = mapper.aCompra(nuevaCompra);
        compra.getComprasProductos().forEach(comprasProducto -> comprasProducto.setCompra(compra));
        return mapper.aCompraDto(compraQuery.save(compra));
    }

    @Override
    public void EliminarCompra(int idCompra) {
        compraQuery.deleteById(idCompra);
    }
}