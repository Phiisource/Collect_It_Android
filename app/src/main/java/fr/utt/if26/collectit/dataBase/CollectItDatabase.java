package fr.utt.if26.collectit.dataBase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Lot.class, MethodesEco.class, Utilisateur.class, HistoriquePoints.class}, version = 10)
public abstract class CollectItDatabase extends RoomDatabase {
    public abstract LotDAO lotDAO();
    public abstract MethodesEcoDAO methodesEcoDAO();
    public abstract UtilisateurDAO utilisateurDAO();
    public abstract HistoriquePointsDAO historiquePointsDAO();


    private static volatile CollectItDatabase INSTANCE;

    public static CollectItDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CollectItDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CollectItDatabase.class, "CollectIt_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final LotDAO lDao;
        private final MethodesEcoDAO mDao;
        private final UtilisateurDAO uDao;
        private final HistoriquePointsDAO hDao;

        PopulateDbAsync(CollectItDatabase db) {
            lDao = db.lotDAO();
            mDao = db.methodesEcoDAO();
            uDao = db.utilisateurDAO();
            hDao = db.historiquePointsDAO();
        }


        @Override
        protected Void doInBackground(final Void... params) {
            // Gestion des lots
            lDao.deleteAll();
            Lot lot = new Lot("Reduction de 30% sur les télévisions samsung", 20, "21/12/30");
            lDao.insert(lot);
            lot = new Lot("Reduction de 30% sur les téléphones Apple", 205, "21/11/20");
            lDao.insert(lot);

            // Gestion des méthodes Eco
            mDao.deleteAll();
            MethodesEco methodesEco = new MethodesEco("Pensez à trier les déchets et à les mettre dans les bonnes poubelles");
            mDao.insert(methodesEco);

            HistoriquePoints historiquePoints = new HistoriquePoints("Ajout de points par un admin", 30, "21/12/2020", "toto");
            hDao.insert(historiquePoints);



            return null;
        }
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };
}
