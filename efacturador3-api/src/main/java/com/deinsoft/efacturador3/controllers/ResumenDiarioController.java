/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3.controllers;

import com.deinsoft.efacturador3commons.bean.ComprobanteCab;
import com.deinsoft.efacturador3commons.bean.ResumenDiarioBean;
import com.deinsoft.efacturador3commons.bean.ResumenDiarioBeanDet;
import com.deinsoft.efacturador3commons.config.AppConfig;
import com.deinsoft.efacturador3commons.model.Empresa;
import com.deinsoft.efacturador3commons.model.FacturaElectronica;
import com.deinsoft.efacturador3commons.model.ResumenDiario;
import com.deinsoft.efacturador3commons.service.ComunesService;
import com.deinsoft.efacturador3commons.service.ResumenDiarioService;
//import com.deinsoft.efacturador3commons.soap.gencdp.TransferirArchivoException;
import io.github.project.openubl.xmlsenderws.webservices.providers.BillServiceModel;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.NonUniqueResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author EDWARD-PC
 */
@RestController
@RequestMapping("api/v1/resumenDiario")
public class ResumenDiarioController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(ResumenDiarioController.class);

    @Autowired
    ResumenDiarioService resumenDiarioService;

    @Autowired
    AppConfig appConfig;

    @Autowired
    private ComunesService comunesService;
    
    @PostMapping(value = "send-document")
    public ResponseEntity<?> save(@Valid @RequestBody ResumenDiarioBean resumenDiarioBean, BindingResult bindingResult,
            HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
        Map<String, Object> result = null;
        try {
            if (bindingResult.hasErrors()) {

                Map<String, Object> errors = this.validar(bindingResult);
                result = new HashMap<>();
                result.put("code", "001");
                result.put("message", errors);
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(result);
            }
            Empresa empresa = getEmpresa(request);
            ResumenDiario resumenDiario = resumenDiarioService.toResumenDiarioModel(resumenDiarioBean,empresa);
            resumenDiario.setEmpresa(empresa);
            
            result = this.resumenDiarioService.generarComprobantePagoSunat(appConfig.getRootPath(), resumenDiario);
        } catch (Exception e) {
            e.printStackTrace();
            result = new HashMap<>();
            result.put("code", "003");
            result.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @PostMapping(value = "/send-sunat")
    public ResponseEntity<?> enviarXML(@RequestParam(name = "id") String id,HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.debug("FacturaController.send-sunat...Iniciando el procesamiento");
        HashMap<String, Object> resultado = new HashMap<>();
        String mensajeValidacion = "", resultadoProceso = "EXITO";
        log.debug("FacturaController.enviarXML...Consultar Comprobante");
        ResumenDiario resumenDiario = resumenDiarioService.getResumenDiarioById(Long.parseLong(id));
        try {
            log.debug("FacturaController.enviarXML...Enviar Comprobante");
            log.debug("FacturaController.enviarXML...enviarComprobantePagoSunat Inicio");
            HashMap<String, Object> retorno = new HashMap<>();
            HashMap<String, Object> resultadoWebService = null;

//            String nombreArchivo = appConfig.getRootPath() + "VALI/" + "constantes.properties";

            if ("02".equals(resumenDiario.getIndSituacion()) || 
                "10".equals(resumenDiario.getIndSituacion()) || 
                "06".equals(resumenDiario.getIndSituacion())) {

//                Properties prop = this.comunesService.getProperties(nombreArchivo);
//
//                String urlWebService = (prop.getProperty("RUTA_SERV_CDP") != null) ? prop.getProperty("RUTA_SERV_CDP") : "XX";
                String urlWebService = (appConfig.getUrlServiceCDP() != null) ? appConfig.getUrlServiceCDP() : "XX";
                String tipoComprobante = "RA";
//                String filename = facturaElectronica.getEmpresa().getNumdoc()
//                        + "-" + String.format("%02d", Integer.parseInt(facturaElectronica.getTipo()))
//                        + "-" + facturaElectronica.getSerie()
//                        + "-" + String.format("%08d", Integer.parseInt(facturaElectronica.getNumero()));
                log.debug("FacturaController.enviarXML...Validando Conexión a Internet");
                String[] rutaUrl = urlWebService.split("\\/");
                log.debug("FacturaController.enviarXML...tokens: " + rutaUrl[2]);
                this.comunesService.validarConexion(rutaUrl[2], 443);

                log.debug("FacturaController.enviarXML...Enviando Documento");
                log.debug("FacturaController.enviarXML...urlWebService: " + urlWebService);
                log.debug("FacturaController.enviarXML...filename: " + resumenDiario.getNombreArchivo());
                log.debug("FacturaController.enviarXML...tipoComprobante: " + tipoComprobante);
                BillServiceModel res = this.comunesService.enviarArchivoSunat(urlWebService, appConfig.getRootPath(),
                        resumenDiario.getNombreArchivo(), resumenDiario.getEmpresa());
                
                return ResponseEntity.status(HttpStatus.CREATED).body(res);
//                retorno.put("resultadoWebService", resultadoWebService);
            }else {
                mensajeValidacion = "El documento se encuentra en una situación incrrecta o ya fue enviado";
                resultadoProceso = "-1";
            }

            log.debug("FacturaController.enviarXML...enviarComprobantePagoSunat Final");
            
        } catch (Exception e) {
            String mensaje = "Hubo un problema al invocar servicio SUNAT: " + e.getMessage();
            e.printStackTrace();
            log.error(mensaje);
            resumenDiario.setFechaEnvio(new Date());
            resumenDiario.setIndSituacion("06");
            resumenDiario.setObservacionEnvio(mensaje);
            resumenDiarioService.save(resumenDiario);
        }

        resultado.put("mensaje", mensajeValidacion);
        resultado.put("codigo", resultadoProceso);
        log.debug("SoftwareFacturadorController.enviarXML...Terminando el procesamiento");

        return ResponseEntity.status(HttpStatus.CREATED).body(resultado);
    }
}
