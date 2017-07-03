package digitalentreprise.com.digitalenterprise.Model;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import digitalentreprise.com.digitalenterprise.Configuration.FirebaseConfiguration;

public class Users {

    private String ID;
    private String Name;
    private String Email;
    private String Password;
    private String Data_Nascimento;
    private String Genre;

    public Users(){

    }


    public void SaveUser(){
        DatabaseReference FirebaseReference = FirebaseConfiguration.getReference();
        FirebaseReference.child("Users").child(getID()).setValue(this);
    }

    public String getData_Nascimento() {
        return Data_Nascimento;
    }

    public void setData_Nascimento(String data_Nascimento) {
        Data_Nascimento = data_Nascimento;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }


    @Exclude
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
    @Exclude
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }



}
