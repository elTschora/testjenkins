package fr.insarouen.asi.asiaventure;
import fr.insarouen.asi.asiaventure.Monde;
import fr.insarouen.asi.asiaventure.elements.Entite;
import fr.insarouen.asi.asiaventure.elements.Executable;
import fr.insarouen.asi.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.asiaventure.elements.vivants.JoueurHumain;
import fr.insarouen.asi.asiaventure.elements.vivants.Monstre;
import fr.insarouen.asi.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.asiaventure.EntiteDejaDansUnAutreMondeException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import org.hamcrest.core.IsEqual;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;



public class testClassMonde{
    public Monde monde;

    @Before
    public void avantTest() throws Exception{
	monde=new Monde("Planete Terre");
    }

    @Test
    public void testConstructeur(){
       	Set<Entite> lEntites=new HashSet<Entite>();
	lEntites.clear();
	assertThat(monde.getNom(),equalTo("Planete Terre"));
	assertThat( (monde.getEntites().containsAll(lEntites) && lEntites.containsAll(monde.getEntites())),is(true));
	}

    @Test
    public void testGetNom(){
	assertThat(monde.getNom(),equalTo("Planete Terre"));
    }

    @Test 
    public void testGetEntite() throws Exception{
	Entite e1=new Entite("e1",monde){};
	assertThat(monde.getEntite("e1"),equalTo(e1));
	assertThat(monde.getEntite("e2"),equalTo(null));
    }
	
    @Test
    public void testEntitePresente() throws Exception{
       	Entite entite=new Entite("Monstre",monde){};
	assertThat(monde.entitePresente("Monstre"),is(true));
	assertThat(monde.entitePresente("Chose"),is(false));
    }
    
    @Test(expected=NomDEntiteDejaUtiliseDansLeMondeException.class)
    public void testNomDEntiteDejaUtiliseDansLeMondeException() throws Exception{
       	Entite entite=new Entite("Monstre",monde){};
	Entite entite2=new Entite("Monstre",monde){};
    }
    @Test(expected=EntiteDejaDansUnAutreMondeException.class)
    public void testEntiteDejaDansUnAutreMondeException() throws Exception{
       	Entite entite=new Entite("Monstre",monde){};
	Monde monde2=new Monde("Planete Mars");
	monde2.ajouter(entite);
    }

    @Test
    public void testGetExecutables() throws Exception{
	Piece piece = new Piece("piece",monde);
	Objet[] objets = new Objet[0];
	Monstre monstre = new Monstre("Monstre1",monde,20,20,piece,objets);
	Entite entite1=new Entite("Monstre2",monde){};
	Entite entite2=new Entite("Monstre3",monde){};
	JoueurHumain joueur = new JoueurHumain("Monstre4",monde,20,20,piece,objets);
	Set<Executable> res = new HashSet<Executable>();
	res.add(monstre);
	res.add(joueur);
	List<Executable> executables = monde.getExecutables();
	assertThat(executables.containsAll(res),is(true));
	assertThat(res.containsAll(executables),is(true));
    }
	
    
   
}

