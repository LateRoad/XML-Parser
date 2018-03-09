package com.lateroad.xmlparser.builder;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class DrugJAXBBuilderTest {

    AbstractDrugBuilder builder = new DrugJAXBBuilder();
    String filepath = "test/resources/medicines.xml";
    String[] expected = {
            "Pills{certificate='100000', type='neutralizing', name='Adderall', pharm='Mydayis', group='antibiotic', analogs='Amphetamine', characteristics=Characteristics{boxing='rigid', dosage='An in-depth'}, id='drug1'}",
            "Powder{certificate='100010', type='regulatory', name='Midnight Rain', pharm='Fantasy', group='painkiller', analogs='2000-12-16', characteristics=Characteristics{boxing='soft', dosage='An in'}, id='drug2'}",
            "Pills{certificate='100023', type='reimbursable', name='Maeve Ascendant', pharm='Fantasy', group='antibiotic', analogs='2000-11-17', characteristics=Characteristics{boxing='rigid', dosage='plications'}, id='drug3'}",
            "Capsules{certificate='120129', type='null', name='Oberon's Legacy', pharm='Fantasy', group='vitamins', analogs='2001-03-10', characteristics=Characteristics{boxing='semi-rigid', dosage='with XML'}, id='drug4'}",
            "Pills{certificate='111111', type='reimbursable', name='Corets, Eva', pharm='The Sundered Grail', group='vitamins', analogs='5.95', characteristics=Characteristics{boxing='semi-rigid', dosage='creating applications'}, id='drug5'}",
            "Powder{certificate='109321', type='regulatory', name='Randall, Cynthia', pharm='Lover Birds', group='painkiller', analogs='4.95', characteristics=Characteristics{boxing='soft', dosage='An in-depth look'}, id='drug6'}",
            "Powder{certificate='462135', type='null', name='Thurman, Paula', pharm='Splish Splash', group='antibiotic', analogs='4.95', characteristics=Characteristics{boxing='rigid', dosage='AXML.'}, id='drug7'}",
            "Capsules{certificate='049524', type='neutralizing', name='Knorr, Stefan', pharm='Creepy Crawlies', group='antibiotic', analogs='4.95', characteristics=Characteristics{boxing='rigid', dosage='look'}, id='drug8'}",
            "Pills{certificate='949592', type='null', name='Kress, Peter', pharm='Paradox Lost', group='vitamins', analogs='6.95', characteristics=Characteristics{boxing='semi-rigid', dosage='Ands'}, id='drug9'}",
            "Powder{certificate='425693', type='regulatory', name='O'Brien, Tim', pharm='Micros', group='antibiotic', analogs='36.95', characteristics=Characteristics{boxing='rigid', dosage='applications with XML'}, id='drug10'}",
            "Pills{certificate='999999', type='null', name='O'Brien, Tim', pharm='MSXML3de', group='painkiller', analogs='36.95', characteristics=Characteristics{boxing='semi-rigid', dosage='An in-dept'}, id='drug11'}",
            "Pills{certificate='294102', type='null', name='Galos, Mike', pharm='Visual Studio 7', group='painkiller', analogs='49.95', characteristics=Characteristics{boxing='soft', dosage='Andfk'}, id='drug12'}",
            "Pills{certificate='245614', type='reimbursable', name='Computer', pharm='XML Developer's Guide', group='vitamins', analogs='2000-10-01', characteristics=Characteristics{boxing='soft', dosage='An in-dok with XM'}, id='drug13'}",
            "Capsules{certificate='950256', type='regulatory', name='Midnight Rain', pharm='Fantasy', group='vitamins', analogs='2000-12-16', characteristics=Characteristics{boxing='soft', dosage='AnML.'}, id='drug14'}",
            "Pills{certificate='623566', type='null', name='Maeve Ascendant', pharm='Fantasy', group='painkiller', analogs='2000-11-17', characteristics=Characteristics{boxing='semi-rigid', dosage='An inook at creatin'}, id='drug15'}",
            "Powder{certificate='205024', type='regulatory', name='Oberon's Legacy', pharm='Fantasy', group='antibiotic', analogs='2001-03-10', characteristics=Characteristics{boxing='rigid', dosage='n-depth'}, id='drug16'}",

    };


    @Test
    public void testBuildDrugs() throws Exception {
        builder.buildDrugs(filepath);
        List actual = builder.getDrugSet();
        for (int i = 0; i < actual.size(); ++i) {
            Assert.assertEquals(actual.get(i).toString(), expected[i]);
        }
    }
}