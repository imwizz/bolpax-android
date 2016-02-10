package id.co.imwizz.bolpax.rest;

import id.co.imwizz.bolpax.data.entity.bolpax.request.User;
import id.co.imwizz.bolpax.data.entity.mandiri.LoginMandiri;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by bimosektiw on 2/3/16.
 */
public interface ApiService {
    @Headers( "Content-Type: application/json" )
    @GET("/loginMember")
    public void getLogin(@Query("uid") String uid, @Query("msisdn") String msisdn, @Query("credentials") String credentials, Callback<LoginMandiri> callback);

    @Headers( "Content-Type: application/json" )
    @POST("/profile/createUser")
    public void getRegister(@Body User user, Callback<String> callback);


}
