package com.joe.mongo20;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface restApi {
    @GET("posts")
    Call <List<post>> getPosts();

}
