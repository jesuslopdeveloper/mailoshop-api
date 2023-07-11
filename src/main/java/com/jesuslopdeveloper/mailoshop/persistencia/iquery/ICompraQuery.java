package com.jesuslopdeveloper.mailoshop.persistencia.iquery;

import com.jesuslopdeveloper.mailoshop.persistencia.entidad.Compra;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ICompraQuery extends CrudRepository<Compra, Integer> {
    Optional<List<Compra>> findByIdClienteOrderByFechaDesc(String idCliente);
}