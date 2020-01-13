package fr.utt.if26.collectit.ui.historique;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.utt.if26.collectit.dataBase.CollectItRepository;
import fr.utt.if26.collectit.dataBase.HistoriquePoints;

public class HistoriqueViewModel extends AndroidViewModel {

    private CollectItRepository hRespository;

    private LiveData<List<HistoriquePoints>> listeHistorique;

    public HistoriqueViewModel(Application application) {
        super(application);
        hRespository = new CollectItRepository(application);
        listeHistorique = hRespository.getAllHistorique();
    }

    public LiveData<List<HistoriquePoints>> getAllHistorique() {
        return listeHistorique;
    }

    public void insert (HistoriquePoints historiquePoints) {
        hRespository.insertHistorique(historiquePoints);
    }
}