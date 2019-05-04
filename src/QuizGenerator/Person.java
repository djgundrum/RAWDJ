package QuizGenerator;

import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;

import java.util.ArrayList;
import java.util.Hashtable;

public class Person {
    private Hashtable<String, String> allPeople;
//Hashtable to store all of the people
    public Person() {
        allPeople = new Hashtable<String, String>();
    }

    public boolean personExists(String personUser, String personPass) {
       //checks to see if the person is in the hashtable, and if not then returns false
        if(allPeople.containsKey(personUser)) {
            //checks password of that user
            if(allPeople.get(personUser).contains(personPass)) {
                return true;

            }
        }
        return false;

    }






}
