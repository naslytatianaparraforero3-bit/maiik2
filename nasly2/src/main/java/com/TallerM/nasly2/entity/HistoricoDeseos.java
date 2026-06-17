package com.TallerM.nasly2.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico_deseos")
@Data
public class HistoricoDeseos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="id_usuario")
    private Long idUsuario;

    @Column(name="id_producto")
    private Long idProducto;

    @Column(name="nombre_producto")
    private String nombreProducto;

    @Column(name="accion")
    private String accion;

    @Column(name="fecha_registro")
    private LocalDateTime fechaRegistro;
}
