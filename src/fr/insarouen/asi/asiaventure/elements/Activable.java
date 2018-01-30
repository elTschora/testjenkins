package fr.insarouen.asi.asiaventure.elements;

import fr.insarouen.asi.asiaventure.elements.objets.Objet;
import fr.insarouen.asi.asiaventure.elements.ActivationImpossibleAvecObjetException;
import fr.insarouen.asi.asiaventure.elements.ActivationImpossibleException;
import fr.insarouen.asi.asiaventure.elements.ActivationException;

public interface Activable{
    boolean activableAvec(Objet obj);
    void activer() throws ActivationException;
    void activerAvec(Objet obj) throws ActivationException;
}
