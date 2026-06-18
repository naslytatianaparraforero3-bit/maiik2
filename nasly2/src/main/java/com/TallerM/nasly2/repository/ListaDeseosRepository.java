package com.TallerM.nasly2.repository;

import com.TallerM.nasly2.entity.ListaDeseos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ListaDeseosRepository extends JpaRepository<ListaDeseos, Long> {
    List<ListaDeseos> findByIdUsuario(Long idUsuario);
}