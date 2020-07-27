package com.demo.ingredisearch.features.details;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class RecipeDetailsViewModelFactory implements ViewModelProvider.Factory {

    private Application app;

    public RecipeDetailsViewModelFactory(Application app) {
        this.app = app;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (!modelClass.isAssignableFrom(RecipeDetailsViewModel.class))
            throw new IllegalArgumentException("No such viewmodel exists");
        return (T) new RecipeDetailsViewModel(app);
    }
}
