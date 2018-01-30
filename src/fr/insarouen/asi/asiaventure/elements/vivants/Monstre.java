package fr.insarouen.asi.asiaventure.elements.vivants;
import fr.insarouen.asi.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.asiaventure.elements.Activable;
import java.lang.String;
import java.lang.Math;
import java.lang.StringBuilder;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;
import java.io.Serializable;
import fr.insarouen.asi.asiaventure.Monde;
import fr.insarouen.asi.asiaventure.elements.Entite;
import fr.insarouen.asi.asiaventure.elements.Etat;
import fr.insarouen.asi.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.asiaventure.elements.structure.PorteFermeException;
import fr.insarouen.asi.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.asi.asiaventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.asi.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.asiaventure.elements.vivants.ObjetNonPossedeParLeVivantException;
import fr.insarouen.asi.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.asi.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.asiaventure.elements.ActivationException;


public class Monstre extends Vivant implements Serializable{
    public Monstre(String nom, Monde monde, int pointVie, int pointForce, Piece piece, Objet... objet) throws NomDEntiteDejaUtiliseDansLeMondeException{
	super(nom,monde,pointVie,pointForce,piece,objet);
    }
    
    public void deposerObjets(){
	Set objetsPieceTemp = new HashSet<Objet>(this.getPiece().getObjets());
	this.getObjets().forEach(objet->
				 {
				     try{
					 this.deposer(objet);
				     }
				     catch(ObjetNonPossedeParLeVivantException e)
					 {
					     e.printStackTrace();
					 }
				 }
				 );
	objetsPieceTemp.forEach(objet->
				{
				    if (((Objet)objet).estDeplacable()){	
					try{
					    this.prendre((Objet)objet);
					}
					catch(ObjetNonDeplacableException e){
					    e.printStackTrace();
					}
					catch(ObjetAbsentDeLaPieceException e){
					    e.printStackTrace();
					}
				    }
				});
    }

    
    public Porte selectionnerPorte() throws  PorteInexistanteDansLaPieceException{
        Random rand=new Random();
	List portesAccessibles = new ArrayList<Porte>();
	int nb;
	int i;
	this.getPiece().getPortes().forEach(porte->
					    {
						if (!((Porte)porte).getEtat().equals(Etat.VERROUILLE)){
						    portesAccessibles.add((Porte)porte);
						}
					    });
	if (portesAccessibles.isEmpty()){
	    throw new PorteInexistanteDansLaPieceException("Il n'y a pas de porte dans la pièce!");
	}
	nb=Math.abs((rand.nextInt())%(portesAccessibles.size()));
	return (Porte)portesAccessibles.get(nb);
    
    }

    public void franchir (Porte porte) throws PorteInexistanteDansLaPieceException{
	if (this.piece.aLaPorte(porte)){
		try {
		    porte.getPieceAutreCote(this.piece).entrer(this);
		    porte.getPieceAutreCote(this.piece).sortir(this);
		}
		catch (VivantAbsentDeLaPieceException e){
		    e.printStackTrace();
		}
	}
	else throw new PorteInexistanteDansLaPieceException("La porte "+porte.getNom()+"n 'existe pas dans la piece "+this.getPiece());
    }

    
    
    public void executer(){
	if (!this.estMort()){
	    try{
		this.franchir(this.selectionnerPorte());
	    }
	    catch (PorteInexistanteDansLaPieceException e){
		System.out.println("Selectionner portes est buggé...");
	    }
	    this.setPointVie(this.getPointVie()-1);
	    this.deposerObjets();
	}
    }
    public String toString(){
	StringBuilder str=new StringBuilder();
	str.append(super.toString());
	str.append("\nEst un monstre");
	return str.toString();
    }

}
