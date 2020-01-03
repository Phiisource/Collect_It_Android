package fr.utt.if26.collectit.dataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UtilisateurDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Utilisateur utilisateur);

    @Query("DELETE FROM utilisateur")
    void deleteAll();

    @Query("SELECT * from utilisateur ORDER BY nom ASC")
    LiveData<List<Utilisateur>> getAlphabetizedUtilisateurs();

    @Query("SELECT * from utilisateur WHERE email = :email")
    LiveData<Utilisateur> getUtilisateur(String email);
}
