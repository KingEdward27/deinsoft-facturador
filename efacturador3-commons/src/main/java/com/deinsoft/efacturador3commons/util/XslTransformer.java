package com.deinsoft.efacturador3commons.util;

import java.io.File;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;




public class XslTransformer
{
  private TransformerFactory factory = TransformerFactory.newInstance();

  
  public void process(Reader xmlFile, Reader xslFile, Writer output) throws TransformerException {
    process(new StreamSource(xmlFile), new StreamSource(xslFile), new StreamResult(output));
  }
  
  public void process(File xmlFile, File xslFile, Writer output) throws TransformerException {
    process(new StreamSource(xmlFile), new StreamSource(xslFile), new StreamResult(output));
  }
  
  public void process(File xmlFile, File xslFile, OutputStream out) throws TransformerException {
    process(new StreamSource(xmlFile), new StreamSource(xslFile), new StreamResult(out));
  }
  
  public void process(Source xml, Source xsl, Result result) throws TransformerException {
    Templates template = this.factory.newTemplates(xsl);
    Transformer transformer = template.newTransformer();
    transformer.transform(xml, result);
  }
}
