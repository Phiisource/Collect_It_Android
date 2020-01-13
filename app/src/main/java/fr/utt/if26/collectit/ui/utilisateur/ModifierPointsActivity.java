package fr.utt.if26.collectit.ui.utilisateur;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import fr.utt.if26.collectit.R;
import fr.utt.if26.collectit.dataBase.HistoriquePoints;
import fr.utt.if26.collectit.dataBase.Utilisateur;
import fr.utt.if26.collectit.ui.historique.HistoriqueViewModel;

public class ModifierPointsActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference databaseReference;

    private TextView nom, prenom, email;
    private UtilisateurViewModel utilisateurViewModel;
    private HistoriqueViewModel historiqueViewModel;
    private EditText points;
    private Button btnEnregistrer;
    private int getAnciensPoints = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_points);
        nom = findViewById(R.id.tv_nom_profil2);
        prenom = findViewById(R.id.tv_nom_profil3);
        email = findViewById(R.id.tv_nom_profil4);
        points = findViewById(R.id.et_points);

        utilisateurViewModel = new ViewModelProvider(this).get(UtilisateurViewModel.class);

        System.out.println("id utilisateur" + getIntent().getStringExtra("id"));
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.child(getIntent().getStringExtra("id")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Utilisateur u = dataSnapshot.getValue(Utilisateur.class);
                nom.setText(u.getNom());
                prenom.setText(u.getPrenom());
                email.setText(u.getEmail());
                getAnciensPoints = u.getPoint();
                points.setText(String.valueOf(u.getPoint()));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        btnEnregistrer = findViewById(R.id.btn_enregistrer_points);
        btnEnregistrer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnEnregistrer) {
            if(! TextUtils.isEmpty(points.getText().toString())) {
                try {
                    databaseReference.child(getIntent().getStringExtra("id")).child("point").setValue(Integer.parseInt(points.getText().toString()));
                    utilisateurViewModel.update(nom.getText().toString(), prenom.getText().toString(), Integer.parseInt(points.getText().toString()), email.getText().toString());

                    historiqueViewModel = new ViewModelProvider(this).get(HistoriqueViewModel.class);
                    SimpleDateFormat dateformat = new SimpleDateFormat("dd/MMM/yyyy");
                    HistoriquePoints hp = new HistoriquePoints("Ajout de points par un admin" , Integer.parseInt(points.getText().toString()) - getAnciensPoints, dateformat.format(Calendar.getInstance().getTime()), getIntent().getStringExtra("id"));
                    historiqueViewModel.insert(hp);


                    Toast.makeText(this, "Le profil a été mis à jour", Toast.LENGTH_SHORT).show();
                } catch(NumberFormatException e) {
                    Toast.makeText(this, "Le champ point doit être un entier", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
