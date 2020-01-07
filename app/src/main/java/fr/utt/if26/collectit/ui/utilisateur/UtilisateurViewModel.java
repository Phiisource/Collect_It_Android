package fr.utt.if26.collectit.ui.utilisateur;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import fr.utt.if26.collectit.dataBase.CollectItRepository;
import fr.utt.if26.collectit.dataBase.Utilisateur;

public class UtilisateurViewModel extends AndroidViewModel {

    private CollectItRepository uRespository;

    private LiveData<List<Utilisateur>> listeUtilisateurs;

    public UtilisateurViewModel(Application application) {
        super(application);
        uRespository = new CollectItRepository(application);
        listeUtilisateurs = uRespository.getAllUtilisateurs();
    }

    public LiveData<List<Utilisateur>> getUtilisateurs() {
        return listeUtilisateurs;
    }

    public void insert (Utilisateur utilisateur) {
        uRespository.insertUtilisateur(utilisateur);
    }

    public void update (String nom, String prenom, int point, String email) {
        ArrayList<Object> attributs = new ArrayList<Object>();
        attributs.add(nom);
        attributs.add(prenom);
        attributs.add(point);
        attributs.add(email);
        uRespository.updateUtilisateur(attributs);
    }
}