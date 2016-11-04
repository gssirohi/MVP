package com.gssirohi.app.ui.viewmodel.contract;

import com.gssirohi.app.domain.model.Model;

/**
 * Created by gssirohi on 7/7/16.
 */
public interface IViewModel {

    public <T extends Model> T getModel();


}
