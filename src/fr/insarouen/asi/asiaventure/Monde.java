package fr.insarouen.asi.asiaventure;
import java.util.HashSet;
import java.util.Collection;
import java.util.Set;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.lang.StringBuilder;
import fr.insarouen.asi.asiaventure.elements.Executable;
import fr.insarouen.asi.asiaventure.elements.vivants.JoueurHumain;
import java.io.Serializable;
import fr.insarouen.asi.asiaventure.elements.Entite;
import fr.insarouen.asi.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.asiaventure.EntiteDejaDansUnAutreMondeException;

public class Monde implements Serializable{
    private String nom;
    private Map<String,Entite> entites;
    public Monde(String nomDuMonde){
	nom=nomDuMonde;
	entites=new HashMap<String,Entite>();
    }
    public String getNom(){
	return nom;
    }
    public List<Entite> getEntites(){
	List<Entite> res=new LinkedList<Entite>(entites.values());
	return res;
    }

    /**
     *Renvoie la première Entite dans le monde dont le nom est celui précisé en paramètre d'entrée.
     * @param nomEnt 
     * Nom de l'entite a renvoyer. 
     * Renvoie null si l'entité n'est pas présente.
     *
     */
    public Entite getEntite(String nomEnt){
	if (this.entitePresente(nomEnt)){
		return entites.get(nomEnt);
	    }
	else return null;
    }

    public boolean entitePresente (String nom){
	return entites.containsKey(nom);
    }

    public List<Executable> getExecutables(){
	List<Entite> lEntites = getEntites();
	List<Executable> res = new ArrayList<Executable>();
	for(Entite entite : lEntites){
			     if (entite instanceof Executable){
				 res.add((Executable)entite);
			     }
			 }
	return res;
    }

    /**
     * Ajoute une entite dans le monde. Ne marche pas si l'entite est déjà ajoutée dans un autre monde ou si une autre entité dans ce monde possède le même nom. Dans le tableau, l'entite s'ajoute à la fin.
     * @param entite
     * Entite à ajouter.
     *
     */
    public void ajouter(Entite entite) throws NomDEntiteDejaUtiliseDansLeMondeException, EntiteDejaDansUnAutreMondeException {
	if (!entitePresente(entite.getNom()) && entite.getMonde().equals(this)){
	    entites.put(entite.getNom(),entite);
	}
	else if (!entite.getMonde().equals(this))
	    {
		throw new EntiteDejaDansUnAutreMondeException(entite.getNom()+" est déjà présent dans un autre monde");
	    }
	else throw new NomDEntiteDejaUtiliseDansLeMondeException(entite.getNom()+" est un nom déjà utilisé");
    }
    public String toString(){
	int i;
	StringBuilder str = new StringBuilder();
	str.append("Nom du monde : ");
	str.append(getNom());
	str.append("\nEntités présentes : ");
	List<Entite> lEntites=getEntites();
	for(Entite entite : lEntites){
			     str.append("\n\t\t   ");
			     str.append(entite.toString());

			 }
	return str.toString();

    }
}
