package com.gssirohi.app.domain.interactor;


import com.gssirohi.app.network.Request;
import com.gssirohi.app.network.ServiceRequest;

/**
 * Created by gssirohi on 11/7/16.
 */
public interface ServiceUseCase extends UseCase {
    ServiceRequest buildServiceRequest();

    Request buildRequest();
}
