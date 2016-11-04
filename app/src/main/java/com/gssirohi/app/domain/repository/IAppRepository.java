package com.gssirohi.app.domain.repository;

import android.graphics.Bitmap;

import com.gssirohi.app.constant.Products;
import com.gssirohi.app.domain.model.ProductModel;
import com.gssirohi.app.executor.BaseInteractor;

import java.util.List;

/**
 * Created by gssirohi on 5/7/16.
 */
public interface IAppRepository {

    public List<ProductModel> getProductList(BaseInteractor interactor, int offset, int limit, Products type);

    public ProductModel getProductById(BaseInteractor interactor, String id, Products type);

    Bitmap getImageFromUrl(BaseInteractor interactor, String imageUrl);
}
