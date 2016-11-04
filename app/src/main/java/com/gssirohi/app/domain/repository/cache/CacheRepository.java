package com.gssirohi.app.domain.repository.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.gssirohi.app.constant.Products;
import com.gssirohi.app.domain.model.ProductModel;
import com.gssirohi.app.domain.repository.IAppRepository;
import com.gssirohi.app.executor.BaseInteractor;
import com.gssirohi.app.network.ResponseContainer;

import java.util.List;

/**
 * Created by gssirohi on 4/9/16.
 */
public class CacheRepository implements IAppRepository {


    private LruCache<String, Bitmap> mImageMemoryCache;
    private LruCache<String, ResponseContainer> mResponseMemoryCache;

    public CacheRepository() {
        mImageMemoryCache = getImageCacheMemory();
        mResponseMemoryCache = getResponseCacheMemory();
    }

    public LruCache<String, Bitmap> getImageCacheMemory() {
        if (mImageMemoryCache != null) return mImageMemoryCache;
        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        mImageMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return bitmap.getByteCount() / 1024;
            }
        };
        return mImageMemoryCache;
    }


    public LruCache<String, ResponseContainer> getResponseCacheMemory() {
        if (mResponseMemoryCache != null) return mResponseMemoryCache;
        // Get max available VM memory, exceeding this amount will throw an
        // OutOfMemory exception. Stored in kilobytes as LruCache takes an
        // int in its constructor.
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

        // Use 1/8th of the available memory for this memory cache.
        final int cacheSize = maxMemory / 8;

        mResponseMemoryCache = new LruCache<String, ResponseContainer>(cacheSize) {
            @Override
            protected int sizeOf(String key, ResponseContainer responseContainer) {
                // The cache size will be measured in kilobytes rather than
                // number of items.
                return 10 * 1024 / 1024;
            }
        };
        return mResponseMemoryCache;
    }


    public void addResponseToMemoryCache(String key, ResponseContainer container) {
        if (getResponseFromMemCache(key) == null) {
            mResponseMemoryCache.put(key, container);
        }
    }

    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mImageMemoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmapFromMemCache(String key) {
        return mImageMemoryCache.get(key);
    }

    public ResponseContainer getResponseFromMemCache(String key) {
        return mResponseMemoryCache.get(key);
    }


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
        return getBitmapFromMemCache(imageUrl);
    }
}
