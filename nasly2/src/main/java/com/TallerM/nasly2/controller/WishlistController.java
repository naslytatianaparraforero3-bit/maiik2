package com.TallerM.nasly2.controller;


import com.TallerM.nasly2.service.WishlistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.TallerM.nasly2.dto.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@CrossOrigin(origins = "*")
public class WishlistController {

    private final WishlistService service;

    public WishlistController(WishlistService service) {
        this.service = service;
    }


    @GetMapping("/productos")
    public ResponseEntity<List<ProductoResponseDto>> verCatalogo() {
        return ResponseEntity.ok(service.obtenerCatalogo());
    }


    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<WishlistResponseDto>> verListaDeseos(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(service.consultarListaDeseos(idUsuario));
    }


    @PostMapping("/agregar")
    public ResponseEntity<WishlistResponseDto> agregar(@RequestBody WishlistRequestDto dto) {
        WishlistResponseDto nuevo = service.agregarADeseos(dto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    
    @DeleteMapping("/eliminar/{idDeseo}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idDeseo) {
        service.eliminarDeLista(idDeseo);
        return ResponseEntity.noContent().build();
    }
}
