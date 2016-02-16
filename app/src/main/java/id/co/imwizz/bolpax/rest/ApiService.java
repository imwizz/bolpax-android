package id.co.imwizz.bolpax.rest;

import java.util.List;

import id.co.imwizz.bolpax.data.entity.bolpax.request.Payment;
import id.co.imwizz.bolpax.data.entity.bolpax.request.User;
import id.co.imwizz.bolpax.data.entity.bolpax.response.IssueDetailBolpax;
import id.co.imwizz.bolpax.data.entity.bolpax.response.LoginBolpax;
import id.co.imwizz.bolpax.data.entity.bolpax.response.MerchantBolpax;
import id.co.imwizz.bolpax.data.entity.bolpax.response.ProfileBolpax;
import id.co.imwizz.bolpax.data.entity.bolpax.response.TransactionDetailBolpax;
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
    @GET("/profile/dologin")
    public void getLogin(@Query("phone") String phone, @Query("pass") String pass, Callback<LoginBolpax> callback);

    @Headers( "Content-Type: application/json" )
    @POST("/profile/createUser")
    public void getRegister(@Body User user, Callback<String> callback);

    @Headers( "Content-Type: application/json" )
    @GET("/profile/user")
    public void getProfile(@Query("userid") String userid, @Query("token") String token, Callback<ProfileBolpax> callback);

    @Headers( "Content-Type: application/json" )
    @GET("/profile/merchant")
    public void getMerchantProfile(@Query("userid") String userid, @Query("token") String token, Callback<MerchantBolpax> callback);

    @Headers( "Content-Type: application/json" )
    @GET("/merchant/list")
    public void getMerchantList(@Query("userid") String userid, Callback <List<MerchantBolpax>> callback);

    @Headers( "Content-Type: application/json" )
    @GET("/trx/detail")
    public void getTransactionDetail(@Query("trxid") String trxid, @Query("role") String role, Callback <TransactionDetailBolpax> callback);

    @Headers( "Content-Type: application/json" )
    @GET("/issue/detail")
    public void getIssueDetail(@Query("issueid") String issueid, Callback <IssueDetailBolpax> callback);

    @Headers( "Content-Type: application/json" )
    @POST("/trx/payment")
    public void getBuyerPayment(@Body Payment payment, Callback<String> callback);



}
