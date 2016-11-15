package com.gssirohi.app.ui.presenter;

import android.content.Context;

import com.gssirohi.app.base.AppCore;
import com.gssirohi.app.constant.AppErrors;
import com.gssirohi.app.constant.Products;
import com.gssirohi.app.constant.UseCases;
import com.gssirohi.app.domain.interactor.FetchProductListUseCase;
import com.gssirohi.app.domain.model.ProductModel;
import com.gssirohi.app.domain.model.pojo.MCharacter;
import com.gssirohi.app.ui.viewcontract.CharactersScreenViewContract;
import com.gssirohi.app.utility.AppLogger;

import java.util.List;


/**
 * Created by gssirohi on 5/7/16.
 */
public class CharactersScreenPresenter implements ICharactersScreenPresenter {
    private final FetchProductListUseCase fetchCharacterListUseCase;
    private CharactersScreenViewContract viewContract;
    private MCharacter character;
    private int offset;
    private int limit = 60;

    public CharactersScreenPresenter(Context context) {
        fetchCharacterListUseCase = (FetchProductListUseCase) AppCore.getInstance().getProvider().getUseCaseImpl(context, UseCases.FETCH_PRODUCT_LIST);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void onViewCreate() {

    }

    @Override
    public void onViewResume() {

    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void setView(CharactersScreenViewContract view) {
        viewContract = view;
    }

    @Override
    public void cancelCurrentRequest() {
        fetchCharacterListUseCase.cancelCurrentRequest();
    }

    @Override
    public void onFetchAllTripRequest(boolean showLoader) {
        AppLogger.i(this, "executing use case..");
        fetchCharacterListUseCase.execute(new FetchProductListUseCase.Callback() {
            @Override
            public void onError(AppErrors error) {

            }

            @Override
            public <T extends ProductModel> void onProductList(List<T> products) {
                AppLogger.i(this, "use case result");
                viewContract.onAllCharacterFetched((List<MCharacter>) products);
            }
        }, offset, limit, Products.CHARACTER, showLoader);
        offset = offset + limit;
    }
}
