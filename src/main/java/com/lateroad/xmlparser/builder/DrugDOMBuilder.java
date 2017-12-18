package com.lateroad.xmlparser.builder;

import com.lateroad.xmlparser.entity.*;
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

    private DocumentBuilder documentBuilder;

    public DrugDOMBuilder() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void buildDrugs(String filePath) {
        Document paperDocument = null;
        try {
            paperDocument = documentBuilder.parse(filePath);
            Element rootElement = paperDocument.getDocumentElement();
            fillinDrugList(rootElement.getElementsByTagName("powder"), "powder");
            fillinDrugList(rootElement.getElementsByTagName("pills"), "pills");
            fillinDrugList(rootElement.getElementsByTagName("capsules"), "capsules");
        } catch (IOException e) {
            System.err.println("File error or I/O error: " + e);
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }


    private void fillinDrugList(NodeList list, String drugName) {
        for (int temp = 0; temp < list.getLength(); temp++) {
            Element currentPowder = (Element) list.item(temp);
            Drug drug = null;
            switch (drugName) {
                case "powder":
                    drug = new Powder();
                    break;
                case "pills":
                    drug = new Pills();
                    break;
                case "capsules":
                    drug = new Capsules();
                    break;
            }
            drug.setId(currentPowder.getAttribute("id"));
            if (currentPowder.getAttribute("type") != null) {
                drug.setType(currentPowder.getAttribute("type"));
            }
            drug.setName(getTextValue(currentPowder, "name"));
            drug.setPharm(getTextValue(currentPowder, "pharm"));

            Characteristics chars = new Characteristics();
            Element characteristicsList = (Element) currentPowder.getElementsByTagName("characteristics").item(0);
            chars.setBoxing(getTextValue(characteristicsList, "boxing"));
            chars.setDosage(getTextValue(characteristicsList, "dosage"));

            drug.setCharacteristics(chars);
            drug.setCertificate(getTextValue(currentPowder, "certificate"));
            drugSet.add(drug);
        }
    }

    public String getTextValue(Element element, String elementName) {
        NodeList elementList = element.getElementsByTagName(elementName);
        Node node = elementList.item(0);
        return node.getTextContent();
    }

}
