package fr.utt.if26.collectit.ui.methodeseco;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import fr.utt.if26.collectit.R;

public class MethodesEcoFragment extends Fragment {

    private MethodeEcoViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(MethodeEcoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_methodes_eco, container, false);
        final TextView textView = root.findViewById(R.id.text_methodes_eco);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}