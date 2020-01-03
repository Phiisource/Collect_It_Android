package fr.utt.if26.collectit.dataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LotDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Lot lot);

    @Query("UPDATE lot SET intitule = :intitule, cout = :cout, date = :date WHERE id = :id")
    void update(String intitule, int cout, String date, long id);

    @Query("DELETE FROM lot")
    void deleteAll();

    @Query("SELECT * from lot ORDER BY intitule ASC")
    LiveData<List<Lot>> getAlphabetizedLots();
}
