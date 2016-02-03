package id.co.imwizz.bolpax.data.entity;

/**
 * Created by MDeveloper on 1/27/2016.
 */
public class TransactionLIst {
    String trxDate;
    String trxLastStatus;
    String amount;

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    String merchant;
}
