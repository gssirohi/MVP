package com.gssirohi.app.ui.presenter;

import com.gssirohi.app.base.IBasePresenter;
import com.gssirohi.app.ui.viewcontract.ProductDeatailsScreenViewContract;

/**
 * Created by gssirohi on 5/7/16.
 */
public interface IProductDetailsScreenPresenter extends IBasePresenter<ProductDeatailsScreenViewContract> {

    void onFetchProductDetailsRequest(String uri, boolean showLoader);

}
