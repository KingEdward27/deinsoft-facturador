package com.deinsoft.efacturador3commons.validator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import com.deinsoft.efacturador3commons.model.FacturaElectronica;
import com.deinsoft.efacturador3commons.config.XsltCpePath;
//import com.deinsoft.efacturador3commons.dao.ErrorDao;
import com.deinsoft.efacturador3commons.exception.XsdException;
import com.deinsoft.efacturador3commons.repository.ErrorRepository;
import com.deinsoft.efacturador3commons.service.ComunesService;
import com.deinsoft.efacturador3commons.util.ResourceResolver;
import com.deinsoft.efacturador3commons.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import com.deinsoft.efacturador3commons.service.ResourceResolver;

public class XsdCpeValidator {

    private static final Logger log = LoggerFactory.getLogger(XsdCpeValidator.class);

    private XsltCpePath xsltCpePath;

    public XsdCpeValidator(XsltCpePath xsltCpePath) {
        this.xsltCpePath = xsltCpePath;
    }

    public void validarSchemaXML(String rootPath, String tipoDocumento, String nombreArchivo) throws XsdException {
        log.debug("validarSchemaXML...Iniciando Validacion de Schema");

        String tipoComprobante = tipoDocumento;

        try {
            String schemaValidador = "", ubl = "2.1";
            String validationBasePath = rootPath + "VALI/";
            if ("01".equals(tipoComprobante) || "03"
                    .equals(tipoComprobante)) {
                //rootPath + "/VALI/" +
                schemaValidador = validationBasePath + xsltCpePath.getFacturaXsd();
            }
            if ("07".equals(tipoComprobante)) {
                schemaValidador = validationBasePath + xsltCpePath.getNotaCreditoXsd();
            }
            if ("08".equals(tipoComprobante)) {
                schemaValidador = validationBasePath + xsltCpePath.getNotaDebitoXsd();
            }
            if ("RA".equals(tipoComprobante)) {
                ubl = "2.0";
                schemaValidador = validationBasePath + xsltCpePath.getResumenAnuladoXsd();
            }
            if ("RC".equals(tipoComprobante)) {
                ubl = "2.0";
                schemaValidador = validationBasePath + xsltCpePath.getResumenBoletaXsd();
            }
            if ("RR".equals(tipoComprobante)) {
                schemaValidador = validationBasePath + xsltCpePath.getResumenReversionXsd();
            }
            if ("20".equals(tipoComprobante)) {
                schemaValidador = validationBasePath + xsltCpePath.getRetencionXsd();
            }
            if ("40".equals(tipoComprobante)) {
                schemaValidador = validationBasePath + xsltCpePath.getPercepcionXsd();
            }
            if ("09".equals(tipoComprobante)) {
                schemaValidador = validationBasePath + xsltCpePath.getGuiaXsd();
            }
            log.debug("validarSchemaXML...Asignando schemaValidador (" + schemaValidador + ")");
            log.debug("validarSchemaXML...Aplicando builderFactory");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setNamespaceAware(true);

            log.debug("validarSchemaXML...Parseando DocumentBuilder");
            DocumentBuilder parser = builderFactory.newDocumentBuilder();

            File file = new File(nombreArchivo);
            InputStream inputStream = new FileInputStream(file);
            Reader reader = new InputStreamReader(inputStream, "ISO-8859-1");
            log.debug("validarSchemaXML...Configurando reader (" + reader + ")");
            Document document = parser.parse(new InputSource(reader));

            log.debug("validarSchemaXML...Configurando ResourceResolver document (" + document + ")");

            SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

            factory.setResourceResolver((LSResourceResolver) new ResourceResolver(validationBasePath + "commons/xsd/" + ubl + "/"));

            log.debug("validarSchemaXML...Configurando SchemaFile");
            StreamSource schemaFile = new StreamSource(new File(schemaValidador));

            Schema schema = factory.newSchema(schemaFile);

            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(document));
            log.debug("validarSchemaXML...Configurando Validator");
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("No se puede leer (parsear) el archivo XML: " + e
                    .getMessage() + " - Causa: " + e.getCause(), e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No se puede leer (parsear) el archivo XML: " + e
                    .getMessage() + " - Causa: " + e.getCause(), e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("No se puede leer (parsear) el archivo XML: " + e
                    .getMessage() + " - Causa: " + e.getCause(), e);
        } catch (SAXException e) {

            if (e.getMessage().contains("cvc-complex-type")) {
                throw new XsdException("No se puede leer (parsear) el archivo XML: " + e
                        .getMessage());
            }
            throw new RuntimeException("No se puede leer (parsear) el archivo XML: " + e
                    .getMessage() + " - Causa: " + e.getCause(), e);

        } catch (IOException e) {
            throw new RuntimeException("No se puede leer (parsear) el archivo XML: " + e
                    .getMessage() + " - Causa: " + e.getCause(), e);
        }

        log.debug("Finalizando Validacion de Schema");
    }
}
