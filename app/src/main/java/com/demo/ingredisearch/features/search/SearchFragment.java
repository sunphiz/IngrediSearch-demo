package com.demo.ingredisearch.features.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.demo.ingredisearch.R;
import com.demo.ingredisearch.util.ViewHelper;

import java.util.Objects;

public class SearchFragment extends Fragment {

    private static final String TAG = "RecipeApp";

    private Button searchActionButton;
    private EditText ingredients;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_search, container, false);
        initUI(root);
        return root;
    }

    private void initUI(View root) {
        ingredients = root.findViewById(R.id.ingredients);
        searchActionButton = root.findViewById(R.id.searchActionButton);

        searchActionButton.setOnClickListener(view -> {
            String query = ingredients.getText().toString();
            ViewHelper.hideKeyboard(this);
            Navigation.findNavController(requireView()).navigate(
                    SearchFragmentDirections.actionSearchFragmentToSearchResultsFragment(query));
        });

        if (requireActivity() instanceof AppCompatActivity) {
            Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setSubtitle(null);
        }
    }

}
