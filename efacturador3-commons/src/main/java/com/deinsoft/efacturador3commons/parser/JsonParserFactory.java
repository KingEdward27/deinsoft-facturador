package com.deinsoft.efacturador3commons.parser;

import com.deinsoft.efacturador3commons.model.Contribuyente;
import com.deinsoft.efacturador3commons.parser.json.JsonFacturaParser;
import com.deinsoft.efacturador3commons.parser.json.JsonGuiaParser;
import com.deinsoft.efacturador3commons.parser.json.JsonNotaCreditoParser;
import com.deinsoft.efacturador3commons.parser.json.JsonNotaDebitoParser;
import com.deinsoft.efacturador3commons.parser.json.JsonPercepcionParser;
import com.deinsoft.efacturador3commons.parser.json.JsonResumenBajaParser;
import com.deinsoft.efacturador3commons.parser.json.JsonResumenBoletaParser;
import com.deinsoft.efacturador3commons.parser.json.JsonResumenReversionParser;
import com.deinsoft.efacturador3commons.parser.json.JsonRetencionParser;
import java.util.HashMap;
















public class JsonParserFactory
{
  public Parser createParser(Contribuyente contri, String tipoDocumento, HashMap<String, Object> objectoJson, String nombreArchivo) {
    if ("RA".equals(tipoDocumento))
    {
      return (Parser)new JsonResumenBajaParser(contri, objectoJson, nombreArchivo);
    }
    
    if ("RC".equals(tipoDocumento)) {
      return (Parser)new JsonResumenBoletaParser(contri, objectoJson, nombreArchivo);
    }
    
    if ("RR".equals(tipoDocumento)) {
      return (Parser)new JsonResumenReversionParser(contri, objectoJson, nombreArchivo);
    }
    
    if ("01".equals(tipoDocumento) || "03"
      .equals(tipoDocumento)) {
      return (Parser)new JsonFacturaParser(contri, objectoJson, nombreArchivo);
    }
    
    if ("07".equals(tipoDocumento)) {
      return (Parser)new JsonNotaCreditoParser(contri, objectoJson, nombreArchivo);
    }
    
    if ("08".equals(tipoDocumento)) {
      return (Parser)new JsonNotaDebitoParser(contri, objectoJson, nombreArchivo);
    }
    
    if ("20".equals(tipoDocumento)) {
      return (Parser)new JsonRetencionParser(contri, objectoJson, nombreArchivo);
    }
    
    if ("40".equals(tipoDocumento)) {
      return (Parser)new JsonPercepcionParser(contri, objectoJson, nombreArchivo);
    }
    
    if ("09".equals(tipoDocumento)) {
      return (Parser)new JsonGuiaParser(contri, objectoJson, nombreArchivo);
    }
    
    throw new IllegalArgumentException("tipo de comprobante no soportado");
  }
}