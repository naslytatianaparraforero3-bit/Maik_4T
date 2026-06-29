package com.MaikNasP.Taller.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GlobalHttpResponse<T> {

    private boolean success;
    private String mensaje;
    private T data;
}