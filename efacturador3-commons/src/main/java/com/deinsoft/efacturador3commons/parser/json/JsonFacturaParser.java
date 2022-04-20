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

public class JsonFacturaParser
  extends JsonCpeAbstractParser
  implements Parser
{
  private static final Logger log = LoggerFactory.getLogger(JsonFacturaParser.class);

  
  private static String plantillaSeleccionada = "ConvertirFacturaXML.ftl";

  
  private HashMap<String, Object> objectoJson;

  
  private String nombreArchivo;
  
  private Contribuyente contri;

  /*     */ 
  
  public JsonFacturaParser(Contribuyente contri, HashMap<String, Object> objectoJson, String nombreArchivo) {
    this.contri = contri;
    
    this.nombreArchivo = nombreArchivo;
    this.objectoJson = objectoJson;
  }





  
  public Map<String, Object> pipeToMap() throws ParserException {
    Map<String, Object> cabecera = (this.objectoJson.get("cabecera") != null) ? (Map<String, Object>)this.objectoJson.get("cabecera") : new HashMap<>();
    
    String tipOperacion = (cabecera.get("tipOperacion") != null) ? (String)cabecera.get("tipOperacion") : "";
    String fecEmision = (cabecera.get("fecEmision") != null) ? (String)cabecera.get("fecEmision") : "";
    String horEmision = (cabecera.get("horEmision") != null) ? (String)cabecera.get("horEmision") : "";
    String fecVencimiento = (cabecera.get("fecVencimiento") != null) ? (String)cabecera.get("fecVencimiento") : "";
    String codLocalEmisor = (cabecera.get("codLocalEmisor") != null) ? (String)cabecera.get("codLocalEmisor") : "";
    String tipDocUsuario = (cabecera.get("tipDocUsuario") != null) ? (String)cabecera.get("tipDocUsuario") : "";
    String numDocUsuario = (cabecera.get("numDocUsuario") != null) ? (String)cabecera.get("numDocUsuario") : "";
    String rznSocialUsuario = (cabecera.get("rznSocialUsuario") != null) ? (String)cabecera.get("rznSocialUsuario") : "";
    
    String tipMoneda = (cabecera.get("tipMoneda") != null) ? (String)cabecera.get("tipMoneda") : "";









    
    String sumTotTributos = (cabecera.get("sumTotTributos") != null) ? (String)cabecera.get("sumTotTributos") : "";
    
    String sumTotValVenta = (cabecera.get("sumTotValVenta") != null) ? (String)cabecera.get("sumTotValVenta") : "";
    String sumPrecioVenta = (cabecera.get("sumPrecioVenta") != null) ? (String)cabecera.get("sumPrecioVenta") : "";
    String sumDescTotal = (cabecera.get("sumDescTotal") != null) ? (String)cabecera.get("sumDescTotal") : "";
    String sumOtrosCargos = (cabecera.get("sumOtrosCargos") != null) ? (String)cabecera.get("sumOtrosCargos") : "";

    
    String sumTotalAnticipos = (cabecera.get("sumTotalAnticipos") != null) ? (String)cabecera.get("sumTotalAnticipos") : "0.00";
    
    String sumImpVenta = (cabecera.get("sumImpVenta") != null) ? (String)cabecera.get("sumImpVenta") : "0.00";

    
    String ublVersionId = (cabecera.get("ublVersionId") != null) ? (String)cabecera.get("ublVersionId") : "2.1";
    String customizationId = (cabecera.get("customizationId") != null) ? (String)cabecera.get("customizationId") : "2.0";


    
    String ctdUnidadItem = "0", mtoValorUnitario = "0.00", sumTotTributosItem = "0.00";
    String mtoPrecioVentaUnitario = "0.00", mtoValorVentaItem = "0.00", mtoValorReferencialUnitario = "0.00";
    String codUnidadMedida = "", codProducto = "", codProductoSUNAT = "", desItem = "";
    
    String mtoIgvItem = "0.00", mtoBaseIgvItem = "0.00", porIgvItem = "0.00";
    String codTriIGV = "", nomTributoIgvItem = "", codTipTributoIgvItem = "", tipAfeIGV = "";
    
    String codTriISC = "", nomTributoIscItem = "", codTipTributoIscItem = "", tipSisISC = "";
    String mtoIscItem = "0.00", mtoBaseIscItem = "0.00", porIscItem = "";
    
    String codTriOtro = "", nomTributoOtroItem = "", codTipTributoOtroItem = "";
    String mtoTriOtroItem = "0.00", mtoBaseTriOtroItem = "0.00", porTriOtroItem = "";
    
    String codTriIcbper = "", nomTributoIcbperItem = "", codTipTributoIcbperItem = "";
    String mtoTriIcbperItem = "0.00", ctdBolsasTriIcbperItem = "0.00", mtoTriIcbperUnidad = "0.00";

    
    List<Map<String, Object>> detalle = (this.objectoJson.get("detalle") != null) ? (List<Map<String, Object>>)this.objectoJson.get("detalle") : new ArrayList<>();

    
    String[] idArchivo = this.nombreArchivo.split("\\-");
    
    String identificadorFirmaSwf = "SIGN";
    Random calcularRnd = new Random();
    Integer codigoFacturadorSwf = Integer.valueOf((int)(calcularRnd.nextDouble() * 1000000.0D));
    
    log.debug("GenerarDocumentosServiceImpl.generarFacturaJson...Cabecera de Factura");
    Map<String, Object> factura = null;
    
    factura = new HashMap<>();
    factura.put("tipOperacion", tipOperacion);
    factura.put("fecEmision", fecEmision);
    factura.put("horEmision", horEmision);
    factura.put("fecVencimiento", fecVencimiento);
    factura.put("codLocalEmisor", codLocalEmisor);
    factura.put("tipDocUsuario", tipDocUsuario);
    factura.put("numDocUsuario", numDocUsuario);
    factura.put("rznSocialUsuario", rznSocialUsuario);
    factura.put("moneda", tipMoneda);



    
    factura.put("sumTotTributos", sumTotTributos);
    factura.put("sumTotValVenta", sumTotValVenta);
    factura.put("sumPrecioVenta", sumPrecioVenta);
    factura.put("sumDescTotal", sumDescTotal);
    factura.put("sumOtrosCargos", sumOtrosCargos);
    factura.put("sumTotalAnticipos", sumTotalAnticipos);
    factura.put("sumImpVenta", sumImpVenta);

    
    factura.put("ublVersionId", ublVersionId);
    factura.put("customizationId", customizationId);

    
    factura.put("ublVersionIdSwf", "2.0");
    factura.put("CustomizationIdSwf", "1.0");
    factura.put("nroCdpSwf", idArchivo[2] + "-" + idArchivo[3]);
    factura.put("tipCdpSwf", idArchivo[1]);
    
    factura.put("nroRucEmisorSwf", this.contri.getNumRuc());
    factura.put("tipDocuEmisorSwf", "6");
    factura.put("nombreComercialSwf", this.contri.getNombreComercial());
    factura.put("razonSocialSwf", this.contri.getRazonSocial());
    factura.put("ubigeoDomFiscalSwf", this.contri.getDireccion().getUbigeo());
    factura.put("direccionDomFiscalSwf", this.contri.getDireccion().getDireccion());
    factura.put("deparSwf", this.contri.getDireccion().getDepar());
    factura.put("provinSwf", this.contri.getDireccion().getProvin());
    factura.put("distrSwf", this.contri.getDireccion().getDistr());
    factura.put("urbanizaSwf", this.contri.getDireccion().getUrbaniza());
    
    factura.put("paisDomFiscalSwf", "PE");
    factura.put("codigoMontoDescuentosSwf", "2005");
    factura.put("codigoMontoOperGravadasSwf", "1001");
    factura.put("codigoMontoOperInafectasSwf", "1002");
    factura.put("codigoMontoOperExoneradasSwf", "1003");
    factura.put("idIgv", "1000");
    factura.put("codIgv", "IGV");
    factura.put("codExtIgv", "VAT");
    factura.put("idIsc", "2000");
    factura.put("codIsc", "ISC");
    factura.put("codExtIsc", "EXC");
    factura.put("idOtr", "9999");
    factura.put("codOtr", "OTROS");
    factura.put("codExtOtr", "OTH");
    factura.put("tipoCodigoMonedaSwf", "01");
    
    factura.put("identificadorFacturadorSwf", "Elaborador por Sistema del contribuyente");
    factura.put("codigoFacturadorSwf", codigoFacturadorSwf.toString());
    factura.put("identificadorFirmaSwf", identificadorFirmaSwf);
    
    log.debug("GenerarDocumentosServiceImpl.generarFacturaJson...Detalle de Factura");
    Iterator<Map<String, Object>> listaDetalle = detalle.iterator();
    List<Map<String, Object>> listaDetaFactura = new ArrayList<>();
    Map<String, Object> detalleFactura = null;
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
      detalleFactura = new HashMap<>();
      detalleFactura.put("unidadMedida", codUnidadMedida);
      detalleFactura.put("ctdUnidadItem", ctdUnidadItem);
      detalleFactura.put("codProducto", codProducto);
      detalleFactura.put("codProductoSUNAT", codProductoSUNAT);
      
      detalleFactura.put("desItem", desItem);
      detalleFactura.put("mtoValorUnitario", mtoValorUnitario);
      detalleFactura.put("sumTotTributosItem", sumTotTributosItem);
      
      detalleFactura.put("codTriIGV", codTriIGV);
      detalleFactura.put("mtoIgvItem", mtoIgvItem);
      detalleFactura.put("mtoBaseIgvItem", mtoBaseIgvItem);
      detalleFactura.put("nomTributoIgvItem", nomTributoIgvItem);
      detalleFactura.put("codTipTributoIgvItem", codTipTributoIgvItem);
      detalleFactura.put("tipAfeIGV", tipAfeIGV);
      detalleFactura.put("porIgvItem", porIgvItem);
      
      detalleFactura.put("codTriISC", codTriISC);
      detalleFactura.put("mtoIscItem", mtoIscItem);
      detalleFactura.put("mtoBaseIscItem", mtoBaseIscItem);
      detalleFactura.put("nomTributoIscItem", nomTributoIscItem);
      detalleFactura.put("codTipTributoIscItem", codTipTributoIscItem);
      detalleFactura.put("tipSisISC", tipSisISC);
      detalleFactura.put("porIscItem", porIscItem);
      
      detalleFactura.put("mtoPrecioVentaUnitario", mtoPrecioVentaUnitario);
      detalleFactura.put("mtoValorVentaItem", mtoValorVentaItem);
      detalleFactura.put("mtoValorReferencialUnitario", mtoValorReferencialUnitario);
      
      detalleFactura.put("codTriOtro", codTriOtro);
      detalleFactura.put("mtoTriOtroItem", mtoTriOtroItem);
      detalleFactura.put("mtoBaseTriOtroItem", mtoBaseTriOtroItem);
      detalleFactura.put("nomTributoOtroItem", nomTributoOtroItem);
      detalleFactura.put("codTipTributoOtroItem", codTipTributoOtroItem);
      detalleFactura.put("porTriOtroItem", porTriOtroItem);
      
      detalleFactura.put("codTriIcbper", codTriIcbper);
      detalleFactura.put("mtoTriIcbperItem", mtoTriIcbperItem);
      detalleFactura.put("ctdBolsasTriIcbperItem", ctdBolsasTriIcbperItem);
      detalleFactura.put("nomTributoIcbperItem", nomTributoIcbperItem);
      detalleFactura.put("codTipTributoIcbperItem", codTipTributoIcbperItem);
      detalleFactura.put("mtoTriIcbperUnidad", mtoTriIcbperUnidad);

      
      detalleFactura.put("lineaSwf", linea);
      detalleFactura.put("tipoCodiMoneGratiSwf", "02");
      
      listaDetaFactura.add(detalleFactura);
    } 
    
    factura.put("listaDetalle", listaDetaFactura);
    
    generarComunesJson(this.objectoJson, factura);
    
    return factura;
  }


  
  public byte[] parse(String templatesPath) throws ParserException {
    return parse(templatesPath, plantillaSeleccionada);
  }
}
