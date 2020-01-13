package fr.utt.if26.collectit.ui.historique;

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
import fr.utt.if26.collectit.dataBase.HistoriquePoints;

public class HistoriqueFragment extends Fragment {

    private HistoriqueViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(HistoriqueViewModel.class);
        View root = inflater.inflate(R.layout.fragment_historique, container, false);

        RecyclerView rv = root.findViewById(R.id.rv_historique);
        final AdapteurHistorique a = new AdapteurHistorique(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(a);

        dashboardViewModel = new ViewModelProvider(this).get(dashboardViewModel.getClass());
        dashboardViewModel.getAllHistorique().observe(this, new Observer<List<HistoriquePoints>>() {
            @Override
            public void onChanged(List<HistoriquePoints> historiquePoints) {
                a.setHistorique(historiquePoints);
            }
        });

        return root;
    }
}