package com.MaikNasP.Taller.controller;

import com.MaikNasP.Taller.dto.EstadisticasDto;
import com.MaikNasP.Taller.dto.GlobalHttpResponse;
import com.MaikNasP.Taller.dto.TareaRequestDto;
import com.MaikNasP.Taller.dto.TareaResponseDto;
import com.MaikNasP.Taller.entity.EstadoTarea;
import com.MaikNasP.Taller.entity.PrioridadTarea;
import com.MaikNasP.Taller.service.TareaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    public ResponseEntity<GlobalHttpResponse<List<TareaResponseDto>>> listarTodas() {
        List<TareaResponseDto> tareas = tareaService.listarTodas();
        GlobalHttpResponse<List<TareaResponseDto>> response = new GlobalHttpResponse<>();
        response.setSuccess(true);
        response.setMensaje("Tareas obtenidas exitosamente");
        response.setData(tareas);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GlobalHttpResponse<TareaResponseDto>> obtenerPorId(@PathVariable Long id) {
        TareaResponseDto tarea = tareaService.buscarPorId(id);
        GlobalHttpResponse<TareaResponseDto> response = new GlobalHttpResponse<>();
        response.setSuccess(true);
        response.setMensaje("Tarea encontrada");
        response.setData(tarea);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<GlobalHttpResponse<TareaResponseDto>> crear(@Valid @RequestBody TareaRequestDto dto) {
        TareaResponseDto creada = tareaService.crear(dto);
        GlobalHttpResponse<TareaResponseDto> response = new GlobalHttpResponse<>();
        response.setSuccess(true);
        response.setMensaje("Tarea creada exitosamente");
        response.setData(creada);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GlobalHttpResponse<TareaResponseDto>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody TareaRequestDto dto) {
        TareaResponseDto actualizada = tareaService.actualizar(id, dto);
        GlobalHttpResponse<TareaResponseDto> response = new GlobalHttpResponse<>();
        response.setSuccess(true);
        response.setMensaje("Tarea actualizada exitosamente");
        response.setData(actualizada);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<GlobalHttpResponse<TareaResponseDto>> cambiarEstado(
            @PathVariable Long id,
            @RequestParam EstadoTarea estado) {
        TareaResponseDto actualizada = tareaService.cambiarEstado(id, estado);
        GlobalHttpResponse<TareaResponseDto> response = new GlobalHttpResponse<>();
        response.setSuccess(true);
        response.setMensaje("Estado actualizado exitosamente");
        response.setData(actualizada);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalHttpResponse<Object>> eliminar(@PathVariable Long id) {
        tareaService.eliminar(id);
        GlobalHttpResponse<Object> response = new GlobalHttpResponse<>();
        response.setSuccess(true);
        response.setMensaje("Tarea eliminada exitosamente");
        response.setData(null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filtrar/estado")
    public ResponseEntity<GlobalHttpResponse<List<TareaResponseDto>>> filtrarPorEstado(
            @RequestParam EstadoTarea estado) {
        List<TareaResponseDto> tareas = tareaService.filtrarPorEstado(estado);
        GlobalHttpResponse<List<TareaResponseDto>> response = new GlobalHttpResponse<>();
        response.setSuccess(true);
        response.setMensaje("Tareas filtradas por estado: " + estado);
        response.setData(tareas);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filtrar/prioridad")
    public ResponseEntity<GlobalHttpResponse<List<TareaResponseDto>>> filtrarPorPrioridad(
            @RequestParam PrioridadTarea prioridad) {
        List<TareaResponseDto> tareas = tareaService.filtrarPorPrioridad(prioridad);
        GlobalHttpResponse<List<TareaResponseDto>> response = new GlobalHttpResponse<>();
        response.setSuccess(true);
        response.setMensaje("Tareas filtradas por prioridad: " + prioridad);
        response.setData(tareas);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/buscar")
    public ResponseEntity<GlobalHttpResponse<List<TareaResponseDto>>> buscarPorTitulo(
            @RequestParam String q) {
        List<TareaResponseDto> tareas = tareaService.buscarPorTitulo(q);
        GlobalHttpResponse<List<TareaResponseDto>> response = new GlobalHttpResponse<>();
        response.setSuccess(true);
        response.setMensaje("Resultados de búsqueda");
        response.setData(tareas);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/estadisticas")
    public ResponseEntity<GlobalHttpResponse<EstadisticasDto>> obtenerEstadisticas() {
        EstadisticasDto estadisticas = tareaService.obtenerEstadisticas();
        GlobalHttpResponse<EstadisticasDto> response = new GlobalHttpResponse<>();
        response.setSuccess(true);
        response.setMensaje("Estadísticas obtenidas");
        response.setData(estadisticas);
        return ResponseEntity.ok(response);
    }
}