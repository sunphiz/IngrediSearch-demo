package com.demo.ingredisearch.features.details;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.demo.ingredisearch.models.Recipe;
import com.demo.ingredisearch.util.Resource;

public class RecipeDetailsViewModel extends AndroidViewModel {

    private static final String TAG = "RecipeApp";

    public RecipeDetailsViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<Resource<Recipe>> getRecipe() {
        // TODO
        return null;
    }

    public void searchRecipe(String recipeId) {
        // TODO
    }
}
