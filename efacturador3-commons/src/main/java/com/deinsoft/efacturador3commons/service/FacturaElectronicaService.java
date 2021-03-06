/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3commons.service;

import com.deinsoft.efacturador3commons.bean.ComprobanteCab;
import com.deinsoft.efacturador3commons.model.Empresa;
import com.deinsoft.efacturador3commons.model.FacturaElectronica;
//import com.deinsoft.efacturador3commons.soap.gencdp.TransferirArchivoException;
import io.github.project.openubl.xmlsenderws.webservices.providers.BillServiceModel;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author EDWARD-PC
 */
public interface FacturaElectronicaService {

    public FacturaElectronica getById(long id);

    public List<FacturaElectronica> getListFacturaElectronica();

    FacturaElectronica save(FacturaElectronica facturaElectronica);

    public List<FacturaElectronica> getBySerieAndNumeroAndEmpresaId(FacturaElectronica facturaElectronica);

    public List<FacturaElectronica> getByNotaReferenciaTipoAndNotaReferenciaSerieAndNotaReferenciaNumero(FacturaElectronica facturaElectronica);

    public FacturaElectronica toFacturaModel(ComprobanteCab documento,Empresa e) throws  ParseException;

    public Map<String, Object> generarComprobantePagoSunat(String rootpath, FacturaElectronica documento) ;
    public Map<String, Object> generarComprobantePagoSunat(long comprobanteId) ;
    public String validarComprobante(ComprobanteCab documento,Empresa e);

    public Map<String, Object> sendToSUNAT(long comprobante_id);

    public void sendToSUNAT();
    
    public List<FacturaElectronica> getByFechaEmisionBetweenAndEmpresaIdIn(LocalDate fecIni, LocalDate fecFin,List<Integer> empresaIds);
}
