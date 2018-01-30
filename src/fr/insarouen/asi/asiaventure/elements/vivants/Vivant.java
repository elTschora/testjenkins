package fr.insarouen.asi.asiaventure.elements.vivants;
import java.lang.String;
import java.lang.StringBuilder;
import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
import fr.insarouen.asi.asiaventure.Monde;
import fr.insarouen.asi.asiaventure.elements.Entite;
import fr.insarouen.asi.asiaventure.elements.Executable;
import fr.insarouen.asi.asiaventure.elements.Etat;
import fr.insarouen.asi.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.asiaventure.elements.Activable;
import fr.insarouen.asi.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.asiaventure.elements.structure.PorteFermeException;
import fr.insarouen.asi.asiaventure.elements.structure.PorteInexistanteDansLaPieceException;
import fr.insarouen.asi.asiaventure.elements.structure.VivantAbsentDeLaPieceException;
import fr.insarouen.asi.asiaventure.elements.objets.ObjetNonDeplacableException;
import fr.insarouen.asi.asiaventure.elements.vivants.ObjetNonPossedeParLeVivantException;
import fr.insarouen.asi.asiaventure.elements.structure.ObjetAbsentDeLaPieceException;
import fr.insarouen.asi.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.asiaventure.elements.vivants.CommandeImpossibleParLeVivantException;
import fr.insarouen.asi.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.asi.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.asiaventure.elements.ActivationException;

public abstract class Vivant extends Entite implements Executable, Serializable {
    String nom;
    Monde monde;
    int pointVie;
    int pointForce;
    public Piece piece;
    Map<String,Objet> objets;
    public Vivant(String nom, Monde monde, int pointVie, int pointForce, Piece piece,Objet... objets)  throws NomDEntiteDejaUtiliseDansLeMondeException{
 	super(nom,monde);
        this.pointVie=pointVie;
        this.pointForce=pointForce;
        this.piece=piece;
        this.objets=new HashMap<String,Objet>();
	for (Objet o : objets){
	    this.objets.put(o.getNom(),o);
	}
	piece.entrer(this);
    }

    public Set<Objet> getObjets(){
	Set<Objet> res=new HashSet<Objet>(objets.values());
	return res;
    }
    public Objet getObjet(String nomObjet){
	if (this.possede(nomObjet)){
	      return (Objet)this.getMonde().getEntite(nomObjet);
	}
	else return null;
    }
    public int getPointVie(){
	    return pointVie;
	}

    public boolean estMort(){
	return this.getPointVie()<=0;
    }
    public void setPointVie(int valeur){
	this.pointVie=valeur;
    }
    public int getPointForce(){
	    return pointForce;
	}

    public Piece getPiece(){
	    return this.piece;
	}
    public boolean possede(String nomObjet){
	return objets.containsKey(nomObjet);
    }
    
    public boolean possede(Objet objet){
	return objets.containsValue(objet);
    }
    
    public void prendre(Objet obj) throws ObjetNonDeplacableException,ObjetAbsentDeLaPieceException{
	if (this.piece.contientObjet(obj) & obj.estDeplacable()){
	    this.getPiece().retirer(obj);
	    objets.put(obj.getNom(),obj);
       	}
	else if (!obj.estDeplacable())
	    throw new ObjetNonDeplacableException(obj.getNom()+" n'est pas déplaçable");
	else throw new ObjetAbsentDeLaPieceException(obj.getNom()+" n'est pas présent dans la pièce");
	    
    }
    public void prendre(String nomObj) throws ObjetNonDeplacableException,ObjetAbsentDeLaPieceException{
	if (!this.piece.contientObjet(nomObj))
	    {
		throw new ObjetAbsentDeLaPieceException(nomObj+" n'est pas présent dans la pièce");
	    }
	else if (!((Objet)this.getMonde().getEntite(nomObj)).estDeplacable()){
	    throw new ObjetNonDeplacableException(nomObj+" n'est pas déplaçable");
	}
	else{
	    Objet o;
	    o=this.piece.retirer(nomObj);
	    objets.put(nomObj,o);
       	}
    }

    public void deposer(String nomObjet) throws ObjetNonPossedeParLeVivantException{
	if (this.possede(nomObjet)){
	    objets.remove(nomObjet);
	}
	else throw new ObjetNonPossedeParLeVivantException(nomObjet+" n'est pas possédé par le vivant");
	this.piece.deposer( (Objet)this.getPiece().getMonde().getEntite(nomObjet) );
    }

    public void deposer(Objet objet) throws ObjetNonPossedeParLeVivantException{
	if (this.possede(objet)){
	    objets.remove(objet.getNom(),objet);
	}
	else throw new ObjetNonPossedeParLeVivantException(objet.getNom()+" n'est pas possédé par le vivant");
	this.piece.deposer(objet);
    }

    public void activerActivable(Activable activable) throws ActivationException{
	activable.activer();
    }

    public void activerActivableAvecObjet(Activable activable, Objet objet) throws ActivationException{
	activable.activerAvec(objet);
    }

    public void franchir (Porte porte) throws PorteFermeException,PorteInexistanteDansLaPieceException{
	if (this.piece.aLaPorte(porte)){
	    if (porte.getEtat()==Etat.OUVERT || porte.getEtat()==Etat.CASSE){
		try {
		    porte.getPieceAutreCote(this.piece).entrer(this);
		    porte.getPieceAutreCote(this.piece).sortir(this);
		}
		catch (VivantAbsentDeLaPieceException e){
		    e.printStackTrace();
		}
	    }
	    else throw new PorteFermeException("La porte "+porte.getNom()+" est fermée!");
	}
	else throw new PorteInexistanteDansLaPieceException("La porte "+porte.getNom()+"n 'existe pas dans la piece "+this.getPiece());
    }

    public void franchir (String nom) throws PorteFermeException,PorteInexistanteDansLaPieceException{
	if (this.piece.aLaPorte(nom)){
	    if (this.piece.getPorte(nom).getEtat()==Etat.OUVERT || this.piece.getPorte(nom).getEtat()==Etat.CASSE){
		try{
		    this.piece.getPorte(nom).getPieceAutreCote(this.piece).entrer(this);
		    this.piece.getPorte(nom).getPieceAutreCote(this.piece).sortir(this);
		}
		catch (VivantAbsentDeLaPieceException e){
		    e.printStackTrace();
		}
	    }
	    else throw new PorteFermeException("La porte "+nom+" est fermée!");
	}
	else throw new PorteInexistanteDansLaPieceException("La porte "+nom+" n 'existe pas dans la piece "+this.getPiece().getNom());
    }
	    
    
    public String toString(){
	int i;
	StringBuilder str=new StringBuilder();
	str.append(super.toString());
	str.append("\n");
	str.append("Habite dans le monde : ");
	str.append(getMonde().getNom());
	str.append("\n");
	str.append("Points de vie : ");
	str.append(Integer.toString(getPointVie()));
	str.append("\n");
	str.append("Points de Force : ");
	str.append(Integer.toString(getPointForce()));
	str.append("\n");
	str.append("Est dans la piece : ");
	str.append(getPiece().getNom());
	str.append("\n");
	str.append("Possède les objets : ");
       	Set<Objet> lObjets=getObjets();
	for(Objet objet : lObjets){
			    str.append("\n\t\t   ");
			    str.append(objet.toString());
			}
	return str.toString();
    }
}
      
