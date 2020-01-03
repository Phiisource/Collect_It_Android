package fr.utt.if26.collectit.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import fr.utt.if26.collectit.MainActivity;
import fr.utt.if26.collectit.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private EditText mdp, email;
    private Button btnConnexion, btnInscription;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.et_email);
        mdp = findViewById(R.id.et_mdp);

        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }
        btnConnexion = findViewById(R.id.btn_connexion);
        btnInscription = findViewById(R.id.btn_inscription);
        btnConnexion.setOnClickListener(this);
        btnInscription.setOnClickListener(this);

    }

    private void registerUser(){
        String mail = email.getText().toString().trim();
        String password = mdp.getText().toString().trim();

        if(TextUtils.isEmpty(mail)){
            Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter email",  Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            // Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(LoginActivity.this, "Authentication réussie.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            // Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


    @Override
    public void onClick(View view) {
        if(view == btnConnexion) {
            registerUser();
        }
        if(view == btnInscription) {
            Intent inscriptionIntent = new Intent(LoginActivity.this, InscriptionActivity.class);
            startActivity(inscriptionIntent);
        }
    }
}

