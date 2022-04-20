/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3commons.service.impl;

import com.deinsoft.efacturador3commons.bean.MailBean;
import com.deinsoft.efacturador3commons.bean.ResumenBajaBean;
import com.deinsoft.efacturador3commons.bean.ResumenBajaBeanDet;
import com.deinsoft.efacturador3commons.config.AppConfig;
import com.deinsoft.efacturador3commons.config.XsltCpePath;
import com.deinsoft.efacturador3commons.model.Empresa;
import com.deinsoft.efacturador3commons.model.FacturaElectronica;
import com.deinsoft.efacturador3commons.model.ResumenBaja;
import com.deinsoft.efacturador3commons.model.ResumenBajaDet;
import com.deinsoft.efacturador3commons.repository.ResumenBajaRepository;
import com.deinsoft.efacturador3commons.service.GenerarDocumentosService;
import com.deinsoft.efacturador3commons.util.CertificadoFacturador;
import com.deinsoft.efacturador3commons.service.ResumenBajaService;
//import com.deinsoft.efacturador3commons.soap.gencdp.ExceptionDetail;
//import com.deinsoft.efacturador3commons.soap.gencdp.TransferirArchivoException;
import com.deinsoft.efacturador3commons.util.Catalogos;
import com.deinsoft.efacturador3commons.util.Constantes;
import com.deinsoft.efacturador3commons.util.FacturadorUtil;
import com.deinsoft.efacturador3commons.util.Impresion;
import com.deinsoft.efacturador3commons.util.SendMail;
import com.deinsoft.efacturador3commons.validator.XsdCpeValidator;
import com.deinsoft.efacturador3commons.validator.XsltCpeValidator;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author EDWARD-PC
 */
@Service
public class ResumenBajaServiceImpl implements ResumenBajaService {

    private static final Logger log = LoggerFactory.getLogger(ResumenBajaServiceImpl.class);

    @Autowired
    private GenerarDocumentosService generarDocumentosService;

    @Autowired
    ResumenBajaRepository resumenBajaRepository;

    @Autowired
    AppConfig appConfig;

    @Autowired
    private XsltCpePath xsltCpePath;

    @Override
    public ResumenBaja getResumenBajaById(long id) {
        return resumenBajaRepository.getById(id);
    }

    @Override
    public List<ResumenBaja> getResumenBajas() {
        return resumenBajaRepository.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ResumenBaja> getByNombreArchivo(String nombreArchivo) {
        return resumenBajaRepository.findByNombreArchivo(nombreArchivo); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResumenBaja save(ResumenBaja e) {
        return resumenBajaRepository.save(e);
    }

    @Override
    public List<ResumenBaja> saveAll(List<ResumenBaja> e) {
        return resumenBajaRepository.saveAll(e);
    }

    public ResumenBaja toResumenBajaModel(ResumenBajaBean resumenBajaBean, Empresa empresa) throws ParseException {
        ResumenBaja cab = new ResumenBaja();
        List<ResumenBaja> list = new ArrayList<>();
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(resumenBajaBean.getFechaComunicacionBaja());
        Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(resumenBajaBean.getFechaDocumentoBaja());
        cab.setFechaComunicacionBaja(date1);
        cab.setFechaDocumentoBaja(date2);
        List<ResumenBajaDet> listDet = new ArrayList<>();
        for (ResumenBajaBeanDet resumenBajaBeanDet : resumenBajaBean.getLista_comprobantes()) {
            ResumenBajaDet e = new ResumenBajaDet();
            e.setTipoDocumentoBaja(resumenBajaBeanDet.getTipoDocumentoBaja());
            String[] serieNro = resumenBajaBeanDet.getNroDocumentoBaja().split("-");
            e.setSerieDocumentoBaja(serieNro[0]);
            e.setNroDocumentoBaja(serieNro[1]);
            e.setMotivoBajaDocumento(resumenBajaBeanDet.getMotivoBajaDocumento());

            listDet.add(e);
        }
        cab.setListResumenBajaDet(listDet);
        cab.setIndSituacion("01");
        cab.getListResumenBajaDet().stream().forEach(item -> {
            cab.addResumenBajaDet(item);
        });
        int total = 1;
        List<ResumenBaja> listT = getResumenBajas();
        if (listT != null) {
            total = listT.size() + 1;
        }
        String dateString = resumenBajaBean.getFechaComunicacionBaja().substring(6, 10)
                + resumenBajaBean.getFechaComunicacionBaja().substring(3, 5)
                + resumenBajaBean.getFechaComunicacionBaja().substring(0, 2);
        cab.setNombreArchivo(empresa.getNumdoc() + "-" + Constantes.CONSTANTE_TIPO_DOCUMENTO_RBAJAS + "-" + dateString + "-" + total);
        return cab;
    }

    @Override
    @Transactional
    public Map<String, Object> generarComprobantePagoSunat(String rootpath, ResumenBaja resumenBaja)  {
        XsltCpeValidator xsltCpeValidator = new XsltCpeValidator(this.xsltCpePath);
        XsdCpeValidator xsdCpeValidator = new XsdCpeValidator(this.xsltCpePath);
        Map<String, Object> retorno = new HashMap<>();
        ResumenBaja resumenBajaResult = null;
        long ticket = Calendar.getInstance().getTimeInMillis();
        retorno.put("ticketOperacion", ticket);
        try {
//            String retorno = "01";

            String nomFile = resumenBaja.getNombreArchivo();
            String tipo = Constantes.CONSTANTE_TIPO_DOCUMENTO_RBAJAS;
            resumenBajaResult = save(resumenBaja);
            if (Constantes.CONSTANTE_SITUACION_POR_GENERAR_XML.equals(resumenBaja.getIndSituacion())
                    || Constantes.CONSTANTE_SITUACION_CON_ERRORES.equals(resumenBaja.getIndSituacion())
                    || Constantes.CONSTANTE_SITUACION_XML_VALIDAR.equals(resumenBaja.getIndSituacion())
                    || Constantes.CONSTANTE_SITUACION_ENVIADO_RECHAZADO.equals(resumenBaja.getIndSituacion())
                    || Constantes.CONSTANTE_SITUACION_ENVIADO_ANULADO.equals(resumenBaja.getIndSituacion())) {
                log.debug("BandejaDocumentosServiceImpl.generarComprobantePagoSunat...tipoComprobante: " + tipo);

                this.generarDocumentosService.formatoPlantillaXmlResumenBaja(rootpath, resumenBaja);

                retorno.putAll(this.generarDocumentosService.firmarXml(rootpath, resumenBaja.getEmpresa(), nomFile));

                xsdCpeValidator.validarSchemaXML(rootpath, tipo, rootpath + "/" + resumenBaja.getEmpresa().getNumdoc() + "/PARSE/" + nomFile + ".xml");

                xsltCpeValidator.validarXMLYComprimir(rootpath, resumenBaja.getEmpresa(), tipo, rootpath + "/" + resumenBaja.getEmpresa().getNumdoc() + "/PARSE/", nomFile);

                resumenBaja.setXmlHash(retorno.get("xmlHash").toString());
//                String tempDescripcionPluralMoneda = "SOLES";
//                ByteArrayInputStream stream = Impresion.Imprimir(rootpath + "TEMP/", 1, documento, tempDescripcionPluralMoneda);
//                int n = stream.available();
//                byte[] bytes = new byte[n];
//                stream.read(bytes, 0, n);

//                retorno.put("pdfBase64", bytes);
//                FileUtils.writeByteArrayToFile(new File(rootpath + "TEMP/" + nomFile + ".pdf"), bytes);
//                
//                if (!appConfig.getEnvironment().equals("PRODUCTION")) {
//                    FileUtils.writeByteArrayToFile(new File("D:/report.pdf"), bytes);
//                }
//                String[] adjuntos = {rootpath + "/" + resumenBaja.getEmpresa().getNumdoc() + "/PARSE/" + nomFile + ".xml",
//                    rootpath + "TEMP/" + nomFile + ".pdf"};
                resumenBajaResult.setFechaGenXml(new Date());
                resumenBajaResult.setIndSituacion(Constantes.CONSTANTE_SITUACION_XML_GENERADO);
                resumenBajaResult.setTicketOperacion(ticket);
                save(resumenBajaResult);

            }
            return retorno;
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
            return null;
//            ExceptionDetail exceptionDetail = new ExceptionDetail();
//            exceptionDetail.setMessage(e.getMessage());
//            throw new TransferirArchivoException(e.getMessage(), exceptionDetail);
        }
    }
    @Override
    public String validarDocumento(ResumenBajaBean resumenBaja) {
        for (ResumenBajaBeanDet object : resumenBaja.getLista_comprobantes()) {
            if (object.getTipoDocumentoBaja().equals("03")) {
                return "El valor del tipo de documento es invalido";
            }
        }
        return "";
    }
}
