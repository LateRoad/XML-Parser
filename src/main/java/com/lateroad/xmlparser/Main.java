package com.lateroad.xmlparser;


import com.lateroad.xmlparser.builder.AbstractDrugBuilder;
import com.lateroad.xmlparser.entity.Drug;
import com.lateroad.xmlparser.factory.DrugBuilderFactory;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        AbstractDrugBuilder paperBuilder = DrugBuilderFactory.createDrugBuilder("dom");
        paperBuilder.buildDrugs("files/medicines.xml");
        Set<Drug> temp = paperBuilder.getDrugSet();
        for(Drug cur :temp){
        System.out.println(cur);
        }
    }
}
