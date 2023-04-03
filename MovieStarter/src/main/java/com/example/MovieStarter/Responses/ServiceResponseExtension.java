package com.example.MovieStarter.Responses;

import java.util.function.Function;
import java.util.function.Supplier;

public class ServiceResponseExtension {

    public static <TIn, TOut> ServiceResponseT<TOut> map(ServiceResponseT<TIn> response, Function<TIn, TOut> selector) {
        if (response.getResult() != null) {
            TOut result = selector.apply(response.getResult());
            return ServiceResponseT.forSuccess(result);
        } else {
            return ServiceResponseT.createError(response.getError());
        }
    }

    public static <TIn, TOut> ServiceResponseT<TOut> flatMap(ServiceResponseT<TIn> response, Function<TIn, ServiceResponseT<TOut>> selector) {
        if (response.getResult() != null) {
            return selector.apply(response.getResult());
        } else {
            return ServiceResponseT.createError(response.getError());
        }
    }

    public static <TOut> ServiceResponseT<TOut> flatMap(ServiceResponse response, Supplier<ServiceResponseT<TOut>> selector) {
        if (response.getError() == null) {
            return selector.get();
        } else {
            return ServiceResponseT.createError(response.getError());
        }
    }

    public static <TIn> ServiceResponseT<TIn> flattenIN(ServiceResponseT<ServiceResponseT<TIn>> response) {
        if (response.getResult() != null) {
            return response.getResult();
        } else {
            return ServiceResponseT.createError(response.getError());
        }
    }

    public static ServiceResponse flatten(ServiceResponseT<ServiceResponse> response) {
        if (response.getResult() != null) {
            return response.getResult();
        } else {
            return ServiceResponse.fromError(response.getError());
        }
    }
}
