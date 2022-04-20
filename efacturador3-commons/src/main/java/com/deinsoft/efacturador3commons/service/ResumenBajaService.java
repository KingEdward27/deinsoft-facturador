/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3commons.service;

import com.deinsoft.efacturador3commons.bean.ResumenBajaBean;
import com.deinsoft.efacturador3commons.model.Empresa;
import com.deinsoft.efacturador3commons.model.ResumenBaja;
//import com.deinsoft.efacturador3commons.soap.gencdp.TransferirArchivoException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author EDWARD-PC
 */
public interface ResumenBajaService {
    
    public ResumenBaja getResumenBajaById(long id) ;
    public List<ResumenBaja> getResumenBajas();
    public ResumenBaja save(ResumenBaja e);
    public List<ResumenBaja> getByNombreArchivo(String nombreArchivo);
    public Map<String, Object> generarComprobantePagoSunat(String rootpath,ResumenBaja resumenBaja) ;
    ResumenBaja toResumenBajaModel(ResumenBajaBean resumenBajaBean,Empresa empresa) throws ParseException;
    public List<ResumenBaja> saveAll(List<ResumenBaja> e);
    public String validarDocumento(ResumenBajaBean resumenBaja);
}
