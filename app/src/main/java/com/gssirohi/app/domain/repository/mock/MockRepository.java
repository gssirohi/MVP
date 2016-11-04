package com.gssirohi.app.domain.repository.mock;

import android.graphics.Bitmap;

import com.gssirohi.app.constant.Products;
import com.gssirohi.app.domain.model.ProductModel;
import com.gssirohi.app.domain.repository.IAppRepository;
import com.gssirohi.app.executor.BaseInteractor;

import java.util.List;

/**
 * Created by gssirohi on 5/7/16.
 */
public class MockRepository implements IAppRepository {


    @Override
    public List<ProductModel> getProductList(BaseInteractor interactor, int offset, int limit, Products type) {
        return null;
    }

    @Override
    public ProductModel getProductById(BaseInteractor interactor, String id, Products type) {
        return null;
    }

    @Override
    public Bitmap getImageFromUrl(BaseInteractor interactor, String imageUrl) {
        return null;
    }
}
