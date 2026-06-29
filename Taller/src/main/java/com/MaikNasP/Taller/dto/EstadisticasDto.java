package com.MaikNasP.Taller.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadisticasDto {

    private Long total;
    private Long pendientes;
    private Long enProgreso;
    private Long completadas;
    private Long canceladas;
}