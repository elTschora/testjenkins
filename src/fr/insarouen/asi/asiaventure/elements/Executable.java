package fr.insarouen.asi.asiaventure.elements;
import fr.insarouen.asi.asiaventure.elements.vivants.CommandeImpossibleParLeVivantException;
public interface Executable{
    void executer() throws CommandeImpossibleParLeVivantException;
}
