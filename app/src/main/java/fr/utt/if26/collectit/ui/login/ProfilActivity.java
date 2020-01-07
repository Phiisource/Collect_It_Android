package fr.utt.if26.collectit.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import fr.utt.if26.collectit.MainActivity;
import fr.utt.if26.collectit.R;
import fr.utt.if26.collectit.dataBase.Utilisateur;
import fr.utt.if26.collectit.ui.utilisateur.UtilisateurViewModel;

public class ProfilActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    private EditText nom, prenom;
    private TextView email;
    private Button btnDeconnexion, btnEnregistrer;
    private UtilisateurViewModel utilisateurViewModel;
    private int points;

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
                points = u.getPoint();
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
            MainActivity.isChecked = false;
        } else if (view == btnEnregistrer) {
            if (!TextUtils.isEmpty(nom.getText().toString()) && !TextUtils.isEmpty(prenom.getText().toString())) {
                FirebaseUser user = mAuth.getCurrentUser();
                utilisateurViewModel = new ViewModelProvider(this).get(UtilisateurViewModel.class);
                databaseReference.child(user.getUid()).child("nom").setValue(nom.getText().toString());
                databaseReference.child(user.getUid()).child("prenom").setValue(prenom.getText().toString());
                utilisateurViewModel.update(nom.getText().toString(), prenom.getText().toString(), points, email.getText().toString());
                Toast.makeText(this, "La modification a réussi", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Remplissez tous les champs", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
