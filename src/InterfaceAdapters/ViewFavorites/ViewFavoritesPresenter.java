package InterfaceAdapters.ViewFavorites;

import InterfaceAdapters.ViewManagerModel;
import UseCase.ViewFavorites.ViewFavoritesOutputBoundary;
import UseCase.ViewFavorites.ViewFavoritesOutputData;

import java.util.ArrayList;

public class ViewFavoritesPresenter implements ViewFavoritesOutputBoundary {

    private final ViewFavoritesViewModel viewModel;
    private final ViewManagerModel viewManagerModel;

    public ViewFavoritesPresenter(ViewFavoritesViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void presentNoFavorites() {
        ViewFavoritesState state = new ViewFavoritesState();
        state.setFavoriteRecipes(new ArrayList<>());
        state.setMessage("No favorite recipes found.");
        viewModel.setState(state);
        viewModel.firePropertyChanged();
        viewManagerModel.setActiveView(viewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void presentFavoriteRecipes(ViewFavoritesOutputData outputData) {
        ViewFavoritesState state = new ViewFavoritesState();
        state.setFavoriteRecipes(outputData.getFavoriteRecipes());
        viewModel.setState(state);
        viewModel.firePropertyChanged();
        viewManagerModel.setActiveView(viewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}
