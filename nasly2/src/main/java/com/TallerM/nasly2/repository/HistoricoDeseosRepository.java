package com.TallerM.nasly2.repository;

import com.TallerM.nasly2.entity.HistoricoDeseos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoDeseosRepository extends JpaRepository<HistoricoDeseos, Long> {
}