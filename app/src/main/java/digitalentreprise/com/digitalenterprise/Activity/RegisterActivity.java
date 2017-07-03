package digitalentreprise.com.digitalenterprise.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import digitalentreprise.com.digitalenterprise.Configuration.FirebaseConfiguration;
import digitalentreprise.com.digitalenterprise.Model.Users;
import digitalentreprise.com.digitalenterprise.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText Register_Name;
    private EditText Register_Email;
    private EditText Register_Password;
    private EditText Register_Data_Nascimento;
    private RadioGroup Register_Genre;
    private RadioButton Register_Selected_Genre;
    private Button Button_Register;
    private Users User;
    private FirebaseAuth Autentication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Register_Name = (EditText) findViewById(R.id.EditText_RegisterActivity_Nome);
        Register_Email = (EditText) findViewById(R.id.EditText_RegisterActivity_Email);
        Register_Password = (EditText) findViewById(R.id.EditText_RegisterActivity_Password);
        Register_Data_Nascimento = (EditText) findViewById(R.id.EditText_RegisterActivity_DataNascimento);
        Button_Register = (Button) findViewById(R.id.Button_RegisterActivity_Register);
        Register_Genre = (RadioGroup) findViewById(R.id.RadioGroup_RegisterActivity_Genre);


        Button_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int IdGenre = Register_Genre.getCheckedRadioButtonId();
                if(IdGenre>0){
                    Register_Selected_Genre = (RadioButton) findViewById(IdGenre);

                }

                User = new Users();
                User.setName(Register_Name.getText().toString());
                User.setEmail(Register_Email.getText().toString());
                User.setPassword(Register_Password.getText().toString());
                User.setData_Nascimento(Register_Data_Nascimento.getText().toString());
                User.setGenre(Register_Selected_Genre.getText().toString());

                RegisterUser();

            }
        });



    }

    private void RegisterUser(){
        //Autentication = FirebaseConfiguration.getAutentication();

        Autentication = FirebaseAuth.getInstance();
        Autentication.createUserWithEmailAndPassword(
               User.getEmail(),
                User.getPassword().toString()
        ).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    FirebaseUser UserFirebase = task.getResult().getUser();
                    User.setID( UserFirebase.getUid() );

                    User.SaveUser();
                    //FirebaseAuthException e = (FirebaseAuthException)task.getException();
                    //Log.e("RegisterActivity", "Failed Registration", e);
                    //Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                    //startActivity(intent);
                    //Toast.makeText(LoginActivity.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    //Log.w("RegisterActivity", "signInWithEmail:failed", task.getException());
                    //Toast.makeText(RegisterActivity.this, "User Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }else{

                    Toast.makeText(RegisterActivity.this,"Falhou",Toast.LENGTH_SHORT);
                }
            }
        });

    }
}
