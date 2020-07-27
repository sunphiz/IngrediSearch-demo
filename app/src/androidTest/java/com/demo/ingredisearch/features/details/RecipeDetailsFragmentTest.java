package com.demo.ingredisearch.features.details;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.demo.ingredisearch.R;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RecipeDetailsFragmentTest {

    @Test
    public void recipeDetailsFragmentInView() {

        // TODO

        // Assert (Then)
        onView(withId(R.id.recipe_image)).check(matches(isDisplayed()));
        onView(withId(R.id.recipe_title)).check(matches(isDisplayed()));
        onView(withId(R.id.recipe_social_score)).check(matches(isDisplayed()));
        onView(withId(R.id.ingredients_title)).check(matches(isDisplayed()));
        onView(withId(R.id.ingredients_container)).check(matches(isDisplayed()));
    }

}