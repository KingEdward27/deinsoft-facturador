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



public class JsonResumenReversionParser
  extends JsonCpeAbstractParser
  implements Parser
{
  private static final Logger log = LoggerFactory.getLogger(JsonResumenReversionParser.class);

  
  private static String plantillaSeleccionada = "ConvertirRReversionXML.ftl";

  
  private HashMap<String, Object> objectoJson;
  
  private String nombreArchivo;
  
  private Contribuyente contri;

  /*     */ 
  
  public JsonResumenReversionParser(Contribuyente contri, HashMap<String, Object> objectoJson, String nombreArchivo) {
    this.contri = contri;
    
    this.nombreArchivo = nombreArchivo;
    this.objectoJson = objectoJson;
  }

  
  public Map<String, Object> pipeToMap() throws ParserException {
    log.debug("GenerarDocumentosServiceImpl.generarResumenReversionJson...Inicio Procesamiento");
    
    List<Map<String, Object>> listaResumen = (this.objectoJson.get("resumenReversion") != null) ? (List<Map<String, Object>>)this.objectoJson.get("resumenReversion") : new ArrayList<>();



    
    String[] idArchivo = this.nombreArchivo.split("\\-");
    String idComunicacion = idArchivo[1] + "-" + idArchivo[2] + "-" + idArchivo[3];

    
    String identificadorFirmaSwf = "SIGN";
    Random calcularRnd = new Random();
    Integer codigoFacturadorSwf = Integer.valueOf((int)(calcularRnd.nextDouble() * 1000000.0D));

    
    log.debug("GenerarDocumentosServiceImpl.generarResumenBajasJson...Detalle del Resumen");
    Iterator<Map<String, Object>> listaDetalle = listaResumen.iterator();
    List<Map<String, Object>> listaResumenReversion = new ArrayList<>();
    String fechaDocumentoReversion = "", fechaComunicacionReversion = "", tipoDocumento = "";
    String serieNumeroDocumento = "", motivoReversionDocumento = "";
    Map<String, Object> reversionResumenJson = null;
    Map<String, Object> resumenReversion = null;
    Map<String, Object> resumenGeneral = new HashMap<>();
    Integer linea = Integer.valueOf(0);
    while (listaDetalle.hasNext()) {
      
      linea = Integer.valueOf(linea.intValue() + 1);
      reversionResumenJson = listaDetalle.next();

      
      fechaDocumentoReversion = (reversionResumenJson.get("fecGeneracion") != null) ? (String)reversionResumenJson.get("fecGeneracion") : "";

      
      fechaComunicacionReversion = (reversionResumenJson.get("fecComunicacion") != null) ? (String)reversionResumenJson.get("fecComunicacion") : "";

      
      tipoDocumento = (reversionResumenJson.get("tipDocBaja") != null) ? (String)reversionResumenJson.get("tipDocBaja") : "";

      
      serieNumeroDocumento = (reversionResumenJson.get("numDocBaja") != null) ? (String)reversionResumenJson.get("numDocBaja") : "";

      
      motivoReversionDocumento = (reversionResumenJson.get("desMotivoBaja") != null) ? (String)reversionResumenJson.get("desMotivoBaja") : "";

      
      String[] nroDocumento = serieNumeroDocumento.split("\\-");
      
      if (linea.intValue() == 1) {
        
        resumenGeneral.put("nombreComercialSwf", this.contri.getNombreComercial());
        resumenGeneral.put("razonSocialSwf", this.contri.getRazonSocial());
        resumenGeneral.put("nroRucEmisorSwf", this.contri.getNumRuc());
        
        resumenGeneral.put("tipDocuEmisorSwf", "6");
        resumenGeneral.put("fechaDocumentoBaja", fechaDocumentoReversion);
        resumenGeneral.put("fechaComunicacioBaja", fechaComunicacionReversion);
        
        resumenGeneral.put("ublVersionIdSwf", "2.0");
        resumenGeneral.put("idComunicacion", idComunicacion);
        resumenGeneral.put("CustomizationIdSwf", "1.0");
        
        resumenGeneral.put("identificadorFacturadorSwf", "Elaborador por Sistema del contribuyente");
        resumenGeneral.put("codigoFacturadorSwf", codigoFacturadorSwf.toString());
        resumenGeneral.put("identificadorFirmaSwf", identificadorFirmaSwf);
        
        resumenReversion = new HashMap<>();
        resumenReversion.put("tipoDocumentoBaja", tipoDocumento);
        resumenReversion.put("serieDocumentoBaja", nroDocumento[0]);
        resumenReversion.put("nroDocumentoBaja", nroDocumento[1]);
        resumenReversion.put("motivoBajaDocumento", motivoReversionDocumento);
        resumenReversion.put("linea", linea);
      }
      else {
        
        resumenReversion = new HashMap<>();
        resumenReversion.put("tipoDocumentoBaja", tipoDocumento);
        resumenReversion.put("serieDocumentoBaja", nroDocumento[0]);
        resumenReversion.put("nroDocumentoBaja", nroDocumento[1]);
        resumenReversion.put("motivoBajaDocumento", motivoReversionDocumento);
        resumenReversion.put("linea", linea);
      } 
      
      listaResumenReversion.add(resumenReversion);
    } 

    
    resumenGeneral.put("listaResumen", listaResumenReversion);
    
    log.debug("GenerarDocumentosServiceImpl.generarResumenReversionJson...Fin Procesamiento");
    
    return resumenGeneral;
  }

  
  public byte[] parse(String templatesPath) throws ParserException {
    return parse(templatesPath, plantillaSeleccionada);
  }
}