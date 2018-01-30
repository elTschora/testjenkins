package fr.insarouen.asi.asiaventure.elements.vivants;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({
	testClassVivant.class,
	    testClassMonstre.class,
	    testClassJoueurHumain.class
	    })

public class AllTestVivants{}
