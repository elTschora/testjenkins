package fr.insarouen.asi.asiaventure.elements.structure;

import fr.insarouen.asi.asiaventure.elements.structure.ElementStructurel;
import fr.insarouen.asi.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.asiaventure.Monde;
import java.io.Serializable;
import java.lang.StringBuilder;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;
import fr.insarouen.asi.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.asiaventure.elements.vivants.ObjetNonPossedeParLeVivantException;
import fr.insarouen.asi.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.asiaventure.elements.structure.VivantAbsentDeLaPieceException;

public class Piece extends ElementStructurel implements Serializable {
    private Map<String,Objet> objets;
    private Map<String,Vivant> vivants;
    public Map<String,Porte> portes;

    public Piece(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
	super(nom,monde);
	objets=new HashMap<String,Objet>();
	vivants=new HashMap<String,Vivant>();
	portes=new HashMap<String,Porte>();
    }

    public Set<Objet> getObjets(){
	Set<Objet> res=new HashSet<Objet>(objets.values());
	return res;
    }
	
    public Set<Vivant> getVivants(){
	Set<Vivant> res=new HashSet<Vivant>(vivants.values());
	return res;
    }
    
   
    
    public boolean contientVivant(Vivant vivant){
	return vivants.containsValue(vivant);
    }

    public boolean contientVivant(String nomVivant){
	return vivants.containsKey(nomVivant);
    }

    public void entrer(Vivant vivant){
	if (!contientVivant(vivant)){
	    vivant.piece=this;
	    vivants.put(vivant.getNom(),vivant);		
	}
    }

    public Vivant sortir(Vivant vivant) throws VivantAbsentDeLaPieceException {
	if (contientVivant(vivant)){
	    vivants.remove(vivant.getNom(),vivant);
      	}
	else throw new VivantAbsentDeLaPieceException(vivant.getNom()+" n'est pas présent dans la pièce");
	return vivant;
    }

    public Vivant sortir(String nomVivant) throws VivantAbsentDeLaPieceException {
	if (this.contientVivant(nomVivant)){
	    vivants.remove(nomVivant);
	}
	else throw new VivantAbsentDeLaPieceException(nomVivant+" n'est pas présent dans la pièce");
	return (Vivant)this.getMonde().getEntite(nomVivant);
    }
		


    public void deposer(Objet obj){
	objets.put(obj.getNom(),obj);	
    }

    public boolean contientObjet(String nomObjet){
	return objets.containsKey(nomObjet);
    }
    public boolean contientObjet(Objet objet){
        return objets.containsValue(objet);
    }

    public Objet retirer(String nomObjet) throws ObjetAbsentDeLaPieceException,ObjetNonDeplacableException {
	if (this.contientObjet(nomObjet)){
	    if (this.objets.get(nomObjet).estDeplacable()){
		objets.remove(nomObjet);
	    }
	    else throw new ObjetNonDeplacableException(nomObjet+" n'est pas déplacable");
 	}
	else throw new ObjetAbsentDeLaPieceException(nomObjet+" n'est pas présent dans la pièce (exception retirer)");
	return (Objet)this.getMonde().getEntite(nomObjet);
    }


    public Objet retirer(Objet objet) throws ObjetAbsentDeLaPieceException,ObjetNonDeplacableException{
	if (this.contientObjet(objet)){
	    if (objet.estDeplacable()){
		objets.remove(objet.getNom(),objet);
	    }
	    else throw new ObjetNonDeplacableException(objet.getNom()+" n'est pas déplacable");
	}
	else throw new ObjetAbsentDeLaPieceException(objet.getNom()+" n'est pas présent dans la pièce");
	return objet;
    }

     public Set<Porte> getPortes(){
	Set<Porte> res=new HashSet<Porte>(portes.values());
	return res;
    }

    public boolean aLaPorte(Porte porte){
	return this.portes.containsValue(porte);
    }

    public boolean aLaPorte(String nom){
	return this.portes.containsKey(nom);
    }
    public void addPorte(Porte porte){
	if (!this.aLaPorte(porte)){
		this.portes.put(porte.getNom(),porte);
	    }
    }

    public Porte getPorte(String nom){
	if (this.aLaPorte(nom)){
	    return this.portes.get(nom);
	}
	else return null;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
	str.append(super.getNom());
	str.append("\nObjets présents : ");
	Set<Objet> lObjets=getObjets();
	for (Objet objet : lObjets){
	    str.append("\n\t\t   ");
	    str.append(objet.getNom());
	}
	str.append("\nPortes présentes : ");
	Set<Porte> lPortes=getPortes();
	for (Porte porte : lPortes){
	    str.append("\n\t\t   ");
	    str.append(porte.getNom());
	    str.append(" vers ");
	    str.append(porte.getPieceAutreCote(this).getNom());	    
	    str.append(". Etat ");
	    str.append(porte.getEtat());
	    if (porte.getSerrure()!=null){
		str.append(". Serrure ");
		str.append(porte.getSerrure().getEtat());
	    }

	}
	str.append("\nVivants présents : ");
	Set<Vivant> lVivants=getVivants();
	for (Vivant vivant : lVivants){
	    str.append("\n\t\t   ");
	    str.append(vivant.getNom());
	}
	return str.toString();
    }



}
