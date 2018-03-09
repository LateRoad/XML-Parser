package com.lateroad.xmlparser.builder;

import com.lateroad.xmlparser.entity.*;
import com.lateroad.xmlparser.exception.XmlParserLogicException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class DrugDOMBuilder extends AbstractDrugBuilder {
    static {
        new DOMConfigurator().doConfigure("log4j2.xml", LogManager.getLoggerRepository());
    }

    private static final Logger logger = Logger.getLogger(DrugDOMBuilder.class);

    private static final String POWDER_TAG = "powder";
    private static final String PILLS_TAG = "pills";
    private static final String CAPSULES_TAG = "capsules";
    private static final String ID_TAG = "id";
    private static final String TYPE_TAG = "type";
    private static final String NAME_TAG = "name";
    private static final String PHARM_TAG = "pharm";
    private static final String CHARACTERISTICS_TAG = "characteristics";
    private static final String BOXING_TAG = "boxing";
    private static final String DOSAGE_TAG = "dosage";
    private static final String CERTIFICATE_TAG = "certificate";


    private DocumentBuilder documentBuilder;

    public DrugDOMBuilder() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            logger.error(e);
        }
    }

    @Override
    public void buildDrugs(String filePath) throws XmlParserLogicException {
        Document paperDocument = null;
        try {
            paperDocument = documentBuilder.parse(filePath);
            Element rootElement = paperDocument.getDocumentElement();
            fillInDrugList(rootElement.getElementsByTagName(POWDER_TAG), POWDER_TAG);
            fillInDrugList(rootElement.getElementsByTagName(PILLS_TAG), PILLS_TAG);
            fillInDrugList(rootElement.getElementsByTagName(CAPSULES_TAG), CAPSULES_TAG);
        } catch (IOException e) {
            logger.error("File error or I/O error: ", e);
            throw new XmlParserLogicException();
        } catch (SAXException e) {
            logger.error(e);
            throw new XmlParserLogicException();
        }
    }


    private void fillInDrugList(NodeList list, String drugName) {
        for (int temp = 0; temp < list.getLength(); temp++) {
            Element currentPowder = (Element) list.item(temp);
            Drug drug = null;
            switch (drugName) {
                case POWDER_TAG:
                    drug = new Powder();
                    break;
                case PILLS_TAG:
                    drug = new Pills();
                    break;
                case CAPSULES_TAG:
                    drug = new Capsules();
                    break;
                default:
                    break;
            }
            drug.setId(currentPowder.getAttribute(ID_TAG));
            if (currentPowder.getAttribute(TYPE_TAG) != null) {
                drug.setType(currentPowder.getAttribute(TYPE_TAG));
            }
            drug.setName(getTextValue(currentPowder, NAME_TAG));
            drug.setPharm(getTextValue(currentPowder, PHARM_TAG));

            Characteristics chars = new Characteristics();
            Element characteristicsList = (Element) currentPowder.getElementsByTagName(CHARACTERISTICS_TAG).item(0);
            chars.setBoxing(getTextValue(characteristicsList, BOXING_TAG));
            chars.setDosage(getTextValue(characteristicsList, DOSAGE_TAG));

            drug.setCharacteristics(chars);
            drug.setCertificate(getTextValue(currentPowder, CERTIFICATE_TAG));
            drugSet.add(drug);
        }
    }

    private String getTextValue(Element element, String elementName) {
        NodeList elementList = element.getElementsByTagName(elementName);
        Node node = elementList.item(0);
        return node.getTextContent();
    }

}
