package com.gssirohi.app.domain.interactor;

import com.gssirohi.app.constant.AppErrors;
import com.gssirohi.app.constant.Products;
import com.gssirohi.app.domain.model.ProductModel;

/**
 * Created by gssirohi on 29/8/16.
 */
public interface FetchProductUseCase extends UseCase {
    void execute(final Callback callback, String id, Products type, boolean showLoader);

    interface Callback {
        void onError(AppErrors error);

        <T extends ProductModel> void onProduct(T product);
    }
}
