package com.MaikNasP.Taller.service;

import com.MaikNasP.Taller.dto.EstadisticasDto;
import com.MaikNasP.Taller.dto.TareaRequestDto;
import com.MaikNasP.Taller.dto.TareaResponseDto;
import com.MaikNasP.Taller.entity.EstadoTarea;
import com.MaikNasP.Taller.entity.PrioridadTarea;
import com.MaikNasP.Taller.entity.Tarea;
import com.MaikNasP.Taller.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public List<TareaResponseDto> listarTodas() {
        List<Tarea> tareas = tareaRepository.findAll();
        List<TareaResponseDto> respuesta = new ArrayList<>();
        for (Tarea t : tareas) {
            respuesta.add(convertirADto(t));
        }
        return respuesta;
    }

    public TareaResponseDto buscarPorId(Long id) {
        Tarea tarea = tareaRepository.findById(id).orElse(null);
        if (tarea == null) {
            throw new RuntimeException("Tarea no encontrada con id: " + id);
        }
        return convertirADto(tarea);
    }

    public TareaResponseDto crear(TareaRequestDto dto) {
        Tarea tarea = new Tarea();
        tarea.setTitulo(dto.getTitulo());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setPrioridad(dto.getPrioridad());
        Tarea guardada = tareaRepository.save(tarea);
        return convertirADto(guardada);
    }

    public TareaResponseDto actualizar(Long id, TareaRequestDto dto) {
        Tarea tarea = tareaRepository.findById(id).orElse(null);
        if (tarea == null) {
            throw new RuntimeException("Tarea no encontrada con id: " + id);
        }
        if (tarea.getEstado() == EstadoTarea.CANCELADA) {
            throw new RuntimeException("No se puede modificar una tarea CANCELADA");
        }
        tarea.setTitulo(dto.getTitulo());
        tarea.setDescripcion(dto.getDescripcion());
        tarea.setPrioridad(dto.getPrioridad());
        Tarea actualizada = tareaRepository.save(tarea);
        return convertirADto(actualizada);
    }

    public TareaResponseDto cambiarEstado(Long id, EstadoTarea nuevoEstado) {
        Tarea tarea = tareaRepository.findById(id).orElse(null);
        if (tarea == null) {
            throw new RuntimeException("Tarea no encontrada con id: " + id);
        }
        EstadoTarea actual = tarea.getEstado();
        if (actual == EstadoTarea.CANCELADA) {
            throw new RuntimeException("Una tarea CANCELADA no puede cambiar de estado");
        }
        if (actual == EstadoTarea.COMPLETADA) {
            if (nuevoEstado == EstadoTarea.PENDIENTE || nuevoEstado == EstadoTarea.EN_PROGRESO) {
                throw new RuntimeException("Una tarea COMPLETADA no puede volver a PENDIENTE ni EN_PROGRESO");
            }
        }
        tarea.setEstado(nuevoEstado);
        Tarea actualizada = tareaRepository.save(tarea);
        return convertirADto(actualizada);
    }

    public void eliminar(Long id) {
        if (!tareaRepository.existsById(id)) {
            throw new RuntimeException("Tarea no encontrada con id: " + id);
        }
        tareaRepository.deleteById(id);
    }

    public List<TareaResponseDto> filtrarPorEstado(EstadoTarea estado) {
        List<Tarea> tareas = tareaRepository.findByEstado(estado);
        List<TareaResponseDto> respuesta = new ArrayList<>();
        for (Tarea t : tareas) {
            respuesta.add(convertirADto(t));
        }
        return respuesta;
    }

    public List<TareaResponseDto> filtrarPorPrioridad(PrioridadTarea prioridad) {
        List<Tarea> tareas = tareaRepository.findByPrioridad(prioridad);
        List<TareaResponseDto> respuesta = new ArrayList<>();
        for (Tarea t : tareas) {
            respuesta.add(convertirADto(t));
        }
        return respuesta;
    }

    public List<TareaResponseDto> buscarPorTitulo(String texto) {
        List<Tarea> tareas = tareaRepository.findByTituloContainingIgnoreCase(texto);
        List<TareaResponseDto> respuesta = new ArrayList<>();
        for (Tarea t : tareas) {
            respuesta.add(convertirADto(t));
        }
        return respuesta;
    }

    public EstadisticasDto obtenerEstadisticas() {
        List<Tarea> todas = tareaRepository.findAll();
        long total = 0;
        long pendientes = 0;
        long enProgreso = 0;
        long completadas = 0;
        long canceladas = 0;
        for (Tarea t : todas) {
            total++;
            if (t.getEstado() == EstadoTarea.PENDIENTE) pendientes++;
            else if (t.getEstado() == EstadoTarea.EN_PROGRESO) enProgreso++;
            else if (t.getEstado() == EstadoTarea.COMPLETADA) completadas++;
            else if (t.getEstado() == EstadoTarea.CANCELADA) canceladas++;
        }
        return new EstadisticasDto(total, pendientes, enProgreso, completadas, canceladas);
    }

    private TareaResponseDto convertirADto(Tarea tarea) {
        TareaResponseDto dto = new TareaResponseDto();
        dto.setId(tarea.getId());
        dto.setTitulo(tarea.getTitulo());
        dto.setDescripcion(tarea.getDescripcion());
        dto.setEstado(tarea.getEstado());
        dto.setPrioridad(tarea.getPrioridad());
        dto.setFechaCreacion(tarea.getFechaCreacion());
        dto.setFechaActualizacion(tarea.getFechaActualizacion());
        return dto;
    }
}