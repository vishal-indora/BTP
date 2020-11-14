package com.example.ntpc;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PowerPlantData {

    @GET("PowerPlantData")
    Call<PowerPlantDataPojo> getPOJOs();
}
