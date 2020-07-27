package com.demo.ingredisearch.features.searchresults;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import java.util.Objects;

public class SearchResultsFragment extends Fragment {

    private static final String TAG = "RecipeApp";

    private RecyclerView mRecyclerView;
    private RecipeAdapter mAdapter;

    private LinearLayout mNoResultsContainer;
    private LinearLayout mLoadingContainer;
    private LinearLayout mErrorContainer;
    private ViewHelper mViewHelper;
    private String mQuery;

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

        mViewHelper = new ViewHelper(mLoadingContainer, mErrorContainer, mNoResultsContainer);

        SearchResultsFragmentArgs arguments = SearchResultsFragmentArgs.fromBundle(requireArguments());
        mQuery = arguments.getQuery();

        TextView mRetry = root.findViewById(R.id.retry);
        mRetry.setOnClickListener(view -> searchRecipes(mQuery));

        if (requireActivity() instanceof AppCompatActivity) {
            Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setSubtitle(null);
        }

        setHasOptionsMenu(true);
        setupRecyclerView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        searchRecipes(mQuery);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.favoritesFragment) {
            Navigation.findNavController(requireView()).navigate(R.id.action_searchResultsFragment_to_favoritesFragment);
            return true;
        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.filter_recipes, menu);
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        mAdapter = new RecipeAdapter(new RecipeAdapter.Interaction() {
            @Override
            public void onRemoveFavorite(@NonNull Recipe recipe) {
                // TODO
            }

            @Override
            public void onAddFavorite(@NonNull Recipe recipe) {
                // TODO
            }

            @Override
            public void onClickItem(@NonNull Recipe recipe) {
                Navigation.findNavController(requireView()).navigate(
                        SearchResultsFragmentDirections.actionSearchResultsFragmentToRecipeDetailsFragment(recipe.getRecipeId()));
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    public void searchRecipes(String query) {
        mNoResultsContainer.setVisibility(View.VISIBLE);
    }
}
