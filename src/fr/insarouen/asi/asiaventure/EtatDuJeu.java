package fr.insarouen.asi.asiaventure;
   
public enum EtatDuJeu{ECHEC ("echec"),
		      ENCOURS ("en cours"),
		      SUCCES ("succes");

		      private String nom;

		      EtatDuJeu(String nom){
			  this.nom=nom;
		      }

		      public String toString(){
			  return nom;
		      }
}
