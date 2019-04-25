package QuizGenerator;

import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;

import java.util.ArrayList;
import java.util.Hashtable;

public class Person {
    private Hashtable<String, String> allPeople;

    public Person() {
        allPeople = new Hashtable<String, String>();
    }

    public boolean personExists(String personUser, String personPass) {
        if(allPeople.containsKey(personUser)) {
            if(allPeople.get(personUser).contains(personPass)) {
                return true;

            }
        }
        return false;

    }






}
