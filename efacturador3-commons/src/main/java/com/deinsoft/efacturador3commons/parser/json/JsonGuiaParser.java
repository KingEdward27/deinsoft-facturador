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


public class JsonGuiaParser
  extends JsonCpeAbstractParser
  implements Parser
{
  private static final Logger log = LoggerFactory.getLogger(JsonGuiaParser.class);

  
  private static String plantillaSeleccionada = "ConvertirGuiaXML.ftl";

  
  private HashMap<String, Object> objectoJson;

  
  private String nombreArchivo;
  
  private Contribuyente contri;

  /*     */ 
  
  public JsonGuiaParser(Contribuyente contri, HashMap<String, Object> objectoJson, String nombreArchivo) {
    this.contri = contri;
    
    this.nombreArchivo = nombreArchivo;
    this.objectoJson = objectoJson;
  }


  
  public Map<String, Object> pipeToMap() throws ParserException {
    Map<String, Object> cabecera = (this.objectoJson.get("cabecera") != null) ? (Map<String, Object>)this.objectoJson.get("cabecera") : new HashMap<>();
    
    String ublVersionId = (cabecera.get("ublVersionId") != null) ? (String)cabecera.get("ublVersionId") : "";
    String customizationId = (cabecera.get("customizationId") != null) ? (String)cabecera.get("customizationId") : "";
    String fecEmision = (cabecera.get("fecEmision") != null) ? (String)cabecera.get("fecEmision") : "";
    String horEmision = (cabecera.get("horEmision") != null) ? (String)cabecera.get("horEmision") : "";
    String tipDocGuia = (cabecera.get("tipDocGuia") != null) ? (String)cabecera.get("tipDocGuia") : "";
    String serNumDocGuia = (cabecera.get("serNumDocGuia") != null) ? (String)cabecera.get("serNumDocGuia") : "";
    String obsGuia = (cabecera.get("obsGuia") != null) ? (String)cabecera.get("obsGuia") : "";
    
    String numDocDestinatario = (cabecera.get("numDocDestinatario") != null) ? (String)cabecera.get("numDocDestinatario") : "";
    String tipDocDestinatario = (cabecera.get("tipDocDestinatario") != null) ? (String)cabecera.get("tipDocDestinatario") : "";
    String rznSocialDestinatario = (cabecera.get("rznSocialDestinatario") != null) ? (String)cabecera.get("rznSocialDestinatario") : "";
    
    String numDocProveedor = (cabecera.get("numDocProveedor") != null) ? (String)cabecera.get("numDocProveedor") : "";
    String tipDocProveedor = (cabecera.get("tipDocProveedor") != null) ? (String)cabecera.get("tipDocProveedor") : "";
    String rznSocialProveedor = (cabecera.get("rznSocialProveedor") != null) ? (String)cabecera.get("rznSocialProveedor") : "";
    
    String motTrasladoDatosEnvio = (cabecera.get("motTrasladoDatosEnvio") != null) ? (String)cabecera.get("motTrasladoDatosEnvio") : "";
    String desMotivoTrasladoDatosEnvio = (cabecera.get("desMotivoTrasladoDatosEnvio") != null) ? (String)cabecera.get("desMotivoTrasladoDatosEnvio") : "";
    String indTransbordoProgDatosEnvio = (cabecera.get("indTransbordoProgDatosEnvio") != null) ? (String)cabecera.get("indTransbordoProgDatosEnvio") : "";
    String psoBrutoTotalBienesDatosEnvio = (cabecera.get("psoBrutoTotalBienesDatosEnvio") != null) ? (String)cabecera.get("psoBrutoTotalBienesDatosEnvio") : "";
    String uniMedidaPesoBrutoDatosEnvio = (cabecera.get("uniMedidaPesoBrutoDatosEnvio") != null) ? (String)cabecera.get("uniMedidaPesoBrutoDatosEnvio") : "";
    String numBultosDatosEnvio = (cabecera.get("numBultosDatosEnvio") != null) ? (String)cabecera.get("numBultosDatosEnvio") : "";
    String modTrasladoDatosEnvio = (cabecera.get("motTrasladoDatosEnvio") != null) ? (String)cabecera.get("motTrasladoDatosEnvio") : "";
    String fecInicioTrasladoDatosEnvio = (cabecera.get("fecInicioTrasladoDatosEnvio") != null) ? (String)cabecera.get("fecInicioTrasladoDatosEnvio") : "";
    
    String numDocTransportista = (cabecera.get("numDocTransportista") != null) ? (String)cabecera.get("numDocTransportista") : "";
    String tipDocTransportista = (cabecera.get("tipDocTransportista") != null) ? (String)cabecera.get("tipDocTransportista") : "";
    String nomTransportista = (cabecera.get("nomTransportista") != null) ? (String)cabecera.get("nomTransportista") : "";
    
    String numPlacaTransPrivado = (cabecera.get("numPlacaTransPrivado") != null) ? (String)cabecera.get("numPlacaTransPrivado") : "";
    String numDocIdeConductorTransPrivado = (cabecera.get("numDocIdeConductorTransPrivado") != null) ? (String)cabecera.get("numDocIdeConductorTransPrivado") : "";
    String tipDocIdeConductorTransPrivado = (cabecera.get("tipDocIdeConductorTransPrivado") != null) ? (String)cabecera.get("tipDocIdeConductorTransPrivado") : "";
    String nomConductorTransPrivado = (cabecera.get("nomConductorTransPrivado") != null) ? (String)cabecera.get("nomConductorTransPrivado") : "";

    
    String ubiLlegada = (cabecera.get("ubiLlegada") != null) ? (String)cabecera.get("ubiLlegada") : "";
    String dirLlegada = (cabecera.get("dirLlegada") != null) ? (String)cabecera.get("dirLlegada") : "";
    String ubiPartida = (cabecera.get("ubiPartida") != null) ? (String)cabecera.get("ubiPartida") : "";
    String dirPartida = (cabecera.get("dirPartida") != null) ? (String)cabecera.get("dirPartida") : "";
    
    String numContenedor = (cabecera.get("numContenedor") != null) ? (String)cabecera.get("numContenedor") : "";
    String codPuerto = (cabecera.get("codPuerto") != null) ? (String)cabecera.get("codPuerto") : "";
    
    String idDocBaja = (cabecera.get("idDocBaja") != null) ? (String)cabecera.get("idDocBaja") : "";
    String codTipDocBaja = (cabecera.get("codTipDocBaja") != null) ? (String)cabecera.get("codTipDocBaja") : "";
    String tipDocBaja = (cabecera.get("tipDocBaja") != null) ? (String)cabecera.get("tipDocBaja") : "";
    
    List<Map<String, Object>> detalle = (this.objectoJson.get("detalle") != null) ? (List<Map<String, Object>>)this.objectoJson.get("detalle") : new ArrayList<>();
    
    String uniMedidaItem = (cabecera.get("uniMedidaItem") != null) ? (String)cabecera.get("uniMedidaItem") : "";
    String canItem = (cabecera.get("canItem") != null) ? (String)cabecera.get("canItem") : "0.00";
    String desItem = (cabecera.get("desItem") != null) ? (String)cabecera.get("desItem") : "";
    String codItem = (cabecera.get("codItem") != null) ? (String)cabecera.get("codItem") : "";
    
    List<Map<String, Object>> docRelacionado = (this.objectoJson.get("docRelacionado") != null) ? (List<Map<String, Object>>)this.objectoJson.get("docRelacionado") : new ArrayList<>();
    
    String numDocRel = (cabecera.get("numDocRel") != null) ? (String)cabecera.get("numDocRel") : "";
    String codTipDocRel = (cabecera.get("codTipDocRel") != null) ? (String)cabecera.get("codTipDocRel") : "";
    
    String[] idArchivo = this.nombreArchivo.split("\\-");
    String identificadorFirmaSwf = "SIGN";
    
    log.debug("JsonGuiaParser.pipeToMap...Cabecera de guia");
    Map<String, Object> guia = null;
    guia = new HashMap<>();
    guia.put("ublVersionId", ublVersionId);
    guia.put("customizationId", customizationId);
    guia.put("fecEmision", fecEmision);
    guia.put("horEmision", horEmision);
    guia.put("tipDocGuia", tipDocGuia);
    guia.put("serNumDocGuia", serNumDocGuia);
    guia.put("obsGuia", obsGuia);
    guia.put("numDocDestinatario", numDocDestinatario);
    guia.put("tipDocDestinatario", tipDocDestinatario);
    guia.put("rznSocialDestinatario", rznSocialDestinatario);
    guia.put("numDocProveedor", numDocProveedor);
    guia.put("tipDocProveedor", tipDocProveedor);
    guia.put("rznSocialProveedor", rznSocialProveedor);
    
    guia.put("motTrasladoDatosEnvio", motTrasladoDatosEnvio);
    guia.put("desMotivoTrasladoDatosEnvio", desMotivoTrasladoDatosEnvio);
    guia.put("indTransbordoProgDatosEnvio", indTransbordoProgDatosEnvio);
    guia.put("psoBrutoTotalBienesDatosEnvio", psoBrutoTotalBienesDatosEnvio);
    guia.put("uniMedidaPesoBrutoDatosEnvio", uniMedidaPesoBrutoDatosEnvio);
    guia.put("numBultosDatosEnvio", numBultosDatosEnvio);
    guia.put("modTrasladoDatosEnvio", modTrasladoDatosEnvio);
    guia.put("fecInicioTrasladoDatosEnvio", fecInicioTrasladoDatosEnvio);
    
    guia.put("numDocTransportista", numDocTransportista);
    guia.put("tipDocTransportista", tipDocTransportista);
    guia.put("nomTransportista", nomTransportista);
    guia.put("numPlacaTransPrivado", numPlacaTransPrivado);
    guia.put("numDocIdeConductorTransPrivado", numDocIdeConductorTransPrivado);
    guia.put("tipDocIdeConductorTransPrivado", tipDocIdeConductorTransPrivado);
    guia.put("nomConductorTransPrivado", nomConductorTransPrivado);
    
    guia.put("ubiLlegada", ubiLlegada);
    guia.put("dirLlegada", dirLlegada);
    guia.put("ubiPartida", ubiPartida);
    guia.put("dirPartida", dirPartida);
    
    guia.put("numContenedor", numContenedor);
    guia.put("codPuerto", codPuerto);
    guia.put("numDocRel", numDocRel);
    guia.put("codTipDocRel", codTipDocRel);
    
    guia.put("idDocBaja", idDocBaja);
    guia.put("codTipDocBaja", codTipDocBaja);
    guia.put("tipDocBaja", tipDocBaja);
    
    guia.put("nroCdpSwf", idArchivo[2] + "-" + idArchivo[3]);
    guia.put("tipCdpSwf", idArchivo[1]);
    guia.put("nroRucEmisorSwf", this.contri.getNumRuc());
    guia.put("tipDocuEmisorSwf", "6");
    guia.put("nombreComercialSwf", this.contri.getNombreComercial());
    guia.put("razonSocialSwf", this.contri.getRazonSocial());
    guia.put("ubigeoDomFiscalSwf", this.contri.getDireccion().getUbigeo());
    guia.put("direccionDomFiscalSwf", this.contri.getDireccion().getDireccion());
    guia.put("deparSwf", this.contri.getDireccion().getDepar());
    guia.put("provinSwf", this.contri.getDireccion().getProvin());
    guia.put("distrSwf", this.contri.getDireccion().getDistr());
    guia.put("urbanizaSwf", this.contri.getDireccion().getUrbaniza());
    guia.put("paisDomFiscalSwf", "PE");
    guia.put("identificadorFacturadorSwf", "Elaborador por Sistema del contribuyente");
    guia.put("identificadorFirmaSwf", identificadorFirmaSwf);
    
    log.debug("JsonGuiaParser.pipeToMap...Detalle de guia");
    Iterator<Map<String, Object>> listaDetalle = detalle.iterator();
    List<Map<String, Object>> listaDetaFactura = new ArrayList<>();
    Map<String, Object> detalleGuia = null;
    Map<String, Object> detalleLista = null;
    
    Integer linea = new Integer(0);
    while (listaDetalle.hasNext()) {
      detalleLista = listaDetalle.next();
      
      canItem = (detalleLista.get("canItem") != null) ? (String)detalleLista.get("canItem") : "0.00";
      uniMedidaItem = (detalleLista.get("uniMedidaItem") != null) ? (String)detalleLista.get("uniMedidaItem") : "";
      desItem = (detalleLista.get("desItem") != null) ? (String)detalleLista.get("desItem") : "";
      codItem = (detalleLista.get("codItem") != null) ? (String)detalleLista.get("codItem") : "";
      
      linea = Integer.valueOf(linea.intValue() + 1);
      detalleGuia = new HashMap<>();
      
      detalleGuia.put("canItem", canItem);
      detalleGuia.put("uniMedidaItem", uniMedidaItem);
      detalleGuia.put("desItem", desItem);
      detalleGuia.put("codItem", codItem);
      detalleGuia.put("lineaSwf", linea);
      listaDetaFactura.add(detalleGuia);
    } 
    guia.put("listaDetalle", listaDetaFactura);
    
    log.debug("JsonGuiaParser.pipeToMap...Doc Relacionado de guia");
    Iterator<Map<String, Object>> listaDocRelacionado = docRelacionado.iterator();
    List<Map<String, Object>> listaDocRelacionadoGuia = new ArrayList<>();
    Map<String, Object> docRelacionadoGuia = null;
    Map<String, Object> docRelacionadoLista = null;

    
    while (listaDocRelacionado.hasNext()) {
      docRelacionadoLista = listaDocRelacionado.next();
      
      numDocRel = (docRelacionadoLista.get("numDocRel") != null) ? (String)docRelacionadoLista.get("numDocRel") : "";
      codTipDocRel = (docRelacionadoLista.get("codTipDocRel") != null) ? (String)docRelacionadoLista.get("codTipDocRel") : "";
      
      docRelacionadoGuia = new HashMap<>();
      
      docRelacionadoGuia.put("numDocRel", numDocRel);
      docRelacionadoGuia.put("codTipDocRel", codTipDocRel);
      
      listaDocRelacionadoGuia.add(docRelacionadoGuia);
    } 
    guia.put("listaDocRelacionado", listaDocRelacionadoGuia);
    
    return guia;
  }

  
  public byte[] parse(String templatesPath) throws ParserException {
    return parse(templatesPath, plantillaSeleccionada);
  }
}
