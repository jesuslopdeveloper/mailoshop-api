package com.jesuslopdeveloper.mailoshop.persistencia.irepositorio;

import com.jesuslopdeveloper.mailoshop.dominio.modelos.dto.ProductoDto;

import java.util.List;
import java.util.Optional;

public interface IProductoRepositorio {
    List<ProductoDto> ObtenerProductos();
    Optional<List<ProductoDto>> ObtenerPorIdCategoria(int idCategoria);
    Optional<List<ProductoDto>> ObtenerEscasos(int cantidad);
    Optional<ProductoDto> ObtenerPorId(int idProducto);
    ProductoDto AgregarProducto(ProductoDto nuevoProducto);
    ProductoDto ActualizarProducto(ProductoDto producto);
    void EliminarProducto(int idProducto);
}
