package com.lateroad.xmlparser.entity;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "drug"
})
@XmlRootElement(name = "medicines", namespace = "medicines")
public class Medicines {

    @XmlElementRef(name = "drug", namespace = "medicines", type = JAXBElement.class)
    protected ArrayList<JAXBElement<? extends Drug>> drug;

    public ArrayList<JAXBElement<? extends Drug>> getDrug() {
        if (drug == null) {
            drug = new ArrayList<>();
        }
        return this.drug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicines medicines = (Medicines) o;
        return Objects.equals(drug, medicines.drug);
    }

    @Override
    public int hashCode() {

        return Objects.hash(drug);
    }
}
