package com.deinsoft.efacturador3commons.parser.pipe;

import com.deinsoft.efacturador3commons.parser.ParserException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.jboss.weld.exceptions.IllegalArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class PipeCpeAbstractParser
{
  private static final Logger log = LoggerFactory.getLogger(PipeCpeAbstractParser.class);




  
  protected void formatoComunes(Map<String, Object> comprobante, String archivoRelacionado, String archivoAdiCabecera, String archivoAdiDetalle, String archivoLeyendas, String archivoAdiTributos, String archivoAdiVariableGlobal) {
    try {
      log.debug("GenerarDocumentosServiceImpl.formatoFactura...Iniciando");
      String cadena = "";
      Integer activarRelacionado = Integer.valueOf(0), activarAdiCabecera = Integer.valueOf(0), activarAdiTributos = Integer.valueOf(0), activarAdiDetalle = Integer.valueOf(0);
      Integer activarLeyendas = Integer.valueOf(0), activarVariablesGlobales = Integer.valueOf(0);


      
      File fileArchivoRelacionado = new File(archivoRelacionado);
      if (fileArchivoRelacionado.exists()) {
        activarRelacionado = Integer.valueOf(1);
      }
      File fileArchivoAdiCabecera = new File(archivoAdiCabecera);
      if (fileArchivoAdiCabecera.exists()) {
        activarAdiCabecera = Integer.valueOf(1);
      }
      File fileArchivoAdiTributos = new File(archivoAdiTributos);
      if (fileArchivoAdiTributos.exists()) {
        activarAdiTributos = Integer.valueOf(1);
      }
      File fileArchivoAdiDetalle = new File(archivoAdiDetalle);
      if (fileArchivoAdiDetalle.exists()) {
        activarAdiDetalle = Integer.valueOf(1);
      }
      File fileArchivoLeyendas = new File(archivoLeyendas);
      if (fileArchivoLeyendas.exists()) {
        activarLeyendas = Integer.valueOf(1);
      }
      File fileVariablesGlobales = new File(archivoAdiVariableGlobal);
      if (fileVariablesGlobales.exists()) {
        activarVariablesGlobales = Integer.valueOf(1);
      }
      
      if (activarAdiCabecera.intValue() == 1) {

        
        FileReader fArchivoAdiCabecera = new FileReader(archivoAdiCabecera);
        
        BufferedReader bArchivoAdiCabecera = new BufferedReader(fArchivoAdiCabecera);
        if ((cadena = bArchivoAdiCabecera.readLine()) != null) {
          String[] registro = cadena.split("\\|");
          
          if (registro.length != 11) {
            try {
              bArchivoAdiCabecera.close();
            } catch (IOException iOException) {}
            
            throw new RuntimeException("El archivo adicionales cabecera no continene la cantidad de datos esperada (11 columnas).");
          } 
          
          comprobante.put("ctaBancoNacionDetraccion", registro[0]);
          comprobante.put("codBienDetraccion", registro[1]);
          comprobante.put("porDetraccion", registro[2]);
          comprobante.put("mtoDetraccion", registro[3]);
          comprobante.put("codMedioPago", registro[4]);
          comprobante.put("codPaisCliente", registro[5]);
          comprobante.put("codUbigeoCliente", registro[6]);
          comprobante.put("desDireccionCliente", registro[7]);
          comprobante.put("codPaisEntrega", registro[8]);
          comprobante.put("codUbigeoEntrega", registro[9]);
          comprobante.put("desDireccionEntrega", registro[10]);
          comprobante.put("codigoMonedaSolesSwf", "PEN");
        } 
        
        try {
          bArchivoAdiCabecera.close();
        } catch (IOException iOException) {}
      } 


      
//      List<Map<String, Object>> listaTributos = new ArrayList<>();
//      Map<String, Object> tributo = null;
//      if (activarAdiTributos.intValue() == 1) {
//        
//        FileReader fArchivoAdiTributos = new FileReader(archivoAdiTributos);
//        BufferedReader bArchivoAdiTributos = new BufferedReader(fArchivoAdiTributos);
//        while ((cadena = bArchivoAdiTributos.readLine()) != null) {
//          String[] registro = cadena.split("\\|");
//          
//          if (registro.length != 5) {
//            bArchivoAdiTributos.close();
//            throw new RuntimeException("El archivo de tributos de cabecera no contiene la cantidad de datos esperada (5 columnas).");
//          } 
//          
//          tributo = new HashMap<>();
//          tributo.put("ideTributo", registro[0]);
//          tributo.put("nomTributo", registro[1]);
//          tributo.put("codTipTributo", registro[2]);
//          tributo.put("mtoBaseImponible", registro[3]);
//          tributo.put("mtoTributo", registro[4]);
//          tributo.put("codigoMonedaSolesSwf", "PEN");
//          
//          listaTributos.add(tributo);
//        } 
//
//        
//        bArchivoAdiTributos.close();
//        comprobante.put("listaTributos", listaTributos);
//      }
//      else {
//        
//        comprobante.put("listaTributos", listaTributos);
//      } 

      
      List<Map<String, Object>> listaAdicionalDetalle = new ArrayList<>();
      Map<String, Object> adicionalDetalle = null;
      if (activarAdiDetalle.intValue() == 1) {
        FileReader fArchivoAdiDetalle = new FileReader(archivoAdiDetalle);
        BufferedReader bArchivoAdiDetalle = new BufferedReader(fArchivoAdiDetalle);
        
        while ((cadena = bArchivoAdiDetalle.readLine()) != null) {
          String[] registro = cadena.split("\\|");
          
          if (registro.length != 16) {
            bArchivoAdiDetalle.close();
            throw new RuntimeException("El archivo de Adicional de Detalle no continene la cantidad de datos esperada (16 columnas).");
          } 
          
          adicionalDetalle = new HashMap<>();
          adicionalDetalle.put("idLinea", registro[0]);
          adicionalDetalle.put("nomPropiedad", registro[1]);
          adicionalDetalle.put("codPropiedad", registro[2]);
          adicionalDetalle.put("valPropiedad", registro[3]);
          adicionalDetalle.put("codBienPropiedad", registro[4]);
          adicionalDetalle.put("fecInicioPropiedad", registro[5]);
          adicionalDetalle.put("horInicioPropiedad", registro[6]);
          adicionalDetalle.put("fecFinPropiedad", registro[7]);
          adicionalDetalle.put("numDiasPropiedad", registro[8]);
          
          adicionalDetalle.put("tipVariable", registro[9]);
          adicionalDetalle.put("codTipoVariable", registro[10]);
          adicionalDetalle.put("porVariable", registro[11]);
          adicionalDetalle.put("monMontoVariable", registro[12]);
          adicionalDetalle.put("mtoVariable", registro[13]);
          adicionalDetalle.put("monBaseImponibleVariable", registro[14]);
          adicionalDetalle.put("mtoBaseImpVariable", registro[15]);
          
          listaAdicionalDetalle.add(adicionalDetalle);
        } 

        
        bArchivoAdiDetalle.close();
        comprobante.put("listaAdicionalDetalle", listaAdicionalDetalle);
      }
      else {
        
        comprobante.put("listaAdicionalDetalle", listaAdicionalDetalle);
      } 

      
//      List<Map<String, Object>> listaRelacionado = new ArrayList<>();
//      Map<String, Object> relacionado = null;
//      if (activarRelacionado.intValue() == 1) {
//        
//        FileReader fArchivoRelacionado = new FileReader(archivoRelacionado);
//        BufferedReader bArchivoRelacionado = new BufferedReader(fArchivoRelacionado);
//        while ((cadena = bArchivoRelacionado.readLine()) != null) {
//          String[] registro = cadena.split("\\|");
//          
//          if (registro.length != 7) {
//            bArchivoRelacionado.close();
//            throw new RuntimeException("El archivo documento relacionado no continene la cantidad de datos esperada (7 columnas).");
//          } 
//          
//          relacionado = new HashMap<>();
//          relacionado.put("indDocRelacionado", registro[0]);
//          relacionado.put("numIdeAnticipo", registro[1]);
//          relacionado.put("tipDocRelacionado", registro[2]);
//          relacionado.put("numDocRelacionado", registro[3]);
//          relacionado.put("tipDocEmisor", registro[4]);
//          relacionado.put("numDocEmisor", registro[5]);
//          relacionado.put("mtoDocRelacionado", registro[6]);
//          
//          listaRelacionado.add(relacionado);
//        } 
//
//        
//        bArchivoRelacionado.close();
//        comprobante.put("listaRelacionado", listaRelacionado);
//      }
//      else {
//        
//        comprobante.put("listaRelacionado", listaRelacionado);
//      } 

      
      List<Map<String, Object>> listaLeyendas = new ArrayList<>();
      Map<String, Object> leyendas = null;
      
      if (activarLeyendas.intValue() == 1) {
        
        FileReader fArchivoLeyendas = new FileReader(archivoLeyendas);
        BufferedReader bArchivoLeyendas = new BufferedReader(fArchivoLeyendas);
        while ((cadena = bArchivoLeyendas.readLine()) != null) {
          String[] registro = cadena.split("\\|");
          
          if (registro.length != 2) {
            bArchivoLeyendas.close();
            throw new RuntimeException("El archivo de leyendas no continene la cantidad de datos esperada (2 columnas).");
          } 
          
          leyendas = new HashMap<>();
          leyendas.put("codigo", registro[0]);
          leyendas.put("descripcion", registro[1]);
          
          listaLeyendas.add(leyendas);
        } 
        
        bArchivoLeyendas.close();
        comprobante.put("listaLeyendas", listaLeyendas);
      }
      else {
        
        comprobante.put("listaLeyendas", listaLeyendas);
      } 

      
//      List<Map<String, Object>> listaVariablesGlobales = new ArrayList<>();
//      Map<String, Object> variablesGlobales = null;
//      
//      if (activarVariablesGlobales.intValue() == 1)
//      {
//        FileReader fArchivoVariablesGlobales = new FileReader(archivoAdiVariableGlobal);
//        BufferedReader bArchivoVariablesGlobales = new BufferedReader(fArchivoVariablesGlobales);
//        while ((cadena = bArchivoVariablesGlobales.readLine()) != null) {
//          String[] registro = cadena.split("\\|");
//          
//          if (registro.length != 7) {
//            bArchivoVariablesGlobales.close();
//            throw new RuntimeException("El archivo de Adicional de Variables Globales no continene la cantidad de datos esperada (7 columnas).");
//          } 
//          
//          variablesGlobales = new HashMap<>();
//          variablesGlobales.put("tipVariableGlobal", registro[0]);
//          variablesGlobales.put("codTipoVariableGlobal", registro[1]);
//          variablesGlobales.put("porVariableGlobal", registro[2]);
//          variablesGlobales.put("monMontoVariableGlobal", registro[3]);
//          variablesGlobales.put("mtoVariableGlobal", registro[4]);
//          variablesGlobales.put("monBaseImponibleVariableGlobal", registro[5]);
//          variablesGlobales.put("mtoBaseImpVariableGlobal", registro[6]);
//          
//          listaVariablesGlobales.add(variablesGlobales);
//        } 
//        
//        bArchivoVariablesGlobales.close();
//        comprobante.put("listaVariablesGlobales", listaVariablesGlobales);
//      }
//      else
//      {
//        comprobante.put("listaVariablesGlobales", listaVariablesGlobales);
//      }
    
    } catch (FileNotFoundException e1) {
      throw new IllegalArgumentException("Error procesando archivo Json", e1);
    } catch (IOException e) {
      throw new IllegalArgumentException("Error procesando archivo Json", e);
    } 
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