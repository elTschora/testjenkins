package fr.insarouen.asi.asiaventure.elements.objets.serrure;
import fr.insarouen.asi.asiaventure.elements.objets.serrurerie.Clef;
import fr.insarouen.asi.asiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.asi.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.asiaventure.Monde;
import fr.insarouen.asi.asiaventure.elements.Entite;
import fr.insarouen.asi.asiaventure.elements.Etat;
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


public class testClassSerrure{
    public Serrure serrure;
    public Monde monde;
    public Clef clef;

    @Before
    public void avantTest() throws Exception{
	monde=new Monde("Planete Terre");
	serrure=new Serrure(monde);
	clef=serrure.creerClef();
    }
    @Test
    public void testConstructeur(){
	assertThat(serrure.getMonde(),equalTo(monde));
	assertThat(serrure.getEtat(),equalTo(Etat.VERROUILLE));	
	}

    @Test
    public void testestDeplacable(){
	assertThat(serrure.estDeplacable(),is(false));
    }

    @Test
    public void testGetEtat(){
	assertThat(serrure.getEtat(),equalTo(Etat.VERROUILLE));
    }

    @Test
    public void testSetEtat(){
	serrure.setEtat(Etat.DEVEROUILLE);
	assertThat(serrure.getEtat(),equalTo(Etat.DEVEROUILLE));
    }

    @Test
    public void testGenererNom(){
    }

    @Test
    public void testCreerClef(){
	assertThat(serrure.creerClef()==null,is(true));
    }

    @Test
    public void activableAvec() throws Exception{
	PiedDeBiche pdb=new PiedDeBiche("pdb1",monde);
	assertThat(serrure.activableAvec(pdb),is(false));
	assertThat(serrure.activableAvec(clef),is(true));
    }
    
    @Test(expected=ActivationImpossibleException.class)
    public void testActiver() throws ActivationImpossibleException{
	serrure.activer();
    }
    
    @Test
    public void testActiverAvec() throws Exception{
	serrure.activerAvec(clef);
	assertThat(serrure.getEtat(),equalTo(Etat.DEVEROUILLE));
	serrure.activerAvec(clef);
	assertThat(serrure.getEtat(),equalTo(Etat.VERROUILLE));
    }

    @Test(expected=ActivationImpossibleAvecObjetException.class)
    public void testActiverAvecException1() throws ActivationImpossibleAvecObjetException,NomDEntiteDejaUtiliseDansLeMondeException{
	PiedDeBiche pdb=new PiedDeBiche("pdb1",monde);
	serrure.activerAvec(pdb);
    }

    @Test(expected=ActivationImpossibleAvecObjetException.class)
    public void testActiverAvecException2() throws ActivationImpossibleAvecObjetException{
	serrure.setEtat(Etat.CASSE);
	serrure.activerAvec(clef);
    }
	
	
	
}
