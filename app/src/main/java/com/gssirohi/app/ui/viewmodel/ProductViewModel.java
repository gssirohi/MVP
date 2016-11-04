package com.gssirohi.app.ui.viewmodel;


import com.gssirohi.app.domain.model.SummaryModel;
import com.gssirohi.app.ui.viewmodel.contract.IProductViewModel;

/**
 * Created by gssirohi on 7/7/16.
 */

public class ProductViewModel implements IProductViewModel {


    private SummaryModel summaryModel;

    public ProductViewModel(SummaryModel summaryModel) {
        this.summaryModel = summaryModel;
    }

    @Override
    public String getName() {
        return this.summaryModel.getName();
    }

    @Override
    public String getResourceUri() {
        return summaryModel.getResourceURI();
    }


    @Override
    public SummaryModel getModel() {
        return summaryModel;
    }
}
