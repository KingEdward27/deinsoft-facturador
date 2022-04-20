package com.deinsoft.efacturador3commons.parser.json;

import com.deinsoft.efacturador3commons.parser.ParserException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class JsonCpeAbstractParser
{
  private static final Logger log = LoggerFactory.getLogger(JsonCpeAbstractParser.class);









  
  protected void generarComunesJson(HashMap<String, Object> objectoJson, Map<String, Object> comprobante) {
    Map<String, Object> cabecera = (objectoJson.get("cabecera") != null) ? (Map<String, Object>)objectoJson.get("cabecera") : new HashMap<>();


    
    List<Map<String, Object>> listaLeyendaJson = (objectoJson.get("leyendas") != null) ? (List<Map<String, Object>>)objectoJson.get("leyendas") : new ArrayList<>();
    
    String codLeyenda = "", desLeyenda = "";

    
    List<Map<String, Object>> listaTributosJson = (objectoJson.get("tributos") != null) ? (List<Map<String, Object>>)objectoJson.get("tributos") : new ArrayList<>();
    
    String ideTributo = "", nomTributo = "", codTipTributo = "", codCatTributo = "", mtoBaseImponible = "0.00";
    String mtoTributo = "0.00";


    
    List<Map<String, Object>> relacion = (objectoJson.get("relacionados") != null) ? (List<Map<String, Object>>)objectoJson.get("relacionados") : new ArrayList<>();

    
    String indDocRelacionado = "", numIdeAnticipo = "", tipDocRelacionado = "", numDocRelacionado = "";
    String tipDocEmisor = "", numDocEmisor = "", mtoDocRelacionado = "0.00";

    
    Map<String, Object> adiCabecera = null;
    String ctaBancoNacionDetraccion = "", codBienDetraccion = "", porDetraccion = "", mtoDetraccion = "", codMedioPago = "";
    String codPaisCliente = "", codUbigeoCliente = "", desDireccionCliente = "", codPaisEntrega = "";
    String codUbigeoEntrega = "", desDireccionEntrega = "";


    
    List<Map<String, Object>> listaVariablesGlobalesJson = (objectoJson.get("variablesGlobales") != null) ? (List<Map<String, Object>>)objectoJson.get("variablesGlobales") : new ArrayList<>();
    
    String tipVariableGlobal = "", codTipoVariableGlobal = "", porVariableGlobal = "", monMontoVariableGlobal = "";
    String mtoVariableGlobal = "", monBaseImponibleVariableGlobal = "", mtoBaseImpVariableGlobal = "";


    
    List<Map<String, Object>> listaAdicionalDetalleJson = (objectoJson.get("adicionalDetalle") != null) ? (List<Map<String, Object>>)objectoJson.get("adicionalDetalle") : new ArrayList<>();
    
    String idLinea = "", nomPropiedad = "", codPropiedad = "", valPropiedad = "", codBienPropiedad = "";
    String fecInicioPropiedad = "", horInicioPropiedad = "", fecFinPropiedad = "", numDiasPropiedad = "";
    String tipVariable = "", codTipoVariable = "", porVariable = "", monMontoVariable = "", mtoVariable = "";
    String monBaseImponibleVariable = "", mtoBaseImpVariable = "";


    
    adiCabecera = (cabecera.get("adicionalCabecera") != null) ? (Map<String, Object>)cabecera.get("adicionalCabecera") : null;
    
    if (adiCabecera != null) {
      
      ctaBancoNacionDetraccion = (adiCabecera.get("ctaBancoNacionDetraccion") != null) ? (String)adiCabecera.get("ctaBancoNacionDetraccion") : "";

      
      codBienDetraccion = (adiCabecera.get("codBienDetraccion") != null) ? (String)adiCabecera.get("codBienDetraccion") : "";
      
      porDetraccion = (adiCabecera.get("porDetraccion") != null) ? (String)adiCabecera.get("porDetraccion") : "";
      mtoDetraccion = (adiCabecera.get("mtoDetraccion") != null) ? (String)adiCabecera.get("mtoDetraccion") : "";
      codMedioPago = (adiCabecera.get("codMedioPago") != null) ? (String)adiCabecera.get("codMedioPago") : "";
      codPaisCliente = (adiCabecera.get("codPaisCliente") != null) ? (String)adiCabecera.get("codPaisCliente") : "";

      
      codUbigeoCliente = (adiCabecera.get("codUbigeoCliente") != null) ? (String)adiCabecera.get("codUbigeoCliente") : "";

      
      desDireccionCliente = (adiCabecera.get("desDireccionCliente") != null) ? (String)adiCabecera.get("desDireccionCliente") : "";
      
      codPaisEntrega = (adiCabecera.get("codPaisEntrega") != null) ? (String)adiCabecera.get("codPaisEntrega") : "";

      
      codUbigeoEntrega = (adiCabecera.get("codUbigeoEntrega") != null) ? (String)adiCabecera.get("codUbigeoEntrega") : "";

      
      desDireccionEntrega = (adiCabecera.get(desDireccionEntrega) != null) ? (String)adiCabecera.get("desDireccionEntrega") : "";


      
      comprobante.put("ctaBancoNacionDetraccion", ctaBancoNacionDetraccion);
      comprobante.put("codBienDetraccion", codBienDetraccion);
      comprobante.put("porDetraccion", porDetraccion);
      comprobante.put("mtoDetraccion", mtoDetraccion);
      comprobante.put("codMedioPago", codMedioPago);
      comprobante.put("codPaisCliente", codPaisCliente);
      comprobante.put("codUbigeoCliente", codUbigeoCliente);
      comprobante.put("desDireccionCliente", desDireccionCliente);
      comprobante.put("codPaisEntrega", codPaisEntrega);
      comprobante.put("codUbigeoEntrega", codUbigeoEntrega);
      comprobante.put("desDireccionEntrega", desDireccionEntrega);
      
      comprobante.put("codigoMonedaSolesSwf", "PEN");
    } 


    
    log.debug("GenerarDocumentosServiceImpl.generarComunesJson...Adicional de detalle de Comprobante");
    Iterator<Map<String, Object>> listaAdicionalDetalle = listaAdicionalDetalleJson.iterator();
    List<Map<String, Object>> listaAdiDeFactura = new ArrayList<>();
    Map<String, Object> adiDetalleFactura = null;
    Map<String, Object> adiDetaLista = null;
    while (listaAdicionalDetalle.hasNext()) {
      adiDetaLista = listaAdicionalDetalle.next();
      idLinea = (adiDetaLista.get("idLinea") != null) ? (String)adiDetaLista.get("idLinea") : "";
      nomPropiedad = (adiDetaLista.get("nomPropiedad") != null) ? (String)adiDetaLista.get("nomPropiedad") : "";
      codPropiedad = (adiDetaLista.get("codPropiedad") != null) ? (String)adiDetaLista.get("codPropiedad") : "";
      valPropiedad = (adiDetaLista.get("valPropiedad") != null) ? (String)adiDetaLista.get("valPropiedad") : "";
      
      codBienPropiedad = (adiDetaLista.get("codBienPropiedad") != null) ? (String)adiDetaLista.get("codBienPropiedad") : "";

      
      fecInicioPropiedad = (adiDetaLista.get("fecInicioPropiedad") != null) ? (String)adiDetaLista.get("fecInicioPropiedad") : "";

      
      horInicioPropiedad = (adiDetaLista.get("horInicioPropiedad") != null) ? (String)adiDetaLista.get("horInicioPropiedad") : "";
      
      fecFinPropiedad = (adiDetaLista.get("fecFinPropiedad") != null) ? (String)adiDetaLista.get("fecFinPropiedad") : "";

      
      numDiasPropiedad = (adiDetaLista.get("numDiasPropiedad") != null) ? (String)adiDetaLista.get("numDiasPropiedad") : "";

      
      tipVariable = (adiDetaLista.get("tipVariable") != null) ? (String)adiDetaLista.get("tipVariable") : "";
      codTipoVariable = (adiDetaLista.get("codTipoVariable") != null) ? (String)adiDetaLista.get("codTipoVariable") : "";
      
      porVariable = (adiDetaLista.get("porVariable") != null) ? (String)adiDetaLista.get("porVariable") : "";
      
      monMontoVariable = (adiDetaLista.get("monMontoVariable") != null) ? (String)adiDetaLista.get("monMontoVariable") : "";
      
      mtoVariable = (adiDetaLista.get("mtoVariable") != null) ? (String)adiDetaLista.get("mtoVariable") : "";
      
      monBaseImponibleVariable = (adiDetaLista.get("monBaseImponibleVariable") != null) ? (String)adiDetaLista.get("monBaseImponibleVariable") : "";

      
      mtoBaseImpVariable = (adiDetaLista.get("mtoBaseImpVariable") != null) ? (String)adiDetaLista.get("mtoBaseImpVariable") : "";


      
      adiDetalleFactura = new HashMap<>();
      adiDetalleFactura.put("idLinea", idLinea);
      adiDetalleFactura.put("nomPropiedad", nomPropiedad);
      
      adiDetalleFactura.put("codPropiedad", codPropiedad);
      adiDetalleFactura.put("valPropiedad", valPropiedad);
      adiDetalleFactura.put("codBienPropiedad", codBienPropiedad);
      adiDetalleFactura.put("fecInicioPropiedad", fecInicioPropiedad);
      adiDetalleFactura.put("horInicioPropiedad", horInicioPropiedad);
      adiDetalleFactura.put("fecFinPropiedad", fecFinPropiedad);
      adiDetalleFactura.put("numDiasPropiedad", numDiasPropiedad);
      
      adiDetalleFactura.put("tipVariable", tipVariable);
      adiDetalleFactura.put("codTipoVariable", codTipoVariable);
      adiDetalleFactura.put("porVariable", porVariable);
      adiDetalleFactura.put("monMontoVariable", monMontoVariable);
      adiDetalleFactura.put("mtoVariable", mtoVariable);
      adiDetalleFactura.put("monBaseImponibleVariable", monBaseImponibleVariable);
      adiDetalleFactura.put("mtoBaseImpVariable", mtoBaseImpVariable);
      
      listaAdiDeFactura.add(adiDetalleFactura);
    } 
    
    comprobante.put("listaAdicionalDetalle", listaAdiDeFactura);

    
    log.debug("GenerarDocumentosServiceImpl.generarComunesJson...Relacionados de Comprobante");
    Iterator<Map<String, Object>> listaRelacionado = relacion.iterator();
    List<Map<String, Object>> listaRelaFactura = new ArrayList<>();
    Map<String, Object> relacionadoFactura = null;
    Map<String, Object> relacionadoLista = null;
    while (listaRelacionado.hasNext()) {
      relacionadoLista = listaRelacionado.next();
      
      indDocRelacionado = (relacionadoLista.get("indDocRelacionado") != null) ? (String)relacionadoLista.get("indDocRelacionado") : "";

      
      numIdeAnticipo = (relacionadoLista.get("numIdeAnticipo") != null) ? (String)relacionadoLista.get("numIdeAnticipo") : "";

      
      tipDocRelacionado = (relacionadoLista.get("tipDocRelacionado") != null) ? (String)relacionadoLista.get("tipDocRelacionado") : "";

      
      numDocRelacionado = (relacionadoLista.get("numDocRelacionado") != null) ? (String)relacionadoLista.get("numDocRelacionado") : "";
      
      tipDocEmisor = (relacionadoLista.get("tipDocEmisor") != null) ? (String)relacionadoLista.get("tipDocEmisor") : "";
      
      numDocEmisor = (relacionadoLista.get("numDocEmisor") != null) ? (String)relacionadoLista.get("numDocEmisor") : "";

      
      mtoDocRelacionado = (relacionadoLista.get("mtoDocRelacionado") != null) ? (String)relacionadoLista.get("mtoDocRelacionado") : "0.00";


      
      relacionadoFactura = new HashMap<>();
      relacionadoFactura.put("indDocRelacionado", indDocRelacionado);
      relacionadoFactura.put("numIdeAnticipo", numIdeAnticipo);
      relacionadoFactura.put("tipDocRelacionado", tipDocRelacionado);
      relacionadoFactura.put("numDocRelacionado", numDocRelacionado);
      relacionadoFactura.put("tipDocEmisor", tipDocEmisor);
      relacionadoFactura.put("numDocEmisor", numDocEmisor);
      relacionadoFactura.put("mtoDocRelacionado", mtoDocRelacionado);
      
      listaRelaFactura.add(relacionadoFactura);
    } 
    
    comprobante.put("listaRelacionado", listaRelaFactura);

    
    log.debug("GenerarDocumentosServiceImpl.generarComunesJson...Leyenda de Comprobante");
    Iterator<Map<String, Object>> listaTributosBloque = listaTributosJson.iterator();
    List<Map<String, Object>> listaTributos = new ArrayList<>();
    Map<String, Object> tributoJson = null;
    Map<String, Object> tributoMap = null;
    while (listaTributosBloque.hasNext()) {
      tributoJson = listaTributosBloque.next();
      ideTributo = (tributoJson.get("ideTributo") != null) ? (String)tributoJson.get("ideTributo") : "";
      nomTributo = (tributoJson.get("nomTributo") != null) ? (String)tributoJson.get("nomTributo") : "";
      codTipTributo = (tributoJson.get("codTipTributo") != null) ? (String)tributoJson.get("codTipTributo") : "";
      codCatTributo = (tributoJson.get("codCatTributo") != null) ? (String)tributoJson.get("codCatTributo") : "";
      
      mtoBaseImponible = (tributoJson.get("mtoBaseImponible") != null) ? (String)tributoJson.get("mtoBaseImponible") : "";
      
      mtoTributo = (tributoJson.get("mtoTributo") != null) ? (String)tributoJson.get("mtoTributo") : "";
      
      tributoMap = new HashMap<>();
      
      tributoMap.put("ideTributo", ideTributo);
      tributoMap.put("nomTributo", nomTributo);
      tributoMap.put("codTipTributo", codTipTributo);
      tributoMap.put("codCatTributo", codCatTributo);
      tributoMap.put("mtoBaseImponible", mtoBaseImponible);
      tributoMap.put("mtoTributo", mtoTributo);
      
      listaTributos.add(tributoMap);
    } 
    
    comprobante.put("listaTributos", listaTributos);

    
    log.debug("GenerarDocumentosServiceImpl.generarComunesJson... Variable Global de Comprobante");
    Iterator<Map<String, Object>> listaVariableSGlobalesBloque = listaVariablesGlobalesJson.iterator();
    List<Map<String, Object>> listaVariablesGlobales = new ArrayList<>();
    Map<String, Object> variableGlobalJson = null;
    Map<String, Object> variableGlobalMap = null;
    while (listaVariableSGlobalesBloque.hasNext()) {
      variableGlobalJson = listaVariableSGlobalesBloque.next();
      
      tipVariableGlobal = (variableGlobalJson.get("tipVariableGlobal") != null) ? (String)variableGlobalJson.get("tipVariableGlobal") : "";

      
      codTipoVariableGlobal = (variableGlobalJson.get("codTipoVariableGlobal") != null) ? (String)variableGlobalJson.get("codTipoVariableGlobal") : "";

      
      porVariableGlobal = (variableGlobalJson.get("porVariableGlobal") != null) ? (String)variableGlobalJson.get("porVariableGlobal") : "";

      
      monMontoVariableGlobal = (variableGlobalJson.get("monMontoVariableGlobal") != null) ? (String)variableGlobalJson.get("monMontoVariableGlobal") : "";

      
      mtoVariableGlobal = (variableGlobalJson.get("mtoVariableGlobal") != null) ? (String)variableGlobalJson.get("mtoVariableGlobal") : "";

      
      monBaseImponibleVariableGlobal = (variableGlobalJson.get("monBaseImponibleVariableGlobal") != null) ? (String)variableGlobalJson.get("monBaseImponibleVariableGlobal") : "";

      
      mtoBaseImpVariableGlobal = (variableGlobalJson.get("mtoBaseImpVariableGlobal") != null) ? (String)variableGlobalJson.get("mtoBaseImpVariableGlobal") : "";

      
      variableGlobalMap = new HashMap<>();
      
      variableGlobalMap.put("tipVariableGlobal", tipVariableGlobal);
      variableGlobalMap.put("codTipoVariableGlobal", codTipoVariableGlobal);
      variableGlobalMap.put("porVariableGlobal", porVariableGlobal);
      variableGlobalMap.put("monMontoVariableGlobal", monMontoVariableGlobal);
      variableGlobalMap.put("mtoVariableGlobal", mtoVariableGlobal);
      variableGlobalMap.put("monBaseImponibleVariableGlobal", monBaseImponibleVariableGlobal);
      variableGlobalMap.put("mtoBaseImpVariableGlobal", mtoBaseImpVariableGlobal);
      
      listaVariablesGlobales.add(variableGlobalMap);
    } 
    
    comprobante.put("listaVariablesGlobales", listaVariablesGlobales);

    
    log.debug("GenerarDocumentosServiceImpl.generarComunesJson...Leyenda de Comprobante");
    Iterator<Map<String, Object>> listaLeyendasBloque = listaLeyendaJson.iterator();
    List<Map<String, Object>> listaLeyendas = new ArrayList<>();
    Map<String, Object> leyendaJson = null;
    Map<String, Object> leyendaMap = null;
    while (listaLeyendasBloque.hasNext()) {
      leyendaJson = listaLeyendasBloque.next();
      codLeyenda = (leyendaJson.get("codLeyenda") != null) ? (String)leyendaJson.get("codLeyenda") : "";
      desLeyenda = (leyendaJson.get("desLeyenda") != null) ? (String)leyendaJson.get("desLeyenda") : "";
      
      leyendaMap = new HashMap<>();
      leyendaMap.put("codigo", codLeyenda);
      leyendaMap.put("descripcion", desLeyenda);
      
      listaLeyendas.add(leyendaMap);
    } 
    
    comprobante.put("listaLeyendas", listaLeyendas);
  }


  
  public byte[] parse(String templatesPath, String plantillaSeleccionada) throws ParserException {
    try {
      File archivoFTL = new File(templatesPath + "/" + plantillaSeleccionada);
      
      if (!archivoFTL.exists()) {
        throw new ParserException("No existe la plantilla para el tipo documento a generar XML (Archivo FTL).");
      }
      
      Configuration cfg = new Configuration();
      cfg.setDirectoryForTemplateLoading(new File(templatesPath));
      cfg.setDefaultEncoding("ISO8859_1");
      cfg.setLocale(Locale.US);
      cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
      
      Template temp = cfg.getTemplate(plantillaSeleccionada);
      
      StringWriter out = new StringWriter();
      
      Map<String, Object> root = pipeToMap();
      
      temp.process(root, out);
      
      log.debug("SoftwareFacturadorController.formatoPlantillaXml...Final Procesamiento");
      
      return out.toString().getBytes();
    }
    catch (IOException e) {
      throw new ParserException("La ruta de las plantillas no es correcta: " + templatesPath, e);
    } catch (TemplateException e) {
      throw new ParserException("Error al tratar de crear el archivo XML: ", e);
    } 
  }
  
  public abstract Map<String, Object> pipeToMap() throws ParserException;
}
