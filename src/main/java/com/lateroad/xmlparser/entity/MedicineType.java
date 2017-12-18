package com.lateroad.xmlparser.entity;

public enum MedicineType {
    MEDICINES("medicines"),
    DRUG("drug"),
    PILLS("pills"),
    POWDER("powder"),
    CAPSULES("capsules"),
    ID("id"),
    TYPE("type"),
    NAME("name"),
    PHARM("pharm"),
    GROUP("group"),
    ANALOGS("analogs"),
    CHARACTERISTICS("characteristics"),
    BOXING("boxing"),
    CERTIFICATE("certificate"),
    DOSAGE("dosage");

    private String value;

    MedicineType(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }


}
