package com.MaikNasP.Taller.repository;

import com.MaikNasP.Taller.entity.EstadoTarea;
import com.MaikNasP.Taller.entity.PrioridadTarea;
import com.MaikNasP.Taller.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long> {

    List<Tarea> findByEstado(EstadoTarea estado);

    List<Tarea> findByPrioridad(PrioridadTarea prioridad);

    List<Tarea> findByTituloContainingIgnoreCase(String titulo);
}