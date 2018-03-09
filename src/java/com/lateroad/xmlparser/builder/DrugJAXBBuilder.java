package com.lateroad.xmlparser.builder;

import com.lateroad.xmlparser.entity.Drug;
import com.lateroad.xmlparser.entity.Medicines;
import com.lateroad.xmlparser.exception.XmlParserLogicException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
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
    static {
        new DOMConfigurator().doConfigure("log4j2.xml", LogManager.getLoggerRepository());
    }

    private static final Logger logger = Logger.getLogger(DrugJAXBBuilder.class);

    private static final String XML_EXTENSION = "xml";
    private static final String XSD_EXTENSION = "xsd";

    @Override
    public void buildDrugs(String filePath) throws XmlParserLogicException {
        JAXBContext jc = null;
        try {
            jc = JAXBContext.newInstance(Medicines.class);
            Unmarshaller um = jc.createUnmarshaller();
            String schemaName = filePath.replace(XML_EXTENSION, XSD_EXTENSION);
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaLocation = new File(schemaName);// создание схемы и перадача ее демарашаллизатору
            Schema schema = factory.newSchema(schemaLocation);
            um.setSchema(schema);

            Source source = new StreamSource(filePath);
            JAXBElement<Medicines> root = um.unmarshal(source, Medicines.class);
            Medicines foo = root.getValue();
            ArrayList<JAXBElement<? extends Drug>> p = foo.getDrug();
            JAXBElement<? extends Drug> jp;
            for (JAXBElement<? extends Drug> aP : p) {
                jp = aP;
                drugSet.add(jp.getValue());
            }
        } catch (JAXBException | SAXException e) {
            logger.error(e);
            throw new XmlParserLogicException();
        }
    }
}
