package com.gssirohi.app.ui.presenter;

import com.gssirohi.app.base.IBasePresenter;
import com.gssirohi.app.ui.viewcontract.ProductViewPresenterContract;

/**
 * Created by gssirohi on 5/7/16.
 */
public interface IProductViewPresenter extends IBasePresenter<ProductViewPresenterContract> {

    void onRequestProductImage(String productUri);

}
