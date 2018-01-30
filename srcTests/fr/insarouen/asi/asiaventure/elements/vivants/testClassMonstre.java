package fr.insarouen.asi.asiaventure.elements.vivants;

import fr.insarouen.asi.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.asiaventure.elements.objets.Enclume;
import fr.insarouen.asi.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.asiaventure.elements.vivants.Monstre;
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
import java.util.HashSet;
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


public class testClassMonstre{
    public Objet[] objets;
    public Monstre monstre;
    public Monde monde;
    public Piece piece;
    public Piece piece2;
    public PiedDeBiche pdb1;
    public PiedDeBiche pdb2;
    public PiedDeBiche pdb3;
    public PiedDeBiche pdb4;
    public Set objM;
    public Set objP;    
    public Porte porte1;
    public Porte porte2;
    
    @Before
    public void avantTest() throws Exception{
	monde=new Monde("Planete Terre");
	piece=new Piece("Salle",monde);	
	piece2=new Piece("Salle2",monde);
	porte1=new Porte("Porte1",monde,piece,piece2);
	porte2=new Porte("Porte2",monde,piece,piece2);

	pdb1 = new PiedDeBiche("Pied de biche du monstre 1",monde);
	pdb2 = new PiedDeBiche("Pied de biche du monstre 2",monde);
	pdb3 = new PiedDeBiche("Pied de biche de la piece 2",monde);
	pdb4 = new PiedDeBiche("Pied de biche de la piece 1",monde);	

	objM = new HashSet<Objet>();
	objP = new HashSet<Objet>();
	objM.add(pdb1);
	objM.add(pdb2);
	objP.add(pdb3);
	objP.add(pdb4);
	Objet[] objetsMonstre = new Objet[2];
	objetsMonstre[0]=pdb1;
	objetsMonstre[1]=pdb2;
	monstre=new Monstre("M.Malandain",monde,20,20,piece,objetsMonstre);
	piece.deposer(pdb3);
	piece.deposer(pdb4);
    }

    @Test
    public void testDeposerObjets(){	
	monstre.deposerObjets();
	assertThat(monstre.getObjets().containsAll(objP) & objP.containsAll(monstre.getObjets()),is(true));
    }


    @Test
    public void testSelectionnerPorte(){
	Porte porte;
	try {
	    porte = monstre.selectionnerPorte();
	    assertThat(monstre.getPiece().getPortes().contains(porte),is(true));
	}
	catch (PorteInexistanteDansLaPieceException e){
	    e.printStackTrace();
	}
	
    }


    @Test
    public void testFranchir() throws Exception{
	porte1.etat=Etat.OUVERT;
	monstre.franchir(porte1);
	assertThat(piece2.contientVivant(monstre),is(true));
	assertThat(piece.contientVivant(monstre),is(false));
	monstre.franchir(porte1);
	assertThat(piece.contientVivant(monstre),is(true));
	assertThat(piece2.contientVivant(monstre),is(false));
    }


    @Test
    public void testExecuter() throws Exception{
	monstre.franchir(porte1);
	monstre.executer();
	assertThat(piece2.contientVivant(monstre),is(false));
	assertThat(piece.contientVivant(monstre),is(true));
	assertThat(monstre.getObjets().containsAll(objP) & objP.containsAll(monstre.getObjets()),is(true));
	assertThat(monstre.getPointVie()==19,is(true));

    }
	
	
}
