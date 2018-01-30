package fr.insarouen.asi.asiaventure;

import fr.insarouen.asi.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.asiaventure.EtatDuJeu;
import fr.insarouen.asi.asiaventure.elements.vivants.Vivant;
import java.util.Set;
import java.util.HashSet;
import java.io.Serializable;
import java.util.StringBuilder;

public class ConditionDeFinVivantPossedeObjets extends ConditionDeFin implements Serializable{
    private EtatDuJeu etatConditionVerifie;
    private Vivant vivant;
    private  Set<Objet> objets;
    
    public ConditionDeFinVivantPossedeObjets(EtatDuJeu etatConditionVerifiee, Vivant vivant, Objet[] objets){
	super(etatConditionVerifiee);
	this.vivant=vivant;
	this.objets = new HashSet<Objet>();
	for (Objet o : objets){
	    this.objets.add(o);
	}    
    }

    public EtatDuJeu verifierCondition(){
	if (vivant.getObjets().containsAll(this.objets)){
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
	str.append(" doit posseder les objets : ");
	str.append("\n");
	for (Objet o : objets){
	    str.append(o.getNom());
	    str.append("\n");
	}
	return str.toString();
    }
    
	    
}
