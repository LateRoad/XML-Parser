package com.lateroad.xmlparser.builder;

import com.lateroad.xmlparser.entity.Drug;
import com.lateroad.xmlparser.exception.XmlParserLogicException;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDrugBuilder {

    protected List<Drug> drugSet;

    AbstractDrugBuilder(){
        drugSet = new ArrayList<>();
    }

    public List<Drug> getDrugSet(){
        return drugSet;
    }

    public abstract void buildDrugs(String filePath) throws XmlParserLogicException;
}
