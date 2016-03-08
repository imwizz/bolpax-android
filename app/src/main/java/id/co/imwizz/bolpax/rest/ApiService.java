package id.co.imwizz.bolpax.rest;

import java.util.List;

import id.co.imwizz.bolpax.data.entity.bolpax.request.AddHistoryIssueRqs;
import id.co.imwizz.bolpax.data.entity.bolpax.request.AddHistoryTrxRqs;
import id.co.imwizz.bolpax.data.entity.bolpax.request.RefundRqs;
import id.co.imwizz.bolpax.data.entity.bolpax.response.PaymentRsp;
import id.co.imwizz.bolpax.data.entity.bolpax.request.PaymentRqs;

import id.co.imwizz.bolpax.data.entity.bolpax.request.BuyerIssueRqs;
import id.co.imwizz.bolpax.data.entity.bolpax.request.BuyerTransactionRqs;
import id.co.imwizz.bolpax.data.entity.bolpax.request.MerchantIssueRqs;
import id.co.imwizz.bolpax.data.entity.bolpax.request.MerchantTransactionRqs;
import id.co.imwizz.bolpax.data.entity.bolpax.response.RefundRsp;
import id.co.imwizz.bolpax.data.entity.bolpax.request.ReportRqs;
import id.co.imwizz.bolpax.data.entity.bolpax.request.StoreRqs;
import id.co.imwizz.bolpax.data.entity.bolpax.request.UserRqs;
import id.co.imwizz.bolpax.data.entity.bolpax.response.IssueDetailRsp;
import id.co.imwizz.bolpax.data.entity.bolpax.response.IssueIdRsp;
import id.co.imwizz.bolpax.data.entity.bolpax.response.LoginRsp;
import id.co.imwizz.bolpax.data.entity.bolpax.response.LogoutRsp;
import id.co.imwizz.bolpax.data.entity.bolpax.response.MerchantRsp;
import id.co.imwizz.bolpax.data.entity.bolpax.response.ProfileRsp;
import id.co.imwizz.bolpax.data.entity.bolpax.response.TransactionDetailRsp;
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
    public void getLogin(@Query("phone") String phone, @Query("pass") String pass, Callback<LoginRsp> callback);

    @Headers( "Content-Type: application/json" )
    @GET("/trx/listbybuyer")
    public void getBuyerTransactionlist(@Query("userid") String id, Callback<List<BuyerTransactionRqs>> callback);

    @Headers( "Content-Type: application/json" )
    @GET("/trx/listbymerchant")
    public void getMerchantTransactionlist(@Query("merchantid") String id, Callback<List<MerchantTransactionRqs>> callback);

    @Headers( "Content-Type: application/json" )
    @GET("/issue/listbyuser")
    public void getBuyerIssuelist(@Query("userid") String id, Callback<List<BuyerIssueRqs>> callback);

    @Headers( "Content-Type: application/json" )
    @GET("/issue/listbymerchant")
    public void getMerchantIssuelist(@Query("merchantid") String id, Callback<List<MerchantIssueRqs>> callback);

    @Headers( "Content-Type: application/json" )
    @GET("/profile/dologout")
    public void getLogout(@Query("token") String id,@Query("phone") String phone, Callback<LogoutRsp> callback);

    @Headers( "Content-Type: application/json" )
    @POST("/profile/createUser")
    public void getRegister(@Body UserRqs user, Callback<String> callback);

    @Headers( "Content-Type: application/json" )
    @GET("/profile/user")
    public void getProfile(@Query("userid") String userid, @Query("token") String token, Callback<ProfileRsp> callback);

    @Headers( "Content-Type: application/json" )
    @GET("/profile/merchant")
    public void getMerchantProfile(@Query("userid") String userid, @Query("token") String token, Callback<MerchantRsp> callback);

    @Headers( "Content-Type: application/json" )
    @GET("/merchant/list")
    public void getMerchantList(@Query("userid") String userid, Callback <List<MerchantRsp>> callback);

    @Headers( "Content-Type: application/json" )
    @GET("/trx/detail")
    public void getTransactionDetail(@Query("trxid") String trxid, @Query("role") String role, Callback <TransactionDetailRsp> callback);

    @Headers( "Content-Type: application/json" )
    @GET("/issue/detail")
    public void getIssueDetail(@Query("issueid") String issueid, Callback <IssueDetailRsp> callback);

    @Headers( "Content-Type: application/json" )
    @POST("/trx/payments")
    public void getBuyerPayment(@Body PaymentRqs payment, Callback<PaymentRsp> callback);


    @Headers( "Content-Type: application/json" )
    @POST("/profile/createMerchant")
    public void postStore(@Body StoreRqs store, Callback<String> callback);

    @Headers( "Content-Type: application/json" )
    @POST("/issue/create")
    public void postBuyerReport(@Body ReportRqs report, Callback<IssueIdRsp> callback);

    @Headers( "Content-Type: application/json" )
    @POST("/trx/insertTrail")
    public void postAddHistoryTransaction(@Body AddHistoryTrxRqs addHistoryTrxRqs, Callback<String> callback);

    @Headers( "Content-Type: application/json" )
    @POST("/issue/insertTrail")
    public void postAddHistoryIssue(@Body AddHistoryIssueRqs addHistoryIssueRqs, Callback<String> callback);

    @Headers( "Content-Type: application/json" )
    @POST("/trx/refund")
    public void postRefund(@Body RefundRqs refund, Callback<RefundRsp> callback);


}
