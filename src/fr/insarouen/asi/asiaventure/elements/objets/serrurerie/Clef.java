package fr.insarouen.asi.asiaventure.elements.objets.serrurerie;
import java.lang.StringBuilder;
import fr.insarouen.asi.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.asiaventure.Monde;
import fr.insarouen.asi.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import java.io.Serializable;

public final class Clef extends Objet implements Serializable{
    protected Clef(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
	super(nom,monde);
    }

    public boolean estDeplacable(){
	return true;
    }

    public String toString(){
	StringBuilder str=new StringBuilder();
	str.append(super.toString());
	return str.toString();
		   }
}
