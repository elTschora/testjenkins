package fr.insarouen.asi.asiaventure;

import fr.insarouen.asi.asiaventure.EtatDuJeu;
import fr.insarouen.asi.asiaventure.elements.objets.Objet;
import java.util.Set;
import java.util.HashSet;
import fr.insarouen.asi.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.asiaventure.elements.vivants.Vivant;
import java.io.Serializable;
import java.util.StringBuilder;

public class ConditionDeFinVivantDansPieceEtPossedeObjets extends ConditionDeFin implements Serializable{
    private EtatDuJeu etatConditionVerifie;
    private Vivant vivant;
    private Piece piece;
    private  Set<Objet> objets;
    
    public ConditionDeFinVivantDansPieceEtPossedeObjets(EtatDuJeu etatConditionVerifiee, Vivant vivant, Piece piece){
	super(etatConditionVerifiee);
	this.vivant=vivant;
	this.piece=piece;
	this.objets = new HashSet<Objet>();
	for (Objet o : objets){
	    this.objets.add(o);
	}    
	
    }

    public EtatDuJeu verifierCondition(){
	if (piece.contientVivant(vivant) & vivant.getObjets().containsAll(objets)){
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
	str.append("et être dans la pièce ");
	str.append(piece.getNom());
	return str.toString();
    }

}
