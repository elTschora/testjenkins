package fr.insarouen.asi.asiaventure.elements.vivants;
import fr.insarouen.asi.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.asiaventure.elements.objets.Enclume;
import fr.insarouen.asi.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.asiaventure.elements.vivants.JoueurHumain;
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
import java.lang.StringBuilder;
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


public class testClassJoueurHumain{
    public Objet[] objets;
    public JoueurHumain joueur;
    public Monde monde;
    public Piece piece;
    
    @Before
    public void avantTest() throws Exception{
	monde=new Monde("Planete Terre");
	piece=new Piece("Salle",monde);
	objets=new Objet[0];
	joueur=new JoueurHumain("Géraldine",monde,20,20,piece,objets);
    }

    @Test
    public void testConstructeur(){
	assertThat(joueur.getMonde(),equalTo(monde));
	assertThat(joueur.getNom(),equalTo("Géraldine"));
	assertThat(joueur.getPiece(),equalTo(piece));
	Set<Objet> objets=new TreeSet<Objet>();
	assertThat( (joueur.getObjets().containsAll(objets) && objets.containsAll(joueur.getObjets())),is(true));
	assertThat(joueur.getPointVie(),equalTo(20));
	assertThat(joueur.getPointForce(),equalTo(20));
	assertThat(joueur.getOrdre(),equalTo("Rien"));
    }

    @Test
    public void testGetOrdre(){
	assertThat(joueur.getOrdre(),equalTo("Rien"));
	joueur.setOrdre("Essai");
	assertThat(joueur.getOrdre(),equalTo("Essai"));
    }

    @Test
    public void testSetOrdre(){
	joueur.setOrdre("Essai");
	assertThat(joueur.getOrdre(),equalTo("Essai"));
    }

    @Test 
    public void testGetObjets(){
	Set<Objet> objets=new TreeSet<Objet>();
	assertThat( (joueur.getObjets().containsAll(objets) && objets.containsAll(joueur.getObjets())),is(true));
    }

    @Test
    public void testGetPointVie(){
	assertThat(joueur.getPointVie(),equalTo(20));
    }	

    @Test
    public void testEstMort(){
	int valeur=10;
	joueur.setPointVie(valeur);
	assertThat(joueur.estMort(),is(false));
	joueur.setPointVie(-valeur);
	assertThat(joueur.estMort(),is(true));
    }

    @Test 
    public void testSetPointVie(){
	int valeur=10;
	joueur.setPointVie(valeur);
	assertThat(joueur.getPointVie(),equalTo(valeur));
    }
	
    
    @Test
    public void testGetPointForce(){
	assertThat(joueur.getPointForce(),equalTo(20));
    }
    @Test
    public void testGetObjet() throws Exception{
	PiedDeBiche pdb1= new PiedDeBiche("pdb1",monde);
	PiedDeBiche pdb2= new PiedDeBiche("pdb2",monde);
	piece.deposer(pdb1);
	piece.deposer(pdb2);
	joueur.prendre(pdb1);
	joueur.prendre("pdb2");
	assertThat(joueur.getObjet("pdb1"),equalTo(pdb1));
	assertThat(joueur.getObjet("pdb2"),equalTo(pdb2));
    }

    @Test
    public void testGetPiece(){
	assertThat(joueur.getPiece(),equalTo(piece));
    }

    @Test
    public void testPossede() throws Exception{
	PiedDeBiche pdb1= new PiedDeBiche("pdb1",monde);
	PiedDeBiche pdb2= new PiedDeBiche("pdb2",monde);
	piece.deposer(pdb1);
	joueur.prendre(pdb1);
	assertThat(joueur.possede("pdb1"),is(true));
        assertThat(joueur.possede(pdb1),is(true));
	assertThat(joueur.possede("pdb2"),is(false));
	assertThat(joueur.possede(pdb2),is(false));
    }

    @Test
    public void testPrendre() throws Exception{
	PiedDeBiche pdb1= new PiedDeBiche("pdb1",monde);
	PiedDeBiche pdb2= new PiedDeBiche("pdb2",monde);
	piece.deposer(pdb1);
	piece.deposer(pdb2);
	joueur.prendre(pdb1);
	joueur.prendre("pdb2");
	assertThat(joueur.possede(pdb1),is(true));
	assertThat(joueur.possede(pdb2),is(true));
	assertThat(piece.contientObjet(pdb1),is(false));
	assertThat(piece.contientObjet(pdb2),is(false));
    }

    @Test(expected=ObjetNonDeplacableException.class)
    public void testObjetNonDeplacableException1() throws Exception{
	Enclume e1=new Enclume("enclume1",monde);
	piece.deposer(e1);
	joueur.prendre(e1);
    }

    @Test(expected=ObjetNonDeplacableException.class)
    public void testObjetNonDeplacableException2() throws Exception{
	Enclume e1=new Enclume("enclume1",monde);
	piece.deposer(e1);
	joueur.prendre("enclume1");
    }
    
    @Test
    public void testDeposer() throws Exception{
	PiedDeBiche pdb1= new PiedDeBiche("pdb1",monde);
	PiedDeBiche pdb2= new PiedDeBiche("pdb2",monde);
	piece.deposer(pdb1);
	piece.deposer(pdb2);
	joueur.prendre(pdb1);
	joueur.prendre("pdb2");
	joueur.deposer(pdb1);
	joueur.deposer("pdb2");
	assertThat(joueur.possede(pdb1),is(false));
	assertThat(joueur.possede(pdb2),is(false));
	assertThat(piece.contientObjet(pdb1),is(true));
	assertThat(piece.contientObjet(pdb2),is(true));
    }

    @Test
    public void testActiverActivable() throws Exception{
	Porte porte= new Porte("p1",monde,piece,piece);
	joueur.activerActivable(porte);
	assertThat(porte.getEtat(),equalTo(Etat.OUVERT));
	joueur.activerActivable(porte);
	assertThat(porte.getEtat(),equalTo(Etat.FERME));
    }
    
    @Test
    public void testActiverActivableAvecObjet() throws Exception{
	Porte porte= new Porte("p1",monde,piece,piece);
	PiedDeBiche pdb=new PiedDeBiche("pdb",monde);
	joueur.activerActivableAvecObjet(porte,pdb);
	assertThat(porte.getEtat(),equalTo(Etat.CASSE));
    }

    @Test
    public void testFranchir() throws Exception{
	Piece piece1=new Piece("Salle de jeux",monde);	
	Porte porte= new Porte("p1",monde,piece,piece1);
	porte.etat=Etat.OUVERT;
	joueur.franchir(porte);
	assertThat(piece1.contientVivant(joueur),is(true));
	assertThat(piece.contientVivant(joueur),is(false));
	joueur.franchir("p1");
	assertThat(piece.contientVivant(joueur),is(true));
	assertThat(piece1.contientVivant(joueur),is(false));
    }

    @Test(expected=PorteFermeException.class)
    public void testPorteFermeException1() throws Exception{
	Piece piece1=new Piece("Salle de jeux",monde);	
	Porte porte= new Porte("p1",monde,piece,piece1);
	joueur.franchir(porte);
    }

    @Test(expected=PorteFermeException.class)
    public void testPorteFermeException2() throws Exception{
	Piece piece1=new Piece("Salle de jeux",monde);	
	Porte porte= new Porte("p1",monde,piece1,piece);
	joueur.franchir("p1");
    }

    @Test(expected=PorteInexistanteDansLaPieceException.class)
    public void testPorteInexistanteDansLaPieceException() throws Exception{
	Piece piece1=new Piece("Salle de jeux",monde);	
	Porte porte= new Porte("p1",monde,piece1,piece1);
	joueur.franchir("p1");
    }

    @Test(expected=PorteInexistanteDansLaPieceException.class)
    public void testPorteInexistanteDansLaPieceException2() throws Exception{
	Piece piece1=new Piece("Salle de jeux",monde);	
	Porte porte= new Porte("p1",monde,piece1,piece1);
	joueur.franchir(porte);
    }
    
    @Test(expected=ObjetAbsentDeLaPieceException.class)
    public void testObjetAbsentDeLaPieceException1() throws Exception{
	PiedDeBiche pdb1= new PiedDeBiche("pdb1",monde);
	joueur.prendre(pdb1);
    }

    @Test(expected=ObjetAbsentDeLaPieceException.class)
    public void testObjetAbsentDeLaPieceException2() throws Exception{
	PiedDeBiche pdb1= new PiedDeBiche("pdb1",monde);
	joueur.prendre("pdb1");
    }
	
    @Test(expected=ObjetNonPossedeParLeVivantException.class)
    public void testObjetNonPossedeParLeVivantException1() throws Exception{
	PiedDeBiche pdb1=new PiedDeBiche("pdb1",monde);
	joueur.deposer(pdb1);
    }

    @Test(expected=ObjetNonPossedeParLeVivantException.class)
    public void testObjetNonPossedeParLeVivantException2() throws Exception{
	PiedDeBiche pdb1=new PiedDeBiche("pdb1",monde);
	joueur.deposer("pdb1");
    }
    
}
	
    

    
