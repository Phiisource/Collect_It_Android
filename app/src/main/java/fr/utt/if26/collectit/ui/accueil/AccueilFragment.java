package fr.utt.if26.collectit.ui.accueil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import fr.utt.if26.collectit.R;
import fr.utt.if26.collectit.dataBase.Utilisateur;

public class AccueilFragment extends Fragment {

    private AccueilViewModel homeViewModel;
    private TextView points;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    public static int pointsUtilisateurs = 0;
    public static Utilisateur utilisateur = new Utilisateur();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(AccueilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_accueil, container, false);
        points = root.findViewById(R.id.tv_valeurs_points);


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Utilisateur u = dataSnapshot.getValue(Utilisateur.class);
                points.setText(String.valueOf(u.getPoint()) + " points");
                pointsUtilisateurs = u.getPoint();
                utilisateur = u;
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
        return root;
    }
}