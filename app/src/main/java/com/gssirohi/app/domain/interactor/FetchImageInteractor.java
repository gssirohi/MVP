package com.gssirohi.app.domain.interactor;

import android.content.Context;
import android.graphics.Bitmap;

import com.gssirohi.app.base.AppCore;
import com.gssirohi.app.constant.Repositories;
import com.gssirohi.app.domain.exception.AppRepositoryException;
import com.gssirohi.app.domain.repository.IAppRepository;
import com.gssirohi.app.domain.repository.cache.CacheRepository;
import com.gssirohi.app.executor.BaseInteractor;
import com.gssirohi.app.executor.IInteractorExecutor;
import com.gssirohi.app.executor.IMainThreadExecutor;
import com.gssirohi.app.utility.AppLogger;

/**
 * Created by gssirohi on 15/7/16.
 */
public class FetchImageInteractor extends BaseInteractor implements LoadImageUseCase {

    private IAppRepository appRepository;
    private Callback callback;
    private String imageUrl;

    public FetchImageInteractor(Context context, IInteractorExecutor interactorExecutor, IMainThreadExecutor mainThreadExecutor, IAppRepository appRepository) {
        super(context, interactorExecutor, mainThreadExecutor);
        this.appRepository = appRepository;
    }


    @Override
    public void run() {

        try {
            //first check in cache
            AppLogger.i(this, "fetching image from cache: " + imageUrl);
            Bitmap bitmap = AppCore.getInstance().getProvider().getAppRepository(Repositories.CACHE).getImageFromUrl(this, imageUrl);
            if (bitmap == null) {
                AppLogger.i(this, "fetching image from apiRepository: " + imageUrl);
                //Not found in cache , load from server
                bitmap = appRepository.getImageFromUrl(this, imageUrl);
                if (bitmap != null) {
                    ((CacheRepository) AppCore.getInstance().getProvider().getAppRepository(Repositories.CACHE)).addBitmapToMemoryCache(imageUrl, bitmap);
                }
            }
            final Bitmap image = bitmap;
            dismissDialog();
            getMainThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    if (!isCancelled())
                        callback.onImage(image);
                }
            });

        } catch (final AppRepositoryException e) {
            AppLogger.e(this, "Error on fetching image");
            getMainThreadExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    if (!isCancelled())
                        callback.onError(e.getError());
                }
            });
        }
    }

    @Override
    public void execute(Callback callback, String imageUrl, boolean showLoader) {
        this.imageUrl = imageUrl;
        this.callback = callback;
        setCancelled(false);
        if (showLoader) showDialog("Loading image..");
        getInteractorExecutor().performAction(this);
    }

    @Override
    public void cancelCurrentRequest() {
        setCancelled(true);
    }
}
