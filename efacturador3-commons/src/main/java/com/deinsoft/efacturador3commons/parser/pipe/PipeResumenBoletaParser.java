package com.deinsoft.efacturador3commons.parser.pipe;

import com.deinsoft.efacturador3commons.model.Contribuyente;
import com.deinsoft.efacturador3commons.model.ResumenDiario;
import com.deinsoft.efacturador3commons.model.ResumenDiarioDet;
import com.deinsoft.efacturador3commons.model.ResumenDiarioTax;
import com.deinsoft.efacturador3commons.parser.Parser;
import com.deinsoft.efacturador3commons.parser.ParserException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PipeResumenBoletaParser
        extends PipeCpeAbstractParser
        implements Parser {

    private static final Logger log = LoggerFactory.getLogger(PipeResumenBoletaParser.class);

    private static String plantillaSeleccionada = "ConvertirRBoletasXML.ftl";

//    private String archivoCabecera;
    private String archivoDetalleTributos;
//    private String nombreArchivo;
//    private Contribuyente contri;
    private ResumenDiario resumenD;

    /*     */
    public PipeResumenBoletaParser(ResumenDiario resumenD) {
//        this.contri = contri;
//        this.nombreArchivo = nombreArchivo;
//        this.archivoCabecera = archivos[0];
//        this.archivoDetalleTributos = archivos[1];
        this.resumenD = resumenD;
    }

    public Map<String, Object> pipeToMap() throws ParserException {
        log.debug("SoftwareFacturadorController.formatoResumenBajas...Inicio Procesamiento");

        Integer error = new Integer(0);

        log.debug("SoftwareFacturadorController.formatoResumenBajas...Leyendo Archivo: " + resumenD.getNombreArchivo());
        Map<String, Object> resumenGeneral = new HashMap<>();
        Map<String, Object> resumenDiario = null;
        List<Map<String, Object>> listaResumenDiario = new ArrayList<>();

//    Path file = Paths.get(this.archivoCabecera, new String[0]);
//    try(InputStream in = Files.newInputStream(file, new java.nio.file.OpenOption[0]); 
//        BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
//      String cadena = null;
//      
//      Integer linea = Integer.valueOf(0);
//      
//      while ((cadena = reader.readLine()) != null) {
//        String[] registro = cadena.split("\\|");
//        
//        if (registro.length != 23 && error.intValue() == 0) {
//          error = new Integer(1);
//          throw new ParserException("El archivo RDI no contiene la cantidad de columnas esperada (23 columnas).");
//        } 
//        
//        Integer integer1 = linea, integer2 = linea = Integer.valueOf(linea.intValue() + 1);
        log.debug("SoftwareFacturadorController.formatoResumen...nombreArchivo: " + resumenD.getNombreArchivo());
        String[] idArchivo = resumenD.getNombreArchivo().split("\\-");
        String idResumen = idArchivo[1] + "-" + idArchivo[2] + "-" + idArchivo[3];

//        String fecEmision = registro[0];
//        String fecResumen = registro[1];
//        String tipDocResumen = registro[2];
//        String idDocResumen = registro[3];
//        String tipDocUsuario = registro[4];
//        String numDocUsuario = registro[5];
//        String tipMoneda = registro[6];
//        String totValGrabado = registro[7];
//        String totValExoneado = registro[8];
//        String totValInafecto = registro[9];
//        String totValExportado = registro[10];
//        String totValGratuito = registro[11];
//        String totOtroCargo = registro[12];
//        String totImpCpe = registro[13];
//        String tipDocModifico = registro[14];
//        String serDocModifico = registro[15];
//        String numDocModifico = registro[16];
//        String tipRegPercepcion = registro[17];
//        String porPercepcion = registro[18];
//        String monBasePercepcion = registro[19];
//        String monPercepcion = registro[20];
//        String monTotIncPercepcion = registro[21];
//        String tipEstado = registro[22];
        String identificadorFirmaSwf = "SIGN";
        Random calcularRnd = new Random();
        Integer codigoFacturadorSwf = Integer.valueOf((int) (calcularRnd.nextDouble() * 1000000.0D));

//        if (linea.intValue() == 1) {
        resumenGeneral.put("nombreComercialSwf", resumenD.getEmpresa().getNombreComercial() == null ? resumenD.getEmpresa().getRazonSocial() : resumenD.getEmpresa().getNombreComercial());
        resumenGeneral.put("razonSocialSwf", resumenD.getEmpresa().getRazonSocial());
        resumenGeneral.put("nroRucEmisorSwf", resumenD.getEmpresa().getNumdoc());
        resumenGeneral.put("tipDocuEmisorSwf", "6");
        resumenGeneral.put("fechaEmision", new SimpleDateFormat("yyyy-MM-dd").format(resumenD.getFechaEmision()));
        resumenGeneral.put("fechaResumen", new SimpleDateFormat("yyyy-MM-dd").format(resumenD.getFechaResumen()));

        resumenGeneral.put("ublVersionIdSwf", "2.0");
        resumenGeneral.put("idResumen", idResumen);
        resumenGeneral.put("CustomizationIdSwf", "1.1");
        resumenGeneral.put("identificadorFacturadorSwf", "Elaborado por Sistema de Emision Electronica Facturador SUNAT (SEE-SFS) 1.3.2");

        resumenGeneral.put("codigoFacturadorSwf", codigoFacturadorSwf.toString());
        resumenGeneral.put("identificadorFirmaSwf", identificadorFirmaSwf);

        resumenDiario = new HashMap<>();
//          resumenDiario.put("tipDocResumen", tipDocResumen);
//          resumenDiario.put("idDocResumen", idDocResumen);
//          resumenDiario.put("tipDocUsuario", tipDocUsuario);
//          resumenDiario.put("numDocUsuario", numDocUsuario);
//          resumenDiario.put("moneda", tipMoneda);
//          resumenDiario.put("totValGrabado", totValGrabado);
//          resumenDiario.put("totValExoneado", totValExoneado);
//          resumenDiario.put("totValInafecto", totValInafecto);
//          resumenDiario.put("totValExportado", totValExportado);
//          resumenDiario.put("totValGratuito", totValGratuito);
//          resumenDiario.put("totOtroCargo", totOtroCargo);
//          resumenDiario.put("totImpCpe", totImpCpe);
//          resumenDiario.put("tipDocModifico", tipDocModifico);
//          resumenDiario.put("serDocModifico", serDocModifico);
//          resumenDiario.put("numDocModifico", numDocModifico);
//          resumenDiario.put("tipRegPercepcion", tipRegPercepcion);
//          resumenDiario.put("porPercepcion", porPercepcion);
//          resumenDiario.put("monBasePercepcion", monBasePercepcion);
//          resumenDiario.put("monPercepcion", monPercepcion);
//          resumenDiario.put("monTotIncPercepcion", monTotIncPercepcion);
//          resumenDiario.put("tipEstado", tipEstado);
//          resumenDiario.put("linea", linea);
//        }
//        else {
        int linea = 0;
        for (ResumenDiarioDet resumenDiarioLinea : resumenD.getListResumenDiarioDet()) {
            linea++;
            resumenDiario = new HashMap<>();
            resumenDiario.put("tipDocResumen", resumenDiarioLinea.getTipDocResumen());
            resumenDiario.put("idDocResumen", resumenDiarioLinea.getNroDocumento());
            resumenDiario.put("tipDocUsuario", resumenDiarioLinea.getTipDocUsuario());
            resumenDiario.put("numDocUsuario", resumenDiarioLinea.getNumDocUsuario());
            resumenDiario.put("moneda", resumenDiarioLinea.getMoneda());
            resumenDiario.put("totValGrabado", String.valueOf(resumenDiarioLinea.getTotValGrabado()));
            resumenDiario.put("totValExoneado", String.valueOf(resumenDiarioLinea.getTotValExonerado()));
            resumenDiario.put("totValInafecto", String.valueOf(resumenDiarioLinea.getTotValInafecto()));
            resumenDiario.put("totValExportado", String.valueOf(resumenDiarioLinea.getTotValExportado()));
            resumenDiario.put("totValGratuito", String.valueOf(resumenDiarioLinea.getTotValGratuito()));
            resumenDiario.put("totOtroCargo", String.valueOf(resumenDiarioLinea.getTotOtroCargo()));
            resumenDiario.put("totImpCpe", String.valueOf(resumenDiarioLinea.getTotImpCpe()));
            resumenDiario.put("tipDocModifico", resumenDiarioLinea.getTipDocModifico());
            resumenDiario.put("serDocModifico", resumenDiarioLinea.getSerDocModifico());
            resumenDiario.put("numDocModifico", resumenDiarioLinea.getNumDocModifico());
            resumenDiario.put("tipRegPercepcion", resumenDiarioLinea.getTipRegPercepcion() == null? "":resumenDiarioLinea.getTipRegPercepcion());
            resumenDiario.put("porPercepcion", resumenDiarioLinea.getPorPercepcion());
            resumenDiario.put("monBasePercepcion", resumenDiarioLinea.getMonBasePercepcion());
            resumenDiario.put("monPercepcion", resumenDiarioLinea.getMonPercepcion());
            resumenDiario.put("monTotIncPercepcion", resumenDiarioLinea.getMonTotIncPercepcion());
            resumenDiario.put("tipEstado", resumenDiarioLinea.getCondicion());
            resumenDiario.put("linea", linea);

            listaResumenDiario.add(resumenDiario);
        }

//        } 
//      } 
        resumenGeneral.put("listaResumen", listaResumenDiario);
//    }
//    catch (IOException x) {
//      throw new ParserException("No se pudo leer el archivo cabecera: " + this.archivoCabecera, x);
//    } 

//        Path fileDetalle = Paths.get(this.archivoDetalleTributos, new String[0]);
//
//        if (!Files.exists(fileDetalle, new java.nio.file.LinkOption[0])) {
//            throw new ParserException("El archivo no existe: " + this.archivoDetalleTributos);
//        }
        List<Map<String, Object>> listaDetalle = new ArrayList<>();
        Map<String, Object> detalle = null;

//        try (InputStream in = Files.newInputStream(fileDetalle, new java.nio.file.OpenOption[0]);
//                BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
//            String cadena = null;
//
//            Integer linea = Integer.valueOf(0);
//            while ((cadena = reader.readLine()) != null) {
//                String[] registro = cadena.split("\\|");
//                if (registro.length != 6) {
//                    throw new ParserException("El archivo detalle no continene la cantidad de datos esperada (6 columnas).");
//                }
        for (ResumenDiarioTax object : resumenD.getListResumenDiarioTax()) {
            detalle = new HashMap<>();
            detalle.put("idLineaRd", object.getIdLineaRd());
            detalle.put("ideTributoRd", object.getIdeTributoRd());
            detalle.put("nomTributoRd", object.getNomTributoRd());
            detalle.put("codTipTributoRd", object.getCodTipTributoRd());
            detalle.put("mtoBaseImponibleRd", String.valueOf(object.getMtoBaseImponibleRd()));
            detalle.put("mtoTributoRd", String.valueOf(object.getMtoTributoRd()));

            listaDetalle.add(detalle);
        }

//            }
        resumenGeneral.put("listaTributosDocResumen", listaDetalle);
//        } catch (IOException x) {
//            throw new ParserException("No se pudo leer el archivo detalle: " + this.archivoDetalleTributos, x);
//        }

        log.debug("SoftwareFacturadorController.formatoResumenBoletas...Fin Procesamiento");

        return resumenGeneral;
    }

    public byte[] parse(String templatesPath) throws ParserException {
        return parse(templatesPath, plantillaSeleccionada);
    }
}
