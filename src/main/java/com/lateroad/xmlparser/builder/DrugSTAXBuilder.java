package com.lateroad.xmlparser.builder;


import com.lateroad.xmlparser.entity.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DrugSTAXBuilder extends AbstractDrugBuilder {

    private XMLInputFactory inputFactory;

    public DrugSTAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }


    @Override
    public void buildDrugs(String filePath) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(filePath));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                Drug currentDrug = null;
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    System.out.println(name);
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
                    }
                    if (currentDrug != null) {
                        drugSet.add(buildCurrentPaper(reader, currentDrug));
                    }
                }
            }
        } catch (XMLStreamException ex) {
            System.err.println("StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public Drug buildCurrentPaper(XMLStreamReader reader, Drug drug) throws XMLStreamException {
        if (reader.getAttributeValue(null, "id") != null) {
            drug.setId(reader.getAttributeValue(null, "id"));
        }
        if (reader.getAttributeValue(null, "type") != null) {
            drug.setType(reader.getAttributeValue(null, "type"));
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
                        }
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        name = reader.getLocalName().toUpperCase();
                        System.out.println(name);
                        if (MedicineType.valueOf(name) == MedicineType.POWDER || MedicineType.valueOf(name) == MedicineType.PILLS || MedicineType.valueOf(name) == MedicineType.CAPSULES) {
                            return drug;
                        }
                        break;
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        throw new XMLStreamException("Unknown element in tag Student");
    }


    private Characteristics getXMLChars(XMLStreamReader reader) throws XMLStreamException {
        Characteristics chars = new Characteristics();
        int type;
        String name;
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
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    if (MedicineType.valueOf(reader.getLocalName().toUpperCase()) == MedicineType.CHARACTERISTICS) {
                        return chars;
                    }
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