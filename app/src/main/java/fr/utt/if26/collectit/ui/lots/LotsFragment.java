package fr.utt.if26.collectit.ui.lots;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Observable;

import fr.utt.if26.collectit.ObservableBoolean;
import fr.utt.if26.collectit.R;
import fr.utt.if26.collectit.dataBase.Lot;

//import java.util.Observer;

public class LotsFragment extends Fragment {

    private LotsViewModel lotsViewModel;
    private FloatingActionButton floatingActionButton;
    private ObservableBoolean isBackOffice;
    private java.util.Observer observer = new java.util.Observer() {
        @Override
        public void update(Observable observable, Object o) {
            //Log.d(LotsFragment.class.getSimpleName(), "a1 has changed, new value:"+ (boolean) isBackOffice.getIsbackOffice());
        }
    };
    // Constructeur
    public LotsFragment() {
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        lotsViewModel =
                ViewModelProviders.of(this).get(LotsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_lots, container, false);
        floatingActionButton = root.findViewById(R.id.btn_ajoute);
        floatingActionButton.hide();

        isBackOffice = new ObservableBoolean();
        isBackOffice.addObserver(observer);
        System.out.println("valeur de" + isBackOffice.getIsbackOffice());

        /*if (isBackOffice.getIsbackOffice()) {
            floatingActionButton.show();
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent ajouteIntent = new Intent(getContext(), AjouteLotActivity.class);
                    startActivity(ajouteIntent);
                }
            });
        } else {
            floatingActionButton.hide();
        }*/

        RecyclerView rv = root.findViewById(R.id.rv_liste_lots);
        final AdapteurLot a = new AdapteurLot(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(a);

        lotsViewModel = new ViewModelProvider(this).get(lotsViewModel.getClass());
        lotsViewModel.getAllLots().observe(this, new Observer<List<Lot>>() {
            @Override
            public void onChanged(List<Lot> lots) {
                a.setLots(lots);
            }
        });
        return root;
    }
}