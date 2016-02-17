package id.co.imwizz.bolpax.data.entity.bolpax.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by MDeveloper on 2/12/2016.
 */
public class MerchantTransactionListPojo {
    @SerializedName("trxId")
    @Expose
    private long trxId;
    @SerializedName("trxDate")
    @Expose
    private String trxDate;
    @SerializedName("trxLastStatus")
    @Expose
    private String trxLastStatus;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("merchant")
    @Expose
    private String merchant;
    @SerializedName("product")
    @Expose
    private String product;

    public long getTrxId() {
        return trxId;
    }

    public void setTrxId(long trxId) {
        this.trxId = trxId;
    }

    public String getTrxDate() {
        return trxDate;
    }

    public void setTrxDate(String trxDate) {
        this.trxDate = trxDate;
    }

    public String getTrxLastStatus() {
        return trxLastStatus;
    }

    public void setTrxLastStatus(String trxLastStatus) {
        this.trxLastStatus = trxLastStatus;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
