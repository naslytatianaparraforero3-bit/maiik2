package com.TallerM.nasly2.service;

import com.TallerM.nasly2.dto.*;
import com.TallerM.nasly2.entity.*;
import com.TallerM.nasly2.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistService {

    private final ListaDeseosRepository wishlistRepo;
    private final ProductoRepository productoRepo;
    private final HistoricoDeseosRepository historicoRepo;

    public WishlistService(ListaDeseosRepository wishlistRepo, ProductoRepository productoRepo, HistoricoDeseosRepository historicoRepo) {
        this.wishlistRepo = wishlistRepo;
        this.productoRepo = productoRepo;
        this.historicoRepo = historicoRepo;
    }


    public List<ProductoResponseDto> obtenerCatalogo() {
        List<Producto> productos = productoRepo.findAll();
        List<ProductoResponseDto> respuesta = new ArrayList<>();
        
        for (Producto p : productos) {
            ProductoResponseDto dto = new ProductoResponseDto();
            dto.setId(p.getId());
            dto.setNombre(p.getNombre());
            dto.setPrecio(p.getPrecio());
            dto.setStock(p.getStock());
            respuesta.add(dto);
        }
        return respuesta;
    }

    public WishlistResponseDto agregarADeseos(WishlistRequestDto dto) {
        Producto producto = productoRepo.findById(dto.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        ListaDeseos deseo = new ListaDeseos();
        deseo.setIdUsuario(dto.getIdUsuario());
        deseo.setProducto(producto);
        deseo.setCantidadDeseada(dto.getCantidadDeseada());
        deseo.setFechaAgregado(LocalDateTime.now());
        
        ListaDeseos guardado = wishlistRepo.save(deseo);

        HistoricoDeseos hist = new HistoricoDeseos();
        hist.setIdUsuario(dto.getIdUsuario());
        hist.setIdProducto(producto.getId());
        hist.setNombreProducto(producto.getNombre());
        hist.setAccion("AGREGADO");
        hist.setFechaRegistro(LocalDateTime.now());
        historicoRepo.save(hist);

        WishlistResponseDto resDto = new WishlistResponseDto();
        resDto.setId(guardado.getId());
        resDto.setIdUsuario(guardado.getIdUsuario());
        resDto.setIdProducto(producto.getId());
        resDto.setNombreProducto(producto.getNombre());
        resDto.setPrecioProducto(producto.getPrecio());
        resDto.setCantidadDeseada(guardado.getCantidadDeseada());
        resDto.setFechaAgregado(guardado.getFechaAgregado());
        
        return resDto;
    }

    public List<WishlistResponseDto> consultarListaDeseos(Long idUsuario) {
        List<ListaDeseos> lista = wishlistRepo.findByIdUsuario(idUsuario);
        List<WishlistResponseDto> respuesta = new ArrayList<>();

        for (ListaDeseos item : lista) {
            WishlistResponseDto resDto = new WishlistResponseDto();
            resDto.setId(item.getId());
            resDto.setIdUsuario(item.getIdUsuario());
            resDto.setIdProducto(item.getProducto().getId());
            resDto.setNombreProducto(item.getProducto().getNombre());
            resDto.setPrecioProducto(item.getProducto().getPrecio());
            resDto.setCantidadDeseada(item.getCantidadDeseada());
            resDto.setFechaAgregado(item.getFechaAgregado());

            if (item.getProducto().getStock() == 0) {
                resDto.setAlertaStock("NOTIFICACIÓN: Este producto ya no se encuentra en stock.");
            }

            respuesta.add(resDto);
        }
        return respuesta;
    }

    public void eliminarDeLista(Long idDeseo) {
        ListaDeseos deseo = wishlistRepo.findById(idDeseo)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
                
        wishlistRepo.delete(deseo);
        
        HistoricoDeseos hist = new HistoricoDeseos();
        hist.setIdUsuario(deseo.getIdUsuario());
        hist.setIdProducto(deseo.getProducto().getId());
        hist.setNombreProducto(deseo.getProducto().getNombre());
        hist.setAccion("ELIMINADO");
        hist.setFechaRegistro(LocalDateTime.now());
        historicoRepo.save(hist);
    }
}
