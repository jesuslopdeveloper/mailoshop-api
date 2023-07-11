package com.jesuslopdeveloper.mailoshop.dominio.servicio;

import com.jesuslopdeveloper.mailoshop.dominio.modelos.dto.CompraDto;
import com.jesuslopdeveloper.mailoshop.persistencia.irepositorio.ICompraRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraServicio {
    private final ICompraRepositorio compraRepositorio;

    @Autowired
    public CompraServicio(ICompraRepositorio compraRepositorio) {
        this.compraRepositorio = compraRepositorio;
    }

    public List<CompraDto> ObtenerCompras() {
        return compraRepositorio.ObtenerCompras();
    }

    public Optional<List<CompraDto>> ObtenerComprasPorIdCliente(String idCliente) {
        return compraRepositorio.ObtenerComprasPorIdCliente(idCliente);
    }

    public Optional<CompraDto> ObtenerCompraPorId(int idCompra) {
        return compraRepositorio.ObtenerCompraPorId(idCompra);
    }

    public CompraDto AgregarCompra(CompraDto nuevaCompra) {
        return compraRepositorio.AgregarCompra(nuevaCompra);
    }

    public boolean EliminarCompra(int idCompra) {
        return compraRepositorio.ObtenerCompraPorId(idCompra).map(compra -> {
            compraRepositorio.EliminarCompra(compra.getIdCompra());
            return true;
        }).orElse(false);
    }
}