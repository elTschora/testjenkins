package fr.insarouen.asi.asiaventure.elements.structure;

import java.io.Serializable;
import fr.insarouen.asi.asiaventure.elements.Entite;
import fr.insarouen.asi.asiaventure.Monde;
import fr.insarouen.asi.asiaventure.elements.structure.ElementStructurelException;
import fr.insarouen.asi.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
public abstract class ElementStructurel extends Entite implements Serializable{
    public ElementStructurel(String nom,Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
	super(nom,monde);
    }


    public String toString(){
	StringBuilder str=new StringBuilder();
	str.append(super.toString());
	return str.toString();
		   }
} 
    
