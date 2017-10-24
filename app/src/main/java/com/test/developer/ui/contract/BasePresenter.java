package com.test.developer.ui.contract;

public interface BasePresenter<T> {
    void start();

    void setView(T view);
}
