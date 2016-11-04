package com.gssirohi.app.domain.interactor;

import android.content.Context;

import com.gssirohi.app.constant.Products;
import com.gssirohi.app.domain.exception.AppRepositoryException;
import com.gssirohi.app.domain.model.ProductModel;
import com.gssirohi.app.domain.repository.IAppRepository;
import com.gssirohi.app.executor.BaseInteractor;
import com.gssirohi.app.executor.IInteractorExecutor;
import com.gssirohi.app.executor.IMainThreadExecutor;
import com.gssirohi.app.utility.AppLogger;

/**
 * Created by gssirohi on 15/7/16.
 */
public class FetchProductInteractor extends BaseInteractor implements FetchProductUseCase {

    private IAppRepository appRepository;
    private Callback callback;
    private String id;
    private Products type;

    public FetchProductInteractor(Context context, IInteractorExecutor interactorExecutor, IMainThreadExecutor mainThreadExecutor, IAppRepository appRepository) {
        super(context, interactorExecutor, mainThreadExecutor);
        this.appRepository = appRepository;
    }


    @Override
    public void run() {

        try {
            final ProductModel product = appRepository.getProductById(this, id, type);
            dismissDialog();
            if (!isCancelled())
                getMainThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onProduct(product);
                    }
                });

        } catch (final AppRepositoryException e) {
            AppLogger.e(this, "Error on fetch all characters");
            if (!isCancelled())
                getMainThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onError(e.getError());
                    }
                });
        }
    }

    @Override
    public void execute(Callback callback, String id, Products type, boolean showLoader) {
        this.callback = callback;
        this.id = id;
        this.type = type;
        setCancelled(false);
        if (showLoader) showDialog("loading product(id:" + id + ")");
        getInteractorExecutor().performAction(this);
    }

    @Override
    public void cancelCurrentRequest() {
        setCancelled(true);
    }
}
