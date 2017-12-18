package com.lateroad.xmlparser.builder;

import com.lateroad.xmlparser.entity.Drug;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractDrugBuilder {

    protected Set<Drug> drugSet;

    AbstractDrugBuilder(){
        drugSet = new HashSet<>();
    }

    AbstractDrugBuilder(Set<Drug> drugs){
        this.drugSet = drugs;
    }

    public Set<Drug> getDrugSet(){
        return drugSet;
    }

    abstract public void buildDrugs(String filePath);
}
