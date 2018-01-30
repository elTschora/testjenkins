package fr.insarouen.asi.asiaventure.elements.objets;
import java.lang.StringBuilder;
import java.io.Serializable;
import fr.insarouen.asi.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.asiaventure.elements.Etat;
import fr.insarouen.asi.asiaventure.elements.Activable;
import fr.insarouen.asi.asiaventure.Monde;
import fr.insarouen.asi.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.asi.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.asiaventure.elements.ActivationException;


public class Coffre  extends Objet implements Activable, Serializable {
    private Etat etat;

    public Coffre(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
	super(nom,monde);
	this.etat=Etat.FERME;
    }
    public boolean estDeplacable(){
	return false;
    }
    public Etat getEtat(){
	return this.etat;
    }
    public void setEtat(Etat etat){
	this.etat=etat;
	    }
    
    public boolean activableAvec(Objet obj){
	return (obj instanceof PiedDeBiche);
    }

    public void activer() throws ActivationException{
	if (this.getEtat()==Etat.OUVERT){
	    this.etat=Etat.FERME;
	}
	else if (this.getEtat()==Etat.FERME){
	    this.etat=Etat.OUVERT;
	}
	else throw new ActivationException(this.getNom()+" n'est ni Fermé ni ouvert");
    }

    public void activerAvec(Objet obj) throws ActivationException{
    	if (this.activableAvec(obj)){
	    if (obj instanceof PiedDeBiche){
		this.etat=Etat.CASSE;
	    }
	}
	else throw new ActivationException(this.getNom()+" n'est pas activable avec "+obj.getNom());
    }

    public String toString(){
	StringBuilder str=new StringBuilder();
	str.append(super.toString());
	str.append("\nN'est pas déplaçable");
	str.append("\nEtat : ");
	str.append(this.getEtat().toString());
	return str.toString();
    }	
}
