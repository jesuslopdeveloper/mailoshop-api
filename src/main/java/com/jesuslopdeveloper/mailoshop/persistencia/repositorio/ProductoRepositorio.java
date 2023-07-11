package com.jesuslopdeveloper.mailoshop.persistencia.repositorio;

import com.jesuslopdeveloper.mailoshop.dominio.modelos.dto.ProductoDto;
import com.jesuslopdeveloper.mailoshop.persistencia.entidad.Producto;
import com.jesuslopdeveloper.mailoshop.persistencia.iquery.IProductoQuery;
import com.jesuslopdeveloper.mailoshop.persistencia.irepositorio.IProductoRepositorio;
import com.jesuslopdeveloper.mailoshop.persistencia.mapper.IProductoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepositorio implements IProductoRepositorio {
    private final IProductoQuery productoQuery;
    private final IProductoMapper mapper;

    @Autowired
    public ProductoRepositorio(IProductoQuery productoQuery, IProductoMapper mapper) {
        this.productoQuery = productoQuery;
        this.mapper = mapper;
    }

    @Override
    public List<ProductoDto> ObtenerProductos() {
        List<Producto> productos = (List<Producto>) productoQuery.findAll();
        return mapper.aProductosDto(productos);
    }

    @Override
    public Optional<List<ProductoDto>> ObtenerPorIdCategoria(int idCategoria) {
        List<Producto> productos = productoQuery.findByIdCategoriaOrderByNombreAsc(idCategoria);
        return Optional.of(mapper.aProductosDto(productos));
    }

    @Override
    public Optional<List<ProductoDto>> ObtenerEscasos(int cantidad) {

        Optional<List<Producto>> productos = productoQuery.findByCantidadStockLessThanAndEstado(cantidad, true);
        return productos.map(prods -> mapper.aProductosDto(prods));
    }

    @Override
    public Optional<ProductoDto> ObtenerPorId(int idProducto) {
        return productoQuery.findById(idProducto).map(prod -> mapper.aProductoDto(prod));
    }

    @Override
    public ProductoDto AgregarProducto(ProductoDto nuevoProducto) {
        Producto producto = mapper.aProducto(nuevoProducto);
        return mapper.aProductoDto(productoQuery.save(producto));
    }

    @Override
    public ProductoDto ActualizarProducto(ProductoDto producto) {
        return productoQuery.findById(producto.getIdProducto()).map(prod -> {
            prod.setIdCategoria(producto.getIdCategoria());
            prod.setNombre(producto.getNombre());
            prod.setPrecioVenta(producto.getPrecio());
            prod.setCantidadStock(producto.getStock());
            return mapper.aProductoDto(productoQuery.save(prod));
        }).orElse(producto);
    }

    @Override
    public void EliminarProducto(int idProducto) {
        productoQuery.deleteById(idProducto);
    }
}
