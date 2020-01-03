package fr.utt.if26.collectit.ui.methodeseco;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import fr.utt.if26.collectit.dataBase.CollectItRepository;
import fr.utt.if26.collectit.dataBase.MethodesEco;

;

public class MethodeEcoViewModel extends AndroidViewModel {

    private CollectItRepository lRespository;

    private LiveData<List<MethodesEco>> listeMethodesEco;

    public MethodeEcoViewModel(Application application) {
        super(application);
        lRespository = new CollectItRepository(application);
        listeMethodesEco = lRespository.getAllMethodesEco();
    }

    LiveData<List<MethodesEco>> getAllMethodesEco() {
        return listeMethodesEco;
    }

    public void insert (MethodesEco methodesEco) {
        lRespository.insertMethodesEco(methodesEco);
    }
}