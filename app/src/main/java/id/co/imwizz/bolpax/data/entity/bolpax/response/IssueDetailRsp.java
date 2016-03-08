package id.co.imwizz.bolpax.data.entity.bolpax.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author bimosektiw
 */
public class IssueDetailRsp {
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
    @SerializedName("reporter")
    @Expose
    private String reporter;

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    private List<IssueHistoryRsp> issueHistory;

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

    public List<IssueHistoryRsp> getIssueHistory() {
        return issueHistory;
    }

    public void setIssueHistory(List<IssueHistoryRsp> issueHistory) {
        this.issueHistory = issueHistory;
    }
}
