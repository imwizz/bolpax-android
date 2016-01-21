package id.co.imwizz.bolpax.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by User on 11/01/2016.
 */
public class TransactionDetail {

    @SerializedName("merchant")
    @Expose
    private String merchant;

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getTrxLastStatus() {
        return trxLastStatus;
    }

    public void setTrxLastStatus(String trxLastStatus) {
        this.trxLastStatus = trxLastStatus;
    }


    @SerializedName("amount")
    @Expose

    private String amount;
    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("trxLastStatus")
    @Expose
    private String trxLastStatus;

    public List<TrxHistory> getTrxHistory() {
        return trxHistory;
    }

    public void setTrxHistory(List<TrxHistory> trxHistory) {
        this.trxHistory = trxHistory;
    }

    private List<TrxHistory> trxHistory;

}
