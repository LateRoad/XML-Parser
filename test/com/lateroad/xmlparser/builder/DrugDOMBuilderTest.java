package com.lateroad.xmlparser.builder;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class DrugDOMBuilderTest {

    AbstractDrugBuilder builder = new DrugDOMBuilder();
    String filepath = "test/resources/medicines.xml";
    String[] expected = {
            "Powder{certificate='100010', type='regulatory', name='Midnight Rain', pharm='Fantasy', group='null', analogs='null', characteristics=Characteristics{boxing='soft', dosage='An in'}, id='drug2'}",
            "Powder{certificate='109321', type='regulatory', name='Randall, Cynthia', pharm='Lover Birds', group='null', analogs='null', characteristics=Characteristics{boxing='soft', dosage='An in-depth look'}, id='drug6'}",
            "Powder{certificate='462135', type='', name='Thurman, Paula', pharm='Splish Splash', group='null', analogs='null', characteristics=Characteristics{boxing='rigid', dosage='AXML.'}, id='drug7'}",
            "Powder{certificate='425693', type='regulatory', name='O'Brien, Tim', pharm='Micros', group='null', analogs='null', characteristics=Characteristics{boxing='rigid', dosage='applications with XML'}, id='drug10'}",
            "Powder{certificate='205024', type='regulatory', name='Oberon's Legacy', pharm='Fantasy', group='null', analogs='null', characteristics=Characteristics{boxing='rigid', dosage='n-depth'}, id='drug16'}",
            "Pills{certificate='100000', type='neutralizing', name='Adderall', pharm='Mydayis', group='null', analogs='null', characteristics=Characteristics{boxing='rigid', dosage='An in-depth'}, id='drug1'}",
            "Pills{certificate='100023', type='reimbursable', name='Maeve Ascendant', pharm='Fantasy', group='null', analogs='null', characteristics=Characteristics{boxing='rigid', dosage='plications'}, id='drug3'}",
            "Pills{certificate='111111', type='reimbursable', name='Corets, Eva', pharm='The Sundered Grail', group='null', analogs='null', characteristics=Characteristics{boxing='semi-rigid', dosage='creating applications'}, id='drug5'}",
            "Pills{certificate='949592', type='', name='Kress, Peter', pharm='Paradox Lost', group='null', analogs='null', characteristics=Characteristics{boxing='semi-rigid', dosage='Ands'}, id='drug9'}",
            "Pills{certificate='999999', type='', name='O'Brien, Tim', pharm='MSXML3de', group='null', analogs='null', characteristics=Characteristics{boxing='semi-rigid', dosage='An in-dept'}, id='drug11'}",
            "Pills{certificate='294102', type='', name='Galos, Mike', pharm='Visual Studio 7', group='null', analogs='null', characteristics=Characteristics{boxing='soft', dosage='Andfk'}, id='drug12'}",
            "Pills{certificate='245614', type='reimbursable', name='Computer', pharm='XML Developer's Guide', group='null', analogs='null', characteristics=Characteristics{boxing='soft', dosage='An in-dok with XM'}, id='drug13'}",
            "Pills{certificate='623566', type='', name='Maeve Ascendant', pharm='Fantasy', group='null', analogs='null', characteristics=Characteristics{boxing='semi-rigid', dosage='An inook at creatin'}, id='drug15'}",
            "Capsules{certificate='120129', type='', name='Oberon's Legacy', pharm='Fantasy', group='null', analogs='null', characteristics=Characteristics{boxing='semi-rigid', dosage='with XML'}, id='drug4'}",
            "Capsules{certificate='049524', type='neutralizing', name='Knorr, Stefan', pharm='Creepy Crawlies', group='null', analogs='null', characteristics=Characteristics{boxing='rigid', dosage='look'}, id='drug8'}",
            "Capsules{certificate='950256', type='regulatory', name='Midnight Rain', pharm='Fantasy', group='null', analogs='null', characteristics=Characteristics{boxing='soft', dosage='AnML.'}, id='drug14'}",
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