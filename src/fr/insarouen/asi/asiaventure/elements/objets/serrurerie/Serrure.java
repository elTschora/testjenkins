package fr.insarouen.asi.asiaventure.elements.objets.serrurerie;
import java.lang.StringBuilder;
import java.util.Random;
import fr.insarouen.asi.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.asiaventure.elements.Etat;
import fr.insarouen.asi.asiaventure.elements.Activable;
import fr.insarouen.asi.asiaventure.Monde;
import fr.insarouen.asi.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.asi.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.asiaventure.elements.ActivationException;
import java.io.Serializable;
import fr.insarouen.asi.asiaventure.elements.objets.serrurerie.Clef;


public class Serrure extends Objet implements Activable, Serializable{
    private Etat etat;
    private Clef clef;

    public Serrure(Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
	this(Serrure.genererNom("serrure",monde),monde);
    }
    public Serrure(String nom, Monde monde) throws NomDEntiteDejaUtiliseDansLeMondeException{
	super(nom,monde);
	this.etat=Etat.VERROUILLE;
	this.clef=null;
    }
    public boolean estDeplacable(){
	return false;
    }
    public Etat getEtat(){
	return this.etat;
    }
    public void setEtat(Etat etat){
	this.etat=etat;
	    }
    protected static String genererNom(String type,Monde monde){
	StringBuilder str= new StringBuilder();
        Random rand=new Random();
	str.append(type);
	while (monde.entitePresente(str.toString())){
	    str.append(rand.nextInt());
	}
	return str.toString();
    }

    public final Clef creerClef() {
	try {
	    if (clef==null){
		Clef clef = new Clef (genererNom("Clef",getMonde()),getMonde());
		this.clef=clef;
		return clef;
	    }
	    else {
		return null;
	    }
	}
	catch (NomDEntiteDejaUtiliseDansLeMondeException e){
	    System.out.println("Si cela arrive, genererNom() est buggé!");
	}
	return null;
    }
	
    public boolean activableAvec(Objet obj){
	return obj==this.clef;
    }

    public void activer() throws ActivationImpossibleException{
	throw new ActivationImpossibleException(this.getNom()+" requiert une clé pour la verouiller/deverouiller!");
    }

    public void activerAvec(Objet obj) throws ActivationImpossibleAvecObjetException{
    	if (this.activableAvec(obj)){
	    if (this.etat==Etat.VERROUILLE){
		this.etat=Etat.DEVEROUILLE;
	    }
	    else if (this.etat==Etat.DEVEROUILLE){
		this.etat=Etat.VERROUILLE;
	    }
	    else throw new ActivationImpossibleAvecObjetException(this.getNom()+"n'est ni verrouille ni deverouille");
	}
	else throw new ActivationImpossibleAvecObjetException(this.getNom()+" n'est pas activable avec "+obj.getNom()+" ,il faut la bonne clé pour l'activer!");
    }

    public String toString(){
	StringBuilder str=new StringBuilder();
	str.append(super.toString());
	str.append("\nEtat : ");
	str.append(this.getEtat().toString());
	return str.toString();
    }	
}
