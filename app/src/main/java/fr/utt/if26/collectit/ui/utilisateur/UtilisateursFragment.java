package fr.utt.if26.collectit.ui.utilisateur;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.collectit.R;
import fr.utt.if26.collectit.dataBase.Utilisateur;

public class UtilisateursFragment extends Fragment {

    private UtilisateurViewModel utilisateurViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        utilisateurViewModel =
                ViewModelProviders.of(this).get(UtilisateurViewModel.class);
        View root = inflater.inflate(R.layout.activity_utilisateur, container, false);

        RecyclerView rv = root.findViewById(R.id.rv_utilisateur);
        final AdapteurUtilisateur a = new AdapteurUtilisateur(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(a);

        utilisateurViewModel = new ViewModelProvider(this).get(utilisateurViewModel.getClass());
        utilisateurViewModel.getUtilisateurs().observe(this, new Observer<List<Utilisateur>>() {
            @Override
            public void onChanged(List<Utilisateur> utilisateurs) {
                a.setUtilisateurs(utilisateurs);
            }
        });

        return root;
    }
}