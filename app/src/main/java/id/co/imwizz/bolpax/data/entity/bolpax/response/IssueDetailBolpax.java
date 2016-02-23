package id.co.imwizz.bolpax.data.entity.bolpax.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bimosektiw on 2/15/16.
 */
public class IssueDetailBolpax {
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
    @SerializedName("subject")
    @Expose
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    private List<IssueHistoryBolpax> issueHistory;

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

    public List<IssueHistoryBolpax> getIssueHistory() {
        return issueHistory;
    }

    public void setIssueHistory(List<IssueHistoryBolpax> issueHistory) {
        this.issueHistory = issueHistory;
    }
}
