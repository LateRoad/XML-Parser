package com.lateroad.xmlparser.factory;


import com.lateroad.xmlparser.builder.*;

public class DrugBuilderFactory {

    private enum ParserType {
        DOM, SAX, STAX, JAXB
    }

    private DrugBuilderFactory() {}

    public static AbstractDrugBuilder createDrugBuilder(String parserType){

        ParserType type = ParserType.valueOf(parserType.toUpperCase());
        switch(type){
            case DOM: return new DrugDOMBuilder();
            case SAX: return new DrugSAXBuilder();
            case STAX: return new DrugSTAXBuilder();
            case JAXB: return new DrugJAXBBuilder();
            default: throw new EnumConstantNotPresentException(type.getDeclaringClass(),type.name());
        }
    }
}
