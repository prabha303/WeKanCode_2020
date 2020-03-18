package com.prabha.wekan.network;


public interface NetworkCallback {
    void onSuccess(Object response);
    void onError(Throwable error);
}
