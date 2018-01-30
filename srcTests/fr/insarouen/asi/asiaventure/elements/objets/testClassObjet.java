package fr.insarouen.asi.asiaventure.elements.objets;
import fr.insarouen.asi.asiaventure.elements.objets.Objet;
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
import java.lang.StringBuilder;


public class testClassObjet{
    public Objet objet;
    public Monde monde;

    @Before
    public void avantTest() throws Exception{
	monde=new Monde("Planete Terre");
	objet=new Objet("Banane",monde){
		public boolean estDeplacable(){
		    return false;
		}
	    };
    }
    @Test
    public void testConstructeur(){
	assertThat(objet.getNom(),equalTo("Banane"));
	assertThat(objet.getMonde(),equalTo(monde));
	}

    }
