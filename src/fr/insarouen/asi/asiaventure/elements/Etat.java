package fr.insarouen.asi.asiaventure.elements;

public enum Etat{CASSE ("Cassé"),
		 DEVEROUILLE ("Dévérouillé"),
		 FERME ("Fermé"),
		 OUVERT ("Ouvert"),
		 VERROUILLE ("Verouillé");

		 private String nom;

		 Etat(String nom){
		     this.nom=nom;
		 }

		 public String toString(){
		     return nom;
		 }
}
