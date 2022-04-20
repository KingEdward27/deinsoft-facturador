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

public class JsonRetencionParser
  extends JsonCpeAbstractParser
  implements Parser
{
  private static final Logger log = LoggerFactory.getLogger(JsonRetencionParser.class);

  
  private static String plantillaSeleccionada = "ConvertirRetencionXML.ftl";

  
  private HashMap<String, Object> objectoJson;

  
  private String nombreArchivo;
  
  private Contribuyente contri;

  /*     */ 
  
  public JsonRetencionParser(Contribuyente contri, HashMap<String, Object> objectoJson, String nombreArchivo) {
    this.contri = contri;
    
    this.nombreArchivo = nombreArchivo;
    this.objectoJson = objectoJson;
  }




  
  public Map<String, Object> pipeToMap() throws ParserException {
    Map<String, Object> cabecera = (this.objectoJson.get("cabecera") != null) ? (Map<String, Object>)this.objectoJson.get("cabecera") : new HashMap<>();
    
    String fecEmision = (cabecera.get("fecEmision") != null) ? (String)cabecera.get("fecEmision") : "";
    
    String nroDocIdeReceptor = (cabecera.get("nroDocIdeReceptor") != null) ? (String)cabecera.get("nroDocIdeReceptor") : "";

    
    String tipDocIdeReceptor = (cabecera.get("tipDocIdeReceptor") != null) ? (String)cabecera.get("tipDocIdeReceptor") : "";

    
    String desNomComReceptor = (cabecera.get("desNomComReceptor") != null) ? (String)cabecera.get("desNomComReceptor") : "";

    
    String desUbiReceptor = (cabecera.get("desUbiReceptor") != null) ? (String)cabecera.get("desUbiReceptor") : "";
    String desDirReceptor = (cabecera.get("desDirReceptor") != null) ? (String)cabecera.get("desDirReceptor") : "";
    String desUrbReceptor = (cabecera.get("desUrbReceptor") != null) ? (String)cabecera.get("desUrbReceptor") : "";
    String desDepReceptor = (cabecera.get("desDepReceptor") != null) ? (String)cabecera.get("desDepReceptor") : "";
    String desProReceptor = (cabecera.get("desProReceptor") != null) ? (String)cabecera.get("desProReceptor") : "";
    String desDisReceptor = (cabecera.get("desDisReceptor") != null) ? (String)cabecera.get("desDisReceptor") : "";
    String codPaisReceptor = (cabecera.get("codPaisReceptor") != null) ? (String)cabecera.get("codPaisReceptor") : "";

    
    String rznSocialReceptor = (cabecera.get("rznSocialReceptor") != null) ? (String)cabecera.get("rznSocialReceptor") : "";

    
    String codRegRetencion = (cabecera.get("codRegRetencion") != null) ? (String)cabecera.get("codRegRetencion") : "";
    
    String tasRetencion = (cabecera.get("tasRetencion") != null) ? (String)cabecera.get("tasRetencion") : "";
    String desObsRetencion = (cabecera.get("desObsRetencion") != null) ? (String)cabecera.get("desObsRetencion") : "";
    
    String mtoTotRetencion = (cabecera.get("mtoTotRetencion") != null) ? (String)cabecera.get("mtoTotRetencion") : "0.00";
    
    String tipMonRetencion = (cabecera.get("tipMonRetencion") != null) ? (String)cabecera.get("tipMonRetencion") : "";

    
    String mtoImpTotPagRetencion = (cabecera.get("mtoImpTotPagRetencion") != null) ? (String)cabecera.get("mtoImpTotPagRetencion") : "0.00";

    
    String tipMonImpTotPagRetencion = (cabecera.get("tipMonImpTotPagRetencion") != null) ? (String)cabecera.get("tipMonImpTotPagRetencion") : "";

    
    String mtoImpTotDocRelacionado = "0.00", mtoPagDocRelacionado = "0.00", mtoRetDocRelacionado = "0.00";
    String mtoTotPagNetoDocRelacionado = "0.00", facTipCambio = "00.000000";
    String tipDocRelacionado = "", nroDocRelacionado = "", fecEmiDocRelacionado = "", tipMonDocRelacionado = "";
    String fecPagDocRelacionado = "", nroPagDocRelacionado = "", tipMonPagDocRelacionado = "";
    String tipMonRetDocRelacionado = "", fecRetDocRelacionado = "", tipMonTotPagNetoDocRelacionado = "";
    String tipMonRefTipCambio = "", tipMonObjTipCambio = "", fecTipCambio = "";

    
    List<Map<String, Object>> detalle = (this.objectoJson.get("detalle") != null) ? (List<Map<String, Object>>)this.objectoJson.get("detalle") : new ArrayList<>();

    
    String[] idArchivo = this.nombreArchivo.split("\\-");
    
    String identificadorFirmaSwf = "SIGN";
    Random calcularRnd = new Random();
    Integer codigoFacturadorSwf = Integer.valueOf((int)(calcularRnd.nextDouble() * 1000000.0D));


    
    log.debug("GenerarDocumentosServiceImpl.generarFacturaJson...Cabecera de Factura");
    Map<String, Object> retencion = null;
    
    retencion = new HashMap<>();
    retencion.put("fecEmision", fecEmision);
    retencion.put("nroDocIdeReceptor", nroDocIdeReceptor);
    retencion.put("tipDocIdeReceptor", tipDocIdeReceptor);
    retencion.put("desNomComReceptor", desNomComReceptor);
    
    retencion.put("desUbiReceptor", desUbiReceptor);
    retencion.put("desDirReceptor", desDirReceptor);
    retencion.put("desUrbReceptor", desUrbReceptor);
    retencion.put("desDepReceptor", desDepReceptor);
    retencion.put("desProReceptor", desProReceptor);
    retencion.put("desDisReceptor", desDisReceptor);
    retencion.put("codPaisReceptor", codPaisReceptor);
    retencion.put("rznSocialReceptor", rznSocialReceptor);
    
    retencion.put("codRegRetencion", codRegRetencion);
    retencion.put("tasRetencion", tasRetencion);
    retencion.put("desObsRetencion", desObsRetencion);
    retencion.put("mtoTotRetencion", mtoTotRetencion);
    retencion.put("tipMonRetencion", tipMonRetencion);
    retencion.put("mtoImpTotPagRetencion", mtoImpTotPagRetencion);
    retencion.put("tipMonImpTotPagRetencion", tipMonImpTotPagRetencion);

    
    retencion.put("ublVersionIdSwf", "2.0");
    retencion.put("CustomizationIdSwf", "1.0");
    retencion.put("nroCdpSwf", idArchivo[2] + "-" + idArchivo[3]);
    retencion.put("tipCdpSwf", idArchivo[1]);
    
    retencion.put("nroRucEmisorSwf", this.contri.getNumRuc());
    retencion.put("tipDocuEmisorSwf", "6");
    retencion.put("nombreComercialSwf", this.contri.getNombreComercial());
    retencion.put("razonSocialSwf", this.contri.getRazonSocial());
    retencion.put("ubigeoDomFiscalSwf", this.contri.getDireccion().getUbigeo());
    retencion.put("direccionDomFiscalSwf", this.contri.getDireccion().getDireccion());
    retencion.put("deparSwf", this.contri.getDireccion().getDepar());
    retencion.put("provinSwf", this.contri.getDireccion().getProvin());
    retencion.put("distrSwf", this.contri.getDireccion().getDistr());
    retencion.put("urbanizaSwf", this.contri.getDireccion().getUrbaniza());
    
    retencion.put("paisDomFiscalSwf", "PE");
    retencion.put("tipoCodigoMonedaSwf", "01");
    
    retencion.put("identificadorFacturadorSwf", "Elaborado por Sistema de Emision Electronica Facturador SUNAT (SEE-SFS) 1.3.2");
    retencion.put("codigoFacturadorSwf", codigoFacturadorSwf.toString());
    retencion.put("identificadorFirmaSwf", identificadorFirmaSwf);
    
    log.debug("GenerarDocumentosServiceImpl.generarFacturaJson...Detalle de Factura");
    Iterator<Map<String, Object>> listaDetalle = detalle.iterator();
    List<Map<String, Object>> listaDetaRetencion = new ArrayList<>();
    Map<String, Object> detalleRetencion = null;
    Map<String, Object> detalleLista = null;
    Integer linea = new Integer(0);
    while (listaDetalle.hasNext()) {
      detalleLista = listaDetalle.next();
      
      tipDocRelacionado = (detalleLista.get("tipDocRelacionado") != null) ? (String)detalleLista.get("tipDocRelacionado") : "";

      
      nroDocRelacionado = (detalleLista.get("nroDocRelacionado") != null) ? (String)detalleLista.get("nroDocRelacionado") : "";

      
      fecEmiDocRelacionado = (detalleLista.get("fecEmiDocRelacionado") != null) ? (String)detalleLista.get("fecEmiDocRelacionado") : "";

      
      mtoImpTotDocRelacionado = (detalleLista.get("mtoImpTotDocRelacionado") != null) ? (String)detalleLista.get("mtoImpTotDocRelacionado") : "0.00";

      
      tipMonDocRelacionado = (detalleLista.get("tipMonDocRelacionado") != null) ? (String)detalleLista.get("tipMonDocRelacionado") : "";

      
      fecPagDocRelacionado = (detalleLista.get("fecPagDocRelacionado") != null) ? (String)detalleLista.get("fecPagDocRelacionado") : "";

      
      nroPagDocRelacionado = (detalleLista.get("nroPagDocRelacionado") != null) ? (String)detalleLista.get("nroPagDocRelacionado") : "";

      
      mtoPagDocRelacionado = (detalleLista.get("mtoPagDocRelacionado") != null) ? (String)detalleLista.get("mtoPagDocRelacionado") : "0.00";

      
      tipMonPagDocRelacionado = (detalleLista.get("tipMonPagDocRelacionado") != null) ? (String)detalleLista.get("tipMonPagDocRelacionado") : "";


      
      mtoRetDocRelacionado = (detalleLista.get("mtoRetDocRelacionado") != null) ? (String)detalleLista.get("mtoRetDocRelacionado") : "0.00";

      
      tipMonRetDocRelacionado = (detalleLista.get("tipMonRetDocRelacionado") != null) ? (String)detalleLista.get("tipMonRetDocRelacionado") : "";

      
      fecRetDocRelacionado = (detalleLista.get("fecRetDocRelacionado") != null) ? (String)detalleLista.get("fecRetDocRelacionado") : "";

      
      mtoTotPagNetoDocRelacionado = (detalleLista.get("mtoTotPagNetoDocRelacionado") != null) ? (String)detalleLista.get("mtoTotPagNetoDocRelacionado") : "0.00";

      
      tipMonTotPagNetoDocRelacionado = (detalleLista.get("tipMonTotPagNetoDocRelacionado") != null) ? (String)detalleLista.get("tipMonTotPagNetoDocRelacionado") : "";


      
      tipMonRefTipCambio = (detalleLista.get("tipMonRefTipCambio") != null) ? (String)detalleLista.get("tipMonRefTipCambio") : "";

      
      tipMonObjTipCambio = (detalleLista.get("tipMonObjTipCambio") != null) ? (String)detalleLista.get("tipMonObjTipCambio") : "PEN";
      
      facTipCambio = (detalleLista.get("facTipCambio") != null) ? (String)detalleLista.get("facTipCambio") : "";
      fecTipCambio = (detalleLista.get("fecTipCambio") != null) ? (String)detalleLista.get("fecTipCambio") : "";

      
      linea = Integer.valueOf(linea.intValue() + 1);
      detalleRetencion = new HashMap<>();
      detalleRetencion.put("tipDocRelacionado", tipDocRelacionado);
      detalleRetencion.put("nroDocRelacionado", nroDocRelacionado);
      detalleRetencion.put("fecEmiDocRelacionado", fecEmiDocRelacionado);
      detalleRetencion.put("mtoImpTotDocRelacionado", mtoImpTotDocRelacionado);
      detalleRetencion.put("tipMonDocRelacionado", tipMonDocRelacionado);
      detalleRetencion.put("fecPagDocRelacionado", fecPagDocRelacionado);
      detalleRetencion.put("nroPagDocRelacionado", nroPagDocRelacionado);
      detalleRetencion.put("mtoPagDocRelacionado", mtoPagDocRelacionado);
      detalleRetencion.put("tipMonPagDocRelacionado", tipMonPagDocRelacionado);
      
      detalleRetencion.put("mtoRetDocRelacionado", mtoRetDocRelacionado);
      detalleRetencion.put("tipMonRetDocRelacionado", tipMonRetDocRelacionado);
      detalleRetencion.put("fecRetDocRelacionado", fecRetDocRelacionado);
      detalleRetencion.put("mtoTotPagNetoDocRelacionado", mtoTotPagNetoDocRelacionado);
      detalleRetencion.put("tipMonTotPagNetoDocRelacionado", tipMonTotPagNetoDocRelacionado);
      
      detalleRetencion.put("tipMonRefTipCambio", tipMonRefTipCambio);
      detalleRetencion.put("tipMonObjTipCambio", tipMonObjTipCambio);
      detalleRetencion.put("facTipCambio", facTipCambio);
      detalleRetencion.put("fecTipCambio", fecTipCambio);

      
      detalleRetencion.put("lineaSwf", linea);
      
      listaDetaRetencion.add(detalleRetencion);
    } 
    
    retencion.put("listaDetalle", listaDetaRetencion);
    
    generarComunesJson(this.objectoJson, retencion);
    
    return retencion;
  }


  
  public byte[] parse(String templatesPath) throws ParserException {
    return parse(templatesPath, plantillaSeleccionada);
  }
}