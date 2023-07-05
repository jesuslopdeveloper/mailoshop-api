package com.jesuslopdeveloper.mailoshop.dominio.servicio;

import com.jesuslopdeveloper.mailoshop.dominio.modelos.dto.ProductoDto;
import com.jesuslopdeveloper.mailoshop.persistencia.irepositorio.IProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicio {
    private final IProductoRepositorio productoRepositorio;

    @Autowired
    public ProductoServicio(IProductoRepositorio productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    public List<ProductoDto> ObtenerProductos() {
        return productoRepositorio.ObtenerProductos();
    }

    public Optional<List<ProductoDto>> ObtenerPorIdCategoria(int idCategoria) {
        return productoRepositorio.ObtenerPorIdCategoria(idCategoria);
    }

    public Optional<List<ProductoDto>> ObtenerEscasos(int cantidad) {
        return productoRepositorio.ObtenerEscasos(cantidad);
    }

    public Optional<ProductoDto> ObtenerPorId(int idProducto) {
        return productoRepositorio.ObtenerPorId(idProducto);
    }

    public ProductoDto AgregarProducto(ProductoDto nuevoProducto) {
        return productoRepositorio.AgregarProducto(nuevoProducto);
    }

    public ProductoDto ActualizarProducto(ProductoDto producto) {
        return productoRepositorio.ActualizarProducto(producto);
    }

    public boolean EliminarProducto(int idProducto) {
        /*id(ObtenerProductoPorId(idProducto).isPresent()){}*/
        return ObtenerPorId(idProducto).map(prod -> {
            productoRepositorio.EliminarProducto(idProducto);
            return true;
        }).orElse(false);
    }
}