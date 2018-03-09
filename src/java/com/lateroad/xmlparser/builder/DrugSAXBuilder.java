package com.lateroad.xmlparser.builder;

import com.lateroad.xmlparser.exception.XmlParserLogicException;
import com.lateroad.xmlparser.handler.DrugHandler;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class DrugSAXBuilder extends AbstractDrugBuilder {
    static {
        new DOMConfigurator().doConfigure("log4j2.xml", LogManager.getLoggerRepository());
    }

    private static final Logger logger = Logger.getLogger(DrugSAXBuilder.class);


    private DrugHandler drugHandler;
    private XMLReader xmlReader;

    public DrugSAXBuilder(){
        try {
            drugHandler = new DrugHandler();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(drugHandler);
        } catch (SAXException e) {
            logger.error(e);
        }
    }


    @Override
    public void buildDrugs(String filePath) throws XmlParserLogicException {
        try {
            xmlReader.parse(filePath);
        } catch (IOException | SAXException e) {
            logger.error(e);
            throw new XmlParserLogicException();
        }
        drugSet = drugHandler.getDrugSet();
    }
}
