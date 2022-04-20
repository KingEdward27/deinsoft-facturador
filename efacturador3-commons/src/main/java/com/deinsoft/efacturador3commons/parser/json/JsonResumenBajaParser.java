package com.deinsoft.efacturador3commons.parser.json;

import com.deinsoft.efacturador3commons.model.Contribuyente;
import com.deinsoft.efacturador3commons.parser.Parser;
import com.deinsoft.efacturador3commons.parser.ParserException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class JsonResumenBajaParser
  extends JsonCpeAbstractParser
  implements Parser
{
  private static final Logger log = LoggerFactory.getLogger(JsonResumenBajaParser.class);

  
  private static String plantillaSeleccionada = "ConvertirRBajasXML.ftl";

  
  private HashMap<String, Object> objectoJson;

  
  private String nombreArchivo;
  
  private Contribuyente contri;

  /*     */ 
  
  public JsonResumenBajaParser(Contribuyente contri, HashMap<String, Object> objectoJson, String nombreArchivo) {
    this.contri = contri;
    
    this.nombreArchivo = nombreArchivo;
    this.objectoJson = objectoJson;
  }

  
  public Map<String, Object> pipeToMap() throws ParserException {
    log.debug("GenerarDocumentosServiceImpl.generarResumenBajasJson...Inicio Procesamiento");
    
    List<Map<String, Object>> listaResumen = (this.objectoJson.get("resumenBajas") != null) ? (List<Map<String, Object>>)this.objectoJson.get("resumenBajas") : new ArrayList<>();



    
    String[] idArchivo = this.nombreArchivo.split("\\-");
    String idComunicacion = idArchivo[1] + "-" + idArchivo[2] + "-" + idArchivo[3];

    
    String identificadorFirmaSwf = "SIGN";
    Random calcularRnd = new Random();
    Integer codigoFacturadorSwf = Integer.valueOf((int)(calcularRnd.nextDouble() * 1000000.0D));

    
    log.debug("GenerarDocumentosServiceImpl.generarResumenBajasJson...Detalle del Resumen");
    Iterator<Map<String, Object>> listaDetalle = listaResumen.iterator();
    List<Map<String, Object>> listaResumenBajas = new ArrayList<>();
    String fechaDocumentoBaja = "", fechaComunicacionBaja = "", tipoDocumento = "", serieNumeroDocumento = "";
    String motivoBajaDocumento = "";
    Map<String, Object> bajaResumenJson = null;
    Map<String, Object> resumenBajas = null;
    Map<String, Object> resumenGeneral = new HashMap<>();
    Integer linea = Integer.valueOf(0);
    while (listaDetalle.hasNext()) {
      
      linea = Integer.valueOf(linea.intValue() + 1);
      bajaResumenJson = listaDetalle.next();

      
      fechaDocumentoBaja = (bajaResumenJson.get("fecGeneracion") != null) ? (String)bajaResumenJson.get("fecGeneracion") : "";

      
      fechaComunicacionBaja = (bajaResumenJson.get("fecComunicacion") != null) ? (String)bajaResumenJson.get("fecComunicacion") : "";
      
      tipoDocumento = (bajaResumenJson.get("tipDocBaja") != null) ? (String)bajaResumenJson.get("tipDocBaja") : "";
      
      serieNumeroDocumento = (bajaResumenJson.get("numDocBaja") != null) ? (String)bajaResumenJson.get("numDocBaja") : "";

      
      motivoBajaDocumento = (bajaResumenJson.get("desMotivoBaja") != null) ? (String)bajaResumenJson.get("desMotivoBaja") : "";

      
      String[] nroDocumento = serieNumeroDocumento.split("\\-");
      
      if (linea.intValue() == 1) {
        resumenGeneral.put("nombreComercialSwf", this.contri.getNombreComercial());
        resumenGeneral.put("razonSocialSwf", this.contri.getRazonSocial());
        resumenGeneral.put("nroRucEmisorSwf", this.contri.getNumRuc());
        resumenGeneral.put("tipDocuEmisorSwf", "6");
        resumenGeneral.put("fechaDocumentoBaja", fechaDocumentoBaja);
        resumenGeneral.put("fechaComunicacioBaja", fechaComunicacionBaja);
        
        resumenGeneral.put("ublVersionIdSwf", "2.0");
        resumenGeneral.put("idComunicacion", idComunicacion);
        resumenGeneral.put("CustomizationIdSwf", "1.0");
        
        resumenGeneral.put("identificadorFacturadorSwf", "Elaborador por Sistema del contribuyente");
        resumenGeneral.put("codigoFacturadorSwf", codigoFacturadorSwf.toString());
        resumenGeneral.put("identificadorFirmaSwf", identificadorFirmaSwf);
        
        resumenBajas = new HashMap<>();
        resumenBajas.put("tipoDocumentoBaja", tipoDocumento);
        resumenBajas.put("serieDocumentoBaja", nroDocumento[0]);
        resumenBajas.put("nroDocumentoBaja", nroDocumento[1]);
        resumenBajas.put("motivoBajaDocumento", motivoBajaDocumento);
        resumenBajas.put("linea", linea);
      }
      else {
        
        resumenBajas = new HashMap<>();
        resumenBajas.put("tipoDocumentoBaja", tipoDocumento);
        resumenBajas.put("serieDocumentoBaja", nroDocumento[0]);
        resumenBajas.put("nroDocumentoBaja", nroDocumento[1]);
        resumenBajas.put("motivoBajaDocumento", motivoBajaDocumento);
        resumenBajas.put("linea", linea);
      } 
      
      listaResumenBajas.add(resumenBajas);
    } 

    
    resumenGeneral.put("listaResumen", listaResumenBajas);
    
    log.debug("GenerarDocumentosServiceImpl.generarResumenBajasJson...Fin Procesamiento");
    
    return resumenGeneral;
  }

  
  public byte[] parse(String templatesPath) throws ParserException {
    return parse(templatesPath, plantillaSeleccionada);
  }
}