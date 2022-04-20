/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3.controllers;

import com.deinsoft.efacturador3commons.bean.ComprobanteCab;
import com.deinsoft.efacturador3commons.model.Empresa;
import com.deinsoft.efacturador3commons.model.FacturaElectronica;
import com.deinsoft.efacturador3commons.config.AppConfig;
import com.deinsoft.efacturador3commons.service.ComunesService;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.deinsoft.efacturador3commons.service.EmpresaService;
import com.deinsoft.efacturador3commons.service.FacturaElectronicaService;
import com.deinsoft.efacturador3commons.service.GenerarDocumentosService;
//import com.deinsoft.efacturador3commons.soap.gencdp.TransferirArchivoException;
import com.deinsoft.efacturador3commons.util.Constantes;
import io.github.project.openubl.xmlsenderws.webservices.providers.BillServiceModel;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author EDWARD-PC
 */
@RestController
@RequestMapping("api/v1/document")
public class FacturaController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(FacturaController.class);

    @Autowired
    FacturaElectronicaService facturaElectronicaService;

    @Autowired
    EmpresaService empresaService;

//    @Autowired
//    private ComunesService comunesService;
//    
//    @Autowired
//    private GenerarDocumentosService generarDocumentosService;
    
    @Autowired
    AppConfig appConfig;
    
    @GetMapping(value = "/cloud-deinsoft/documents")
    public ResponseEntity<?> getDocuments2(){
        return ResponseEntity.status(HttpStatus.OK).body(facturaElectronicaService.getListFacturaElectronica());
    }
    
    @GetMapping(value = "/documents")
    public ResponseEntity<?> getDocuments(){
        return ResponseEntity.status(HttpStatus.OK).body(facturaElectronicaService.getListFacturaElectronica());
    }
    @PostMapping(value = "/send-document")
    public ResponseEntity<?> sendDocument(@Valid @RequestBody ComprobanteCab documento, 
            BindingResult bindingResult,HttpServletRequest request, HttpServletResponse response) throws ParseException {
        Map<String,Object> result = null;
        log.debug("documento recibido: "+ documento.toString());
        if(documento.getLista_productos() != null){
            log.debug("items: "+ documento.toString());
            documento.getLista_productos().forEach((lista_producto) -> {
                log.debug("documento det recibido: "+ lista_producto.toString());
            });
        }
        try {
            if (bindingResult.hasErrors()) {
                
                Map<String,Object> errors = this.validar(bindingResult);
                result  = new HashMap<>();
                result.put("code","001");
                result.put("message",errors);
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(result);
            }
            Empresa empresa = getEmpresa(request);
            
            if(empresa == null || (empresa != null && empresa.getNumdoc() == null)){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("La empresa no tiene permiso de registrar documentos");
            }
            String mensajeValidacion = facturaElectronicaService.validarComprobante(documento,empresa);
            if (!mensajeValidacion.equals("")) {
                result  = new HashMap<>();
                result.put("code","002");
                result.put("message",mensajeValidacion);
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(result);
            }
            
            FacturaElectronica comprobante = facturaElectronicaService.toFacturaModel(documento,empresa);
            comprobante.setEmpresa(empresa);

            List<FacturaElectronica> listFact = facturaElectronicaService.getBySerieAndNumeroAndEmpresaId(comprobante);
            if (listFact == null || (listFact != null && listFact.size() > 0)) {
                result  = new HashMap<>();
                result.put("code","002");
                result.put("message","El documento ya existe");
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(result);
            }
            if (comprobante.getTipo().equals("07") || comprobante.getTipo().equals("08")) {
                listFact = facturaElectronicaService.getByNotaReferenciaTipoAndNotaReferenciaSerieAndNotaReferenciaNumero(comprobante);
                if (listFact == null || (listFact != null && listFact.size() == 0)) {
                    result  = new HashMap<>();
                result.put("code","002");
                result.put("message","El documento referenciado no existe");
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(result);
                }

            }
            result = this.facturaElectronicaService.generarComprobantePagoSunat(appConfig.getRootPath(), comprobante);
            
        } catch (Exception e) {
            e.printStackTrace();
            result  = new HashMap<>();
            result.put("code","003");
            result.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @PostMapping(value = "/gen-xml")
    public ResponseEntity<?> genXmlFromExistingData(@RequestParam(name = "id") String id, 
            HttpServletRequest request, HttpServletResponse response) throws  ParseException {
        Map<String,Object> result = null;
        try {
            result = this.facturaElectronicaService.generarComprobantePagoSunat(new Long(id));
        } catch (Exception e) {
            e.printStackTrace();
            result  = new HashMap<>();
            result.put("code","003");
            result.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @PostMapping(value = "/send-sunat")
    public ResponseEntity<?> enviarXML(@RequestParam(name = "id") String id,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("FacturaController.send-sunat...Iniciando el procesamiento");
        Map<String, Object> resultado = null;
        try {
            resultado =  facturaElectronicaService.sendToSUNAT(new Long(id));
        }catch (Exception e) {
            e.printStackTrace();
            resultado  = new HashMap<>();
            resultado.put("code","003");
            resultado.put("message",e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultado);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }

    

    
}
