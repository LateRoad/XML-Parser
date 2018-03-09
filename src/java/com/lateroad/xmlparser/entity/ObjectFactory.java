package com.lateroad.xmlparser.entity;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry
public class ObjectFactory {
    private final static QName _Pills_QNAME = new QName("medicines", "pills");
    private final static QName _Powder_QNAME = new QName("medicines", "powder");
    private final static QName _Capsules_QNAME = new QName("medicines", "capsules");
    private final static QName _Drug_QNAME = new QName("medicines", "drug");

    public ObjectFactory() {}

    @XmlElementDecl(namespace = "medicines", name = "capsules", substitutionHeadNamespace = "medicines", substitutionHeadName = "drug")
    public JAXBElement<Capsules> createCapsules(Capsules value) {
        return new JAXBElement<>(_Capsules_QNAME, Capsules.class, null, value);
    }

    @XmlElementDecl(namespace = "medicines", name = "pills", substitutionHeadNamespace = "medicines", substitutionHeadName = "drug")
    public JAXBElement<Pills> createPills(Pills value) {
        return new JAXBElement<>(_Pills_QNAME, Pills.class, null, value);
    }

    @XmlElementDecl(namespace = "medicines", name = "powder", substitutionHeadNamespace = "medicines", substitutionHeadName = "drug")
    public JAXBElement<Powder> createPowder(Powder value) {
        return new JAXBElement<>(_Powder_QNAME, Powder.class, null, value);
    }

    @XmlElementDecl(namespace = "medicines", name = "drug")
    public JAXBElement<Drug> createPaper(Drug value) {
        return new JAXBElement<>(_Drug_QNAME, Drug.class, null, value);
    }

}