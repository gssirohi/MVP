package com.gssirohi.app.ui.viewmodel;


import com.gssirohi.app.constant.Products;
import com.gssirohi.app.domain.model.SummaryListContainer;
import com.gssirohi.app.domain.model.SummaryModel;
import com.gssirohi.app.ui.viewmodel.contract.IProductCardViewModel;
import com.gssirohi.app.ui.viewmodel.contract.IProductViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gssirohi on 7/7/16.
 */

public class ProductCardViewModel implements IProductCardViewModel {


    private final Products type;
    private SummaryListContainer summaryListContainer;

    public ProductCardViewModel(SummaryListContainer summaryListContainer, Products type) {
        this.summaryListContainer = summaryListContainer;
        this.type = type;
    }

    @Override
    public SummaryListContainer getModel() {
        return summaryListContainer;
    }

    @Override
    public String getProductCardLable() {
        return type.getLable();
    }

    @Override
    public int getAvailabe() {
        return summaryListContainer.getAvailable();
    }

    @Override
    public int getShowing() {
        return summaryListContainer.getReturned();
    }

    @Override
    public List<IProductViewModel> getProductViewModelList() {
        List<SummaryModel> summaryList = summaryListContainer.getItems();
        ArrayList<IProductViewModel> products = new ArrayList<>();
        for (SummaryModel model : summaryList) {
            if (model != null) {
                products.add(new ProductViewModel(model));
            }
        }
        return products;
    }
}
