package fr.insarouen.asi.asiaventure;

import fr.insarouen.asi.asiaventure.EtatDuJeu;
import java.io.Serializable;
import java.lang.StringBuilder;

public abstract class ConditionDeFin implements Serializable{
    private EtatDuJeu etatConditionVerifie;
    public ConditionDeFin(EtatDuJeu etatDuJeu){
	etatConditionVerifie=etatDuJeu;
    }

    public EtatDuJeu getEtatConditionVerifiee(){
	return etatConditionVerifie;
    }

    public abstract EtatDuJeu verifierCondition();

    public String toString(){
	StringBuilder str=new StringBuilder();
	str.append("etat si la condition est vérifiée : ");
	str.append(etatConditionVerifie.toString());
	return str.toString();
    }
	
}
