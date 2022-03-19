package com.evanemran.gorest;

public interface UserResponseListener {
    void didFetch(UserResponse response, String message);
    void didError(String message);
}
