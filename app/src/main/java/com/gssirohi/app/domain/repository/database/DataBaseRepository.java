package com.gssirohi.app.domain.repository.database;

import android.graphics.Bitmap;

import com.gssirohi.app.constant.Products;
import com.gssirohi.app.domain.model.ProductModel;
import com.gssirohi.app.domain.repository.IAppRepository;
import com.gssirohi.app.executor.BaseInteractor;

import java.util.List;

/**
 * Created by gssirohi on 25/8/16.
 */
public class DataBaseRepository implements IAppRepository {

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
