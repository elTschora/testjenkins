package fr.insarouen.asi.asiaventure.elements.vivants;
import fr.insarouen.asi.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.asiaventure.elements.objets.Enclume;
import fr.insarouen.asi.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.asiaventure.Monde;
import fr.insarouen.asi.asiaventure.elements.Etat;
import fr.insarouen.asi.asiaventure.elements.Activable;
import fr.insarouen.asi.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.asiaventure.elements.structure.PorteFermeException;
import fr.insarouen.asi.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.asiaventure.elements.vivants.ObjetNonPossedeParLeVivantException;
import fr.insarouen.asi.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.asiaventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.asi.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
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


public class testClassVivant{
    public Objet[] objets;
    public Vivant vivant;
    public Monde monde;
    public Piece piece;
    
    @Before
    public void avantTest() throws Exception{
	monde=new Monde("Planete Terre");
	piece=new Piece("Salle",monde);
	objets=new Objet[0];
	vivant=new Vivant("Géraldine",monde,20,20,piece,objets){
		public void executer(){
		    System.out.println("Coucou");
		}
	    };
    }

    @Test
    public void testConstructeur(){
	assertThat(vivant.getMonde(),equalTo(monde));
	assertThat(vivant.getNom(),equalTo("Géraldine"));
	assertThat(vivant.getPiece(),equalTo(piece));
	Set<Objet> objets=new TreeSet<Objet>();
	assertThat( (vivant.getObjets().containsAll(objets) && objets.containsAll(vivant.getObjets())),is(true));
	assertThat(vivant.getPointVie(),equalTo(20));
	assertThat(vivant.getPointForce(),equalTo(20));
    }

    @Test 
    public void testGetObjets(){
	Set<Objet> objets=new TreeSet<Objet>();
	assertThat( (vivant.getObjets().containsAll(objets) && objets.containsAll(vivant.getObjets())),is(true));
    }

    @Test
    public void testGetPointVie(){
	assertThat(vivant.getPointVie(),equalTo(20));
    }	

    @Test
    public void testEstMort(){
	int valeur=10;
	vivant.setPointVie(valeur);
	assertThat(vivant.estMort(),is(false));
	vivant.setPointVie(-valeur);
	assertThat(vivant.estMort(),is(true));
    }

    @Test 
    public void testSetPointVie(){
	int valeur=10;
	vivant.setPointVie(valeur);
	assertThat(vivant.getPointVie(),equalTo(valeur));
    }
	
    
    @Test
    public void testGetPointForce(){
	assertThat(vivant.getPointForce(),equalTo(20));
    }
    @Test
    public void testGetObjet() throws Exception{
	PiedDeBiche pdb1= new PiedDeBiche("pdb1",monde);
	PiedDeBiche pdb2= new PiedDeBiche("pdb2",monde);
	piece.deposer(pdb1);
	piece.deposer(pdb2);
	vivant.prendre(pdb1);
	vivant.prendre("pdb2");
	assertThat(vivant.getObjet("pdb1"),equalTo(pdb1));
	assertThat(vivant.getObjet("pdb2"),equalTo(pdb2));
    }

    @Test
    public void testGetPiece(){
	assertThat(vivant.getPiece(),equalTo(piece));
    }

    @Test
    public void testPossede() throws Exception{
	PiedDeBiche pdb1= new PiedDeBiche("pdb1",monde);
	PiedDeBiche pdb2= new PiedDeBiche("pdb2",monde);
	piece.deposer(pdb1);
	vivant.prendre(pdb1);
	assertThat(vivant.possede("pdb1"),is(true));
        assertThat(vivant.possede(pdb1),is(true));
	assertThat(vivant.possede("pdb2"),is(false));
	assertThat(vivant.possede(pdb2),is(false));
    }

    @Test
    public void testPrendre() throws Exception{
	PiedDeBiche pdb1= new PiedDeBiche("pdb1",monde);
	PiedDeBiche pdb2= new PiedDeBiche("pdb2",monde);
	piece.deposer(pdb1);
	piece.deposer(pdb2);
	vivant.prendre(pdb1);
	vivant.prendre("pdb2");
	assertThat(vivant.possede(pdb1),is(true));
	assertThat(vivant.possede(pdb2),is(true));
	assertThat(piece.contientObjet(pdb1),is(false));
	assertThat(piece.contientObjet(pdb2),is(false));
    }

    @Test(expected=ObjetNonDeplacableException.class)
    public void testObjetNonDeplacableException1() throws Exception{
	Enclume e1=new Enclume("enclume1",monde);
	piece.deposer(e1);
	vivant.prendre(e1);
    }

    @Test(expected=ObjetNonDeplacableException.class)
    public void testObjetNonDeplacableException2() throws Exception{
	Enclume e1=new Enclume("enclume1",monde);
	piece.deposer(e1);
	vivant.prendre("enclume1");
    }
    
    @Test
    public void testDeposer() throws Exception{
	PiedDeBiche pdb1= new PiedDeBiche("pdb1",monde);
	PiedDeBiche pdb2= new PiedDeBiche("pdb2",monde);
	piece.deposer(pdb1);
	piece.deposer(pdb2);
	vivant.prendre(pdb1);
	vivant.prendre("pdb2");
	vivant.deposer(pdb1);
	vivant.deposer("pdb2");
	assertThat(vivant.possede(pdb1),is(false));
	assertThat(vivant.possede(pdb2),is(false));
	assertThat(piece.contientObjet(pdb1),is(true));
	assertThat(piece.contientObjet(pdb2),is(true));
    }

    @Test
    public void testActiverActivable() throws Exception{
	Porte porte= new Porte("p1",monde,piece,piece);
	vivant.activerActivable(porte);
	assertThat(porte.getEtat(),equalTo(Etat.OUVERT));
	vivant.activerActivable(porte);
	assertThat(porte.getEtat(),equalTo(Etat.FERME));
    }
    
    @Test
    public void testActiverActivableAvecObjet() throws Exception{
	Porte porte= new Porte("p1",monde,piece,piece);
	PiedDeBiche pdb=new PiedDeBiche("pdb",monde);
	vivant.activerActivableAvecObjet(porte,pdb);
	assertThat(porte.getEtat(),equalTo(Etat.CASSE));
    }

    @Test
    public void testFranchir() throws Exception{
	Piece piece1=new Piece("Salle de jeux",monde);	
	Porte porte= new Porte("p1",monde,piece,piece1);
	porte.etat=Etat.OUVERT;
	vivant.franchir(porte);
	assertThat(piece1.contientVivant(vivant),is(true));
	assertThat(piece.contientVivant(vivant),is(false));
	vivant.franchir("p1");
	assertThat(piece.contientVivant(vivant),is(true));
	assertThat(piece1.contientVivant(vivant),is(false));
    }

    @Test(expected=PorteFermeException.class)
    public void testPorteFermeException1() throws Exception{
	Piece piece1=new Piece("Salle de jeux",monde);	
	Porte porte= new Porte("p1",monde,piece,piece1);
	vivant.franchir(porte);
    }

    @Test(expected=PorteFermeException.class)
    public void testPorteFermeException2() throws Exception{
	Piece piece1=new Piece("Salle de jeux",monde);	
	Porte porte= new Porte("p1",monde,piece1,piece);
	vivant.franchir("p1");
    }

    @Test(expected=PorteInexistanteDansLaPieceException.class)
    public void testPorteInexistanteDansLaPieceException() throws Exception{
	Piece piece1=new Piece("Salle de jeux",monde);	
	Porte porte= new Porte("p1",monde,piece1,piece1);
	vivant.franchir("p1");
    }

    @Test(expected=PorteInexistanteDansLaPieceException.class)
    public void testPorteInexistanteDansLaPieceException2() throws Exception{
	Piece piece1=new Piece("Salle de jeux",monde);	
	Porte porte= new Porte("p1",monde,piece1,piece1);
	vivant.franchir(porte);
    }
    
    @Test(expected=ObjetAbsentDeLaPieceException.class)
    public void testObjetAbsentDeLaPieceException1() throws Exception{
	PiedDeBiche pdb1= new PiedDeBiche("pdb1",monde);
	vivant.prendre(pdb1);
    }

    @Test(expected=ObjetAbsentDeLaPieceException.class)
    public void testObjetAbsentDeLaPieceException2() throws Exception{
	PiedDeBiche pdb1= new PiedDeBiche("pdb1",monde);
	vivant.prendre("pdb1");
    }
	
    @Test(expected=ObjetNonPossedeParLeVivantException.class)
    public void testObjetNonPossedeParLeVivantException1() throws Exception{
	PiedDeBiche pdb1=new PiedDeBiche("pdb1",monde);
	vivant.deposer(pdb1);
    }

    @Test(expected=ObjetNonPossedeParLeVivantException.class)
    public void testObjetNonPossedeParLeVivantException2() throws Exception{
	PiedDeBiche pdb1=new PiedDeBiche("pdb1",monde);
	vivant.deposer("pdb1");
    }
    
}
	
    
