package fr.insarouen.asi.asiaventure.elements.objets;
import fr.insarouen.asi.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.asiaventure.Monde;
import fr.insarouen.asi.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import java.io.Serializable;
public class PiedDeBiche extends Objet implements Serializable {
    public PiedDeBiche(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
	super(nom,monde);
    }
    public boolean estDeplacable(){
	return true;
    }

    public String toString(){
	StringBuilder str=new StringBuilder();
	str.append(super.toString());
       	str.append("\nEst déplaçable");
	return str.toString();
		   }

}	
