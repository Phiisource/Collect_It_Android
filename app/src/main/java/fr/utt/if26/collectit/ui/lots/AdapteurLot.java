package fr.utt.if26.collectit.ui.lots;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import fr.utt.if26.collectit.MainActivity;
import fr.utt.if26.collectit.R;
import fr.utt.if26.collectit.dataBase.HistoriquePoints;
import fr.utt.if26.collectit.dataBase.Lot;
import fr.utt.if26.collectit.ui.accueil.AccueilFragment;
import fr.utt.if26.collectit.ui.historique.HistoriqueViewModel;
import fr.utt.if26.collectit.ui.utilisateur.UtilisateurViewModel;

public class AdapteurLot extends RecyclerView.Adapter<AdapteurLot.LotViewHolder> {

    private Context con;
    public static Button globalObtenirModifier;
    private UtilisateurViewModel utilisateurViewModel;
    private HistoriqueViewModel historiqueViewModel;
    private DatabaseReference databaseReference;


    class LotViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView label;
        private final TextView cout;
        private final TextView date;
        private long id;
        public final Button obtenirModifierLot;

        private LotViewHolder(View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.tv_intitule_lot);
            cout = itemView.findViewById(R.id.tv_cout_lot);
            date = itemView.findViewById(R.id.tv_date_validite);
            id = 0;
            obtenirModifierLot = itemView.findViewById(R.id.btn_obtenir_modifier_lot);
            globalObtenirModifier = obtenirModifierLot;

            if (MainActivity.isChecked) {
                obtenirModifierLot.setText(R.string.modifier_lot);
            } else {
                obtenirModifierLot.setText(R.string.obtenir_lot);
            }
            obtenirModifierLot.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (MainActivity.isChecked) {
                Intent intentAjouteLot = new Intent(con, ModifieLotActivity.class);
                intentAjouteLot.putExtra("intitule", label.getText().toString());
                intentAjouteLot.putExtra("cout", cout.getText().toString().split(" : ")[1].split(" ")[0]);
                intentAjouteLot.putExtra("date", date.getText().toString().split(" : ")[1]);
                intentAjouteLot.putExtra("id", String.valueOf(id));
                con.startActivity(intentAjouteLot);
            } else {
                if(AccueilFragment.utilisateur.getPoint() > Integer.parseInt(cout.getText().toString().split(" : ")[1].split(" ")[0])) {
                    Toast.makeText(con, "Lot obtenu", Toast.LENGTH_SHORT).show();

                    // Mise à jour de l'utilisateur
                    utilisateurViewModel.update(AccueilFragment.utilisateur.getNom(), AccueilFragment.utilisateur.getPrenom(),AccueilFragment.utilisateur.getPoint() - Integer.parseInt(cout.getText().toString().split(" : ")[1].split(" ")[0]), AccueilFragment.utilisateur.getEmail());
                    databaseReference.child(AccueilFragment.utilisateur.getId()).child("point").setValue(AccueilFragment.utilisateur.getPoint() - Integer.parseInt(cout.getText().toString().split(" : ")[1].split(" ")[0]));

                    // Mise à jour de l'historique
                    SimpleDateFormat dateformat = new SimpleDateFormat("dd/MMM/yyyy");
                    HistoriquePoints hp = new HistoriquePoints(label.getText().toString(), Integer.parseInt(cout.getText().toString().split(" : ")[1].split(" ")[0]), dateformat.format(Calendar.getInstance().getTime()), AccueilFragment.utilisateur.getId());
                    historiqueViewModel.insert(hp);

                } else {
                    Toast.makeText(con, "Vous n'avez pas assez de points", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private final LayoutInflater inflater;
    //private final Context contextAdapteur;
    private List<Lot> listeLots;

    public AdapteurLot(Context context) {
        inflater = LayoutInflater.from(context);
        con = context;
        utilisateurViewModel = ViewModelProviders.of((FragmentActivity) con).get(UtilisateurViewModel.class);
        historiqueViewModel = ViewModelProviders.of((FragmentActivity) con).get(HistoriqueViewModel.class);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

    }

    @NonNull
    @Override
    public LotViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.lot, parent, false);
        return new LotViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull LotViewHolder holder, int position) {
        if (listeLots != null) {
            Lot current = listeLots.get(position);
            holder.id = current.getId();
            holder.label.setText(current.getIntitule());
            holder.cout.setText("Coût : " + Integer.toString(current.getCout()) + " points");
            holder.date.setText("Valable jusqu'au : " + current.getDate());
        } else {
            holder.label.setText("No intitule");
            holder.cout.setText("No cout");
            holder.date.setText("No date");
        }
    }

    public List<Lot> getLots() {
        return listeLots ;
    }

    public void setLots(List<Lot> newListeLots) {
        listeLots = newListeLots;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(listeLots != null) {
            return listeLots.size();
        } else {
            return 0;
        }
    }
}
