package fr.insarouen.asi.asiaventure.elements.objets;
import fr.insarouen.asi.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.asiaventure.Monde;
import fr.insarouen.asi.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import java.io.Serializable;
public class Enclume extends Objet implements Serializable {
    public Enclume(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
	super(nom,monde);
    }
    public boolean estDeplacable(){
	return false;
    }

      public String toString(){
	StringBuilder str=new StringBuilder();
	str.append(super.toString());
       	str.append("\nN'est pas déplaçable");
	return str.toString();
		   }

}	
