package id.co.imwizz.bolpax.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bimosektiw on 1/14/16.
 */
public class IssueDetail {
    @SerializedName("suspect")
    @Expose
    private String suspect;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("issueLastStatus")
    @Expose
    private String issueLastStatus;
    private List<IssueHistory> issueHistory;

    public String getSuspect() {
        return suspect;
    }

    public void setSuspect(String suspect) {
        this.suspect = suspect;
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

    public String getIssueLastStatus() {
        return issueLastStatus;
    }

    public void setIssueLastStatus(String issueLastStatus) {
        this.issueLastStatus = issueLastStatus;
    }

    public List<IssueHistory> getIssueHistory() {
        return issueHistory;
    }

    public void setIssueHistory(List<IssueHistory> issueHistory) {
        this.issueHistory = issueHistory;
    }
}