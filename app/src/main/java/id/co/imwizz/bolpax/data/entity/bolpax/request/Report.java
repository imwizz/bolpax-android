package id.co.imwizz.bolpax.data.entity.bolpax.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by MDeveloper on 2/16/2016.
 */
public class Report {
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("trxId")
    @Expose
    private long trxId;


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getTrxId() {
        return trxId;
    }

    public void setTrxId(long trxId) {
        this.trxId = trxId;
    }
}
