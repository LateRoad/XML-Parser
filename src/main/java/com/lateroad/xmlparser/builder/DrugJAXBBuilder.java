package com.lateroad.xmlparser.builder;

import com.lateroad.xmlparser.entity.Drug;
import com.lateroad.xmlparser.entity.Medicines;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.ArrayList;

public class DrugJAXBBuilder extends AbstractDrugBuilder {

    @Override
    public void buildDrugs(String filePath) {
        JAXBContext jc = null;
        try {
            jc = JAXBContext.newInstance(Medicines.class);
            Unmarshaller um = jc.createUnmarshaller();
            String schemaName = "files/medicines.xsd";
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaLocation = new File(schemaName);// создание схемы и перадача ее демарашаллизатору
            Schema schema = factory.newSchema(schemaLocation);
            um.setSchema(schema);

            Source source = new StreamSource("files/medicines.xml");
            JAXBElement<Medicines> root = um.unmarshal(source, Medicines.class);
            Medicines foo = root.getValue();
            ArrayList<JAXBElement<? extends Drug>> p = foo.getDrug();
            JAXBElement<? extends Drug> jp;
            for(int i = 0; i < p.size(); ++i){
                jp = p.get(i);
                drugSet.add(jp.getValue());
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SAXException e) {
        }
    }
}
