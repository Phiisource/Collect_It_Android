package fr.utt.if26.collectit.dataBase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class CollectItRepository {

    private LotDAO lotDAO;
    private MethodesEcoDAO methodesEcoDAO;
    private UtilisateurDAO utilisateurDAO;
    private HistoriquePointsDAO historiquePointsDAO;
    private LiveData<List<Lot>> mAllLots;
    private LiveData<List<MethodesEco>> mAllMethodesEco;
    private LiveData<List<Utilisateur>> mAllUtilisateurs;
    private LiveData<List<HistoriquePoints>> mAllHistorique;
    private FirebaseAuth mAuth;


    public CollectItRepository(Application application) {
        CollectItDatabase db = CollectItDatabase.getDatabase(application);

        lotDAO = db.lotDAO();
        methodesEcoDAO = db.methodesEcoDAO();
        utilisateurDAO = db.utilisateurDAO();
        historiquePointsDAO = db.historiquePointsDAO();
        mAllLots = lotDAO.getAlphabetizedLots();
        mAllMethodesEco = methodesEcoDAO.getAlphabetizedMethodesEcos();
        mAllUtilisateurs = utilisateurDAO.getAlphabetizedUtilisateurs();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            mAllHistorique = historiquePointsDAO.getAlphabetizedHistoriquePoints(user.getUid());
        }
    }

    public LiveData<List<Lot>> getAllLots() {
        return mAllLots;
    }

    public LiveData<List<MethodesEco>> getAllMethodesEco() {
        return mAllMethodesEco;
    }

    public LiveData<List<Utilisateur>> getAllUtilisateurs() {
        return mAllUtilisateurs;
    }

    public LiveData<List<HistoriquePoints>> getAllHistorique() {
        return mAllHistorique;
    }

    public LotDAO getLotDAO() {
        return lotDAO;
    }

    public void insert (Lot lot) {
        new insertAsyncTask(lotDAO).execute(lot);
    }

    public void insertMethodesEco (MethodesEco methodesEco) {
        new insertAsyncTaskMethodesEco(methodesEcoDAO).execute(methodesEco);
    }

    public void insertUtilisateur (Utilisateur utilisateur) {
        new insertAsyncTaskUtilisateur(utilisateurDAO).execute(utilisateur);
    }

    public void insertHistorique (HistoriquePoints historiquePoints) {
        new insertAsyncTaskHistorique(historiquePointsDAO).execute(historiquePoints);
    }

    private static class insertAsyncTask extends AsyncTask<Lot, Void, Void> {

        private LotDAO mAsyncTaskDao;
        insertAsyncTask(LotDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Lot... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class insertAsyncTaskMethodesEco extends AsyncTask<MethodesEco, Void, Void> {

        private MethodesEcoDAO mAsyncTaskDaoMethodesEco;
        insertAsyncTaskMethodesEco(MethodesEcoDAO dao) {
            mAsyncTaskDaoMethodesEco = dao;
        }

        @Override
        protected Void doInBackground(final MethodesEco... params) {
            mAsyncTaskDaoMethodesEco.insert(params[0]);
            return null;
        }
    }

    private static class insertAsyncTaskUtilisateur extends AsyncTask<Utilisateur, Void, Void> {

        private UtilisateurDAO mAsyncTaskDaoUtilisateur;
        insertAsyncTaskUtilisateur(UtilisateurDAO dao) {
            mAsyncTaskDaoUtilisateur = dao;
        }

        @Override
        protected Void doInBackground(final Utilisateur... params) {
            mAsyncTaskDaoUtilisateur.insert(params[0]);
            return null;
        }
    }

    private static class insertAsyncTaskHistorique extends AsyncTask<HistoriquePoints, Void, Void> {

        private HistoriquePointsDAO mAsyncTaskDaoHistorique;
        insertAsyncTaskHistorique(HistoriquePointsDAO dao) {
            mAsyncTaskDaoHistorique = dao;
        }

        @Override
        protected Void doInBackground(final HistoriquePoints... params) {
            mAsyncTaskDaoHistorique.insert(params[0]);
            return null;
        }
    }

    public void updateLot(ArrayList<Object> attributs) {
        new updateAsyncTask(lotDAO).execute(attributs);
    }

    public void updateUtilisateur(ArrayList<Object> attributs) {
        new updateUtilisateurAsyncTask(utilisateurDAO).execute(attributs);
    }

    private static class updateAsyncTask extends AsyncTask<ArrayList<Object>, Void, Void> {

        private LotDAO mAsyncTaskDao;
        updateAsyncTask(LotDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(ArrayList<Object>... paramsAll) {
            Object[] params = paramsAll[0].toArray();
            mAsyncTaskDao.update(String.valueOf(params[0]), Integer.parseInt(String.valueOf(params[1])), String.valueOf(params[2]), Long.valueOf(String.valueOf(params[3])));
            return null;
        }
    }

    private static class updateUtilisateurAsyncTask extends AsyncTask<ArrayList<Object>, Void, Void> {

        private UtilisateurDAO mAsyncTaskDao;
        updateUtilisateurAsyncTask(UtilisateurDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(ArrayList<Object>... paramsAll) {
            Object[] params = paramsAll[0].toArray();
            mAsyncTaskDao.update(String.valueOf(params[0]), String.valueOf(params[1]), Integer.parseInt(String.valueOf(params[2])), String.valueOf(params[3]));
            return null;
        }
    }
}