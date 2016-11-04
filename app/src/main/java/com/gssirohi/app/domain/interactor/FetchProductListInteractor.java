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

import java.util.List;

/**
 * Created by gssirohi on 15/7/16.
 */
public class FetchProductListInteractor extends BaseInteractor implements FetchProductListUseCase {

    private IAppRepository appRepository;
    private Callback callback;
    private int offset;
    private int limit;
    private Products productType;

    public FetchProductListInteractor(Context context, IInteractorExecutor interactorExecutor, IMainThreadExecutor mainThreadExecutor, IAppRepository appRepository) {
        super(context, interactorExecutor, mainThreadExecutor);
        this.appRepository = appRepository;
    }


    @Override
    public void run() {

        try {
            Thread.sleep(300);

            final List<ProductModel> products = appRepository.getProductList(this, offset, limit, productType);
            dismissDialog();
            if (!isCancelled())
                getMainThreadExecutor().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onProductList(products);
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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(Callback callback, int offset, int limit, Products type, boolean showLoader) {
        this.callback = callback;
        this.offset = offset;
        this.limit = limit;
        this.productType = type;
        setCancelled(false);
        if (showLoader) showDialog("Loading characters..");
        getInteractorExecutor().performAction(this);
    }

    @Override
    public void cancelCurrentRequest() {
        setCancelled(true);
    }
}
