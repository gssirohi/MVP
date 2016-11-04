package com.gssirohi.app.ui.presenter;

import android.content.Context;

import com.gssirohi.app.base.AppCore;
import com.gssirohi.app.constant.AppErrors;
import com.gssirohi.app.constant.UseCases;
import com.gssirohi.app.domain.interactor.FetchProductUseCase;
import com.gssirohi.app.domain.model.ProductModel;
import com.gssirohi.app.ui.viewcontract.ProductDeatailsScreenViewContract;
import com.gssirohi.app.ui.viewmodel.ProductDetailsViewModel;
import com.gssirohi.app.utility.AppUtils;


/**
 * Created by gssirohi on 5/7/16.
 */
public class ProductDetailsScreenPresenter implements IProductDetailsScreenPresenter {
    private final FetchProductUseCase fetchProductUseCase;
    private ProductDeatailsScreenViewContract viewContract;

    public ProductDetailsScreenPresenter(Context context) {
        fetchProductUseCase = (FetchProductUseCase) AppCore.getInstance().getProvider().getUseCaseImpl(context, UseCases.FETCH_PRODUCT_DETAILS);
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
    public void setView(ProductDeatailsScreenViewContract view) {

        this.viewContract = view;
    }


    @Override
    public void cancelCurrentRequest() {
        fetchProductUseCase.cancelCurrentRequest();
    }


    @Override
    public void onFetchProductDetailsRequest(String uri, boolean showLoader) {

        fetchProductUseCase.execute(new FetchProductUseCase.Callback() {
            @Override
            public void onError(AppErrors error) {

            }

            @Override
            public <T extends ProductModel> void onProduct(T product) {
                viewContract.onProductDetailsFetched(new ProductDetailsViewModel(product));
            }
        }, AppUtils.getProductIdFromUri(uri), AppUtils.getProductTypeFromUri(uri), showLoader);
    }
}
