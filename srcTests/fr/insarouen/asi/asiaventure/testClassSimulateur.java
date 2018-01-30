package fr.insarouen.asi.asiaventure;
import fr.insarouen.asi.asiaventure.Simulateur;
import java.io.Reader;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import fr.insarouen.asi.asiaventure.Monde;


public class testClassSimulateur {
    public static void main(String[] args) throws Exception{
	Reader fr= new FileReader("test.txt");
	Simulateur sim= new Simulateur(fr);
	EtatDuJeu etat = EtatDuJeu.ENCOURS;
	/*FileOutputStream fos=new FileOutputStream("sauvegarde.txt");
	ObjectOutputStream oos = new ObjectOutputStream(fos);
	sim.enregistrer(oos);
	oos.close();
	
	FileInputStream fis=new FileInputStream("sauvegarde.txt");
	ObjectInputStream ois = new ObjectInputStream(fis);
	Simulateur sim2 = new Simulateur(ois);*/
	
	//	System.out.println(sim.monde.toString());
	etat = sim.executerJusquALaFin();
    }
}
    
