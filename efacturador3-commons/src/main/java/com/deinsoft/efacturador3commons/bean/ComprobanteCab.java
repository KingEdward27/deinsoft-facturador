/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3commons.bean;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author EDWARD
 */
public class ComprobanteCab {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "m_id")
//    private Long id;
    
    @NotBlank
    private String tipo;
    
    @NotBlank
    private String serie;
    
    @NotBlank
    private String numero;
    
    @NotBlank
    private String fecha_emision;
    
    private String fecha_vencimiento;
    
    @NotBlank
    private String tipo_operacion;
    
    @NotBlank
    private String cliente_tipo;
    
    @NotBlank
    private String cliente_documento;
    
    @NotBlank
    private String cliente_nombre;
    private String cliente_direccion;
    private String cliente_email;
    private String cliente_telefono;
    private String vendedor_nombre;
    private String observaciones;
    private String placa_vehiculo;
    private String orden_compra;
    private String guia_remision;
    private String descuento_global_porcentaje;
    
    @NotBlank
    private String moneda;
    private String nota_tipo;
    private String nota_motivo;
    private String nota_referencia_tipo;
    private String nota_referencia_serie;
    private String nota_referencia_numero;
//    private String incluir_pdf;
//    private String incluir_xml;
    
    private BigDecimal sumatoriaIGV;
    private BigDecimal sumatoriaISC;
    private BigDecimal sumatoriaOtrosTributos;
    private BigDecimal sumatoriaOtrosCargos;
    private BigDecimal totalValorVentasGravadas;
    private BigDecimal totalValorVentasInafectas;
    private BigDecimal totalValorVentasExoneradas;
    
    private String forma_pago;
    
    private String moneda_monto_neto_pendiente;
    private BigDecimal monto_neto_pendiente;
    
    @Valid
    private Set<ComprobanteDet> lista_productos;

    private Set<ComprobanteCuotas> lista_cuotas;
    
    private String serie_ref;
    private String numero_ref;
    private String monto_ref;
    private String fecha_ref;
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(String fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(String fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public String getTipo_operacion() {
        return tipo_operacion;
    }

    public void setTipo_operacion(String tipo_operacion) {
        this.tipo_operacion = tipo_operacion;
    }

    public String getCliente_tipo() {
        return cliente_tipo;
    }

    public void setCliente_tipo(String cliente_tipo) {
        this.cliente_tipo = cliente_tipo;
    }

    public String getCliente_documento() {
        return cliente_documento;
    }

    public void setCliente_documento(String cliente_documento) {
        this.cliente_documento = cliente_documento;
    }

    public String getCliente_nombre() {
        return cliente_nombre;
    }

    public void setCliente_nombre(String cliente_nombre) {
        this.cliente_nombre = cliente_nombre;
    }

    public String getCliente_direccion() {
        return cliente_direccion;
    }

    public void setCliente_direccion(String cliente_direccion) {
        this.cliente_direccion = cliente_direccion;
    }

    public String getCliente_email() {
        return cliente_email;
    }

    public void setCliente_email(String cliente_email) {
        this.cliente_email = cliente_email;
    }

    public String getCliente_telefono() {
        return cliente_telefono;
    }

    public void setCliente_telefono(String cliente_telefono) {
        this.cliente_telefono = cliente_telefono;
    }

    public String getVendedor_nombre() {
        return vendedor_nombre;
    }

    public void setVendedor_nombre(String vendedor_nombre) {
        this.vendedor_nombre = vendedor_nombre;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getPlaca_vehiculo() {
        return placa_vehiculo;
    }

    public void setPlaca_vehiculo(String placa_vehiculo) {
        this.placa_vehiculo = placa_vehiculo;
    }

    public String getOrden_compra() {
        return orden_compra;
    }

    public void setOrden_compra(String orden_compra) {
        this.orden_compra = orden_compra;
    }

    public String getGuia_remision() {
        return guia_remision;
    }

    public void setGuia_remision(String guia_remision) {
        this.guia_remision = guia_remision;
    }

    public String getDescuento_global_porcentaje() {
        return descuento_global_porcentaje;
    }

    public void setDescuento_global_porcentaje(String descuento_global_porcentaje) {
        this.descuento_global_porcentaje = descuento_global_porcentaje;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getNota_tipo() {
        return nota_tipo;
    }

    public void setNota_tipo(String nota_tipo) {
        this.nota_tipo = nota_tipo;
    }

    public String getNota_motivo() {
        return nota_motivo;
    }

    public void setNota_motivo(String nota_motivo) {
        this.nota_motivo = nota_motivo;
    }

    public String getNota_referencia_tipo() {
        return nota_referencia_tipo;
    }

    public void setNota_referencia_tipo(String nota_referencia_tipo) {
        this.nota_referencia_tipo = nota_referencia_tipo;
    }

    public String getNota_referencia_serie() {
        return nota_referencia_serie;
    }

    public void setNota_referencia_serie(String nota_referencia_serie) {
        this.nota_referencia_serie = nota_referencia_serie;
    }

    public String getNota_referencia_numero() {
        return nota_referencia_numero;
    }

    public void setNota_referencia_numero(String nota_referencia_numero) {
        this.nota_referencia_numero = nota_referencia_numero;
    }

//    public String getIncluir_pdf() {
//        return incluir_pdf;
//    }
//
//    public void setIncluir_pdf(String incluir_pdf) {
//        this.incluir_pdf = incluir_pdf;
//    }
//
//    public String getIncluir_xml() {
//        return incluir_xml;
//    }
//
//    public void setIncluir_xml(String incluir_xml) {
//        this.incluir_xml = incluir_xml;
//    }

    public Set<ComprobanteDet> getLista_productos() {
        return lista_productos;
    }

    public void setLista_productos(Set<ComprobanteDet> lista_productos) {
        this.lista_productos = lista_productos;
    }

    public Set<ComprobanteCuotas> getLista_cuotas() {
        return lista_cuotas;
    }

    public void setLista_cuotas(Set<ComprobanteCuotas> lista_cuotas) {
        this.lista_cuotas = lista_cuotas;
    }

    public String getSerie_ref() {
        return serie_ref;
    }

    public void setSerie_ref(String serie_ref) {
        this.serie_ref = serie_ref;
    }

    public String getNumero_ref() {
        return numero_ref;
    }

    public void setNumero_ref(String numero_ref) {
        this.numero_ref = numero_ref;
    }

    public String getMonto_ref() {
        return monto_ref;
    }

    public void setMonto_ref(String monto_ref) {
        this.monto_ref = monto_ref;
    }

    public String getFecha_ref() {
        return fecha_ref;
    }

    public void setFecha_ref(String fecha_ref) {
        this.fecha_ref = fecha_ref;
    }
    
    

    
    public BigDecimal getSumatoriaIGV() {
        return sumatoriaIGV;
    }

    public void setSumatoriaIGV(BigDecimal sumatoriaIGV) {
        this.sumatoriaIGV = sumatoriaIGV;
    }

    public BigDecimal getSumatoriaISC() {
        return sumatoriaISC;
    }

    public void setSumatoriaISC(BigDecimal sumatoriaISC) {
        this.sumatoriaISC = sumatoriaISC;
    }

    public BigDecimal getSumatoriaOtrosTributos() {
        return sumatoriaOtrosTributos;
    }

    public void setSumatoriaOtrosTributos(BigDecimal sumatoriaOtrosTributos) {
        this.sumatoriaOtrosTributos = sumatoriaOtrosTributos;
    }

    public BigDecimal getSumatoriaOtrosCargos() {
        return sumatoriaOtrosCargos;
    }

    public void setSumatoriaOtrosCargos(BigDecimal sumatoriaOtrosCargos) {
        this.sumatoriaOtrosCargos = sumatoriaOtrosCargos;
    }

    public BigDecimal getTotalValorVentasGravadas() {
        return totalValorVentasGravadas;
    }

    public void setTotalValorVentasGravadas(BigDecimal totalValorVentasGravadas) {
        this.totalValorVentasGravadas = totalValorVentasGravadas;
    }

    public BigDecimal getTotalValorVentasInafectas() {
        return totalValorVentasInafectas;
    }

    public void setTotalValorVentasInafectas(BigDecimal totalValorVentasInafectas) {
        this.totalValorVentasInafectas = totalValorVentasInafectas;
    }

    public BigDecimal getTotalValorVentasExoneradas() {
        return totalValorVentasExoneradas;
    }

    public void setTotalValorVentasExoneradas(BigDecimal totalValorVentasExoneradas) {
        this.totalValorVentasExoneradas = totalValorVentasExoneradas;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }

    public String getMoneda_monto_neto_pendiente() {
        return moneda_monto_neto_pendiente;
    }

    public void setMoneda_monto_neto_pendiente(String moneda_monto_neto_pendiente) {
        this.moneda_monto_neto_pendiente = moneda_monto_neto_pendiente;
    }

    public BigDecimal getMonto_neto_pendiente() {
        return monto_neto_pendiente;
    }

    public void setMonto_neto_pendiente(BigDecimal monto_neto_pendiente) {
        this.monto_neto_pendiente = monto_neto_pendiente;
    }

    
    
    public ComprobanteCab() {
    }
    
//    public String toJson(ComprobanteCab c) throws IllegalArgumentException, IllegalAccessException {
//        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
//        for (Field f : c.getClass().getDeclaredFields()) {
//            if (f.get(c) == null || f.getName().equals("lista")) {
//                continue;
//            }
//            System.out.println(f.getGenericType() + " " + f.getName() + " = " + f.get(c));
//            objectBuilder.add(f.getName(), f.get(c).toString());
//        }
//
//        JsonArrayBuilder objectBuilderDetail = Json.createArrayBuilder();
//        for (ComprobanteDet items : lista) {
//            objectBuilderDetail.add(items.toJson(items));
//        }
//        JsonArray objectDetail = objectBuilderDetail.build();
//        objectBuilder.add("items", objectDetail);
//        JsonObject object = objectBuilder.build();
//        return object.toString();
//    }

    @Override
    public String toString() {
        return "ComprobanteCab{" + "tipo=" + tipo + ", serie=" + serie + ", numero=" + numero + ", fecha_emision=" + fecha_emision + ", fecha_vencimiento=" + fecha_vencimiento + ", tipo_operacion=" + tipo_operacion + ", cliente_tipo=" + cliente_tipo + ", cliente_documento=" + cliente_documento + ", cliente_nombre=" + cliente_nombre + ", cliente_direccion=" + cliente_direccion + ", cliente_email=" + cliente_email + ", cliente_telefono=" + cliente_telefono + ", vendedor_nombre=" + vendedor_nombre + ", observaciones=" + observaciones + ", placa_vehiculo=" + placa_vehiculo + ", orden_compra=" + orden_compra + ", guia_remision=" + guia_remision + ", descuento_global_porcentaje=" + descuento_global_porcentaje + ", moneda=" + moneda + ", nota_tipo=" + nota_tipo + ", nota_motivo=" + nota_motivo + ", nota_referencia_tipo=" + nota_referencia_tipo + ", nota_referencia_serie=" + nota_referencia_serie + ", nota_referencia_numero=" + nota_referencia_numero + ", sumatoriaIGV=" + sumatoriaIGV + ", sumatoriaISC=" + sumatoriaISC + ", sumatoriaOtrosTributos=" + sumatoriaOtrosTributos + ", sumatoriaOtrosCargos=" + sumatoriaOtrosCargos + ", totalValorVentasGravadas=" + totalValorVentasGravadas + ", totalValorVentasInafectas=" + totalValorVentasInafectas + ", totalValorVentasExoneradas=" + totalValorVentasExoneradas + ", forma_pago=" + forma_pago + ", moneda_monto_neto_pendiente=" + moneda_monto_neto_pendiente + ", monto_neto_pendiente=" + monto_neto_pendiente + ", lista_productos=" + lista_productos + ", lista_cuotas=" + lista_cuotas + ", serie_ref=" + serie_ref + ", numero_ref=" + numero_ref + ", monto_ref=" + monto_ref + ", fecha_ref=" + fecha_ref + '}';
    }
}
