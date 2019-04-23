package io.github.rahmatsyam.retrofit2test;

import io.github.rahmatsyam.retrofit2test.data.remote.RetrofitClient;
import io.github.rahmatsyam.retrofit2test.data.remote.SOService;

public class ApiUtils {

    public static final String BASE_URL = "https://api.stackexchange.com/2.2";

    public static SOService getSOService() {
        return RetrofitClient.getClient().create(SOService.class);
    }
}
