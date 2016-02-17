package id.co.imwizz.bolpax.rest;

import java.util.List;

import id.co.imwizz.bolpax.data.entity.bolpax.request.BuyerIssueListPojo;
import id.co.imwizz.bolpax.data.entity.bolpax.request.BuyerTransactionListPojo;
import id.co.imwizz.bolpax.data.entity.bolpax.request.MerchantIssueListPojo;
import id.co.imwizz.bolpax.data.entity.bolpax.request.MerchantTransactionListPojo;
import id.co.imwizz.bolpax.data.entity.bolpax.request.Report;
import id.co.imwizz.bolpax.data.entity.bolpax.request.Store;
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
    @GET("/trx/listbybuyer")
    public void getBuyerTransactionlist(@Query("userid") String id, Callback<List<BuyerTransactionListPojo>> callback);

    @Headers( "Content-Type: application/json" )
    @GET("/trx/listbymerchant")
    public void getMerchantTransactionlist(@Query("merchantid") String id, Callback<List<MerchantTransactionListPojo>> callback);

    @Headers( "Content-Type: application/json" )
    @GET("/issue/listbyuser")
    public void getBuyerIssuelist(@Query("userid") String id, Callback<List<BuyerIssueListPojo>> callback);

    @Headers( "Content-Type: application/json" )
    @GET("/issue/listbymerchant")
    public void getMerchantIssuelist(@Query("merchantid") String id, Callback<List<MerchantIssueListPojo>> callback);

    @Headers( "Content-Type: application/json" )
    @POST("/profile/createUser")
    public void getRegister(@Body User user, Callback<String> callback);

    @Headers( "Content-Type: application/json" )
    @POST("/profile/createMerchant")
    public void postStore(@Body Store store, Callback<String> callback);

    @Headers( "Content-Type: application/json" )
    @POST("/issue/create")
    public void postBuyerReport(@Body Report report, Callback<String> callback);


}
