package id.co.imwizz.bolpax.data.entity.bolpax.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Duway
 */
public class RefundRqs {
    @SerializedName("issueId")
    @Expose
    private long issueId;

    public long getIssueId() {
        return issueId;
    }

    public void setIssueId(long issueId) {
        this.issueId = issueId;
    }
}
