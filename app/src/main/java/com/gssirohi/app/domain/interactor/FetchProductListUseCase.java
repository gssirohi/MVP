package com.gssirohi.app.domain.interactor;

import com.gssirohi.app.constant.AppErrors;
import com.gssirohi.app.constant.Products;
import com.gssirohi.app.domain.model.ProductModel;

import java.util.List;

/**
 * Created by gssirohi on 29/8/16.
 */
public interface FetchProductListUseCase extends UseCase {
    void execute(final Callback callback, int offset, int limit, Products type, boolean showLoader);

    interface Callback {
        void onError(AppErrors error);

        <T extends ProductModel> void onProductList(List<T> products);
    }
}
