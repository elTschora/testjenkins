package fr.insarouen.asi.asiaventure;

import fr.insarouen.asi.asiaventure.EtatDuJeu;
import fr.insarouen.asi.asiaventure.elements.vivants.Vivant;
import java.io.Serializable;
import java.util.StringBuilder;

public class ConditionDeFinVivantMort extends ConditionDeFin implements Serializable{
    private EtatDuJeu etatConditionVerifie;
    private Vivant vivant;
    public ConditionDeFinVivantMort(EtatDuJeu etatConditionVerifiee, Vivant vivant){
	super(etatConditionVerifiee);
	this.vivant=vivant;	
    }

    public EtatDuJeu verifierCondition(){
	if (vivant.estMort()){
	    return this.getEtatConditionVerifiee();
	}
	else{
	    return EtatDuJeu.ENCOURS;
	}
    }

    public String toString(){
	StringBuilder str = new StringBuilder();
	str.append(super.toString());
	str.append("\n");
	str.append(vivant.getNom());
	str.append(" doit être mort");
	return str.toString();
    }

    
	    
}
