package com.TallerM.nasly2.dto;

import lombok.Data;

@Data
public class ProductoResponseDto {
    private Long id;
    private String nombre;
    private Double precio;
    private Integer stock;
}