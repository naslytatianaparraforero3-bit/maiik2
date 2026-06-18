package com.TallerM.nasly2.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class WishlistResponseDto {
    private Long id;
    private Long idUsuario;
    private Long idProducto;
    private String nombreProducto;
    private Double precioProducto;
    private Integer cantidadDeseada;
    private LocalDateTime fechaAgregado;
    private String alertaStock;
}
