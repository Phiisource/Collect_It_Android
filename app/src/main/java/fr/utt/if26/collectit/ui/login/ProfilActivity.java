package fr.utt.if26.collectit.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import fr.utt.if26.collectit.R;
import fr.utt.if26.collectit.dataBase.Utilisateur;

public class ProfilActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    private EditText nom, prenom;
    private TextView email;
    private Button btnDeconnexion, btnEnregistrer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        nom = findViewById(R.id.et_nom_profil);
        prenom = findViewById(R.id.et_prenom_profil);
        email = findViewById(R.id.tv_email_p);


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Utilisateur u = dataSnapshot.getValue(Utilisateur.class);
                nom.setText(u.getNom());
                prenom.setText(u.getPrenom());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        email.setText(user.getEmail());

        btnDeconnexion = findViewById(R.id.btn_deconnexion);
        btnDeconnexion.setOnClickListener(this);

        btnEnregistrer = findViewById(R.id.btn_enregistrer);
        btnEnregistrer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnDeconnexion) {
            mAuth.signOut();
            Intent loginIntent = new Intent(ProfilActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        } else if (view == btnEnregistrer) {
            FirebaseUser user = mAuth.getCurrentUser();
            databaseReference.child(user.getUid()).child("nom").setValue(nom.getText().toString());
            databaseReference.child(user.getUid()).child("prenom").setValue(prenom.getText().toString());
        }
    }
}
