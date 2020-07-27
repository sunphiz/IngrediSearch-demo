package com.demo.ingredisearch.features.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.ingredisearch.R;
import com.demo.ingredisearch.adapters.RecipeAdapter;
import com.demo.ingredisearch.models.Recipe;
import com.demo.ingredisearch.util.ViewHelper;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class FavoritesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecipeAdapter mAdapter;

    private LinearLayout mLoadingContainer;
    private LinearLayout mErrorContainer;
    private LinearLayout mNoResultsContainer;

    private ViewHelper mViewHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.fragment_list, container, false);
        initUI(root);
        return root;
    }

    private void initUI(View root) {
        mRecyclerView = root.findViewById(R.id.list);
        mLoadingContainer = root.findViewById(R.id.loadingContainer);
        mErrorContainer = root.findViewById(R.id.errorContainer);
        mNoResultsContainer = root.findViewById(R.id.noresultsContainer);
        TextView noResultsTitle = root.findViewById(R.id.noresultsTitle);
        noResultsTitle.setText(getString(R.string.nofavorites));

        mViewHelper = new ViewHelper(mLoadingContainer, mErrorContainer, mNoResultsContainer);

        if (requireActivity() instanceof AppCompatActivity) {
            Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setSubtitle(null);
        }

        setupRecyclerView();
        showNoFavorites();
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new RecipeAdapter(new RecipeAdapter.Interaction() {
            @Override
            public void onRemoveFavorite(@NotNull Recipe item) {
                // TODO
            }

            @Override
            public void onAddFavorite(@NotNull Recipe item) {
            }

            @Override
            public void onClickItem(@NotNull Recipe recipe) {
                launchRecipeDetails(recipe.getRecipeId());
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private void showNoFavorites() {
        mLoadingContainer.setVisibility(View.GONE);
        mErrorContainer.setVisibility(View.GONE);
        mNoResultsContainer.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    private void launchRecipeDetails(String recipeId) {
        Navigation.findNavController(requireView()).navigate(
                FavoritesFragmentDirections.actionFavoritesFragmentToRecipeDetailsFragment(recipeId)
        );
    }
}
