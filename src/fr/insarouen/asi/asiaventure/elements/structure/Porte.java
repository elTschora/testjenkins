package fr.insarouen.asi.asiaventure.elements.structure;
import java.lang.StringBuilder;
import fr.insarouen.asi.asiaventure.elements.Activable;
import fr.insarouen.asi.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.asiaventure.elements.objets.PiedDeBiche;
import fr.insarouen.asi.asiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.asi.asiaventure.Monde;
import java.io.Serializable;
import fr.insarouen.asi.asiaventure.elements.Etat;
import fr.insarouen.asi.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.asiaventure.EntiteDejaDansUnAutreMondeException;
import fr.insarouen.asi.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.asi.asiaventure.elements.ActivationImpossibleException;

public class Porte extends ElementStructurel implements Activable, Serializable{
    public String nom;
    public Monde monde;
    public Piece pieceA;
    public Piece pieceB;
    public Etat etat;
    public Serrure serrure;

    public Porte(String nom, Monde monde, Piece pieceA, Piece pieceB) throws NomDEntiteDejaUtiliseDansLeMondeException{
	super(nom,monde);
	this.pieceA=pieceA;
	this.pieceB=pieceB;
	this.etat=Etat.FERME;
	pieceA.addPorte(this);
	pieceB.addPorte(this);
    }

    public Porte(String nom, Monde monde,Serrure serrure, Piece pieceA, Piece pieceB) throws NomDEntiteDejaUtiliseDansLeMondeException{
	super(nom,monde);
	this.pieceA=pieceA;
	this.pieceB=pieceB;
	this.etat=Etat.FERME;
	pieceA.addPorte(this);
	pieceB.addPorte(this);
	this.serrure=serrure;
    }

    public Serrure getSerrure(){
	return this.serrure;
    }
    public Etat getEtat(){
	return this.etat;
    }

    public Piece getPieceAutreCote(Piece piece){
	if (this.pieceA.equals(piece)){
	    return this.pieceB;
	}
        else {
	    return this.pieceA;
        }
    }

    public void activerAvec(Objet obj) throws ActivationImpossibleException, ActivationImpossibleAvecObjetException{
	if (this.activableAvec(obj)){
	    if (this.getEtat()==Etat.FERME || this.getEtat()==Etat.VERROUILLE)
		{
		    if (obj instanceof PiedDeBiche){
			this.etat=Etat.CASSE;
		    }
		}
	    else throw new ActivationImpossibleException(this.getNom()+" n'est pas activable");
	}
	else throw new ActivationImpossibleAvecObjetException(this.getNom()+" n'est pas activable avec "+obj.getNom());
    }

    public void activer() throws ActivationImpossibleException{
	if (this.getEtat()==Etat.OUVERT){
	    this.etat=Etat.FERME;
	}
	else if (this.getEtat()==Etat.FERME){
		this.etat=Etat.OUVERT;
	    }
	else throw new ActivationImpossibleException(this.getNom()+" n'est ni Fermée ni ouverte");
    }

    public boolean activableAvec(Objet obj){
	return (obj instanceof PiedDeBiche);
    }

    public String toString(){
	StringBuilder str=new StringBuilder();
	str.append(super.toString());
	str.append("\nRelie les pièces : ");
	str.append(this.pieceA.getNom());
	str.append(" et ");
	str.append(this.pieceB.getNom());
	str.append("\nCette porte est ");
	str.append(this.getEtat().name());
	if (serrure!=null){
	    str.append("\nCette porte possède la serrure : ");
	    str.append(serrure.toString());
	}
	return str.toString();
    }
}
