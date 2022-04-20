package com.deinsoft.efacturador3commons.parser.json;

import com.deinsoft.efacturador3commons.model.Contribuyente;
import com.deinsoft.efacturador3commons.parser.Parser;
import com.deinsoft.efacturador3commons.parser.ParserException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class JsonResumenBoletaParser
  extends JsonCpeAbstractParser
  implements Parser
{
  private static final Logger log = LoggerFactory.getLogger(JsonResumenBoletaParser.class);

  
  private static String plantillaSeleccionada = "ConvertirRBoletasXML.ftl";

  
  private HashMap<String, Object> objectoJson;

  
  private String nombreArchivo;
  
  private Contribuyente contri;

  /*     */ 
  
  public JsonResumenBoletaParser(Contribuyente contri, HashMap<String, Object> objectoJson, String nombreArchivo) {
    this.contri = contri;
    
    this.nombreArchivo = nombreArchivo;
    this.objectoJson = objectoJson;
  }

  
  public Map<String, Object> pipeToMap() throws ParserException {
    log.debug("GenerarDocumentosServiceImpl.generarResumenBajasJson...Inicio Procesamiento");
    
    List<Map<String, Object>> listaResumen = (this.objectoJson.get("resumenDiario") != null) ? (List<Map<String, Object>>)this.objectoJson.get("resumenDiario") : new ArrayList<>();

    
    String[] idArchivo = this.nombreArchivo.split("\\-");
    String idResumen = idArchivo[1] + "-" + idArchivo[2] + "-" + idArchivo[3];

    
    String identificadorFirmaSwf = "SIGN";



    
    log.debug("GenerarDocumentosServiceImpl.generarResumenDiarioJson...Detalle del Resumen");
    Iterator<Map<String, Object>> listaDetalle = listaResumen.iterator();
    List<Map<String, Object>> listaResumenDiario = new ArrayList<>();
    List<Map<String, Object>> listaTributosDocResumenTxt = new ArrayList<>();
    
    Map<String, Object> diarioResumenJson = null;
    Map<String, Object> resumenDiario = null;
    Map<String, Object> resumenGeneral = new HashMap<>();
    
    Integer linea = Integer.valueOf(0);
    while (listaDetalle.hasNext()) {
      
      linea = Integer.valueOf(linea.intValue() + 1);
      diarioResumenJson = listaDetalle.next();

      
      String fechaEmision = (diarioResumenJson.get("fecEmision") != null) ? (String)diarioResumenJson.get("fecEmision") : "";

      
      String fechaResumen = (diarioResumenJson.get("fecResumen") != null) ? (String)diarioResumenJson.get("fecResumen") : "";

      
      String tipDocResumen = (diarioResumenJson.get("tipDocResumen") != null) ? (String)diarioResumenJson.get("tipDocResumen") : "";

      
      String idDocResumen = (diarioResumenJson.get("idDocResumen") != null) ? (String)diarioResumenJson.get("idDocResumen") : "";

      
      String tipDocUsuario = (diarioResumenJson.get("tipDocUsuario") != null) ? (String)diarioResumenJson.get("tipDocUsuario") : "";

      
      String numDocUsuario = (diarioResumenJson.get("numDocUsuario") != null) ? (String)diarioResumenJson.get("numDocUsuario") : "";
      
      String tipMoneda = (diarioResumenJson.get("tipMoneda") != null) ? (String)diarioResumenJson.get("tipMoneda") : "";

      
      String totValGrabado = (diarioResumenJson.get("totValGrabado") != null) ? (String)diarioResumenJson.get("totValGrabado") : "";

      
      String totValExoneado = (diarioResumenJson.get("totValExoneado") != null) ? (String)diarioResumenJson.get("totValExoneado") : "";

      
      String totValInafecto = (diarioResumenJson.get("totValInafecto") != null) ? (String)diarioResumenJson.get("totValInafecto") : "";

      
      String totValExportado = (diarioResumenJson.get("totValExportado") != null) ? (String)diarioResumenJson.get("totValExportado") : "";

      
      String totValGratuito = (diarioResumenJson.get("totValGratuito") != null) ? (String)diarioResumenJson.get("totValGratuito") : "";

      
      String totOtroCargo = (diarioResumenJson.get("totOtroCargo") != null) ? (String)diarioResumenJson.get("totOtroCargo") : "";

      
      String totImpCpe = (diarioResumenJson.get("totImpCpe") != null) ? (String)diarioResumenJson.get("totImpCpe") : "";

      
      String tipDocModifico = (diarioResumenJson.get("tipDocModifico") != null) ? (String)diarioResumenJson.get("tipDocModifico") : "";

      
      String serDocModifico = (diarioResumenJson.get("serDocModifico") != null) ? (String)diarioResumenJson.get("serDocModifico") : "";

      
      String numDocModifico = (diarioResumenJson.get("numDocModifico") != null) ? (String)diarioResumenJson.get("numDocModifico") : "";

      
      String tipRegPercepcion = (diarioResumenJson.get("tipRegPercepcion") != null) ? (String)diarioResumenJson.get("tipRegPercepcion") : "";

      
      String porPercepcion = (diarioResumenJson.get("porPercepcion") != null) ? (String)diarioResumenJson.get("porPercepcion") : "";

      
      String monBasePercepcion = (diarioResumenJson.get("monBasePercepcion") != null) ? (String)diarioResumenJson.get("monBasePercepcion") : "";

      
      String monPercepcion = (diarioResumenJson.get("monPercepcion") != null) ? (String)diarioResumenJson.get("monPercepcion") : "";

      
      String monTotIncPercepcion = (diarioResumenJson.get("monTotIncPercepcion") != null) ? (String)diarioResumenJson.get("monTotIncPercepcion") : "";
      
      String tipEstado = (diarioResumenJson.get("tipEstado") != null) ? (String)diarioResumenJson.get("tipEstado") : "";



      
      List<Map<String, Object>> listaTributosDocRdJson = (diarioResumenJson.get("tributosDocResumen") != null) ? (List<Map<String, Object>>)diarioResumenJson.get("tributosDocResumen") : new ArrayList<>();
      
      String idLineaRd = "", ideTributoRd = "", nomTributoRd = "", codTipTributoRd = "", mtoBaseImponibleRd = "", mtoTributoRd = "";
      Iterator<Map<String, Object>> listaTributosDocResumen = listaTributosDocRdJson.iterator();
      
      Map<String, Object> tributosDocResumen = null;
      Map<String, Object> tributosDocResumenTxt = null;
      
      if (linea.intValue() == 1) {
        resumenGeneral.put("nombreComercialSwf", this.contri.getNombreComercial());
        resumenGeneral.put("razonSocialSwf", this.contri.getRazonSocial());
        resumenGeneral.put("nroRucEmisorSwf", this.contri.getNumRuc());
        
        resumenGeneral.put("tipDocuEmisorSwf", "6");
        resumenGeneral.put("fechaEmision", fechaEmision);
        resumenGeneral.put("fechaResumen", fechaResumen);
        
        resumenGeneral.put("ublVersionIdSwf", "2.0");
        resumenGeneral.put("idResumen", idResumen);
        resumenGeneral.put("CustomizationIdSwf", "1.1");
        
        resumenGeneral.put("identificadorFacturadorSwf", "Elaborador por Sistema del contribuyente");
        
        resumenGeneral.put("identificadorFirmaSwf", identificadorFirmaSwf);
        
        resumenDiario = new HashMap<>();
        resumenDiario.put("tipDocResumen", tipDocResumen);
        resumenDiario.put("idDocResumen", idDocResumen);
        resumenDiario.put("tipDocUsuario", tipDocUsuario);
        resumenDiario.put("numDocUsuario", numDocUsuario);
        resumenDiario.put("moneda", tipMoneda);
        resumenDiario.put("totValGrabado", totValGrabado);
        resumenDiario.put("totValExoneado", totValExoneado);
        resumenDiario.put("totValInafecto", totValInafecto);
        resumenDiario.put("totValExportado", totValExportado);
        resumenDiario.put("totValGratuito", totValGratuito);
        resumenDiario.put("totOtroCargo", totOtroCargo);
        
        resumenDiario.put("totImpCpe", totImpCpe);
        resumenDiario.put("tipDocModifico", tipDocModifico);
        resumenDiario.put("serDocModifico", serDocModifico);
        resumenDiario.put("numDocModifico", numDocModifico);
        resumenDiario.put("tipRegPercepcion", tipRegPercepcion);
        resumenDiario.put("porPercepcion", porPercepcion);
        resumenDiario.put("monBasePercepcion", monBasePercepcion);
        resumenDiario.put("monPercepcion", monPercepcion);
        resumenDiario.put("monTotIncPercepcion", monTotIncPercepcion);
        resumenDiario.put("tipEstado", tipEstado);
        resumenDiario.put("linea", linea);

        
        while (listaTributosDocResumen.hasNext()) {
          tributosDocResumen = listaTributosDocResumen.next();
          idLineaRd = (tributosDocResumen.get("idLineaRd") != null) ? (String)tributosDocResumen.get("idLineaRd") : "";
          ideTributoRd = (tributosDocResumen.get("ideTributoRd") != null) ? (String)tributosDocResumen.get("ideTributoRd") : "";
          nomTributoRd = (tributosDocResumen.get("nomTributoRd") != null) ? (String)tributosDocResumen.get("nomTributoRd") : "";
          codTipTributoRd = (tributosDocResumen.get("codTipTributoRd") != null) ? (String)tributosDocResumen.get("codTipTributoRd") : "";
          mtoBaseImponibleRd = (tributosDocResumen.get("mtoBaseImponibleRd") != null) ? (String)tributosDocResumen.get("mtoBaseImponibleRd") : "";
          mtoTributoRd = (tributosDocResumen.get("mtoTributoRd") != null) ? (String)tributosDocResumen.get("mtoTributoRd") : "";

          
          tributosDocResumenTxt = new HashMap<>();
          tributosDocResumenTxt.put("idLineaRd", idLineaRd);
          tributosDocResumenTxt.put("ideTributoRd", ideTributoRd);
          tributosDocResumenTxt.put("nomTributoRd", nomTributoRd);
          tributosDocResumenTxt.put("codTipTributoRd", codTipTributoRd);
          tributosDocResumenTxt.put("mtoBaseImponibleRd", mtoBaseImponibleRd);
          tributosDocResumenTxt.put("mtoTributoRd", mtoTributoRd);
          
          listaTributosDocResumenTxt.add(tributosDocResumenTxt);
        } 
        
        resumenGeneral.put("listaTributosDocResumen", listaTributosDocResumenTxt);
      }
      else {
        
        resumenDiario = new HashMap<>();
        resumenDiario.put("tipDocResumen", tipDocResumen);
        resumenDiario.put("idDocResumen", idDocResumen);
        resumenDiario.put("tipDocUsuario", tipDocUsuario);
        resumenDiario.put("numDocUsuario", numDocUsuario);
        resumenDiario.put("moneda", tipMoneda);
        resumenDiario.put("totValGrabado", totValGrabado);
        resumenDiario.put("totValExoneado", totValExoneado);
        resumenDiario.put("totValInafecto", totValInafecto);
        resumenDiario.put("totValExportado", totValExportado);
        resumenDiario.put("totValGratuito", totValGratuito);
        resumenDiario.put("totOtroCargo", totOtroCargo);
        resumenDiario.put("totImpCpe", totImpCpe);
        resumenDiario.put("tipDocModifico", tipDocModifico);
        resumenDiario.put("serDocModifico", serDocModifico);
        resumenDiario.put("numDocModifico", numDocModifico);
        resumenDiario.put("tipRegPercepcion", tipRegPercepcion);
        resumenDiario.put("porPercepcion", porPercepcion);
        resumenDiario.put("monBasePercepcion", monBasePercepcion);
        resumenDiario.put("monPercepcion", monPercepcion);
        resumenDiario.put("monTotIncPercepcion", monTotIncPercepcion);
        resumenDiario.put("tipEstado", tipEstado);
        resumenDiario.put("linea", linea);
        
        while (listaTributosDocResumen.hasNext()) {
          tributosDocResumen = listaTributosDocResumen.next();
          idLineaRd = (tributosDocResumen.get("idLineaRd") != null) ? (String)tributosDocResumen.get("idLineaRd") : "";
          ideTributoRd = (tributosDocResumen.get("ideTributoRd") != null) ? (String)tributosDocResumen.get("ideTributoRd") : "";
          nomTributoRd = (tributosDocResumen.get("nomTributoRd") != null) ? (String)tributosDocResumen.get("nomTributoRd") : "";
          codTipTributoRd = (tributosDocResumen.get("codTipTributoRd") != null) ? (String)tributosDocResumen.get("codTipTributoRd") : "";
          mtoBaseImponibleRd = (tributosDocResumen.get("mtoBaseImponibleRd") != null) ? (String)tributosDocResumen.get("mtoBaseImponibleRd") : "";
          mtoTributoRd = (tributosDocResumen.get("mtoTributoRd") != null) ? (String)tributosDocResumen.get("mtoTributoRd") : "";

          
          tributosDocResumenTxt = new HashMap<>();
          tributosDocResumenTxt.put("idLineaRd", idLineaRd);
          tributosDocResumenTxt.put("ideTributoRd", ideTributoRd);
          tributosDocResumenTxt.put("nomTributoRd", nomTributoRd);
          tributosDocResumenTxt.put("codTipTributoRd", codTipTributoRd);
          tributosDocResumenTxt.put("mtoBaseImponibleRd", mtoBaseImponibleRd);
          tributosDocResumenTxt.put("mtoTributoRd", mtoTributoRd);
          
          listaTributosDocResumenTxt.add(tributosDocResumenTxt);
        } 
        
        resumenGeneral.put("listaTributosDocResumen", listaTributosDocResumenTxt);
      } 
      
      listaResumenDiario.add(resumenDiario);
    } 

    
    resumenGeneral.put("listaResumen", listaResumenDiario);


    
    log.debug("GenerarDocumentosServiceImpl.generarResumenDiarioJson...Fin Procesamiento");
    
    return resumenGeneral;
  }

  
  public byte[] parse(String templatesPath) throws ParserException {
    return parse(templatesPath, plantillaSeleccionada);
  }
}
