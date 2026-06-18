package com.TallerM.nasly2.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class HistoricoRequestDto {
    private Long idUsuario;
    private Long idProducto;
    private String nombreProducto;
    private String accion;
    private LocalDateTime fechaRegistro;
}
