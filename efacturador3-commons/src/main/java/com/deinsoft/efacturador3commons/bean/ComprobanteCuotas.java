/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3commons.bean;

import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author EDWARD-PC
 */
public class ComprobanteCuotas {
    @NotBlank
    private BigDecimal monto_pago;
    private String fecha_pago;
    @NotBlank
    private String tipo_moneda_pago;
    
    public ComprobanteCuotas() {
    }

    public BigDecimal getMonto_pago() {
        return monto_pago;
    }

    public void setMonto_pago(BigDecimal monto_pago) {
        this.monto_pago = monto_pago;
    }

    public String getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(String fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public String getTipo_moneda_pago() {
        return tipo_moneda_pago;
    }

    public void setTipo_moneda_pago(String tipo_moneda_pago) {
        this.tipo_moneda_pago = tipo_moneda_pago;
    }
    
}
