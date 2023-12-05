package InterfaceAdapters.recipesearch;

import Entities.Nutrition;
import Entities.Recipe;
import UseCase.recipesearch.RecipeDataAccessInterface;
import UseCase.recipesearch.RecipeSearchInputData;
import UseCase.recipesearch.RecipeSearchInteractor;
import UseCase.recipesearch.RecipeSearchOutputBoundary;
import UseCase.recipesearch.RecipeSearchOutputData;
import UseCase.recipesearch.RecipeSearchDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
public class RecipeSearchInteractorTest {

    @Mock
    private RecipeSearchOutputBoundary outputBoundary;
    @Mock
    private RecipeDataAccessInterface apiSearch;
    @Mock
    private Recipe mockRecipe; // Mocked Recipe object
    private RecipeSearchInteractor interactor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        interactor = new RecipeSearchInteractor(outputBoundary, apiSearch);
        when(mockRecipe.getRecipeId()).thenReturn("dummyId");
        Nutrition mockNutrition = mock(Nutrition.class);
        when(mockNutrition.getCalorie()).thenReturn(100); // example value
        when(mockRecipe.getNutrition()).thenReturn(mockNutrition);

        when(mockRecipe.getDescription()).thenReturn("Dummy description");
    }

    @Test
    void testExecuteWithValidData() {
        RecipeSearchInputData inputData = new RecipeSearchInputData(500, Arrays.asList("Chicken", "Rice"), false, false, false, false, false);
        when(apiSearch.searchReturnResults(inputData)).thenReturn(Collections.singletonList(mockRecipe));
        interactor.execute(inputData);
        verify(outputBoundary).present(any(RecipeSearchOutputData.class));
    }
}