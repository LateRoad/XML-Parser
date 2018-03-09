package com.lateroad.xmlparser.builder;


import com.lateroad.xmlparser.entity.*;
import com.lateroad.xmlparser.exception.XmlParserLogicException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DrugSTAXBuilder extends AbstractDrugBuilder {
    static {
        new DOMConfigurator().doConfigure("log4j2.xml", LogManager.getLoggerRepository());
    }

    private static final Logger logger = Logger.getLogger(DrugSTAXBuilder.class);

    private static final String ID_TAG = "id";
    private static final String TYPE_TAG = "type";


    private XMLInputFactory inputFactory;

    public DrugSTAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }


    @Override
    public void buildDrugs(String filePath) throws XmlParserLogicException {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(new File(filePath))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                Drug currentDrug = null;
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    MedicineType tempEnum = MedicineType.valueOf(name.toUpperCase());
                    switch (tempEnum) {
                        case POWDER:
                            currentDrug = new Powder();
                            break;
                        case PILLS:
                            currentDrug = new Pills();
                            break;
                        case CAPSULES:
                            currentDrug = new Capsules();
                            break;
                        default:
                            break;
                    }
                    if (currentDrug != null) {
                        drugSet.add(buildCurrentDrug(reader, currentDrug));
                    }
                }
            }
        } catch (XMLStreamException | IOException e) {
            logger.error("StAX parsing error! ", e);
            throw new XmlParserLogicException();
        }
    }


    private Drug buildCurrentDrug(XMLStreamReader reader, Drug drug) throws XmlParserLogicException {
        if (reader.getAttributeValue(null, ID_TAG) != null) {
            drug.setId(reader.getAttributeValue(null, ID_TAG));
        }
        if (reader.getAttributeValue(null, TYPE_TAG) != null) {
            drug.setType(reader.getAttributeValue(null, TYPE_TAG));
        }

        String name;
        try {
            while (reader.hasNext()) {
                int type = reader.next();
                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        name = reader.getLocalName();
                        switch (MedicineType.valueOf(name.toUpperCase())) {
                            case NAME:
                                drug.setName(getXMLText(reader));
                                break;
                            case PHARM:
                                drug.setPharm(getXMLText(reader));
                                break;
                            case GROUP:
                                drug.setGroup(getXMLText(reader));
                                break;
                            case ANALOGS:
                                drug.setAnalogs(getXMLText(reader));
                                break;
                            case CERTIFICATE:
                                drug.setCertificate(getXMLText(reader));
                                break;
                            case CHARACTERISTICS:
                                drug.setCharacteristics(getXMLChars(reader));
                                break;
                            default:
                                break;
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName().toUpperCase();
                        if (MedicineType.valueOf(name) == MedicineType.POWDER || MedicineType.valueOf(name) == MedicineType.PILLS || MedicineType.valueOf(name) == MedicineType.CAPSULES) {
                            return drug;
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (XMLStreamException e) {
            logger.error(e);
        }
        throw new XmlParserLogicException();
    }


    private Characteristics getXMLChars(XMLStreamReader reader) throws XMLStreamException {
        Characteristics chars = new Characteristics();
        int type;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    switch (MedicineType.valueOf(reader.getLocalName().toUpperCase())) {
                        case BOXING:
                            chars.setBoxing(getXMLText(reader));
                            break;
                        case DOSAGE:
                            chars.setDosage(getXMLText(reader));
                            break;
                        default:
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if (MedicineType.valueOf(reader.getLocalName().toUpperCase()) == MedicineType.CHARACTERISTICS) {
                        return chars;
                    }
                    break;
                default:
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Address");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

}