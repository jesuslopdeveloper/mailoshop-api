package com.jesuslopdeveloper.mailoshop.persistencia.entidad;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @Column(name = "id_categoria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategoria;
    private String descripcion;
    private Boolean estado;
    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;
}
