package id.co.imwizz.bolpax.data.entity.bolpax.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bimosektiw on 2/15/16.
 */
public class PaymentRqs {
    @SerializedName("userId")
    @Expose
    private Long userId;
    @SerializedName("merchantId")
    @Expose
    private Long merchantId;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("token")
    @Expose
    private String token;
    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
