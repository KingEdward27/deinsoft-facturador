package com.deinsoft.efacturador3commons.parser;

import com.deinsoft.efacturador3commons.model.Empresa;
import com.deinsoft.efacturador3commons.model.FacturaElectronica;
import com.deinsoft.efacturador3commons.model.Contribuyente;
import com.deinsoft.efacturador3commons.model.ResumenBaja;
import com.deinsoft.efacturador3commons.model.ResumenDiario;
import com.deinsoft.efacturador3commons.parser.pipe.PipeFacturaParser;
import com.deinsoft.efacturador3commons.parser.pipe.PipeGuiaParser;
import com.deinsoft.efacturador3commons.parser.pipe.PipeNotaCreditoParser;
import com.deinsoft.efacturador3commons.parser.pipe.PipeNotaDebitoParser;
import com.deinsoft.efacturador3commons.parser.pipe.PipePercepcionParser;
import com.deinsoft.efacturador3commons.parser.pipe.PipeResumenBajaParser;
import com.deinsoft.efacturador3commons.parser.pipe.PipeResumenBoletaParser;
import com.deinsoft.efacturador3commons.parser.pipe.PipeResumenReversionParser;
import com.deinsoft.efacturador3commons.parser.pipe.PipeRetencionParser;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;









public class PipeParserFactory
{
  private static final Logger log = LoggerFactory.getLogger(PipeParserFactory.class);



  
  public Parser createParser(Empresa contri, String tipoDocumento, FacturaElectronica facturaElectronica) {
//    if ("RA".equals(tipoDocumento)) {
//      log.debug("GenerarDocumentosServiceImpl.formatoPlantillaXml...Aplicando Formato de Bajas");
//      return (Parser)new PipeResumenBajaParser(contri, archivos, nombreArchivo);
//    } 
//    
//    if ("RC".equals(tipoDocumento)) {
//      log.debug("GenerarDocumentosServiceImpl.formatoPlantillaXml...Aplicando Formato de Resumen");
//      return (Parser)new PipeResumenBoletaParser(contri, archivos, nombreArchivo);
//    } 
//    
//    if ("RR".equals(tipoDocumento)) {
//      log.debug("GenerarDocumentosServiceImpl.formatoPlantillaXml...Aplicando Formato de Reversion");
//      return (Parser)new PipeResumenReversionParser(contri, archivos, nombreArchivo);
//    } 
//
//    
    if ("01".equals(tipoDocumento) || "03"
      .equals(tipoDocumento)) {
      log.debug("GenerarDocumentosServiceImpl.formatoPlantillaXml...Aplicando Formato Factura");
      return (Parser)new PipeFacturaParser(contri, facturaElectronica);
    } 

    
    if ("07".equals(tipoDocumento))
    {
      return (Parser)new PipeNotaCreditoParser(contri, facturaElectronica);
    }

    
    if ("08".equals(tipoDocumento))
    {
      return (Parser)new PipeNotaDebitoParser(contri, facturaElectronica);
    }
//
//    
//    if ("20".equals(tipoDocumento))
//    {
//      return (Parser)new PipeRetencionParser(contri, archivos, nombreArchivo);
//    }
//
//    
//    if ("40".equals(tipoDocumento))
//    {
//      return (Parser)new PipePercepcionParser(contri, archivos, nombreArchivo);
//    }
//    
//    if ("09".equals(tipoDocumento))
//    {
//      return (Parser)new PipeGuiaParser(contri, archivos, nombreArchivo);
//    }
    
    throw new IllegalArgumentException("tipo de comprobante no soportado");
  }
  public Parser createParserResumenBaja(ResumenBaja resumenBaja) {
      return (Parser)new PipeResumenBajaParser(resumenBaja);
  }
  public Parser createParserResumenDiario(ResumenDiario resumenDiario) {
      return (Parser)new PipeResumenBoletaParser(resumenDiario);
  }
}