package fr.insarouen.asi.asiaventure;

import fr.insarouen.asi.asiaventure.elements.vivants.CommandeImpossibleParLeVivantException;
import fr.insarouen.asi.asiaventure.Monde;
import fr.insarouen.asi.asiaventure.EtatDuJeu;
import fr.insarouen.asi.asiaventure.elements.Entite;
import fr.insarouen.asi.asiaventure.elements.Executable;
import fr.insarouen.asi.asiaventure.elements.vivants.Vivant;
import fr.insarouen.asi.asiaventure.elements.vivants.Monstre;
import fr.insarouen.asi.asiaventure.elements.vivants.JoueurHumain;
import fr.insarouen.asi.asiaventure.elements.structure.Piece;
import fr.insarouen.asi.asiaventure.elements.structure.Porte;
import fr.insarouen.asi.asiaventure.elements.objets.serrurerie.Serrure;
import fr.insarouen.asi.asiaventure.elements.objets.PiedDeBiche;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;
import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.lang.StringBuilder;
import fr.insarouen.asi.asiaventure.NomDEntiteDejaUtiliseDansLeMondeException;
import fr.insarouen.asi.asiaventure.ConditionDeFin;
    
public class Simulateur{
    public Monde monde;
    public List<ConditionDeFin> conditionsDeFin;
    private EtatDuJeu etatDuJeu;

    public Simulateur(Monde monde, ConditionDeFin... conditionsDeFin){
	this.monde=monde;
	this.conditionsDeFin=new ArrayList<ConditionDeFin>();
	for (ConditionDeFin cdt : conditionsDeFin){
	    this.conditionsDeFin.add(cdt);
	}
	etatDuJeu = EtatDuJeu.ENCOURS;
    }

    
    public Simulateur(ObjectInputStream ois) throws IOException, ClassNotFoundException{
	monde=(Monde)ois.readObject();
	conditionsDeFin=(List<ConditionDeFin>)ois.readObject();
	etatDuJeu=(EtatDuJeu)ois.readObject();
	
    }

    public Simulateur(Reader r) throws IOException, NomDEntiteDejaUtiliseDansLeMondeException{
	StreamTokenizer streamT= new StreamTokenizer(r);
	conditionsDeFin = new ArrayList<ConditionDeFin>();
	String nomPorte;
	String nomPieceA;
	String nomPieceB;
	String nomEtudiant;
	int pointVie;
	int pointForce;
	String etatDuJeu;
	streamT.nextToken();	
	while (streamT.sval!=null){	    
	    switch (streamT.sval){
	    case "Monde":
		streamT.nextToken();
		creerMonde(streamT.sval);
		break;
	    case "Piece":
		streamT.nextToken();
		creerPiece(streamT.sval);
		break;
	    case "PorteSerrure":
		streamT.nextToken();
		nomPorte=streamT.sval;
		streamT.nextToken();		
		nomPieceA=streamT.sval;
		streamT.nextToken();		
		nomPieceB=streamT.sval;
		creerPorteSerrure(nomPorte,nomPieceA,nomPieceB);
		break;
	    case "Porte":
		streamT.nextToken();
		nomPorte=streamT.sval;
		streamT.nextToken();		
		nomPieceA=streamT.sval;
		streamT.nextToken();		
		nomPieceB=streamT.sval;
		creerPorte(nomPorte,nomPieceA,nomPieceB);
		break;
	    case "Clef":
		streamT.nextToken();
		nomPorte=streamT.sval;
		streamT.nextToken();
		nomPieceA=streamT.sval;
		creerClef(nomPorte,nomPieceA);		
		break;
	    case "PiedDeBiche":
		streamT.nextToken();
		nomPorte=streamT.sval;
		streamT.nextToken();
		nomPieceA=streamT.sval;
		creerPiedDeBiche(nomPorte,nomPieceA);		
		break;
	    case "JoueurHumain":
		streamT.nextToken();
		nomEtudiant=streamT.sval;
		streamT.nextToken();
		pointVie=(int)streamT.nval;
		streamT.nextToken();
		pointForce=(int)streamT.nval;
		streamT.nextToken();
		nomPieceA=streamT.sval;
		creerJoueurHumain(nomEtudiant,pointVie,pointForce,nomPieceA);
		break;
	    case "Monstre":
		streamT.nextToken();
		nomEtudiant=streamT.sval;
		streamT.nextToken();
		pointVie=(int)streamT.nval;
		streamT.nextToken();
		pointForce=(int)streamT.nval;
		streamT.nextToken();
		nomPieceA=streamT.sval;
		creerMonstre(nomEtudiant,pointVie,pointForce,nomPieceA);
		break;
		
	    case "ConditionDeFinVivantDansPiece":
		streamT.nextToken();
		etatDuJeu=streamT.sval;
		streamT.nextToken();
		nomEtudiant=streamT.sval;
		streamT.nextToken();
		nomPieceA=streamT.sval;
		creerConditionDeFinVivantDansPiece(etatDuJeu,nomEtudiant, nomPieceA);
		break;		
	    default:
	   
	    }
    	    streamT.nextToken();
	    
	}
	this.etatDuJeu = EtatDuJeu.ENCOURS;
    }

    public EtatDuJeu getEtatDuJeu(){
	return etatDuJeu;
    }
    private void creerMonde(String nomMonde){
	monde=new Monde(nomMonde);
    }
    private void creerPiece(String nomPiece) throws NomDEntiteDejaUtiliseDansLeMondeException{
	Piece piece=new Piece(nomPiece, monde);
    }

    private void creerPorteSerrure(String nomPorte,String nomPieceA,String nomPieceB) throws NomDEntiteDejaUtiliseDansLeMondeException{
	Serrure serrure = new Serrure(monde);
	Porte porte = new Porte(nomPorte,monde,serrure,(Piece)monde.getEntite(nomPieceA),(Piece)monde.getEntite(nomPieceB));
    }
    
    private void creerPorte(String nomPorte,String nomPieceA,String nomPieceB) throws NomDEntiteDejaUtiliseDansLeMondeException{
	Porte porte = new Porte(nomPorte,monde,(Piece)monde.getEntite(nomPieceA),(Piece)monde.getEntite(nomPieceB));
    }

    private void creerClef(String nomPorte,String nomEmplacement) throws NomDEntiteDejaUtiliseDansLeMondeException{
        ((Piece)monde.getEntite(nomEmplacement)).deposer(((Porte)monde.getEntite(nomPorte)).getSerrure().creerClef());
    }

    private void creerPiedDeBiche(String nomPdb, String nomEmplacement) throws NomDEntiteDejaUtiliseDansLeMondeException{
	PiedDeBiche pdb = new PiedDeBiche(nomPdb,monde);
	((Piece)monde.getEntite(nomEmplacement)).deposer(pdb);
    }
    private void creerJoueurHumain(String nomJoueur, int pv, int pf, String nomEmplacement) throws NomDEntiteDejaUtiliseDansLeMondeException{
	JoueurHumain joueur=new JoueurHumain(nomJoueur,monde,pv,pf,(Piece)monde.getEntite(nomEmplacement));
    }

    private void creerMonstre(String nomJoueur, int pv, int pf, String nomEmplacement) throws NomDEntiteDejaUtiliseDansLeMondeException{
	Monstre monstre=new Monstre(nomJoueur,monde,pv,pf,(Piece)monde.getEntite(nomEmplacement));
    }

    private EtatDuJeu transcrireEtat(String nomEtat){
	EtatDuJeu etat=EtatDuJeu.ENCOURS;
	switch (nomEtat){
	case "SUCCES":
	    etat=EtatDuJeu.SUCCES;
	    break;
	case "ECHEC":

	    etat=EtatDuJeu.ECHEC;
	    break;
	case "ENCOURS":
	    etat=EtatDuJeu.ENCOURS;
	    break;
	default:
	}
	return etat;
    }
	    

    private void creerConditionDeFinVivantDansPiece(String etatDuJeu, String nomEtudiant, String nomPieceA){
	EtatDuJeu etat=transcrireEtat(etatDuJeu);
	ConditionDeFinVivantDansPiece condition = new ConditionDeFinVivantDansPiece(etat,(Vivant)monde.getEntite(nomEtudiant),(Piece)monde.getEntite(nomPieceA));
	ajouterConditionDeFin(condition);
    }
    
    public void enregistrer(ObjectOutputStream oos) throws IOException{
	oos.writeObject(monde);
	oos.writeObject(conditionsDeFin);
	oos.writeObject(etatDuJeu);
    }


    public void ajouterConditionDeFin(ConditionDeFin condition){
	conditionsDeFin.add(condition);
    }

    public void ajouterConditionsDeFin(ConditionDeFin... conditions){
	for (ConditionDeFin cdt : conditions){
	    this.conditionsDeFin.add(cdt);
	}
    }
    
     public Set<JoueurHumain> getJoueurs(List<Executable> executables){
	Set<JoueurHumain> res = new HashSet<JoueurHumain>();
	for(Executable executable : executables){
			     if (executable instanceof JoueurHumain){
				 res.add((JoueurHumain)executable);
			     }
			 }
	return res;

    }

    
    public EtatDuJeu executerUnTour(){
	EtatDuJeu etat = EtatDuJeu.ENCOURS;
	List<Executable> executables = monde.getExecutables();
	List<Executable> aSupprimer = new LinkedList<Executable>();	
	while (!executables.isEmpty()){
	    for(JoueurHumain joueur : getJoueurs(executables)){
		faireTour(joueur);	
	    }
	    for(Executable executable : executables){
		try{
		    executable.executer();
		    aSupprimer.add(executable);
		}
		catch(CommandeImpossibleParLeVivantException e){
		    System.out.println(e.getMessage());
		}
		catch(Exception e){
		    e.printStackTrace();
		}
	    }

	    for (Executable executable: aSupprimer){
		executables.remove(executable);
	    }
	}
	int i=0;
        while (etat.equals(EtatDuJeu.ENCOURS) & i<conditionsDeFin.size()){
	    etat=conditionsDeFin.get(i).verifierCondition();
    	    i=i+1;
	}
				    
	return etat;
    }
    	    
    public EtatDuJeu executerNbTours(int n){
	EtatDuJeu etat = EtatDuJeu.ENCOURS;
	for(int i=0;i<n;i++){
	    etat=executerUnTour();
	}
	return etat;
    }
    
    public EtatDuJeu executerJusquALaFin(){
	EtatDuJeu etat = EtatDuJeu.ENCOURS;
	boolean rejouer = true;
	
	int i=0;
	
	while (etat.equals(EtatDuJeu.ENCOURS) & rejouer==true){
    	    i=i+1;
	    System.out.println("\nC'est le tour n°" + i + "\n");
	    etat=executerUnTour();
	    rejouer = demanderDeRejouer();
     	}
	return etat;
    }
    
    public boolean demanderDeRejouer(){
	String rep;
	Scanner sc = new Scanner(System.in);
	boolean res = true;
	boolean aRep = false;
	while (!aRep){
	    System.out.println("\nVoulez vous rejouer? (y/n)\n");
	    rep = sc.next();
	    switch (rep){
	    case "y" : res = true;
		aRep=true;
		break;
	    case "n" : res = false;
		aRep = true;
		break;
	    default : aRep = false;
		break;
	    }
	}
	return res;
    }
		
	    
 
				  
    public void faireTour(JoueurHumain joueur){
	StringBuilder str = new StringBuilder();
	str.append("\n\n\n");
	str.append("Joueur");
	str.append(joueur.getNom());
	str.append(", c'est à vous de jouer.\n");
	str.append("(Prendre objet, Poser objet, ActiverPorte porte, ActiverPorteAvec porte objet, ActiverSerrure porte clef, Franchir porte)\nVous etes dans la piece : ");
	str.append(joueur.piece.toString());
	str.append("\nQue voulez vous faire?");
	System.out.println(str.toString());

	Scanner sc = new Scanner(System.in);
	joueur.setOrdre(sc.nextLine());
	
    }
    
    public String toString(){
	StringBuilder str= new StringBuilder();
	str.append(monde.toString());
	for(ConditionDeFin cdt : conditionsDeFin){
	    str.append("\n");
	    str.append(cdt.toString());
    }
	return str.toString();
    }
    
    
}
