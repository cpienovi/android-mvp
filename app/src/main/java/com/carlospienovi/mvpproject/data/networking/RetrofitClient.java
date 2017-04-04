package com.carlospienovi.mvpproject.data.networking;

import com.carlospienovi.mvpproject.MVPProjectApp;
import com.carlospienovi.mvpproject.utils.NetworkUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitClient {

    private static final long CACHE_SIZE = 10 * 1024 * 1024; // 10 MB
    private static final String CACHE_NAME = "apiResponses";

    private static final int RESPONSE_MAX_AGE_MINUTES = 2;

    private static final String HEADER_PRAGMA = "Pragma";
    private static final String HEADER_CACHE_CONTROL = "Cache-Control";

    private static Retrofit provideRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(provideOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(new ResponseCacheInterceptor())
                .addInterceptor(new OfflineResponseCacheInterceptor())
                .cache(provideCache())
                .build();
    }

    private static Cache provideCache() {
        Cache cache = null;
        try {
            cache = new Cache(new File(MVPProjectApp.getInstance().getCacheDir(), CACHE_NAME), CACHE_SIZE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cache;
    }


    private static class ResponseCacheInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Response originalResponse = chain.proceed(chain.request());

            CacheControl cacheControl = new CacheControl.Builder()
                    .maxAge(RESPONSE_MAX_AGE_MINUTES, TimeUnit.MINUTES)
                    .build();

            return originalResponse.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build();
        }
    }

    private static class OfflineResponseCacheInterceptor implements Interceptor {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            if (!NetworkUtils.hasNetwork()) {
                request = request.newBuilder()
                        .header(HEADER_CACHE_CONTROL, CacheControl.FORCE_CACHE.toString())
                        .build();
            }

            return chain.proceed(request);
        }
    }

    static PostService providePostService() {
        return provideRetrofit(PostService.BASE_URL).create(PostService.class);
    }

}
