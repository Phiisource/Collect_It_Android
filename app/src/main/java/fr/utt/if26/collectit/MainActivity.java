package fr.utt.if26.collectit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import fr.utt.if26.collectit.dataBase.Utilisateur;
import fr.utt.if26.collectit.ui.login.ProfilActivity;
import fr.utt.if26.collectit.ui.lots.AdapteurLot;
import fr.utt.if26.collectit.ui.lots.AjouteLotActivity;
import fr.utt.if26.collectit.ui.lots.LotsFragment;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingActionButtonProfil;
    public Switch switchButton;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    public static boolean isChecked = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_accueil, R.id.navigation_historique, R.id.navigation_lots, R.id.navigation_methodes_eco)
                .build();
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        floatingActionButtonProfil  = findViewById(R.id.btn_profil);
        floatingActionButtonProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ajouteIntent = new Intent(MainActivity.this, ProfilActivity.class);
                startActivity(ajouteIntent);
            }
        });

        switchButton = findViewById(R.id.btn_switch);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.child(user.getUid()).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Utilisateur u = dataSnapshot.getValue(Utilisateur.class);
                if (u.getAdmin()) {
                    switchButton.setVisibility(View.VISIBLE);
                    switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if(switchButton.isChecked()) {
                                isChecked = true;
                                // Changement de la barre de navigation
                                navView.getMenu().findItem(R.id.navigation_accueil).setVisible(false);
                                navView.getMenu().findItem(R.id.navigation_historique).setVisible(false);
                                navView.getMenu().findItem(R.id.navigation_methodes_eco).setVisible(false);
                                navView.getMenu().findItem(R.id.navigation_utilisateurs).setVisible(true);

                                if(navView.getMenu().findItem(R.id.navigation_lots).isChecked()) {
                                    // Ajouter le bouton ajoute
                                    LotsFragment.floatingActionButton.show();
                                    LotsFragment.floatingActionButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent ajouteIntent = new Intent(MainActivity.this, AjouteLotActivity.class);
                                            startActivity(ajouteIntent);
                                        }
                                    });
                                    //Modifier le texte du bouton
                                    AdapteurLot.globalObtenirModifier.setText(R.string.modifier_lot);
                                }
                            } else {
                                isChecked = false;
                                // Changement de la barre de navigation
                                navView.getMenu().findItem(R.id.navigation_accueil).setVisible(true);
                                navView.getMenu().findItem(R.id.navigation_historique).setVisible(true);
                                navView.getMenu().findItem(R.id.navigation_methodes_eco).setVisible(true);
                                navView.getMenu().findItem(R.id.navigation_utilisateurs).setVisible(false);

                                if(navView.getMenu().findItem(R.id.navigation_lots).isChecked()) {
                                    //Masquer le buton ajoute
                                    LotsFragment.floatingActionButton.hide();

                                    //Modifier le texte du bouton
                                    AdapteurLot.globalObtenirModifier.setText(R.string.obtenir_lot);
                                }
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }
}
