package fr.insarouen.asi.asiaventure.elements;
import fr.insarouen.asi.asiaventure.Monde;
import fr.insarouen.asi.asiaventure.elements.Entite;
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



public class testClassEntite{
    public Entite entite;
    public Monde monde;

    @Before
    public void avantTest() throws Exception{
	monde=new Monde("Planete Terre");
	entite=new Entite("Monstre",monde){};
    }

    @Test
    public void testConstructeur(){
	assertThat(entite.getNom(),equalTo("Monstre"));
	assertThat(entite.getMonde(),equalTo(monde));
	}

    @Test
    public void testGetNom(){
	assertThat(entite.getNom(),equalTo("Monstre"));
    }

    @Test
    public void testGetMonde(){
	assertThat(entite.getMonde(),equalTo(monde));
    }

    @Test
    public void testHashCode()throws Exception{
	
	Entite entite2= new Entite("chose",monde){};
	assertThat(entite.hashCode()==entite.hashCode(),is(true));
       	assertThat(entite.hashCode()==entite2.hashCode(),is(false));
    }

    @Test
    public void testEquals()throws Exception{
	Entite entite2= new Entite("chose",monde){};
	assertThat(entite.equals(entite),is(true));
       	assertThat(entite.equals(entite2),is(false));
    }

    @Test(expected=NomDEntiteDejaUtiliseDansLeMondeException.class)
    public void testNomDEntiteDejaUtiliseDansLeMondeException() throws Exception{
	Entite entite=new Entite("Monstre",monde){};
    }
   
}

