package fr.utt.if26.collectit.ui.methodeseco;

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
import fr.utt.if26.collectit.dataBase.MethodesEco;

public class MethodesEcoFragment extends Fragment {

    private MethodeEcoViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(MethodeEcoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_methodes_eco, container, false);

        RecyclerView rv = root.findViewById(R.id.rv_liste_methodes_eco);
        final AdapteurMethodesEco a = new AdapteurMethodesEco(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(a);

        homeViewModel = new ViewModelProvider(this).get(homeViewModel.getClass());
        homeViewModel.getAllMethodesEco().observe(this, new Observer<List<MethodesEco>>() {
            @Override
            public void onChanged(List<MethodesEco> methodesEco) {
                a.setMethodesEco(methodesEco);
            }
        });

        return root;
    }
}