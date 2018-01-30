
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
  	   fr.insarouen.asi.asiaventure.AllTestAsiAventure.class,
	   fr.insarouen.asi.asiaventure.elements.AllTestElements.class,
	   fr.insarouen.asi.asiaventure.elements.objets.AllTestObjets.class,
	   fr.insarouen.asi.asiaventure.elements.structure.AllTestStructure.class,
	   fr.insarouen.asi.asiaventure.elements.vivants.AllTestVivants.class,
	   fr.insarouen.asi.asiaventure.elements.objets.serrure.AllTestSerrure.class
		})
public class AllTest{}
