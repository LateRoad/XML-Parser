package com.lateroad.xmlparser.handler;

import com.lateroad.xmlparser.entity.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashSet;
import java.util.Set;

public class DrugHandler extends DefaultHandler {

    private MedicineType medicineType;
    private Set<Drug> drugSet;
    private Drug currentDrug;

    public DrugHandler() {
        drugSet = new HashSet<>();
    }

    @Override
    public void startDocument() {
        System.out.println("SAX parser starts...");
    }

    @Override
    public void endDocument() {
        System.out.println("SAX parser ends...");
    }

    @Override
    public void startElement(String namespace, String localName, String qName, Attributes attr) {
        switch (localName) {
            case "powder":
                currentDrug = new Powder();
                break;
            case "pills":
                currentDrug = new Pills();
                break;
            case "capsules":
                currentDrug = new Capsules();
                break;
            default:
                medicineType = MedicineType.valueOf(localName.toUpperCase());
        }

        if (currentDrug != null) {
            int attrQuantity = attr.getLength();
            for (int i = 0; i < attrQuantity; i++) {
                if ("id".equals(attr.getLocalName(i))) {
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
            }
        }
    }

    @Override
    public void endElement(String namespace, String localName, String qName) {
        if ("pills".equals(localName) || "powder".equals(localName) || "capsules".equals(localName)) {
            drugSet.add(currentDrug);
        }
    }

    public Set<Drug> getDrugSet() {
        return drugSet;
    }
}
