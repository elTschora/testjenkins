package fr.insarouen.asi.asiaventure.elements.objets;

import fr.insarouen.asi.asiaventure.elements.Entite;
import fr.insarouen.asi.asiaventure.Monde;
import java.lang.StringBuilder;
import java.io.Serializable;
import fr.insarouen.asi.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
public abstract class Objet extends Entite implements Serializable{
    public Objet(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
	super(nom,monde);
    }
    public abstract boolean estDeplacable();
    
    public String toString(){
	StringBuilder str=new StringBuilder();
	str.append(super.toString());
       	//str.append(" || est déplaçable? ");
        //str.append(this.estDeplacable());
	return str.toString();
		   }
	
}
