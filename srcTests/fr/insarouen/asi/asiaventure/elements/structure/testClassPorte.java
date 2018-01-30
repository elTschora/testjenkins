package fr.insarouen.asi.asiaventure.elements.structure;

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
import fr.insarouen.asi.asiaventure.Monde;
import fr.insarouen.asi.asiaventure.elements.Etat;
import fr.insarouen.asi.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.asiaventure.elements.objets.Enclume;
import fr.insarouen.asi.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.asi.asiaventure.elements.ActivationImpossibleException;


public class testClassPorte{
    public Piece pieceA;
    public Piece pieceB;
    public Etat etat;
    public String nom;
    public Monde monde;
    public Porte porte;

    @Before
    public void avantTest() throws Exception{
	monde = new Monde ("Planete Terre");
	pieceA = new Piece("PieceA",monde);
	pieceB=new Piece("PieceB",monde);
	porte = new Porte ("Porte d'entrée",monde,pieceA,pieceB);
    }

    @Test
    public void testConstructeur (){
	assertThat(porte.getMonde(),equalTo(monde));
	assertThat(porte.getNom(),equalTo("Porte d'entrée"));
	assertThat(porte.pieceA,equalTo(pieceA));
	assertThat(porte.pieceB,equalTo(pieceB));
	assertThat(porte.etat,equalTo(Etat.FERME));
	assertThat(pieceA.aLaPorte(porte),is(true));
	assertThat(pieceB.aLaPorte(porte),is(true));
    }
	
    @Test
    public void testGetEtat(){
	assertThat(porte.getEtat(),equalTo(Etat.FERME));
    }

    @Test
    public void testGetPieceAutreCote(){
	assertThat(porte.getPieceAutreCote(pieceA),equalTo(pieceB));
	assertThat(porte.getPieceAutreCote(pieceB),equalTo(pieceA));
    }

    @Test
    public void testActiverAvec() throws Exception{
	PiedDeBiche pdb=new PiedDeBiche("pdb1",monde);
	porte.activerAvec(pdb);
	assertThat(porte.getEtat(),equalTo(Etat.CASSE));
	porte.etat=Etat.VERROUILLE;
	porte.activerAvec(pdb);
	assertThat(porte.getEtat(),equalTo(Etat.CASSE));
    }

    @Test
    public void testActiver() throws Exception{
	porte.activer();
	assertThat(porte.getEtat(),equalTo(Etat.OUVERT));
	porte.activer();
	assertThat(porte.getEtat(),equalTo(Etat.FERME));
    }

    @Test
    public void testActivableAvec() throws Exception{
	PiedDeBiche pdb=new PiedDeBiche("pdb1",monde);
	Enclume enclume=new Enclume("enclume",monde);
	assertThat(porte.activableAvec(pdb),is(true));
	assertThat(porte.activableAvec(enclume),is(false));
    }

    @Test(expected=ActivationImpossibleAvecObjetException.class)
    public void testActivationImpossibleAvecObjetException() throws Exception{
	Enclume enclume=new Enclume("enclume",monde);
	porte.activerAvec(enclume);
    }
    
    @Test(expected=ActivationImpossibleException.class)
    public void testActivationImpossible1() throws Exception{
	porte.etat=Etat.VERROUILLE;
	porte.activer();
    }

    @Test(expected=ActivationImpossibleException.class)
    public void testActivationImpossible2() throws Exception{
	PiedDeBiche pdb=new PiedDeBiche("pdb1",monde);
	porte.etat=Etat.CASSE;
	porte.activerAvec(pdb);
    }

       
}
