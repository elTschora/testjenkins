package fr.insarouen.asi.asiaventure.elements.vivants;
import fr.insarouen.asi.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.asiaventure.elements.Activable;
import fr.insarouen.asi.asiaventure.elements.Etat;
import fr.insarouen.asi.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.asiaventure.elements.vivants.ObjetNonPossedeParLeVivantException;
import fr.insarouen.asi.asiaventure.elements.vivants.CommandeImpossibleParLeVivantException;
import fr.insarouen.asi.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.asiaventure.elements.structure.PorteFermeException;
import fr.insarouen.asi.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.asi.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.asi.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.asiaventure.elements.ActivationException;
import fr.insarouen.asi.asiaventure.elements.objets.Objet;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable;
import java.util.Scanner;
import fr.insarouen.asi.asiaventure.Monde;
public class JoueurHumain extends Vivant implements Serializable{
    private String ordre;
    public JoueurHumain(String nom, Monde monde, int pointVie, int pointForce, Piece piece,Objet... objets) throws NomDEntiteDejaUtiliseDansLeMondeException {
	super(nom,monde,pointVie,pointForce,piece,objets);
	ordre="Rien";
    }

    public void executer() throws CommandeImpossibleParLeVivantException{
	Scanner sc = new Scanner(ordre);
	List params = new ArrayList<String>();

	while (sc.hasNext()){
	    if (params.isEmpty()){
		params.add("commande"+sc.next());
	    }
	    else {
		params.add(sc.next());
	    }
	}
	String yolo = "Bonjour";
	Class[] paramsTypes = new Class[params.size()-1];
	for (int i=0; i<params.size()-1;i++){
	    paramsTypes[i]=yolo.getClass();
	    }
	
	if (params.size()<=1 | params.size()>3){
	    throw new CommandeImpossibleParLeVivantException("Mauvais nombre de paramètres");
	}
	Class classe = this.getClass();
	try{
	    Method methode = classe.getDeclaredMethod((String)params.get(0),paramsTypes);
	    params.remove(0);
	    methode.invoke(this,params.toArray());
	}
       	catch(NoSuchMethodException e){
	    throw new CommandeImpossibleParLeVivantException("La commande "+(String)params.get(0)+" n'existe pas!");
	}
	catch(InvocationTargetException e){
	    throw new CommandeImpossibleParLeVivantException(e.getCause().getMessage());
	}
	catch(Exception e){
	    e.printStackTrace();
	}
	
    }

    public void setOrdre(String ordre){
	this.ordre=ordre;
    }
    public String getOrdre(){
	return ordre;
    }

    private void commandePrendre(String nomObjet) throws ObjetAbsentDeLaPieceException, ObjetNonDeplacableException {
	this.prendre(nomObjet);
    }

    private void commandePoser(String nomObjet) throws ObjetNonPossedeParLeVivantException{
	this.deposer(nomObjet);
    }

    private void commandeFranchir(String nomPorte) throws PorteFermeException, PorteInexistanteDansLaPieceException{
	this.franchir(nomPorte);
    }

    private void commandeActiverPorte(String nomPorte) throws ActivationException, PorteInexistanteDansLaPieceException{
	if (!(this.getPiece().portes.get(nomPorte)==null)) {   
	    this.activerActivable(this.getPiece().portes.get(nomPorte));
	}
	else throw new PorteInexistanteDansLaPieceException("La porte" + nomPorte + " n'est pas présente dans la pièce" + this.getPiece().getNom());
    }

    private void commandeActiverPorteAvec(String nomPorte, String nomObjet) throws ActivationException, PorteInexistanteDansLaPieceException, ObjetNonPossedeParLeVivantException{
	if (!(this.getPiece().portes.get(nomPorte)==null)) {   
	    if (this.possede(nomObjet)){
		this.activerActivableAvecObjet(this.getPiece().portes.get(nomPorte),this.objets.get(nomObjet));
	}
	    else throw new ObjetNonPossedeParLeVivantException("L'objet " + nomObjet + " n'est pas en votre possession");
	}
	else throw new PorteInexistanteDansLaPieceException("La porte" + nomPorte + " n'est pas présente dans la pièce" + this.getPiece().getNom());
    }

    private void commandeActiverSerrure(String nomPorte, String nomObjet) throws ActivationException, PorteInexistanteDansLaPieceException, ObjetNonPossedeParLeVivantException{
	if (this.possede(nomObjet)){
	    this.activerActivableAvecObjet(this.getPiece().portes.get(nomPorte).getSerrure(),this.objets.get(nomObjet));}
	else throw new ObjetNonPossedeParLeVivantException("L'objet " + nomObjet + " n'est pas en votre possession");
    }
    
    public String toString(){
	StringBuilder str=new StringBuilder();
	str.append(super.toString());
	str.append("\nEst un joueur humain");
	return str.toString();
    }
}    
