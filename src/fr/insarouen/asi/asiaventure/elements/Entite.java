package fr.insarouen.asi.asiaventure.elements;

import java.lang.StringBuilder;
import java.io.Serializable;
import fr.insarouen.asi.asiaventure.Monde;
import fr.insarouen.asi.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.asiaventure.EntiteDejaDansUnAutreMondeException;

public abstract class Entite implements Serializable{
    private String nom;
    private Monde monde;
    /**
     *Constructeur de classe Entite.
     * L'Entite est automatiquement ajoutée dans le monde précisé en entrée. 
     * Ne fonctionne pas si une entité possède déjà ce nom dans le monde.
     *@param monde
     * Monde dans lequel l'entité se crée
     *@param nom
     * Nom souhaité pour l'entité
     *
     */
    public Entite(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
	this.nom=nom;
	this.monde=monde;
	try{
		monde.ajouter(this);
	}
	catch(EntiteDejaDansUnAutreMondeException e){
		e.printStackTrace();
		System.err.println("ça devrait jamais arriver");
		System.exit (-1);
	}
    }
 
    public String getNom(){
	return nom;

    }
    public Monde  getMonde(){
	
	return monde;
    }
    public String toString(){
       	StringBuilder str=new StringBuilder();
	str.append("Nom : ");
	str.append(this.getNom());
	str.append("\nSe situe dans le monde : ");
	str.append(this.getMonde().getNom());
	return str.toString();
	
    }
    public int hashCode(){
	return nom.hashCode()*7+monde.hashCode()*7;
    }
    public boolean equals(Object o){
	if (o.getClass() != getClass()) {
	    return false;
	}
	Entite entite = (Entite)o;
	return (entite.getNom().equals(getNom()) && entite.getMonde()==getMonde());
	
	
    }
    
}
	


