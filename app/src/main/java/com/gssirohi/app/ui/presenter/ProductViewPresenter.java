package com.gssirohi.app.ui.presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.gssirohi.app.base.AppCore;
import com.gssirohi.app.constant.AppErrors;
import com.gssirohi.app.constant.UseCases;
import com.gssirohi.app.domain.interactor.FetchImageInteractor;
import com.gssirohi.app.domain.interactor.FetchProductUseCase;
import com.gssirohi.app.domain.interactor.LoadImageUseCase;
import com.gssirohi.app.domain.model.ProductModel;
import com.gssirohi.app.domain.model.pojo.MCharacter;
import com.gssirohi.app.domain.model.pojo.Thumbnail;
import com.gssirohi.app.ui.viewcontract.ProductViewPresenterContract;
import com.gssirohi.app.utility.AppUtils;


/**
 * Created by gssirohi on 5/7/16.
 */
public class ProductViewPresenter implements IProductViewPresenter {

    private final FetchProductUseCase fetchProductDetailsUsecase;
    private final FetchImageInteractor fetchProductImageInteractor;
    private ProductViewPresenterContract viewContract;
    private MCharacter character;
    private int offset;
    private int limit = 30;

    public ProductViewPresenter(Context context) {
        fetchProductDetailsUsecase = (FetchProductUseCase) AppCore.getInstance().getProvider().getUseCaseImpl(context, UseCases.FETCH_PRODUCT_DETAILS);
        fetchProductImageInteractor = (FetchImageInteractor) AppCore.getInstance().getProvider().getUseCaseImpl(context, UseCases.FETCH_PRODUCT_IMAGE);
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
    public void setView(ProductViewPresenterContract view) {
        viewContract = view;
    }

    @Override
    public void cancelCurrentRequest() {
        fetchProductDetailsUsecase.cancelCurrentRequest();
    }


    @Override
    public void onRequestProductImage(String productUri) {

        fetchProductDetailsUsecase.execute(new FetchProductUseCase.Callback() {
            @Override
            public void onError(AppErrors error) {

            }

            @Override
            public <T extends ProductModel> void onProduct(T product) {

                if (product != null) {
                    Thumbnail t = product.getThumbnail();
                    if (t == null) return;
                    String url = t.getPath() + "/standard_large." + t.getExtension();
                    fetchProductImageInteractor.execute(new LoadImageUseCase.Callback() {
                        @Override
                        public void onError(AppErrors error) {

                        }

                        @Override
                        public void onImage(Bitmap image) {

                            viewContract.onProductImageFetched(image);
                        }
                    }, url, false);
                }
            }
        }, AppUtils.getProductIdFromUri(productUri), AppUtils.getProductTypeFromUri(productUri), false);
    }
}
