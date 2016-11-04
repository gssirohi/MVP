package com.gssirohi.app.ui.presenter;

import com.gssirohi.app.base.IBasePresenter;
import com.gssirohi.app.ui.viewcontract.CharactersScreenViewContract;

/**
 * Created by gssirohi on 5/7/16.
 */
public interface ICharactersScreenPresenter extends IBasePresenter<CharactersScreenViewContract> {

    void onFetchAllTripRequest(boolean showLoader);

}
