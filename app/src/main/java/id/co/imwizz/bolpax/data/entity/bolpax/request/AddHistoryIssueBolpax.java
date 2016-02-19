package id.co.imwizz.bolpax.data.entity.bolpax.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bimosektiw on 2/19/16.
 */
public class AddHistoryIssueBolpax {
    @SerializedName("fromAdmin")
    @Expose
    private String fromAdmin;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("issueId")
    @Expose
    private Long issueId;
    @SerializedName("issueStatusId")
    @Expose
    private Long issueStatusId;

    public String getFromAdmin() {
        return fromAdmin;
    }

    public void setFromAdmin(String fromAdmin) {
        this.fromAdmin = fromAdmin;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }

    public Long getIssueStatusId() {
        return issueStatusId;
    }

    public void setIssueStatusId(Long issueStatusId) {
        this.issueStatusId = issueStatusId;
    }
}
