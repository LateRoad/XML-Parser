package com.lateroad.xmlparser.entity;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "drug", namespace = "medicines", propOrder = {
        "name",
        "pharm",
        "group",
        "analogs",
        "characteristics"
})
@XmlSeeAlso({
        Powder.class,
        Capsules.class,
        Pills.class
})
@XmlRootElement(name = "drug")
public abstract class Drug {

    @XmlElement(namespace = "medicines", required = true)
    protected String name;
    @XmlElement(namespace = "medicines", required = true)
    protected String pharm;
    @XmlElement(namespace = "medicines", required = true)
    protected String group;
    @XmlElement(namespace = "medicines", required = true)
    protected String analogs;
    @XmlElement(namespace = "medicines", required = true)
    protected Characteristics characteristics = new Characteristics();
    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getPharm() {
        return pharm;
    }

    public void setPharm(String value) {
        this.pharm = value;
    }

    public Characteristics getCharacteristics() {
        return characteristics;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getAnalogs() {
        return analogs;
    }

    public void setAnalogs(String analogs) {
        this.analogs = analogs;
    }

    public void setCharacteristics(Characteristics value) {
        this.characteristics = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String value) {
        this.id = value;
    }

    public abstract void setCertificate(String value);

    public abstract String getCertificate();

    public abstract void setType(String value);

    public abstract String getType();

    @Override
    public String toString() {
        return "Drug{" +
                "name='" + name + '\'' +
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
        Drug drug = (Drug) o;
        return Objects.equals(name, drug.name) &&
                Objects.equals(pharm, drug.pharm) &&
                Objects.equals(group, drug.group) &&
                Objects.equals(analogs, drug.analogs) &&
                Objects.equals(characteristics, drug.characteristics) &&
                Objects.equals(id, drug.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, pharm, group, analogs, characteristics, id);
    }
}
