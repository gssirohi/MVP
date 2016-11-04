package com.gssirohi.app.base;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.gssirohi.app.constant.InteractorExecutors;
import com.gssirohi.app.constant.Repositories;
import com.gssirohi.app.constant.UseCases;
import com.gssirohi.app.domain.interactor.FetchImageInteractor;
import com.gssirohi.app.domain.interactor.FetchProductInteractor;
import com.gssirohi.app.domain.interactor.FetchProductListInteractor;
import com.gssirohi.app.domain.interactor.UseCase;
import com.gssirohi.app.domain.repository.IAppRepository;
import com.gssirohi.app.domain.repository.api.APIRepository;
import com.gssirohi.app.domain.repository.cache.CacheRepository;
import com.gssirohi.app.domain.repository.database.DataBaseRepository;
import com.gssirohi.app.domain.repository.mock.MockRepository;
import com.gssirohi.app.executor.AsyncLoaderInteractorExecutor;
import com.gssirohi.app.executor.IInteractorExecutor;
import com.gssirohi.app.executor.IMainThreadExecutor;
import com.gssirohi.app.executor.InteractorAsyncLoader;
import com.gssirohi.app.executor.MainThreadExecutorImpl;
import com.gssirohi.app.executor.ThreadInteractorExecutor;

/**
 * Created by gssirohi on 25/8/16.
 */
public class Provider {

    private final Configuration config;
    private final MainThreadExecutorImpl mainThreadExecutor;
    private final ThreadInteractorExecutor threadInteractorExecutor;
    private final MockRepository mockRepository;
    private final APIRepository apiRepository;
    private final DataBaseRepository databaseRepository;
    private final CacheRepository cacheRepository;

    public Provider(Context context) {
        this.config = new Configuration(context);
        this.mainThreadExecutor = new MainThreadExecutorImpl();
        this.threadInteractorExecutor = new ThreadInteractorExecutor();
        this.mockRepository = new MockRepository();
        this.apiRepository = new APIRepository();
        this.cacheRepository = new CacheRepository();
        this.databaseRepository = null;
    }

    public Configuration getConfig() {
        return config;
    }

    public UseCase getUseCaseImpl(Context context, UseCases type) {
        UseCase useCase = null;
        switch (type) {
            case FETCH_PRODUCT_LIST:
                return new FetchProductListInteractor(context, getInteractorExecutor(context, InteractorExecutors.ASYNC_TASK)
                        , getMainThreadExecutor(), getAppRepository(Repositories.API));
            case FETCH_PRODUCT_DETAILS:
                return new FetchProductInteractor(context, getInteractorExecutor(context, InteractorExecutors.THREAD)
                        , getMainThreadExecutor(), getAppRepository(Repositories.API));
            case FETCH_PRODUCT_IMAGE:
                return new FetchImageInteractor(context, getInteractorExecutor(context, InteractorExecutors.THREAD)
                        , getMainThreadExecutor(), getAppRepository(Repositories.API));
        }
        return useCase;
    }

    public IAppRepository getAppRepository(Repositories type) {
        switch (type) {
            case MOCK:
                return this.mockRepository;
            case API:
                return this.apiRepository;
            case DATABASE:
                return this.databaseRepository;
            case CACHE:
                return this.cacheRepository;
        }
        return null;
    }

    private IMainThreadExecutor getMainThreadExecutor() {
        return mainThreadExecutor;
    }

    private IInteractorExecutor getInteractorExecutor(Context context, InteractorExecutors type) {
        switch (type) {
            case ASYNC_TASK:
                return new AsyncLoaderInteractorExecutor((AppCompatActivity) context, new InteractorAsyncLoader(context));
            case THREAD:
                return this.threadInteractorExecutor;

        }
        return null;
    }

}
