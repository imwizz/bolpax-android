package id.co.imwizz.bolpax.data.entity.bolpax.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import id.co.imwizz.bolpax.data.entity.bolpax.response.TransactionHistoryBolpax;

/**
 * Created by bimosektiw on 2/15/16.
 */
public class TransactionDetailBolpax {
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("merchant")
    @Expose
    private String merchant;
    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("trxLastStatus")
    @Expose
    private String trxLastStatus;
    private List<TransactionHistoryBolpax> trxHistory;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
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

    public String getTrxLastStatus() {
        return trxLastStatus;
    }

    public void setTrxLastStatus(String trxLastStatus) {
        this.trxLastStatus = trxLastStatus;
    }

    public List<TransactionHistoryBolpax> getTrxHistory() {
        return trxHistory;
    }

    public void setTrxHistory(List<TransactionHistoryBolpax> trxHistory) {
        this.trxHistory = trxHistory;
    }
}
