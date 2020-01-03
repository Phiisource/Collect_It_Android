package fr.utt.if26.collectit.dataBase;

        import androidx.lifecycle.LiveData;
        import androidx.room.Dao;
        import androidx.room.Insert;
        import androidx.room.OnConflictStrategy;
        import androidx.room.Query;

        import java.util.List;

@Dao
public interface MethodesEcoDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MethodesEco methodesEco);

    @Query("DELETE FROM methodes_eco")
    void deleteAll();

    @Query("SELECT * from methodes_eco ORDER BY description ASC")
    LiveData<List<MethodesEco>> getAlphabetizedMethodesEcos();
}
