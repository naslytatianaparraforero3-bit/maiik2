package com.TallerM.nasly2.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HistoricoResponseDto {
    private Long id;
    private Long idUsuario;
    private Long idProducto;
    private String nombreProducto;
    private String accion;
    private LocalDateTime fechaRegistro;
}
