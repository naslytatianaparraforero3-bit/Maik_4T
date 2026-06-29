package com.MaikNasP.Taller.dto;

import com.MaikNasP.Taller.entity.EstadoTarea;
import com.MaikNasP.Taller.entity.PrioridadTarea;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TareaResponseDto {

    private Long id;
    private String titulo;
    private String descripcion;
    private EstadoTarea estado;
    private PrioridadTarea prioridad;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}