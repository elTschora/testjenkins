package fr.insarouen.asi.asiaventure.elements.structure;
import fr.insarouen.asi.asiaventure.elements.structure.ElementStructurel;
import fr.insarouen.asi.asiaventure.Monde;
import fr.insarouen.asi.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.hamcrest.core.IsEqual;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import java.lang.StringBuilder;


public class testClassElementStructurel{
    public ElementStructurel elem;
    public Monde monde;

    @Before
    public void avantTest() throws Exception{
	monde=new Monde("Planete Terre");
	elem=new ElementStructurel("Elem",monde){};
    }
    @Test
    public void testConstructeur(){
	assertThat(elem.getNom(),equalTo("Elem"));
	assertThat(elem.getMonde(),equalTo(monde));
	}

    }
