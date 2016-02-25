package id.co.imwizz.bolpax.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by MDeveloper on 2/25/2016.
 */
public class Refund {
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
