package com.test.developer.data.model.response;

import java.util.List;

public abstract class BaseResponse<T> {
    public abstract List<T> getData();
}
