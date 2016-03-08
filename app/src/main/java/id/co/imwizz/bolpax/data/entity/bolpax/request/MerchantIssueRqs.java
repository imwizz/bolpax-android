package id.co.imwizz.bolpax.data.entity.bolpax.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Duway
 */
public class MerchantIssueRqs {
    @SerializedName("issueId")
    @Expose
    private long issueId;
    @SerializedName("suspect")
    @Expose
    private String suspect;
    @SerializedName("issueDate")
    @Expose
    private String issueDate;
    @SerializedName("issueLastStatus")
    @Expose
    private String issueLastStatus;
    @SerializedName("amount")
    @Expose
    private Double amount;

    public long getIssueId() {
        return issueId;
    }

    public void setIssueId(long issueId) {
        this.issueId = issueId;
    }

    public String getSuspect() {
        return suspect;
    }

    public void setSuspect(String suspect) {
        this.suspect = suspect;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueLastStatus() {
        return issueLastStatus;
    }

    public void setIssueLastStatus(String issueLastStatus) {
        this.issueLastStatus = issueLastStatus;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
