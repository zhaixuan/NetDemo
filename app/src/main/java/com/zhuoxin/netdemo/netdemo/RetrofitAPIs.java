package com.zhuoxin.netdemo.netdemo;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Dionysus on 2017/8/28.
 */

public interface RetrofitAPIs {
    //?q=language:java&page=1"
    @GET ("https://api.github.com/{search}/repositories")
    Call<ResponseBody> getRequest(@Path("search")String search, @Query("q")String lj, @Query("page")String page);

    @POST("http://admin.syfeicuiedu.com/Handler/UserHandler.ashx?action=register")
    Call<ResponseBody> postRequest(@Body RequestBody body);

    @FormUrlEncoded
    @POST("http://wx.feicuiedu.com:9094/yitao/UserWeb?method=register")
    Call<ResponseBody> formQuest(@Field("username")String username, @Field("password")String password);

    @Multipart
    @POST("http://wx.feicuiedu.com:9094/yitao/UserWeb?method=update")
        //Call<ResponseBody> nultiRequest(@Part("part")RequestBody body)
    Call<ResponseBody> nultiRequest(@Part MultipartBody.Part part);
}
