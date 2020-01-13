package fr.utt.if26.collectit.dataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoriquePointsDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(HistoriquePoints historiquePoints);

    @Query("DELETE FROM historique_points")
    void deleteAll();

    @Query("SELECT * from historique_points WHERE id_utilisateur = :idUtilisateur ORDER BY date ASC")
    LiveData<List<HistoriquePoints>> getAlphabetizedHistoriquePoints(String idUtilisateur);
}
