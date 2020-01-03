package fr.utt.if26.collectit.ui.methodeseco;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.collectit.R;
import fr.utt.if26.collectit.dataBase.MethodesEco;

public class AdapteurMethodesEco extends RecyclerView.Adapter<AdapteurMethodesEco.MethodesEcoViewHolder> {

    class MethodesEcoViewHolder extends RecyclerView.ViewHolder {
        private final TextView description;

        private MethodesEcoViewHolder(View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.tv_description);
        }
    }

    private final LayoutInflater inflater;
    private List<MethodesEco> listeMethodesEco;

    AdapteurMethodesEco(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MethodesEcoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.methodes_eco, parent, false);
        return new MethodesEcoViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MethodesEcoViewHolder holder, int position) {
        if (listeMethodesEco != null) {
            MethodesEco current = listeMethodesEco.get(position);
            holder.description.setText(current.getDescription());
        } else {
            holder.description.setText("No intitule");
        }
    }

    public List<MethodesEco> getMethodesEco() {
        return listeMethodesEco ;
    }

    public void setMethodesEco(List<MethodesEco> newListeMethodesEco) {
        listeMethodesEco = newListeMethodesEco;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(listeMethodesEco != null) {
            return listeMethodesEco.size();
        } else {
            return 0;
        }
    }
}
