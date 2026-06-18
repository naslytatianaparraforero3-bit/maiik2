package com.TallerM.nasly2.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class WishlistRequestDto {
    private Long idUsuario;
    private Long idProducto;
    private String nombreProducto;
    private Double precioProducto;
    private Integer cantidadDeseada;
    private LocalDateTime fechaAgregado;
    private String alertaStock;
}
