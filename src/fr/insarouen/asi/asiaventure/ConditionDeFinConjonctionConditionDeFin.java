package fr.insarouen.asi.asiaventure;

import fr.insarouen.asi.asiaventure.EtatDuJeu;
import fr.insarouen.asi.asiaventure.elements.vivants.Vivant;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.utili.StringBuilder;

public class ConditionDeFinConjonctionConditionDeFin extends ConditionDeFin implements Serializable{
    private EtatDuJeu etatConditionVerifie;
    public List<ConditionDeFin> conditionsDeFin;
    public ConditionDeFinConjonctionConditionDeFin(EtatDuJeu etatConditionVerifiee, ConditionDeFin... cfs){
	super(etatConditionVerifiee);
	this.conditionsDeFin=new ArrayList<ConditionDeFin>();	
	for (ConditionDeFin cdt : cfs){
	    this.conditionsDeFin.add(cdt);
	}

    }

    public EtatDuJeu verifierCondition(){
	for (ConditionDeFin cdt : conditionsDeFin){
				   if (cdt.getEtatConditionVerifiee().equals(EtatDuJeu.ENCOURS)){
				       return EtatDuJeu.ENCOURS;
				   }
			       };
	return this.getEtatConditionVerifiee();
    }

    public String toString(){
	StringBuilder str = new StringBuilder();
	str.append(super.toString());
	for (ConditionDeFin cdf : conditionsDeFin){
	    str.append("\n");	    
	    str.append(cdf.toString());
	}
	return str.toString();
    }
}
