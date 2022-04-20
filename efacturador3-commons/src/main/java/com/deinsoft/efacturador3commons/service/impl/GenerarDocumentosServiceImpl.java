/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3commons.service.impl;

/**
 *
 * @author EDWARD-PC
 */
import com.deinsoft.efacturador3commons.config.AppConfig;
import com.deinsoft.efacturador3commons.model.Empresa;
import com.deinsoft.efacturador3commons.model.FacturaElectronica;
import com.deinsoft.efacturador3commons.model.ResumenBaja;
import com.deinsoft.efacturador3commons.model.ResumenDiario;
import com.deinsoft.efacturador3commons.parser.Parser;
import com.deinsoft.efacturador3commons.parser.ParserException;
import com.deinsoft.efacturador3commons.parser.PipeParserFactory;
import com.deinsoft.efacturador3commons.repository.ParametroRepository;
import com.deinsoft.efacturador3commons.service.ComunesService;
import com.deinsoft.efacturador3commons.service.GenerarDocumentosService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.ManagedBean;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import com.deinsoft.efacturador3commons.repository.ErrorRepository;
import com.deinsoft.efacturador3commons.signer.SignerXml;
//import com.deinsoft.efacturador3commons.soap.gencdp.ExceptionDetail;
//import com.deinsoft.efacturador3commons.soap.gencdp.TransferirArchivoException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@ManagedBean
public class GenerarDocumentosServiceImpl implements GenerarDocumentosService {

    private static final Logger log = LoggerFactory.getLogger(GenerarDocumentosServiceImpl.class);

    @Autowired
    private ParametroRepository parametroDao;

    @Autowired
    private ErrorRepository errorDao;

    @Autowired
    private ComunesService comunesService;

    @Autowired
    AppConfig appConfig;
//    ConfigurationHolder config = ConfigurationHolder.getInstance();

    public byte[] formatoPlantillaXml(String rootPath, FacturaElectronica facturaElectronica, String nombreArchivo)  {
        String idXml = "";
        String tipoComprobante = facturaElectronica.getTipo();
        if ("01".equals(tipoComprobante) || "03"
                .equals(tipoComprobante) || "07"
                .equals(tipoComprobante) || "08"
                .equals(tipoComprobante)) {
            log.debug("BandejaDocumentosServiceImpl.generarComprobantePagoSunat...Formato de Facturas.");
        }
        log.debug("GenerarDocumentosServiceImpl.formatoPlantillaXml...Inicio Procesamiento");
        log.debug("GenerarDocumentosServiceImpl.formatoPlantillaXml...tipoDocumento: " + tipoComprobante);

        try {
            PipeParserFactory parserFactory = new PipeParserFactory();
            Parser xmlParser = parserFactory.createParser(facturaElectronica.getEmpresa(), tipoComprobante, facturaElectronica);

            StringBuilder rutaSalida = new StringBuilder();
            rutaSalida.setLength(0);
            rutaSalida.append(rootPath + "/" + facturaElectronica.getEmpresa().getNumdoc() + "/TEMP/").append(nombreArchivo).append(".xml");
            String templatesPath = rootPath + "/VALI/";

            byte[] myByteArray = xmlParser.parse(templatesPath);

            try (FileOutputStream fos = new FileOutputStream(rutaSalida.toString())) {
                fos.write(myByteArray);
            }

            log.debug("SoftwareFacturadorController.formatoPlantillaXml...Final Procesamiento");
            return myByteArray;
        } catch (Exception e) {

            log.info("formatoPlantillaXml: " + e.getMessage());
            e.printStackTrace();
            return null;
//            ExceptionDetail exceptionDetail = new ExceptionDetail();
//            exceptionDetail.setMessage(e.getMessage());
//            throw new TransferirArchivoException("Error en archivo TxT: " + e.getMessage(), exceptionDetail);

        }
    }

    public byte[] formatoPlantillaXmlResumenBaja(String rootPath, ResumenBaja resumenBaja) {
        String idXml = "";
//        String tipoComprobante = facturaElectronica.getTipo();
        try {
            PipeParserFactory parserFactory = new PipeParserFactory();
            Parser xmlParser = parserFactory.createParserResumenBaja(resumenBaja);
//            StringBuilder rutaSalida = new StringBuilder();
//            rutaSalida.setLength(0);
//            rutaSalida.append(rootPath + "/" + resumenBaja.getEmpresa().getNumdoc() + "/TEMP/").
//                    append(resumenBaja.getNombreArchivo()).append(".xml");
//            String templatesPath = rootPath + "/VALI/";
//
//            byte[] myByteArray = xmlParser.parse(templatesPath);
//
//            try (FileOutputStream fos = new FileOutputStream(rutaSalida.toString())) {
//                fos.write(myByteArray);
//            }

            log.debug("SoftwareFacturadorController.formatoPlantillaXml...Final Procesamiento");
//            return myByteArray;
            return getByteArray(rootPath, xmlParser, resumenBaja.getNombreArchivo(), resumenBaja.getEmpresa().getNumdoc());
        } catch (Exception e) {
            e.printStackTrace();
            log.info("formatoPlantillaXml: " + e.getMessage());
            return null;
            
//            ExceptionDetail exceptionDetail = new ExceptionDetail();
//            exceptionDetail.setMessage(e.getMessage());
//            throw new TransferirArchivoException("Error en archivo TxT: " + e.getMessage(), exceptionDetail);

        }
    }
    public byte[] formatoPlantillaXmlResumenDiario(String rootPath, ResumenDiario resumenDiario) {
        String idXml = "";
//        String tipoComprobante = facturaElectronica.getTipo();
        try {
            PipeParserFactory parserFactory = new PipeParserFactory();
            Parser xmlParser = parserFactory.createParserResumenDiario(resumenDiario);

//            StringBuilder rutaSalida = new StringBuilder();
//            rutaSalida.setLength(0);
//            rutaSalida.append(rootPath + "/" + resumenDiario.getEmpresa().getNumdoc() + "/TEMP/").
//                    append(resumenDiario.getNombreArchivo()).append(".xml");
//            String templatesPath = rootPath + "/VALI/";
//
//            byte[] myByteArray = xmlParser.parse(templatesPath);
//
//            try (FileOutputStream fos = new FileOutputStream(rutaSalida.toString())) {
//                fos.write(myByteArray);
//            }
            log.debug("SoftwareFacturadorController.formatoPlantillaXml...Final Procesamiento");
            return getByteArray(rootPath, xmlParser,resumenDiario.getNombreArchivo(), resumenDiario.getEmpresa().getNumdoc());
        } catch (Exception e) {
            e.printStackTrace();
            log.info("formatoPlantillaXml: " + e.getMessage());
            return null;
//            ExceptionDetail exceptionDetail = new ExceptionDetail();
//            exceptionDetail.setMessage(e.getMessage());
//            throw new TransferirArchivoException("Error en archivo TxT: " + e.getMessage(), exceptionDetail);

        }
    }

    public Map<String, Object> firmarXml(String rootPath, Empresa empresa, String nombreArchivo) {
        String rutaNombreEntrada = rootPath + empresa.getNumdoc() + "/TEMP/" + nombreArchivo + ".xml";
        String rutaNombreSalida = rootPath + empresa.getNumdoc() + "/PARSE/" + nombreArchivo + ".xml";

        try {
            FileInputStream inDocument = new FileInputStream(rutaNombreEntrada);
            return firmarDocumento(rootPath, empresa, inDocument, nombreArchivo);
        } catch (Exception e) {
            throw new RuntimeException("Error al firma archivo XML", e);
        }
    }

//    public Map<String, String> obtenerEstadoTicket(String rutaArchivo, String wsUrl, String nroTicket) {
//        Parametro paramtroBean = new Parametro();
//        String param = "", numeroRUC = "", usuarioSOL = "", password = "";
//        paramtroBean = new Parametro();
//        paramtroBean.setId_para("PARASIST");
//        List<Parametro> listaParametros = null;
//        listaParametros = this.parametroDao.consultarParametro(paramtroBean);
//        if (listaParametros.size() > 0) {
//            for (Parametro parametro : listaParametros) {
//                param = parametro.getVal_para();
//                if ("NUMRUC".equals(parametro.getCod_para())) {
//                    numeroRUC = param;
//                }
//                if ("USUSOL".equals(parametro.getCod_para())) {
//                    usuarioSOL = param;
//                }
//                if ("CLASOL".equals(parametro.getCod_para())) {
//                    password = FacturadorUtil.Desencriptar(param);
//                }
//            }
//        }
//        UsuarioSol usuarioSol = new UsuarioSol(numeroRUC, usuarioSOL, password);
//        SunatGEMServiceWrapper client = new SunatGEMServiceWrapper(usuarioSol, wsUrl);
//        Response respon = null;
//
//        respon = client.getStatus(rutaArchivo, nroTicket);
//
//        Map<String, String> resultado = new HashMap<>();
//        if (!respon.isError()) {
//            Integer codError = Integer.valueOf(respon.getCodigo());
//            log.debug("obtenerEstadoTicket Codigo de Error : " + codError);
//            String msgError = respon.getMensaje();
//            Integer pos = Integer.valueOf(msgError.indexOf("Detalle:"));
//            if (pos.intValue() > 0) {
//                msgError = msgError.substring(0, pos.intValue() - 1);
//            }
//            log.debug("obtenerEstadoTicket mensaje : " + msgError);
//            if (codError.intValue() == 0) {
//                resultado.put("situacion", "03");
//                resultado.put("mensaje", "-");
//            } else {
//                resultado.put("situacion", "05");
//                resultado.put("mensaje", msgError);
//            }
//        } else {
//            Integer codError = Integer.valueOf(respon.getCodigo());
//            log.debug("obtenerEstadoTicket Codigo de Error : " + codError);
//            String msgError = respon.getMensaje();
//            Integer pos = Integer.valueOf(msgError.indexOf("Detalle:"));
//            if (pos.intValue() > 0) {
//                msgError = msgError.substring(0, pos.intValue() - 1);
//            }
//            log.debug("obtenerEstadoTicket mensaje : " + msgError);
//
//            if (codError.intValue() == 98) {
//                resultado.put("situacion", "08");
//            }
//            if (codError.intValue() == 99) {
//                resultado.put("situacion", "05");
//            }
//            resultado.put("mensaje", msgError);
//        }
//
//        return resultado;
//    }
//    public HashMap<String, Object> enviarArchivoSunat(String wsUrl, String rootPath, String filename, FacturaElectronica facturaElectronica) {
//        String tipoComprobante = facturaElectronica.getTipo();
//        HashMap<String, Object> resultado = new HashMap<>();
//        String numeroRUC = "", usuarioSOL = "", password = "", mensaje = "";
//        log.info("url del servicio : " + wsUrl);
//
//        synchronized (this) {
//            Empresa empresa = facturaElectronica.getEmpresa();
//            numeroRUC = empresa.getNumdoc();
//            usuarioSOL = empresa.getUsuariosol();
//            password = empresa.getClavesol();
//            String zipFile = filename + ".zip";
//            log.debug("Enviando archivo a SUNAT, filename:  " + filename);
//            log.debug("Enviando archivo a SUNAT, zipFile:  " + zipFile);
//            log.debug("Enviando archivo a SUNAT, numeroRUC:  " + numeroRUC);
//            log.debug("Enviando archivo a SUNAT, usuarioSOL:  " + usuarioSOL);
//            log.debug("Enviando archivo a SUNAT, password:  " + password);
//            UsuarioSol usuarioSol = new UsuarioSol(numeroRUC, usuarioSOL, password);
//            SunatGEMServiceWrapper client = new SunatGEMServiceWrapper(usuarioSol, wsUrl);
//            Response respon = null;
//
//            log.debug("Enviando archivo a SUNAT, tipoComprobante:  " + tipoComprobante);
//            byte[] bytes = null;
//            try {
//                bytes = Files.readAllBytes(Paths.get(rootPath + numeroRUC + "/ENVIO/" + zipFile));
//                System.out.println(bytes.toString());
//            } catch (IOException ex) {
//                java.util.logging.Logger.getLogger(GenerarDocumentosServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            resultado.put("bytes",bytes);
//            if ("RA".equals(tipoComprobante) || "RC"
//                    .equals(tipoComprobante) || "RR"
//                    .equals(tipoComprobante)) {
//                respon = client.sendSummary(zipFile, rootPath + "ENVIO/" + zipFile);
//            } else {
//                respon = client.sendBill(rootPath + numeroRUC + "/RPTA/", zipFile, rootPath + numeroRUC + "/ENVIO/" + zipFile);
//            }
//            if (!respon.isError()) {
//                Integer errorCode = respon.getCodigo();
//                log.debug("isError es Falso, Codigo de Error : " + errorCode);
//                String msgError = respon.getMensaje();
//                log.debug("isError es Falso, Mensaje Error Obtenido: " + respon.getMensaje());
//                List<String> listaWarnings = (respon.getWarnings() != null) ? respon.getWarnings() : new ArrayList<>();
//
//                log.debug("isError es Falso, Cantidad de Warnings: " + listaWarnings.size());
//
//                if (!"RA".equals(tipoComprobante)
//                        && !"RC".equals(tipoComprobante)
//                        && !"RR".equals(tipoComprobante)) {
//
//                    if (errorCode == 0 && listaWarnings.isEmpty()) {
//                        resultado.put("situacion", "03");
//                        resultado.put("mensaje", respon.getMensaje());
//                    }
//                    if (errorCode == 0 && listaWarnings.size() > 0) {
//                        resultado.put("situacion", "04");
//                        resultado.put("mensaje", respon.getMensaje());
//                    }
//                    if (errorCode > 0) {
//                        String codError = FacturadorUtil.completarCeros(errorCode.toString(), "I", Integer.valueOf(4));
//                        Error error = new Error();
//                        error.setCod_cataerro(codError);
//                        Error txxxzBean = this.errorDao.consultarErrorById(error);
//                        if (txxxzBean != null) {
//                            mensaje = txxxzBean.getCod_cataerro() + " - " + txxxzBean.getNom_cataerro();
//                        } else {
//                            mensaje = msgError;
//                        }
//                        resultado.put("situacion", "10");
//                        resultado.put("mensaje", mensaje);
//                    }
//
//                    if (errorCode < 0) {
//                        mensaje = "Error al invocar el servicio de SUNAT.";
//                        resultado.put("situacion", "06");
//                        resultado.put("mensaje", mensaje);
//                    }
//
//                } else {
//
//                    mensaje = "Nro. Ticket: " + msgError;
//                    resultado.put("situacion", "08");
//                    resultado.put("mensaje", mensaje);
//                }
//
//            } else {
//
//                Integer errorCode = respon.getCodigo();
//                log.debug("isError es True, Codigo de Error : " + errorCode);
//                String msgError = respon.getMensaje();
//                log.debug("isError es True, Mensaje Error Obtenido: " + msgError);
//                log.debug("isError es True, tipoComprobante: " + tipoComprobante);
//                if (Constantes.CONSTANTE_CODIGO_ENVIO_PREVIO.compareTo(errorCode) == 0
//                        && !"RA".equals(tipoComprobante)
//                        && !"RC".equals(tipoComprobante)
//                        && !"RR".equals(tipoComprobante)) {
//
//                    String nombreArchivoProp = "constantes.properties";
//                    Properties prop = this.comunesService.getProperties(nombreArchivoProp);
//
//                    String urlWebServiceCdr = (prop.getProperty("RUTA_SERV_CDR") != null) ? prop.getProperty("RUTA_SERV_CDR") : "XX";
//
//                    WebServiceConsultaWrapper consultaCdr = new WebServiceConsultaWrapper(urlWebServiceCdr);
//                    String usuario = numeroRUC + usuarioSOL;
//                    log.debug("isError es True, usuario: " + usuario);
//                    String nombreArchivo = this.comunesService.obtenerRutaTrabajo("RPTA") + "R" + filename + ".zip";
//                    String[] datoArchivo = filename.split("\\-");
//                    String rucCdp = datoArchivo[0];
//                    log.debug("isError es True, rucCdp: " + rucCdp);
//                    String tipoCdp = datoArchivo[1];
//                    log.debug("isError es True, tipoCdp: " + tipoCdp);
//                    String serieCdp = datoArchivo[2];
//                    log.debug("isError es True, serieCdp: " + serieCdp);
//                    String nroCdp = datoArchivo[3];
//                    log.debug("isError es True, nroCdp: " + nroCdp);
//                    log.debug("isError es True, Inicio llamada al WS");
//                    Integer estado = consultaCdr.obtenerEstadoCdr(usuario, password, nombreArchivo, rucCdp, tipoCdp, serieCdp, new Integer(nroCdp), "0004");
//
//                    log.debug("isError es True, Fin llamada al WS: " + estado);
//                    if (null == estado) {
//                        mensaje = "El webservice para obtener CDR, no se encontró el archivo CDR en SUNAT.";
//                        resultado.put("situacion", "06");
//                        resultado.put("mensaje", mensaje);
//
//                    } else {
//                        switch (estado) {
//                            case 0:
//                                mensaje = "Este Comprobante ya fue enviado anteriormente, sólo se descargó el CDR nuevamente.";
//                                resultado.put("situacion", "11");
//                                resultado.put("mensaje", mensaje);
//                                break;
//                            case 1:
//                                mensaje = "Este Comprobante ya fue enviado anteriormente, sólo se descargó el CDR nuevamente.";
//                                resultado.put("situacion", "12");
//                                resultado.put("mensaje", mensaje);
//                                break;
//                            case -1:
//                                mensaje = "Error invocando al webservice para obtener CDR.";
//                                resultado.put("situacion", "06");
//                                resultado.put("mensaje", mensaje);
//                                break;
//                            default:
//                                mensaje = "El webservice para obtener CDR, no se encontró el archivo CDR en SUNAT.";
//                                resultado.put("situacion", "06");
//                                resultado.put("mensaje", mensaje);
//                                break;
//                        }
//                    }
//
//                } else {
//                    String codError = FacturadorUtil.completarCeros(errorCode.toString(), "I", 4);
//                    Error error = new Error();
//                    error.setCod_cataerro(codError);
//                    Error txxxzBean = this.errorDao.consultarErrorById(error);
//                    if (txxxzBean != null) {
//                        mensaje = txxxzBean.getCod_cataerro() + " - " + txxxzBean.getNom_cataerro();
//                    } else {
//                        mensaje = msgError;
//                    }
//                    resultado.put("situacion", "05");
//                    resultado.put("mensaje", mensaje);
//                }
//            }
//        }
//
//        return resultado;
//    }
    public String obtenerArchivoXml(String nombreArchivo) {
        try {
            log.debug("GenerarDocumentosServiceImpl.obtenerArchivoXml...Inicio Procesamiento");
            String contenido = "", linea = "";
            String archivoXml = nombreArchivo + ".xml";
            String archivoZip = "R" + nombreArchivo + ".zip";

            log.debug("GenerarDocumentosServiceImpl.obtenerArchivoXml...Ruta: " + this.comunesService
                    .obtenerRutaTrabajo("RPTA"));
            log.debug("GenerarDocumentosServiceImpl.obtenerArchivoXml...Archivo: " + archivoXml);

            File archivoRespuesta = new File(this.comunesService.obtenerRutaTrabajo("RPTA"), archivoZip);

            if (archivoRespuesta.exists()) {
                File archivoContenido = new File(this.comunesService.obtenerRutaTrabajo("ENVI"), archivoXml);
                if (archivoContenido.exists()) {
                    FileReader fileReader = new FileReader(archivoContenido);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    StringBuffer cadenaLinea = new StringBuffer();
                    cadenaLinea.setLength(0);
                    while ((linea = bufferedReader.readLine()) != null) {
                        cadenaLinea.append(linea);
                        cadenaLinea.append("\n");
                    }
                    fileReader.close();

                    contenido = cadenaLinea.toString();
                }
            }

            log.debug("GenerarDocumentosServiceImpl.obtenerArchivoXml...Final Procesamiento");
            return contenido;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener archivo xml", e);
        }
    }

    public void validarPlazo2(String path, String fecEmision) {
//        String fecEmision = (String) expr.evaluate(document, XPathConstants.STRING);
        Integer fecEmisionInt = Integer.valueOf(Integer.parseInt(fecEmision.replaceAll("-", "")));
        log.debug("fecEmision: " + fecEmision + ", " + fecEmisionInt);
        try {
            if (fecEmisionInt.intValue() >= 20200107) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date fecEmisionDate = null;
                fecEmisionDate = df.parse(fecEmision);
                Date today = new Date();
                long diff = today.getTime() - fecEmisionDate.getTime();
                long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                Integer nroDias = Integer.valueOf(Integer.parseInt(this.appConfig.getPlazoBoleta()));
                if (days > nroDias.intValue()) {
                    log.debug("El XML fue generado, pero el Comprobante tiene mas de " + nroDias + " dís. Emisión: " + fecEmision + ". Use el resumen diario para generar y enviar.");

                    throw new Exception("El XML fue generado, pero el Comprobante tiene mas de " + nroDias + " días. Emisión: " + fecEmision + ". Use el resumen diario para generar y enviar.");
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void validarPlazo(String nombreArchivoXml) {
        try {
            String serie = nombreArchivoXml.substring(15, 16);

            if (serie.equals("B")) {

                String documentoOrigen = this.comunesService.obtenerRutaTrabajo("PARS") + nombreArchivoXml + ".xml";

                DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
                domFactory.setIgnoringComments(true);
                DocumentBuilder builder = domFactory.newDocumentBuilder();
                Document document = builder.parse(new File(documentoOrigen));

                XPath xpath = XPathFactory.newInstance().newXPath();
                XPathExpression expr = xpath.compile(document.getDocumentElement().getNodeName() + "/IssueDate");
                String fecEmision = (String) expr.evaluate(document, XPathConstants.STRING);
                Integer fecEmisionInt = Integer.valueOf(Integer.parseInt(fecEmision.replaceAll("-", "")));
                log.debug("fecEmision: " + fecEmision + ", " + fecEmisionInt);

                if (fecEmisionInt.intValue() >= 20200107) {
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    Date fecEmisionDate = null;
                    fecEmisionDate = df.parse(fecEmision);
                    Date today = new Date();
                    long diff = today.getTime() - fecEmisionDate.getTime();
                    long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                    Integer nroDias = Integer.valueOf(Integer.parseInt(this.appConfig.getPlazoBoleta()));
                    if (days > nroDias.intValue()) {
                        log.debug("El XML fue generado, pero el Comprobante tiene mas de " + nroDias + " dÃ­as. EmisiÃ³n: " + fecEmision + ". Use el resumen diario para generar y enviar.");

                        throw new Exception("El XML fue generado, pero el Comprobante tiene mas de " + nroDias + " dÃ­as. EmisiÃ³n: " + fecEmision + ". Use el resumen diario para generar y enviar.");
                    }

                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Map<String, Object> firmarDocumento(String rootPath, Empresa empresa, InputStream inDocument, String fileName) {
        try {
            log.debug("GenerarDocumentosServiceImpl.firmarDocumento...Inicio de Firma");
            log.debug("GenerarDocumentosServiceImpl.firmarDocumento...Crear Document");
            Document doc = buildDocument(inDocument);
            addExtensionContent(doc);
            return SignerXml.firmarXml(rootPath, empresa, doc, rootPath + "/" + empresa.getNumdoc() + "/PARSE/" + fileName + ".xml");
        } catch (Exception e) {

            throw new RuntimeException("Error al firmar documento: ", e);
        }
    }

    private String obtenerNodo(Node node) throws Exception {
        StringBuffer valorClave = new StringBuffer();
        valorClave.setLength(0);

        Integer tamano = Integer.valueOf(node.getChildNodes().getLength());

        for (int i = 0; i < tamano.intValue(); i++) {
            Node c = node.getChildNodes().item(i);
            if (c.getNodeType() == 3) {
                valorClave.append(c.getNodeValue());
            }
        }

        String nodo = valorClave.toString().trim();

        return nodo;
    }

    private Document buildDocument(InputStream inDocument) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setAttribute("http://xml.org/sax/features/namespaces", Boolean.TRUE);
        DocumentBuilder db = dbf.newDocumentBuilder();
        Reader reader = new InputStreamReader(inDocument, "ISO8859_1");
        Document doc = db.parse(new InputSource(reader));
        return doc;
    }

    private Node addExtensionContent(Document doc) {
        NodeList nodeList = doc.getDocumentElement().getElementsByTagNameNS("urn:oasis:names:specification:ubl:schema:xsd:CommonExtensionComponents-2", "UBLExtensions");

        Node extensions = nodeList.item(0);
        extensions.appendChild(doc.createTextNode("\n\t\t"));
        Node extension = doc.createElement("ext:UBLExtension");
        extension.appendChild(doc.createTextNode("\n\t\t\t"));
        Node content = doc.createElement("ext:ExtensionContent");
        extension.appendChild(content);
        extension.appendChild(doc.createTextNode("\n\t\t"));
        extensions.appendChild(extension);
        extensions.appendChild(doc.createTextNode("\n\t"));
        return content;
    }
    byte[] getByteArray(String rootPath, Parser xmlParser, String nombreArchivo, String numRuc) throws FileNotFoundException, IOException, ParserException {
        StringBuilder rutaSalida = new StringBuilder();
        rutaSalida.setLength(0);
        rutaSalida.append(rootPath + "/" + numRuc + "/TEMP/").
                append(nombreArchivo).append(".xml");
        String templatesPath = rootPath + "/VALI/";

        byte[] myByteArray = xmlParser.parse(templatesPath);

        try (FileOutputStream fos = new FileOutputStream(rutaSalida.toString())) {
            fos.write(myByteArray);
        }
        return myByteArray;
    }
}
