package com.gssirohi.app.base;


import com.gssirohi.app.ui.viewcontract.ViewContract;

public interface IBasePresenter<T extends ViewContract> {

    void initialize();

    void onViewCreate();

    void onViewResume();

    void onViewDestroy();

    void setView(T view);

    void cancelCurrentRequest();
}

