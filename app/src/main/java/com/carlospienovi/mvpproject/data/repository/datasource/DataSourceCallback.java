package com.carlospienovi.mvpproject.data.repository.datasource;

public interface DataSourceCallback<T> {

    void onResponse(T response);

    void onFailure(Throwable error);

}
