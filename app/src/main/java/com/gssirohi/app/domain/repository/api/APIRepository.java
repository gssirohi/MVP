package com.gssirohi.app.domain.repository.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.gssirohi.app.constant.AppAPIMethods;
import com.gssirohi.app.constant.AppConstants;
import com.gssirohi.app.constant.AppErrors;
import com.gssirohi.app.constant.Products;
import com.gssirohi.app.domain.exception.AppRepositoryException;
import com.gssirohi.app.domain.model.ProductModel;
import com.gssirohi.app.domain.repository.IAppRepository;
import com.gssirohi.app.executor.BaseInteractor;
import com.gssirohi.app.network.AppRestClient;
import com.gssirohi.app.network.Request;
import com.gssirohi.app.network.RequestMethod;
import com.gssirohi.app.network.ResponseContainer;
import com.gssirohi.app.network.ServiceRequest;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gssirohi on 5/7/16.
 */
public class APIRepository implements IAppRepository {
    private final APIResponseMapper responseMapper;
    private AppRestClient restClient;

    public APIRepository() {
        responseMapper = new APIResponseMapper();
        //TODO: modify constructor API repository constructor
    }

    @Override
    public List<ProductModel> getProductList(BaseInteractor interactor, int offset, int limit, Products type) {

        ResponseContainer container = executeRestClient(buildProductListServiceRequest(interactor.getContext(), offset, limit, type));
        if (container != null) {
            List<ProductModel> products = responseMapper.getProductListFromResponse(container, type);
            if (products != null) {
                return products;
            } else {
                throw new AppRepositoryException(AppErrors.PRODUCT_LIST_NULL);
            }
        } else {
            throw new AppRepositoryException(AppErrors.RESPONSE_NULL);
        }

    }

    @Override
    public ProductModel getProductById(BaseInteractor interactor, String id, Products type) {

        ResponseContainer container = executeRestClient(buildProductServiceRequest(interactor.getContext(), id, type));
        if (container != null) {
            ProductModel product = responseMapper.getProductFromResponse(container, type);
            if (product != null) {
                return product;
            } else {
                throw new AppRepositoryException(AppErrors.PRODUCT_LIST_NULL);
            }
        } else {
            throw new AppRepositoryException(AppErrors.RESPONSE_NULL);
        }

    }

    @Override
    public Bitmap getImageFromUrl(BaseInteractor interactor, String imageUrl) {
        Bitmap bimage = null;
        try {
            InputStream in = new java.net.URL(imageUrl).openStream();
            bimage = BitmapFactory.decodeStream(in);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bimage;
    }


    private ResponseContainer executeRestClient(ServiceRequest serviceRequest) {
        ResponseContainer container = null;
        try {
            restClient = new AppRestClient(serviceRequest);
            container = restClient.execute();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return container;
        }
    }


    private ServiceRequest buildProductServiceRequest(Context context, String id, Products type) {
        Request request = buildRequest();
        request.getRequestParams().put("id", id);
        ServiceRequest serviceRequest = new ServiceRequest(request, context);
        serviceRequest.setConnectionTimeout(AppConstants.MAX_CONNECTION_TIMEOUT);
        serviceRequest.setMethod(AppAPIMethods.getProductRequestMethod(type));
        serviceRequest.setSecureConnectionRequest(false);
        serviceRequest.setPath("");
        serviceRequest.setResponsibleClass(AppAPIMethods.getProductClass(type));
        serviceRequest.setRequestCode(null);
        return serviceRequest;
    }


    public ServiceRequest buildProductListServiceRequest(Context context, int offset, int limit, Products productType) {
        Request request = buildRequest();
        request.getRequestParams().put("offset", "" + offset);
        request.getRequestParams().put("limit", "" + limit);
        ServiceRequest serviceRequest = new ServiceRequest(request, context);
        serviceRequest.setConnectionTimeout(AppConstants.MAX_CONNECTION_TIMEOUT);
        serviceRequest.setMethod(AppAPIMethods.getProductRequestMethod(productType));
        serviceRequest.setSecureConnectionRequest(false);
        serviceRequest.setPath("");
        serviceRequest.setResponsibleClass(AppAPIMethods.getProductClass(productType));
        serviceRequest.setRequestCode(null);
        return serviceRequest;
    }

    public Request buildRequest() {
        String authValue = AppConstants.MARVEL_PUBLIC_KEY;
        String md5hash = AppConstants.MARVEL_MD5_HASH;
        String timeStamp = AppConstants.MARVEL_TS;
        Request request = new Request();
        request.setRequestMethod(RequestMethod.GET);
        HashMap<String, String> requestParams = new HashMap<String, String>();
        requestParams.put("apikey", authValue);
        requestParams.put("hash", md5hash);
        requestParams.put("ts", timeStamp);
        request.setRequestParams(requestParams);

        return request;
    }
}
