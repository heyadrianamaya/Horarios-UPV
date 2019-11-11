package com.upv.utils;

public interface Parametized<T> extends ChangeValues {
    void setParameter(T value);
    T getParameter();
}
