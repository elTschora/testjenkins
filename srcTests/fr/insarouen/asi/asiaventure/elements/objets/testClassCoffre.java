package fr.insarouen.asi.asiaventure.elements.objets;
import fr.insarouen.asi.asiaventure.elements.objets.Coffre;
import fr.insarouen.asi.asiaventure.elements.objets.Enclume;
import fr.insarouen.asi.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.asiaventure.Monde;
import fr.insarouen.asi.asiaventure.elements.Etat;
import fr.insarouen.asi.asiaventure.elements.Activable;
import fr.insarouen.asi.asiaventure.elements.Entite;
import fr.insarouen.asi.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.asi.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.asiaventure.elements.ActivationException;
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


public class testClassCoffre{
    public Coffre coffre;
    public Monde monde;

    @Before
    public void avantTest() throws Exception{
	monde=new Monde("Planete Terre");
	coffre=new Coffre("coffre",monde);
    }
    @Test
    public void testConstructeur(){
	assertThat(coffre.getNom(),equalTo("coffre"));
	assertThat(coffre.getMonde(),equalTo(monde));
	assertThat(coffre.getEtat(),equalTo(Etat.FERME));
	}
    @Test
    public void testestDeplacable(){
	assertThat(coffre.estDeplacable(),is(false));
    }
    @Test
    public void testGetEtat(){
	assertThat(coffre.getEtat(),equalTo(Etat.FERME));
    }
     @Test
    public void testSetEtat(){
	coffre.setEtat(Etat.DEVEROUILLE);
	assertThat(coffre.getEtat(),equalTo(Etat.DEVEROUILLE));
    }
   
    @Test
    public void testActivableAvec() throws Exception{
	PiedDeBiche pdb=new PiedDeBiche("pdb1",monde);
	Enclume encl = new Enclume("encl1",monde);
	assertThat(coffre.activableAvec(pdb),is(true));
	assertThat(coffre.activableAvec(encl),is(false));
    }

    @Test
    public void testActiver() throws Exception{
	coffre.activer();
	assertThat(coffre.getEtat(),equalTo(Etat.OUVERT));
	coffre.activer();
	assertThat(coffre.getEtat(),equalTo(Etat.FERME));
    }
    @Test
    public void testActiverAvec() throws Exception{
	PiedDeBiche pdb=new PiedDeBiche("pdb1",monde);
	coffre.activerAvec(pdb);
	assertThat(coffre.getEtat(),equalTo(Etat.CASSE));
    }

    @Test(expected=ActivationException.class)
    public void testActivationException1() throws Exception{
	coffre.setEtat(Etat.VERROUILLE);
	coffre.activer();
    }

    @Test(expected=ActivationException.class)
    public void testActivationException2() throws Exception{
	Enclume encl = new Enclume("encl1",monde);	
	coffre.activerAvec(encl);
    }
	


}
