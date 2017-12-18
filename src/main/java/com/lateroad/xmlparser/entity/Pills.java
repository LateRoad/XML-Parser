package com.lateroad.xmlparser.entity;

import javax.xml.bind.annotation.*;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pills", namespace = "medicines", propOrder = {
    "certificate"
})
public class Pills extends Drug {

    @XmlElement(namespace = "medicines", required = true)
    protected String certificate;
    @XmlAttribute(name = "type")
    protected String type;

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String value) {
        this.certificate = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String value) {
        this.type = value;
    }

    @Override
    public String toString() {
        return "Pills{" +
                "certificate='" + certificate + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", pharm='" + pharm + '\'' +
                ", group='" + group + '\'' +
                ", analogs='" + analogs + '\'' +
                ", characteristics=" + characteristics +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pills pills = (Pills) o;
        return Objects.equals(certificate, pills.certificate) &&
                Objects.equals(type, pills.type);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), certificate, type);
    }
}
