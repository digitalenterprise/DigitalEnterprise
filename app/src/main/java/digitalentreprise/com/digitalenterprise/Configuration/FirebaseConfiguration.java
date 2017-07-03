package digitalentreprise.com.digitalenterprise.Configuration;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public final class FirebaseConfiguration {

    private static DatabaseReference FirebaseReference;
    private static FirebaseAuth FirebaseAutentication;

    public static DatabaseReference getReference(){
        if(FirebaseReference == null){
            FirebaseReference = FirebaseDatabase.getInstance().getReference();
        }
        return FirebaseReference;
    }

    public static FirebaseAuth getAutentication(){

        if(FirebaseAutentication==null){
            FirebaseAutentication = FirebaseAuth.getInstance();
        }

        return FirebaseAutentication;
    }

}
