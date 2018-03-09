package com.lateroad.xmlparser.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "chars", namespace = "medicines", propOrder = {
        "boxing",
        "dosage"
})
public class Characteristics {

    @XmlElement(namespace = "medicines", required = true)
    protected String boxing;
    @XmlElement(namespace = "medicines")
    protected String dosage;


    public String getBoxing() {
        return boxing;
    }

    public void setBoxing(String value) {
        this.boxing = value;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String value) {
        this.dosage = value;
    }

    @Override
    public String toString() {
        return "Characteristics{" +
                "boxing='" + boxing + '\'' +
                ", dosage='" + dosage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Characteristics that = (Characteristics) o;
        return Objects.equals(boxing, that.boxing) &&
                Objects.equals(dosage, that.dosage);
    }

    @Override
    public int hashCode() {

        return Objects.hash(boxing, dosage);
    }
}
