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

public class JsonNotaDebitoParser
  extends JsonCpeAbstractParser
  implements Parser
{
  private static final Logger log = LoggerFactory.getLogger(JsonNotaDebitoParser.class);

  
  private static String plantillaSeleccionada = "ConvertirNDebitoXML.ftl";

  
  private HashMap<String, Object> objectoJson;

  
  private String nombreArchivo;
  
  private Contribuyente contri;

  /*     */ 
  
  public JsonNotaDebitoParser(Contribuyente contri, HashMap<String, Object> objectoJson, String nombreArchivo) {
    this.contri = contri;
    
    this.nombreArchivo = nombreArchivo;
    this.objectoJson = objectoJson;
  }




  
  public Map<String, Object> pipeToMap() throws ParserException {
    Map<String, Object> cabecera = (this.objectoJson.get("cabecera") != null) ? (Map<String, Object>)this.objectoJson.get("cabecera") : new HashMap<>();

    
    String tipOperacion = (cabecera.get("tipOperacion") != null) ? (String)cabecera.get("tipOperacion") : "";
    String fecEmision = (cabecera.get("fecEmision") != null) ? (String)cabecera.get("fecEmision") : "";
    String horEmision = (cabecera.get("horEmision") != null) ? (String)cabecera.get("horEmision") : "";
    String codLocalEmisor = (cabecera.get("codLocalEmisor") != null) ? (String)cabecera.get("codLocalEmisor") : "";
    String tipDocUsuario = (cabecera.get("tipDocUsuario") != null) ? (String)cabecera.get("tipDocUsuario") : "";
    String numDocUsuario = (cabecera.get("numDocUsuario") != null) ? (String)cabecera.get("numDocUsuario") : "";
    String rznSocialUsuario = (cabecera.get("rznSocialUsuario") != null) ? (String)cabecera.get("rznSocialUsuario") : "";
    
    String tipMoneda = (cabecera.get("tipMoneda") != null) ? (String)cabecera.get("tipMoneda") : "";
    String codMotivo = (cabecera.get("codMotivo") != null) ? (String)cabecera.get("codMotivo") : "";
    String desMotivo = (cabecera.get("desMotivo") != null) ? (String)cabecera.get("desMotivo") : "";
    String numDocAfectado = (cabecera.get("numDocAfectado") != null) ? (String)cabecera.get("numDocAfectado") : "";
    String tipDocAfectado = (cabecera.get("tipDocAfectado") != null) ? (String)cabecera.get("tipDocAfectado") : "";
    
    String porDescGlobal = (cabecera.get("porDescGlobal") != null) ? (String)cabecera.get("porDescGlobal") : "";
    String mtoDescGlobal = (cabecera.get("mtoDescGlobal") != null) ? (String)cabecera.get("mtoDescGlobal") : "";
    
    String mtoBasImpDescGlobal = (cabecera.get("mtoBasImpDescGlobal") != null) ? (String)cabecera.get("mtoBasImpDescGlobal") : "";
    
    String sumTotTributos = (cabecera.get("sumTotTributos") != null) ? (String)cabecera.get("sumTotTributos") : "0.00";
    
    String sumTotValVenta = (cabecera.get("sumTotValVenta") != null) ? (String)cabecera.get("sumTotValVenta") : "0.00";
    
    String sumPrecioVenta = (cabecera.get("sumPrecioVenta") != null) ? (String)cabecera.get("sumPrecioVenta") : "0.00";
    
    String sumDescTotal = (cabecera.get("sumDescTotal") != null) ? (String)cabecera.get("sumDescTotal") : "0.00";
    String sumOtrosCargos = (cabecera.get("sumOtrosCargos") != null) ? (String)cabecera.get("sumOtrosCargos") : "0.00";

    
    String sumTotalAnticipos = (cabecera.get("sumTotalAnticipos") != null) ? (String)cabecera.get("sumTotalAnticipos") : "0.00";
    
    String sumImpVenta = (cabecera.get("sumImpVenta") != null) ? (String)cabecera.get("sumImpVenta") : "0.00";
    String ublVersionId = (cabecera.get("ublVersionId") != null) ? (String)cabecera.get("ublVersionId") : "";
    String customizationId = (cabecera.get("customizationId") != null) ? (String)cabecera.get("customizationId") : "";


    
    String ctdUnidadItem = "0", mtoValorUnitario = "0.00", sumTotTributosItem = "0.00";
    String mtoPrecioVentaUnitario = "0.00", mtoValorVentaItem = "0.00", mtoValorReferencialUnitario = "0.00";
    String codUnidadMedida = "", codProducto = "", codProductoSUNAT = "", desItem = "";
    String mtoIgvItem = "0.00", mtoBaseIgvItem = "0.00", porIgvItem = "0.00";
    String codTriIGV = "", nomTributoIgvItem = "", codTipTributoIgvItem = "", tipAfeIGV = "";
    String mtoIscItem = "0.00", mtoBaseIscItem = "0.00", porIscItem = "";
    String codTriISC = "", nomTributoIscItem = "", codTipTributoIscItem = "", tipSisISC = "";
    
    String codTriOtro = "", nomTributoOtroItem = "", codTipTributoOtroItem = "";
    String mtoTriOtroItem = "0.00", mtoBaseTriOtroItem = "0.00", porTriOtroItem = "";
    
    String codTriIcbper = "", nomTributoIcbperItem = "", codTipTributoIcbperItem = "";
    String mtoTriIcbperItem = "0.00", ctdBolsasTriIcbperItem = "0.00", mtoTriIcbperUnidad = "0.00";

    
    List<Map<String, Object>> detalle = (this.objectoJson.get("detalle") != null) ? (List<Map<String, Object>>)this.objectoJson.get("detalle") : new ArrayList<>();

    
    String[] idArchivo = this.nombreArchivo.split("\\-");
    
    String identificadorFirmaSwf = "SIGN";
    Random calcularRnd = new Random();
    Integer codigoFacturadorSwf = Integer.valueOf((int)(calcularRnd.nextDouble() * 1000000.0D));


    
    log.debug("GenerarDocumentosServiceImpl.generarFacturaJson...Cabecera de Nota");
    Map<String, Object> nota = null;
    
    nota = new HashMap<>();
    nota.put("tipOperacion", tipOperacion);
    nota.put("fecEmision", fecEmision);
    nota.put("horEmision", horEmision);
    nota.put("codLocalEmisor", codLocalEmisor);
    nota.put("tipDocUsuario", tipDocUsuario);
    nota.put("numDocUsuario", numDocUsuario);
    nota.put("rznSocialUsuario", rznSocialUsuario);
    nota.put("moneda", tipMoneda);
    
    nota.put("codMotivo", codMotivo);
    nota.put("desMotivo", desMotivo);
    nota.put("tipDocAfectado", tipDocAfectado);
    nota.put("numDocAfectado", numDocAfectado);
    
    nota.put("porDescGlobal", porDescGlobal);
    nota.put("mtoDescGlobal", mtoDescGlobal);
    nota.put("mtoBasImpDescGlobal", mtoBasImpDescGlobal);
    nota.put("sumTotTributos", sumTotTributos);
    nota.put("sumTotValVenta", sumTotValVenta);
    nota.put("sumPrecioVenta", sumPrecioVenta);
    nota.put("sumDescTotal", sumDescTotal);
    nota.put("sumOtrosCargos", sumOtrosCargos);
    nota.put("sumTotalAnticipos", sumTotalAnticipos);
    nota.put("sumImpVenta", sumImpVenta);
    nota.put("ublVersionId", ublVersionId);
    nota.put("customizationId", customizationId);

    
    nota.put("ublVersionIdSwf", "2.0");
    nota.put("CustomizationIdSwf", "1.0");
    nota.put("nroCdpSwf", idArchivo[2] + "-" + idArchivo[3]);
    nota.put("tipCdpSwf", idArchivo[1]);
    nota.put("nroRucEmisorSwf", this.contri.getNumRuc());
    nota.put("tipDocuEmisorSwf", "6");
    nota.put("nombreComercialSwf", this.contri.getNombreComercial());
    nota.put("razonSocialSwf", this.contri.getRazonSocial());
    nota.put("ubigeoDomFiscalSwf", this.contri.getDireccion().getUbigeo());
    nota.put("direccionDomFiscalSwf", this.contri.getDireccion().getDireccion());
    nota.put("deparSwf", this.contri.getDireccion().getDepar());
    nota.put("provinSwf", this.contri.getDireccion().getProvin());
    nota.put("distrSwf", this.contri.getDireccion().getDistr());
    nota.put("urbanizaSwf", this.contri.getDireccion().getUrbaniza());
    nota.put("paisDomFiscalSwf", "PE");
    nota.put("codigoMontoDescuentosSwf", "2005");
    nota.put("codigoMontoOperGravadasSwf", "1001");
    nota.put("codigoMontoOperInafectasSwf", "1002");
    nota.put("codigoMontoOperExoneradasSwf", "1003");
    nota.put("idIgv", "1000");
    nota.put("codIgv", "IGV");
    nota.put("codExtIgv", "VAT");
    nota.put("idIsc", "2000");
    nota.put("codIsc", "ISC");
    nota.put("codExtIsc", "EXC");
    nota.put("idOtr", "9999");
    nota.put("codOtr", "OTROS");
    nota.put("codExtOtr", "OTH");
    nota.put("tipoCodigoMonedaSwf", "01");
    
    nota.put("identificadorFacturadorSwf", "Elaborador por Sistema del contribuyente");
    nota.put("codigoFacturadorSwf", codigoFacturadorSwf.toString());
    nota.put("identificadorFirmaSwf", identificadorFirmaSwf);
    
    log.debug("GenerarDocumentosServiceImpl.generarFacturaJson...Detalle de Nota");
    Iterator<Map<String, Object>> listaDetalle = detalle.iterator();
    List<Map<String, Object>> listaDetaNota = new ArrayList<>();
    Map<String, Object> detalleNota = null;
    Map<String, Object> detalleLista = null;
    Integer linea = new Integer(0);
    while (listaDetalle.hasNext()) {
      detalleLista = listaDetalle.next();
      codUnidadMedida = (detalleLista.get("codUnidadMedida") != null) ? (String)detalleLista.get("codUnidadMedida") : "";
      
      ctdUnidadItem = (detalleLista.get("ctdUnidadItem") != null) ? (String)detalleLista.get("ctdUnidadItem") : "0";
      
      codProducto = (detalleLista.get("codProducto") != null) ? (String)detalleLista.get("codProducto") : "";
      
      codProductoSUNAT = (detalleLista.get("codProductoSUNAT") != null) ? (String)detalleLista.get("codProductoSUNAT") : "";
      
      desItem = (detalleLista.get("desItem") != null) ? (String)detalleLista.get("desItem") : "";
      
      mtoValorUnitario = (detalleLista.get("mtoValorUnitario") != null) ? (String)detalleLista.get("mtoValorUnitario") : "0.00";

      
      sumTotTributosItem = (detalleLista.get("sumTotTributosItem") != null) ? (String)detalleLista.get("sumTotTributosItem") : "0.00";
      
      codTriIGV = (detalleLista.get("codTriIGV") != null) ? (String)detalleLista.get("codTriIGV") : "";
      mtoIgvItem = (detalleLista.get("mtoIgvItem") != null) ? (String)detalleLista.get("mtoIgvItem") : "0.00";
      mtoBaseIgvItem = (detalleLista.get("mtoBaseIgvItem") != null) ? (String)detalleLista.get("mtoBaseIgvItem") : "0.00";

      
      nomTributoIgvItem = (detalleLista.get("nomTributoIgvItem") != null) ? (String)detalleLista.get("nomTributoIgvItem") : "0.00";

      
      codTipTributoIgvItem = (detalleLista.get("codTipTributoIgvItem") != null) ? (String)detalleLista.get("codTipTributoIgvItem") : "0.00";

      
      tipAfeIGV = (detalleLista.get("tipAfeIGV") != null) ? (String)detalleLista.get("tipAfeIGV") : "0.00";
      porIgvItem = (detalleLista.get("porIgvItem") != null) ? (String)detalleLista.get("porIgvItem") : "0.00";
      
      codTriISC = (detalleLista.get("codTriISC") != null) ? (String)detalleLista.get("codTriISC") : "";
      mtoIscItem = (detalleLista.get("mtoIscItem") != null) ? (String)detalleLista.get("mtoIscItem") : "0.00";
      mtoBaseIscItem = (detalleLista.get("mtoBaseIscItem") != null) ? (String)detalleLista.get("mtoBaseIscItem") : "0.00";

      
      nomTributoIscItem = (detalleLista.get("nomTributoIscItem") != null) ? (String)detalleLista.get("nomTributoIscItem") : "";

      
      codTipTributoIscItem = (detalleLista.get("codTipTributoIscItem") != null) ? (String)detalleLista.get("codTipTributoIscItem") : "0.00";
      
      tipSisISC = (detalleLista.get("tipSisISC") != null) ? (String)detalleLista.get("tipSisISC") : "0.00";
      porIscItem = (detalleLista.get("porIscItem") != null) ? (String)detalleLista.get("porIscItem") : "0.00";

      
      mtoPrecioVentaUnitario = (detalleLista.get("mtoPrecioVentaUnitario") != null) ? (String)detalleLista.get("mtoPrecioVentaUnitario") : "0.00";

      
      mtoValorVentaItem = (detalleLista.get("mtoValorVentaItem") != null) ? (String)detalleLista.get("mtoValorVentaItem") : "";

      
      mtoValorReferencialUnitario = (detalleLista.get("mtoValorReferencialUnitario") != null) ? (String)detalleLista.get("mtoValorReferencialUnitario") : "0.00";
      
      codTriOtro = (detalleLista.get("codTriOtro") != null) ? (String)detalleLista.get("codTriOtro") : "";

      
      mtoTriOtroItem = (detalleLista.get("mtoTriOtroItem") != null) ? (String)detalleLista.get("mtoTriOtroItem") : "0.00";

      
      mtoBaseTriOtroItem = (detalleLista.get("mtoBaseTriOtroItem") != null) ? (String)detalleLista.get("mtoBaseTriOtroItem") : "0.00";

      
      nomTributoOtroItem = (detalleLista.get("nomTributoOtroItem") != null) ? (String)detalleLista.get("nomTributoOtroItem") : "0.00";

      
      codTipTributoOtroItem = (detalleLista.get("codTipTributoOtroItem") != null) ? (String)detalleLista.get("codTipTributoOtroItem") : "0.00";
      
      porTriOtroItem = (detalleLista.get("porTriOtroItem") != null) ? (String)detalleLista.get("porTriOtroItem") : "0.00";

      
      codTriIcbper = (detalleLista.get("codTriIcbper") != null) ? (String)detalleLista.get("codTriIcbper") : "";
      
      mtoTriIcbperItem = (detalleLista.get("mtoTriIcbperItem") != null) ? (String)detalleLista.get("mtoTriIcbperItem") : "0.00";
      
      ctdBolsasTriIcbperItem = (detalleLista.get("ctdBolsasTriIcbperItem") != null) ? (String)detalleLista.get("ctdBolsasTriIcbperItem") : "0.00";
      
      nomTributoIcbperItem = (detalleLista.get("nomTributoIcbperItem") != null) ? (String)detalleLista.get("nomTributoIcbperItem") : "";
      
      codTipTributoIcbperItem = (detalleLista.get("codTipTributoIcbperItem") != null) ? (String)detalleLista.get("codTipTributoIcbperItem") : "0.00";
      
      mtoTriIcbperUnidad = (detalleLista.get("mtoTriIcbperUnidad") != null) ? (String)detalleLista.get("mtoTriIcbperUnidad") : "0.00";


      
      linea = Integer.valueOf(linea.intValue() + 1);
      detalleNota = new HashMap<>();
      detalleNota.put("unidadMedida", codUnidadMedida);
      detalleNota.put("ctdUnidadItem", ctdUnidadItem);
      detalleNota.put("codProducto", codProducto);
      detalleNota.put("codProductoSUNAT", codProductoSUNAT);
      
      detalleNota.put("desItem", desItem);
      detalleNota.put("mtoValorUnitario", mtoValorUnitario);
      detalleNota.put("sumTotTributosItem", sumTotTributosItem);
      detalleNota.put("codTriIGV", codTriIGV);
      detalleNota.put("mtoIgvItem", mtoIgvItem);
      detalleNota.put("mtoBaseIgvItem", mtoBaseIgvItem);
      detalleNota.put("nomTributoIgvItem", nomTributoIgvItem);
      detalleNota.put("codTipTributoIgvItem", codTipTributoIgvItem);
      detalleNota.put("tipAfeIGV", tipAfeIGV);
      detalleNota.put("porIgvItem", porIgvItem);
      detalleNota.put("codTriISC", codTriISC);
      detalleNota.put("mtoIscItem", mtoIscItem);
      detalleNota.put("mtoBaseIscItem", mtoBaseIscItem);
      detalleNota.put("nomTributoIscItem", nomTributoIscItem);
      detalleNota.put("codTipTributoIscItem", codTipTributoIscItem);
      detalleNota.put("tipSisISC", tipSisISC);
      detalleNota.put("porIscItem", porIscItem);
      detalleNota.put("mtoPrecioVentaUnitario", mtoPrecioVentaUnitario);
      detalleNota.put("mtoValorVentaItem", mtoValorVentaItem);
      detalleNota.put("mtoValorReferencialUnitario", mtoValorReferencialUnitario);
      detalleNota.put("codTriOtro", codTriOtro);
      detalleNota.put("mtoTriOtroItem", mtoTriOtroItem);
      detalleNota.put("mtoBaseTriOtroItem", mtoBaseTriOtroItem);
      detalleNota.put("nomTributoOtroItem", nomTributoOtroItem);
      detalleNota.put("codTipTributoOtroItem", codTipTributoOtroItem);
      detalleNota.put("porTriOtroItem", porTriOtroItem);
      
      detalleNota.put("codTriIcbper", codTriIcbper);
      detalleNota.put("mtoTriIcbperItem", mtoTriIcbperItem);
      detalleNota.put("ctdBolsasTriIcbperItem", ctdBolsasTriIcbperItem);
      detalleNota.put("nomTributoIcbperItem", nomTributoIcbperItem);
      detalleNota.put("codTipTributoIcbperItem", codTipTributoIcbperItem);
      detalleNota.put("mtoTriIcbperUnidad", mtoTriIcbperUnidad);

      
      detalleNota.put("lineaSwf", linea);
      detalleNota.put("tipoCodiMoneGratiSwf", "02");
      
      listaDetaNota.add(detalleNota);
    } 
    
    nota.put("listaDetalle", listaDetaNota);
    
    generarComunesJson(this.objectoJson, nota);
    
    log.debug("GenerarDocumentosServiceImpl.generarFacturaJson...retornar Nota" + nota);
    
    return nota;
  }

  
  public byte[] parse(String templatesPath) throws ParserException {
    return parse(templatesPath, plantillaSeleccionada);
  }
}
