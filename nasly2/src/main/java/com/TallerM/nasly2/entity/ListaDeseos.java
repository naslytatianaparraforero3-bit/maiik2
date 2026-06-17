package com.TallerM.nasly2.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "lista_deseos")
@Data
public class ListaDeseos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @Column(name = "cantidad_deseada")
    private Integer cantidadDeseada;

    @Column(name = "fecha_agregado")
    private LocalDateTime fechaAgregado;
}
