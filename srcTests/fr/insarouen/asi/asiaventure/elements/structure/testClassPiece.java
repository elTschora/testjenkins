package fr.insarouen.asi.asiaventure.elements.structure;
import fr.insarouen.asi.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.asiaventure.elements.objets.Enclume;
import fr.insarouen.asi.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.asiaventure.Monde;
import fr.insarouen.asi.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.asiaventure.elements.vivants.ObjetNonPossedeParLeVivantException;
import fr.insarouen.asi.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.asiaventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.asi.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
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


public class testClassPiece{
    public Piece piece;
    public Monde monde;

    @Before
    public void avantTest() throws NomDEntiteDejaUtiliseDansLeMondeException{
	monde=new Monde("Planete Terre");
	piece=new Piece("Salle",monde);
    }
    @Test
    public void testConstructeur(){
	Set<Objet> objets=new TreeSet<Objet>();
	Set<Vivant> vivants=new TreeSet<Vivant>();
	Set<Porte> portes=new TreeSet<Porte>();
	assertThat(piece.getNom(),equalTo("Salle"));
	assertThat(piece.getMonde(),equalTo(monde));
	assertThat( (piece.getObjets().containsAll(objets) && objets.containsAll(piece.getObjets())),is(true));
	assertThat( (piece.getVivants().containsAll(vivants) && vivants.containsAll(piece.getVivants())),is(true));
	assertThat( (piece.getPortes().containsAll(portes) && portes.containsAll(piece.getPortes())),is(true));
    }

    @Test
    public void testGetObjets(){
	Set<Objet> objets=new TreeSet<Objet>();
	assertThat( (piece.getObjets().containsAll(objets) && objets.containsAll(piece.getObjets())),is(true));
    }
    @Test
    public void testGetVivants(){
	Set<Vivant> vivants=new TreeSet<Vivant>();
	assertThat(piece.getVivants(),equalTo(vivants));
        assertThat( (piece.getVivants().containsAll(vivants) && vivants.containsAll(piece.getVivants())),is(true));
    }

    @Test
    public void testGetPortes(){
	Set<Porte> portes=new TreeSet<Porte>();
	assertThat(piece.getPortes(),equalTo(portes));
        assertThat( (piece.getPortes().containsAll(portes) && portes.containsAll(piece.getPortes())),is(true));
    }
    

    @Test
    public void testContientVivant() throws Exception{
        Objet[] lObjets= new Objet[0];
	Vivant vivant=new Vivant("vivant1",monde,20,20,piece,lObjets){
		public void executer(){
		    System.out.println("Coucou");
		}
	    };
	Vivant vivant2=new Vivant("vivant2",monde,20,20,piece,lObjets){
		public void executer(){
		    System.out.println("Coucou");
		}
	    };
	piece.sortir(vivant2);
	assertThat(piece.contientVivant(vivant2),is(false));
	assertThat(piece.contientVivant("vivant2"),is(false));
	assertThat(piece.contientVivant(vivant),is(true));
	assertThat(piece.contientVivant("vivant1"),is(true));
    }

    @Test
    public void testEntrer() throws Exception{
       	Objet[] objets=new Objet[0];
	Vivant vivant=new Vivant("vivant1",monde,20,20,piece,objets){
		public void executer(){
		    System.out.println("Coucou");
		}
	    };
	piece.sortir(vivant);
	piece.entrer(vivant);
	assertThat(piece.contientVivant("vivant1"),is(true));
    }

    @Test
    public void testSortir() throws Exception{
       	Objet[] objets=new Objet[0];
	Vivant vivant=new Vivant("vivant1",monde,20,20,piece,objets){
		public void executer(){
		    System.out.println("Coucou");
		}
	    };
	piece.sortir(vivant);
	assertThat(piece.contientVivant("vivant1"),is(false));
	piece.entrer(vivant);
	piece.sortir("vivant1");
	assertThat(piece.contientVivant("vivant1"),is(false));
    }

    @Test(expected=VivantAbsentDeLaPieceException.class)
    public void testVivantAbsentDeLaPieceException1() throws Exception{
       	Objet[] objets=new Objet[0];
	Vivant vivant=new Vivant("vivant1",monde,20,20,piece,objets){
		public void executer(){
		    System.out.println("Coucou");
		}
	    };
	piece.sortir(vivant);
	piece.sortir(vivant);
    }

    @Test(expected=VivantAbsentDeLaPieceException.class)
    public void testVivantAbsentDeLaPieceException2() throws Exception{
       	Objet[] objets=new Objet[0];
	Vivant vivant=new Vivant("vivant1",monde,20,20,piece,objets){
		public void executer(){
		    System.out.println("Coucou");
		}
	    };
	piece.sortir(vivant);
	piece.sortir("vivant1");
    }

    @Test
    public void testDeposer() throws Exception{
	PiedDeBiche pdb1=new PiedDeBiche("pdb1",monde);
	piece.deposer(pdb1);
	assertThat(piece.contientObjet(pdb1),is(true));
    }

    @Test
    public void testRetirer() throws Exception{
	PiedDeBiche pdb1=new PiedDeBiche("pdb1",monde);
	piece.deposer(pdb1);
	piece.retirer(pdb1);
	assertThat(piece.contientObjet(pdb1),is(false));
	piece.deposer(pdb1);
	piece.retirer("pdb1");
	assertThat(piece.contientObjet(pdb1),is(false));
    }

    @Test(expected=ObjetNonDeplacableException.class)
    public void testRetirerException1() throws NomDEntiteDejaUtiliseDansLeMondeException,ObjetNonDeplacableException,ObjetAbsentDeLaPieceException{
	Enclume enclume=new Enclume("enclume",monde);
	piece.deposer(enclume);
	piece.retirer(enclume);
    }
    
    @Test(expected=ObjetAbsentDeLaPieceException.class)
    public void testRetirerException2() throws NomDEntiteDejaUtiliseDansLeMondeException,ObjetNonDeplacableException,ObjetAbsentDeLaPieceException{
	Enclume enclume=new Enclume("enclume",monde);
	piece.retirer("enclume");
    }

    @Test
    public void testContientObjet() throws Exception{
	PiedDeBiche pdb1=new PiedDeBiche("pdb1",monde);
        piece.deposer(pdb1);
	assertThat(piece.contientObjet(pdb1),is(true));
	assertThat(piece.contientObjet("pdb1"),is(true));
	piece.retirer(pdb1);
	assertThat(piece.contientObjet(pdb1),is(false));
	assertThat(piece.contientObjet("pdb1"),is(false));
    }

    @Test(expected=ObjetAbsentDeLaPieceException.class)
    public void testObjetAbsentDeLaPieceException1() throws Exception{
       	PiedDeBiche pdb1=new PiedDeBiche("pdb1",monde);
        piece.retirer(pdb1);
    }
    @Test(expected=ObjetAbsentDeLaPieceException.class)
    public void testObjetAbsentDeLaPieceException() throws Exception{
       	PiedDeBiche pdb1=new PiedDeBiche("pdb1",monde);
        piece.retirer("pdb1");
    }

    @Test
    public void testALaPorte() throws Exception{
	Porte p1=new Porte("p1",monde,piece,piece);	
	Piece piece2=new Piece("Salle a manger",monde);
	assertThat(piece.aLaPorte(p1),is(true));
	assertThat(piece.aLaPorte("p1"),is(true));
	assertThat(piece2.aLaPorte(p1),is(false));
	assertThat(piece2.aLaPorte("p1"),is(false));	
    }

    @Test
    public void testAddPorte() throws Exception{
	Porte p1=new Porte("p1",monde,piece,piece);
	Porte p2=new Porte("p2",monde,piece,piece);	
	piece.addPorte(p1);
	piece.addPorte(p2);	
	assertThat(piece.aLaPorte(p1),is(true));
	assertThat(piece.aLaPorte(p2),is(true));
    }

    @Test
    public void getPorte() throws Exception{
	Porte p1=new Porte("p1",monde,piece,piece);
	Porte p2=new Porte("p2",monde,piece,piece);	
	piece.addPorte(p1);
	piece.addPorte(p2);
	assertThat(piece.getPorte("p1"),equalTo(p1));
       	assertThat(piece.getPorte("p2"),equalTo(p2));
    }
}
