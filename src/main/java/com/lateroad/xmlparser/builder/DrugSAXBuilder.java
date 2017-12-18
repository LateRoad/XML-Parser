package com.lateroad.xmlparser.builder;

import com.lateroad.xmlparser.handler.DrugHandler;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;

public class DrugSAXBuilder extends AbstractDrugBuilder {

    private DrugHandler drugHandler;
    private XMLReader xmlReader;

    public DrugSAXBuilder(){
        try {
            drugHandler = new DrugHandler();
            xmlReader = XMLReaderFactory.createXMLReader();
            xmlReader.setContentHandler(drugHandler);
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void buildDrugs(String filePath) {
        try {
            xmlReader.parse(filePath);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        drugSet = drugHandler.getDrugSet();
    }
}
