package fr.insarouen.asi.asiaventure;

import fr.insarouen.asi.asiaventure.EtatDuJeu;
import fr.insarouen.asi.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.asiaventure.elements.vivants.Vivant;
import java.io.Serializable;
import java.lang.StringBuilder;

public class ConditionDeFinVivantDansPiece extends ConditionDeFin implements Serializable{
    private EtatDuJeu etatConditionVerifie;
    private Vivant vivant;
    private Piece piece;
    
    public ConditionDeFinVivantDansPiece(EtatDuJeu etatConditionVerifiee, Vivant vivant, Piece piece){
	super(etatConditionVerifiee);
	this.vivant=vivant;
	this.piece=piece;
    }

    public EtatDuJeu verifierCondition(){
	if (piece.contientVivant(vivant)){
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
	str.append(" doit être dans la pièce");
        str.append(piece.getNom());
	return str.toString();
    }
}
