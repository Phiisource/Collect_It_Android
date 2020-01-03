package fr.utt.if26.collectit.ui.lots;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import fr.utt.if26.collectit.dataBase.CollectItRepository;
import fr.utt.if26.collectit.dataBase.Lot;

public class LotsViewModel extends AndroidViewModel {

    private CollectItRepository lRespository;

    private LiveData<List<Lot>> listeLots;

    public LotsViewModel(Application application) {
        super(application);
        lRespository = new CollectItRepository(application);
        listeLots = lRespository.getAllLots();
    }

    public LiveData<List<Lot>> getAllLots() {
        return listeLots;
    }

    public void insert (Lot lot) {
        lRespository.insert(lot);
    }

    public void update (String intitule, int cout, String date, long id) {
        ArrayList<Object> attributs = new ArrayList<Object>();
        attributs.add(intitule);
        attributs.add(cout);
        attributs.add(date);
        attributs.add(id);
        lRespository.updateLot(attributs);
    }
}