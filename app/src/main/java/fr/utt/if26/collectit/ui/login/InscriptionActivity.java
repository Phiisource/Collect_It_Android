package fr.utt.if26.collectit.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import fr.utt.if26.collectit.R;
import fr.utt.if26.collectit.dataBase.CollectItRepository;
import fr.utt.if26.collectit.dataBase.Utilisateur;

public class InscriptionActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private EditText mdp, email, nom, prenom, confmdp, mdpAdmin;
    private Button btnValider;
    private CollectItRepository uRespository;
    private CheckBox admin;

    private DatabaseReference databaseReference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inscription_activity);

        email = findViewById(R.id.et_email_inscription);
        mdp = findViewById(R.id.et_mdp_inscription);
        confmdp = findViewById(R.id.et_conf_mdp);
        nom = findViewById(R.id.et_nom_inscrip);
        prenom = findViewById(R.id.et_prenom_inscrip);
        admin = findViewById(R.id.check);
        mdpAdmin = findViewById(R.id.et_mdp_admin);
        uRespository = new CollectItRepository(getApplication());

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        btnValider = findViewById(R.id.btn_valider);
        btnValider.setOnClickListener(this);

    }

    private void registerUser(){
        final String mail = email.getText().toString().trim();
        final String password = mdp.getText().toString().trim();
        final String lastName = nom.getText().toString().trim();
        final String firstName = prenom.getText().toString().trim();
        final String confPassword = confmdp.getText().toString().trim();
        final String passwordAdmin = mdpAdmin.getText().toString().trim();

        if(TextUtils.isEmpty(lastName)){
            Toast.makeText(this, "Entrez votre nom", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(firstName)){
            Toast.makeText(this,"Entrez votre prénom",  Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(mail)){
            Toast.makeText(this, "Entrez votre email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Entrez votre mot de passe",  Toast.LENGTH_SHORT).show();
            return;
        }
        if(! TextUtils.equals(confPassword, password)){
            Toast.makeText(this,"Confirmer votre mot de passe",  Toast.LENGTH_SHORT).show();
            return;
        }
        if(admin.isChecked() && TextUtils.isEmpty(passwordAdmin)){
            Toast.makeText(this,"Entrez le mot de passe administrateur",  Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(mail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            System.out.println(admin.isChecked() + "et aussi" + passwordAdmin);
                            boolean isAdmin = false;
                            if (admin.isChecked() && TextUtils.equals("Admin", passwordAdmin)) {
                                isAdmin = true;
                            } else {
                                isAdmin = false;
                            }

                            // Insertion de l'utilisateur en base de données
                            Utilisateur u = new Utilisateur(mail, lastName, firstName, 0, isAdmin);
                            uRespository.insertUtilisateur(u);
                            databaseReference.child(user.getUid()).setValue(u);

                            Intent intent = new Intent(InscriptionActivity.this, LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(InscriptionActivity.this, "Les informations ont bien été enregistrées", Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
                            // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(InscriptionActivity.this, "Les informations sont incorrectes",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if(view == btnValider) {
            registerUser();
        }
    }
}
