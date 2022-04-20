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





public class JsonPercepcionParser
  extends JsonCpeAbstractParser
  implements Parser
{
  private static final Logger log = LoggerFactory.getLogger(JsonPercepcionParser.class);

  
  private static String plantillaSeleccionada = "ConvertirPercepcionXML.ftl";

  
  private HashMap<String, Object> objectoJson;

  
  private String nombreArchivo;
  
  private Contribuyente contri;

  /*     */ 
  
  public JsonPercepcionParser(Contribuyente contri, HashMap<String, Object> objectoJson, String nombreArchivo) {
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

    
    String codRegPercepcion = (cabecera.get("codRegPercepcion") != null) ? (String)cabecera.get("codRegPercepcion") : "";
    
    String tasPercepcion = (cabecera.get("tasPercepcion") != null) ? (String)cabecera.get("tasPercepcion") : "";
    String desObsPercepcion = (cabecera.get("desObsPercepcion") != null) ? (String)cabecera.get("desObsPercepcion") : "";
    
    String mtoTotPercepcion = (cabecera.get("mtoTotPercepcion") != null) ? (String)cabecera.get("mtoTotPercepcion") : "0.00";
    
    String tipMonPercepcion = (cabecera.get("tipMonPercepcion") != null) ? (String)cabecera.get("tipMonPercepcion") : "";

    
    String mtoImpTotPagPercepcion = (cabecera.get("mtoImpTotPagPercepcion") != null) ? (String)cabecera.get("mtoImpTotPagPercepcion") : "0.00";

    
    String tipMonImpTotPagPercepcion = (cabecera.get("tipMonImpTotPagPercepcion") != null) ? (String)cabecera.get("tipMonImpTotPagPercepcion") : "";

    
    String mtoImpTotDocRelacionado = "0.00", mtoPagDocRelacionado = "0.00", mtoPerDocRelacionado = "0.00";
    String mtoTotPagNetoDocRelacionado = "0.00", facTipCambio = "00.000000";
    String tipDocRelacionado = "", nroDocRelacionado = "", fecEmiDocRelacionado = "", tipMonDocRelacionado = "";
    String fecPagDocRelacionado = "", nroPagDocRelacionado = "", tipMonPagDocRelacionado = "";
    String tipMonPerDocRelacionado = "", fecPerDocRelacionado = "", tipMonTotPagNetoDocRelacionado = "";
    String tipMonRefTipCambio = "", tipMonObjTipCambio = "", fecTipCambio = "";

    
    List<Map<String, Object>> detalle = (this.objectoJson.get("detalle") != null) ? (List<Map<String, Object>>)this.objectoJson.get("detalle") : new ArrayList<>();

    
    String[] idArchivo = this.nombreArchivo.split("\\-");
    
    String identificadorFirmaSwf = "SIGN";
    Random calcularRnd = new Random();
    Integer codigoFacturadorSwf = Integer.valueOf((int)(calcularRnd.nextDouble() * 1000000.0D));

    
    log.debug("GenerarDocumentosServiceImpl.generarFacturaJson...Cabecera de Factura");
    Map<String, Object> Percepcion = null;
    
    Percepcion = new HashMap<>();
    Percepcion.put("fecEmision", fecEmision);
    Percepcion.put("nroDocIdeReceptor", nroDocIdeReceptor);
    Percepcion.put("tipDocIdeReceptor", tipDocIdeReceptor);
    Percepcion.put("desNomComReceptor", desNomComReceptor);
    
    Percepcion.put("desUbiReceptor", desUbiReceptor);
    Percepcion.put("desDirReceptor", desDirReceptor);
    Percepcion.put("desUrbReceptor", desUrbReceptor);
    Percepcion.put("desDepReceptor", desDepReceptor);
    Percepcion.put("desProReceptor", desProReceptor);
    Percepcion.put("desDisReceptor", desDisReceptor);
    Percepcion.put("codPaisReceptor", codPaisReceptor);
    Percepcion.put("rznSocialReceptor", rznSocialReceptor);
    
    Percepcion.put("codRegPercepcion", codRegPercepcion);
    Percepcion.put("tasPercepcion", tasPercepcion);
    Percepcion.put("desObsPercepcion", desObsPercepcion);
    Percepcion.put("mtoTotPercepcion", mtoTotPercepcion);
    Percepcion.put("tipMonPercepcion", tipMonPercepcion);
    Percepcion.put("mtoImpTotPagPercepcion", mtoImpTotPagPercepcion);
    Percepcion.put("tipMonImpTotPagPercepcion", tipMonImpTotPagPercepcion);

    
    Percepcion.put("ublVersionIdSwf", "2.0");
    Percepcion.put("CustomizationIdSwf", "1.0");
    Percepcion.put("nroCdpSwf", idArchivo[2] + "-" + idArchivo[3]);
    Percepcion.put("tipCdpSwf", idArchivo[1]);
    Percepcion.put("nroRucEmisorSwf", this.contri.getNumRuc());
    Percepcion.put("tipDocuEmisorSwf", "6");
    Percepcion.put("nombreComercialSwf", this.contri.getNombreComercial());
    Percepcion.put("razonSocialSwf", this.contri.getRazonSocial());
    Percepcion.put("ubigeoDomFiscalSwf", this.contri.getDireccion().getUbigeo());
    Percepcion.put("direccionDomFiscalSwf", this.contri.getDireccion().getDireccion());
    Percepcion.put("deparSwf", this.contri.getDireccion().getDepar());
    Percepcion.put("provinSwf", this.contri.getDireccion().getProvin());
    Percepcion.put("distrSwf", this.contri.getDireccion().getDistr());
    Percepcion.put("urbanizaSwf", this.contri.getDireccion().getUrbaniza());
    
    Percepcion.put("paisDomFiscalSwf", "PE");
    Percepcion.put("tipoCodigoMonedaSwf", "01");
    
    Percepcion.put("identificadorFacturadorSwf", "Elaborador por Sistema del contribuyente");
    Percepcion.put("codigoFacturadorSwf", codigoFacturadorSwf.toString());
    Percepcion.put("identificadorFirmaSwf", identificadorFirmaSwf);
    
    log.debug("GenerarDocumentosServiceImpl.generarFacturaJson...Detalle de Factura");
    Iterator<Map<String, Object>> listaDetalle = detalle.iterator();
    List<Map<String, Object>> listaDetaPercepcion = new ArrayList<>();
    Map<String, Object> detallePercepcion = null;
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


      
      mtoPerDocRelacionado = (detalleLista.get("mtoPerDocRelacionado") != null) ? (String)detalleLista.get("mtoPerDocRelacionado") : "0.00";

      
      tipMonPerDocRelacionado = (detalleLista.get("tipMonPerDocRelacionado") != null) ? (String)detalleLista.get("tipMonPerDocRelacionado") : "";

      
      fecPerDocRelacionado = (detalleLista.get("fecPerDocRelacionado") != null) ? (String)detalleLista.get("fecPerDocRelacionado") : "";

      
      mtoTotPagNetoDocRelacionado = (detalleLista.get("mtoTotPagNetoDocRelacionado") != null) ? (String)detalleLista.get("mtoTotPagNetoDocRelacionado") : "0.00";

      
      tipMonTotPagNetoDocRelacionado = (detalleLista.get("tipMonTotPagNetoDocRelacionado") != null) ? (String)detalleLista.get("tipMonTotPagNetoDocRelacionado") : "";


      
      tipMonRefTipCambio = (detalleLista.get("tipMonRefTipCambio") != null) ? (String)detalleLista.get("tipMonRefTipCambio") : "";

      
      tipMonObjTipCambio = (detalleLista.get("tipMonObjTipCambio") != null) ? (String)detalleLista.get("tipMonObjTipCambio") : "PEN";
      
      facTipCambio = (detalleLista.get("facTipCambio") != null) ? (String)detalleLista.get("facTipCambio") : "";
      fecTipCambio = (detalleLista.get("fecTipCambio") != null) ? (String)detalleLista.get("fecTipCambio") : "";

      
      linea = Integer.valueOf(linea.intValue() + 1);
      detallePercepcion = new HashMap<>();
      detallePercepcion.put("tipDocRelacionado", tipDocRelacionado);
      detallePercepcion.put("nroDocRelacionado", nroDocRelacionado);
      detallePercepcion.put("fecEmiDocRelacionado", fecEmiDocRelacionado);
      detallePercepcion.put("mtoImpTotDocRelacionado", mtoImpTotDocRelacionado);
      detallePercepcion.put("tipMonDocRelacionado", tipMonDocRelacionado);
      detallePercepcion.put("fecPagDocRelacionado", fecPagDocRelacionado);
      detallePercepcion.put("nroPagDocRelacionado", nroPagDocRelacionado);
      detallePercepcion.put("mtoPagDocRelacionado", mtoPagDocRelacionado);
      detallePercepcion.put("tipMonPagDocRelacionado", tipMonPagDocRelacionado);
      
      detallePercepcion.put("mtoPerDocRelacionado", mtoPerDocRelacionado);
      detallePercepcion.put("tipMonPerDocRelacionado", tipMonPerDocRelacionado);
      detallePercepcion.put("fecPerDocRelacionado", fecPerDocRelacionado);
      detallePercepcion.put("mtoTotPagNetoDocRelacionado", mtoTotPagNetoDocRelacionado);
      detallePercepcion.put("tipMonTotPagNetoDocRelacionado", tipMonTotPagNetoDocRelacionado);
      
      detallePercepcion.put("tipMonRefTipCambio", tipMonRefTipCambio);
      detallePercepcion.put("tipMonObjTipCambio", tipMonObjTipCambio);
      detallePercepcion.put("facTipCambio", facTipCambio);
      detallePercepcion.put("fecTipCambio", fecTipCambio);

      
      detallePercepcion.put("lineaSwf", linea);
      
      listaDetaPercepcion.add(detallePercepcion);
    } 
    
    Percepcion.put("listaDetalle", listaDetaPercepcion);
    
    generarComunesJson(this.objectoJson, Percepcion);
    
    return Percepcion;
  }


  
  public byte[] parse(String templatesPath) throws ParserException {
    return parse(templatesPath, plantillaSeleccionada);
  }
}