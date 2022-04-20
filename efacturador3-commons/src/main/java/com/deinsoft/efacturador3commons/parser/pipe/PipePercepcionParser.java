package com.deinsoft.efacturador3commons.parser.pipe;

import com.deinsoft.efacturador3commons.model.Contribuyente;
import com.deinsoft.efacturador3commons.parser.Parser;
import com.deinsoft.efacturador3commons.parser.ParserException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class PipePercepcionParser
  extends PipeCpeAbstractParser
  implements Parser
{
  private static final Logger log = LoggerFactory.getLogger(PipePercepcionParser.class);

  
  private static String plantillaSeleccionada = "ConvertirPercepcionXML.ftl";

  
  private String archivoCabecera;

  
  private String archivoDetalle;
  
  private String nombreArchivo;
  
  private Contribuyente contri;

  /*     */ 
  
  public PipePercepcionParser(Contribuyente contri, String[] archivos, String nombreArchivo) {
    this.contri = contri;
    this.nombreArchivo = nombreArchivo;
    
    this.archivoCabecera = archivos[0];
    this.archivoDetalle = archivos[1];
  }

  
  public Map<String, Object> pipeToMap() throws ParserException {
    log.debug("SoftwareFacturadorController.formatoResumenBajas...Inicio Procesamiento");

    
    String[] datosArchivo = this.nombreArchivo.split("\\-");
    
    String identificadorFirmaSwf = "SIGN";
    Random calcularRnd = new Random();
    Integer codigoFacturadorSwf = Integer.valueOf((int)(calcularRnd.nextDouble() * 1000000.0D));

    
    log.debug("SoftwareFacturadorController.formatoResumenBajas...Leyendo Archivo: " + this.archivoCabecera);
    Map<String, Object> Percepcion = new HashMap<>();

    
    Path fileCabecera = Paths.get(this.archivoCabecera, new String[0]);
    
    if (!Files.exists(fileCabecera, new java.nio.file.LinkOption[0]))
    {
      throw new ParserException("El archivo no existe: " + this.archivoCabecera);
    }

    
    try(InputStream in = Files.newInputStream(fileCabecera, new java.nio.file.OpenOption[0]); 
        BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
      String cadena = null;
      
      while ((cadena = reader.readLine()) != null) {
        String[] registro = cadena.split("\\|");
        
        if (registro.length != 19) {
          throw new ParserException("El archivo cabecera no continene la cantidad de datos esperada (19 columnas).");
        }


        
        Percepcion = new HashMap<>();
        Percepcion.put("fecEmision", registro[0]);
        Percepcion.put("nroDocIdeReceptor", registro[1]);
        Percepcion.put("tipDocIdeReceptor", registro[2]);
        Percepcion.put("desNomComReceptor", registro[3]);
        
        Percepcion.put("desUbiReceptor", registro[4]);
        Percepcion.put("desDirReceptor", registro[5]);
        Percepcion.put("desUrbReceptor", registro[6]);
        Percepcion.put("desDepReceptor", registro[7]);
        Percepcion.put("desProReceptor", registro[8]);
        Percepcion.put("desDisReceptor", registro[9]);
        Percepcion.put("codPaisReceptor", registro[10]);
        Percepcion.put("rznSocialReceptor", registro[11]);
        
        Percepcion.put("codRegPercepcion", registro[12]);
        Percepcion.put("tasPercepcion", registro[13]);
        Percepcion.put("desObsPercepcion", registro[14]);
        Percepcion.put("mtoTotPercepcion", registro[15]);
        Percepcion.put("tipMonPercepcion", registro[16]);
        Percepcion.put("mtoImpTotPagPercepcion", registro[17]);
        Percepcion.put("tipMonImpTotPagPercepcion", registro[18]);

        
        Percepcion.put("ublVersionIdSwf", "2.0");
        Percepcion.put("CustomizationIdSwf", "1.0");
        Percepcion.put("nroCdpSwf", datosArchivo[2] + "-" + datosArchivo[3]);
        Percepcion.put("tipCdpSwf", datosArchivo[1]);
        
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
        
        Percepcion.put("identificadorFacturadorSwf", "Elaborado por Sistema de Emision Electronica Facturador SUNAT (SEE-SFS) 1.3.2");
        Percepcion.put("codigoFacturadorSwf", codigoFacturadorSwf.toString());
        Percepcion.put("identificadorFirmaSwf", identificadorFirmaSwf);

      
      }


    
    }
    catch (IOException x) {
      throw new ParserException("No se pudo leer el archivo cabecera: " + this.archivoCabecera, x);
    } 

    
    Path fileDetalle = Paths.get(this.archivoDetalle, new String[0]);
    
    if (!Files.exists(fileDetalle, new java.nio.file.LinkOption[0]))
    {
      throw new ParserException("El archivo no existe: " + this.archivoDetalle);
    }

    
    List<Map<String, Object>> listaDetalle = new ArrayList<>();
    Map<String, Object> detalle = null;
    
    try(InputStream in = Files.newInputStream(fileDetalle, new java.nio.file.OpenOption[0]); 
        BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
      String cadena = null;
      
      Integer linea = Integer.valueOf(0);
      
      while ((cadena = reader.readLine()) != null) {
        String[] registro = cadena.split("\\|");
        if (registro.length != 18 && registro.length != 5)
        {
          throw new ParserException("El archivo detalle no continene la cantidad de datos esperada (18 columnas).");
        }


        
        linea = Integer.valueOf(linea.intValue() + 1);
        detalle = new HashMap<>();
        detalle.put("tipDocRelacionado", registro[0]);
        detalle.put("nroDocRelacionado", registro[1]);
        detalle.put("fecEmiDocRelacionado", registro[2]);
        detalle.put("mtoImpTotDocRelacionado", registro[3]);
        detalle.put("tipMonDocRelacionado", registro[4]);
        detalle.put("fecPagDocRelacionado", registro[5]);
        detalle.put("nroPagDocRelacionado", registro[6]);
        detalle.put("mtoPagDocRelacionado", registro[7]);
        detalle.put("tipMonPagDocRelacionado", registro[8]);
        
        detalle.put("mtoPerDocRelacionado", registro[9]);
        detalle.put("tipMonPerDocRelacionado", registro[10]);
        detalle.put("fecPerDocRelacionado", registro[11]);
        detalle.put("mtoTotPagNetoDocRelacionado", registro[12]);
        detalle.put("tipMonTotPagNetoDocRelacionado", registro[13]);
        
        detalle.put("tipMonRefTipCambio", registro[14]);
        detalle.put("tipMonObjTipCambio", registro[15]);
        detalle.put("facTipCambio", registro[16]);
        detalle.put("fecTipCambio", registro[17]);




        
        Percepcion.put("codigofacturadorSwf", codigoFacturadorSwf.toString());
        Percepcion.put("identificadorFirmaSwf", identificadorFirmaSwf);
        
        listaDetalle.add(detalle);
      } 

      
      Percepcion.put("listaDetalle", listaDetalle);


    
    }
    catch (IOException x) {
      throw new ParserException("No se pudo leer el archivo detalle: " + this.archivoDetalle, x);
    } 


    
    log.debug("SoftwareFacturadorController.formatoResumenBajas...Fin Procesamiento");
    
    return Percepcion;
  }




  
  public byte[] parse(String templatesPath) throws ParserException {
    return parse(templatesPath, plantillaSeleccionada);
  }
}