package com.werpindia.internnigeria.models;

public class FirebaseResponse {
    private Object data;
    private Exception error;

    public FirebaseResponse(Object data, Exception error) {
        this.data = data;
        this.error = error;
    }

    public boolean hasError() {
        return error == null;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Exception getError() {
        return error;
    }

    public void setError(Exception error) {
        this.error = error;
    }
}
