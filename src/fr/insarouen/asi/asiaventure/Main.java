package fr.insarouen.asi.asiaventure;
import fr.insarouen.asi.asiaventure.Simulateur;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.StringBuilder;
import java.util.Scanner;
import java.io.FileReader;
import java.io.Reader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main{
    public static Simulateur sim;
    public static void main(String[] args) throws Exception{
	Scanner sc = new Scanner(System.in);
	int str=0;
	boolean jeuCharge = false;
	while (str!=5){
	    System.out.println("\n\n\n");
	    afficherMenu();
	    str = sc.nextInt();
	    System.out.println("\n\n\n");
	    switch(str){
	    case 1:
		if (jeuCharge) {
		commandeJeu();
		
		}
		else System.out.println("Veuillez charger un jeu pour pouvoir jouer");
		break;
	    case 2:
		commandeChargerFichier();
		jeuCharge = true;
		break;
	    case 3:
		commandeSauverPartie();
		break;
	    case 4:
		commandeChargerPartie();
		jeuCharge = true;
		break;
	    default:
	    }
	}
    }
    
    public static void afficherMenu(){
	StringBuilder str = new StringBuilder();
	str.append("\t--- Menu ---");
	str.append("\n1/ jouer");
	str.append("\n2/ charger un fichier de description");
	str.append("\n3/ sauver la partie actuelle");
	str.append("\n4/ charger une partie");
	str.append("\n5/ quitter");
	System.out.println(str.toString());
    }

    public static void commandeJeu() throws Exception{
	EtatDuJeu etat;
	System.out.println("Vous avez décidé de jouer");
	System.out.println("Conditions de fin : \n");
	for(ConditionDeFin condition : sim.conditionsDeFin){
	    System.out.println(condition.toString());
	    }
	etat=sim.executerJusquALaFin();
	System.out.println(etat.toString());
    }
    public static void commandeChargerFichier() throws Exception{
	System.out.println("Nom du fichier de description : test.txt");
        Reader fr= new FileReader("test.txt");
	sim= new Simulateur(fr);
	System.out.println("test.txt bien chargé");
    }

    public static void commandeSauverPartie() throws Exception{
	System.out.println("Nom du fichier de sauvegarde : sauvegarde.txt");
       	FileOutputStream fos=new FileOutputStream("sauvegarde.txt");
	ObjectOutputStream oos = new ObjectOutputStream(fos);
	sim.enregistrer(oos);
	oos.close();
	System.out.println("Sauvegarde effectuée");
    }

    public static void commandeChargerPartie() throws Exception {
	System.out.println("Chargement depuis sauvegarde.txt");
	FileInputStream fis=new FileInputStream("sauvegarde.txt");
	ObjectInputStream ois = new ObjectInputStream(fis);
	sim = new Simulateur(ois);
	System.out.println("Partie chargée");
    }

   
}
	
