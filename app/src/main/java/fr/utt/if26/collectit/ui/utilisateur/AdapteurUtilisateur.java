package fr.utt.if26.collectit.ui.utilisateur;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.collectit.MainActivity;
import fr.utt.if26.collectit.R;
import fr.utt.if26.collectit.dataBase.Utilisateur;

public class AdapteurUtilisateur extends RecyclerView.Adapter<AdapteurUtilisateur.UtilisateurViewHolder> {

    private Context con;

    class UtilisateurViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView nom;
        private final TextView prenom;
        private final TextView points;
        private String id;
        private final Button btnConsulterProfil;

        private UtilisateurViewHolder(View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.nom);
            prenom = itemView.findViewById(R.id.prenom);
            points = itemView.findViewById(R.id.points);
            btnConsulterProfil = itemView.findViewById(R.id.btn_modifier_point);
            btnConsulterProfil.setOnClickListener(this);
            id = "";
        }


        @Override
        public void onClick(View view) {
            if(MainActivity.isChecked) {
                Intent intentModifiePoints = new Intent(con, ModifierPointsActivity.class);
                intentModifiePoints.putExtra("id", id);
                con.startActivity(intentModifiePoints);
            } else {
                Toast.makeText(con, "Vous n'êtes pas en mode admin", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private final LayoutInflater inflater;
    private List<Utilisateur> listeUtilisateurs;

    AdapteurUtilisateur(Context context) {
        inflater = LayoutInflater.from(context);
        con = context;
    }

    @NonNull
    @Override
    public UtilisateurViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.utilisateur, parent, false);
        return new UtilisateurViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull UtilisateurViewHolder holder, int position) {
        if (listeUtilisateurs != null) {
            Utilisateur current = listeUtilisateurs.get(position);
            System.out.println("id utilisateur" + current.getId());
            holder.id = current.getId();
            holder.nom.setText("Nom : " + current.getNom());
            holder.prenom.setText("Prénom : " + current.getPrenom());
            holder.points.setText(Integer.toString(current.getPoint()) + " points");
        } else {
            holder.id = "";
            holder.nom.setText("Pas de nom");
            holder.prenom.setText("Pas de prénom");
            holder.points.setText("Pas de points");
        }
    }

    public List<Utilisateur> getUtilisateurs() {
        return listeUtilisateurs ;
    }

    public void setUtilisateurs(List<Utilisateur> newListeUtilisateurs) {
        listeUtilisateurs = newListeUtilisateurs;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(listeUtilisateurs != null) {
            return listeUtilisateurs.size();
        } else {
            return 0;
        }
    }
}
