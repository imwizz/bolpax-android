package id.co.imwizz.bolpax.data.entity.bolpax.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author bimosektiw
 */
public class IssueHistoryRsp {
    @SerializedName("fromAdmin")
    @Expose
    private String fromAdmin;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("issueStatus")
    @Expose
    private String issueStatus;

    public String getIssueStatus() {
        return issueStatus;
    }

    public void setIssueStatus(String issueStatus) {
        this.issueStatus = issueStatus;
    }

    public String getFromAdmin() {
        return fromAdmin;
    }

    public void setFromAdmin(String fromAdmin) {
        this.fromAdmin = fromAdmin;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
