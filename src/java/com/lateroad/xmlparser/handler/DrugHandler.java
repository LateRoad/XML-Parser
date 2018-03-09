package com.lateroad.xmlparser.handler;

import com.lateroad.xmlparser.entity.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class DrugHandler extends DefaultHandler {
    static {
        new DOMConfigurator().doConfigure("log4j2.xml", LogManager.getLoggerRepository());
    }

    private static final Logger logger = Logger.getLogger(DrugHandler.class);

    private static final String POWDER_TAG = "powder";
    private static final String PILLS_TAG = "pills";
    private static final String CAPSULES_TAG = "capsules";
    private static final String ID_TAG = "id";


    private MedicineType medicineType;
    private ArrayList<Drug> drugSet;
    private Drug currentDrug;

    public DrugHandler() {
        drugSet = new ArrayList<>();
    }

    @Override
    public void startDocument() {
        logger.info("SAX parser starts...");
    }

    @Override
    public void endDocument() {
        logger.info("SAX parser ends...");
    }

    @Override
    public void startElement(String namespace, String localName, String qName, Attributes attr) {
        switch (localName) {
            case POWDER_TAG:
                currentDrug = new Powder();
                break;
            case PILLS_TAG:
                currentDrug = new Pills();
                break;
            case CAPSULES_TAG:
                currentDrug = new Capsules();
                break;
            default:
                medicineType = MedicineType.valueOf(localName.toUpperCase());
        }

        if (currentDrug != null) {
            int attrQuantity = attr.getLength();
            for (int i = 0; i < attrQuantity; i++) {
                if (ID_TAG.equals(attr.getLocalName(i))) {
                    currentDrug.setId(attr.getValue(i));
                } else {
                    currentDrug.setType(attr.getValue(i));
                }
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int end) {
        String s = String.copyValueOf(ch, start, end).trim();
        if (medicineType != null && !s.isEmpty()) {
            switch (medicineType) {
                case NAME:
                    currentDrug.setName(s);
                    break;
                case PHARM:
                    currentDrug.setPharm(s);
                    break;
                case GROUP:
                    currentDrug.setGroup(s);
                    break;
                case ANALOGS:
                    currentDrug.setAnalogs(s);
                    break;
                case BOXING:
                    currentDrug.getCharacteristics().setBoxing(s);
                    break;
                case DOSAGE:
                    currentDrug.getCharacteristics().setDosage(s);
                    break;
                case CERTIFICATE:
                    currentDrug.setCertificate(s);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void endElement(String namespace, String localName, String qName) {
        if (PILLS_TAG.equals(localName) || POWDER_TAG.equals(localName) || CAPSULES_TAG.equals(localName)) {
            drugSet.add(currentDrug);
        }
    }

    public List<Drug> getDrugSet() {
        return drugSet;
    }
}
