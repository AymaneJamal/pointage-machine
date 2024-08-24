package com.Pointage.machine.dto;

import lombok.Data;

@Data
public class PointageRequest {
    private String qrCode;
    private String type;
}
