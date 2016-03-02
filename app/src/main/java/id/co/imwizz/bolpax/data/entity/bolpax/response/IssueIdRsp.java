package id.co.imwizz.bolpax.data.entity.bolpax.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bimosektiw on 2/24/16.
 */
public class IssueIdRsp {
    @SerializedName("issueId")
    @Expose
    private Long issueId;

    public Long getIssueId() {
        return issueId;
    }

    public void setIssueId(Long issueId) {
        this.issueId = issueId;
    }
}
