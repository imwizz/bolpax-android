package id.co.imwizz.bolpax.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by MDeveloper on 2/18/2016.
 */
public class History {
    @SerializedName("trxId")
    @Expose
    private Long trxId;
    @SerializedName("trxStatusMapping")
    @Expose
    private String trxStatusMapping;

    public Long getTrxId() {
        return trxId;
    }

    public void setTrxId(Long trxId) {
        this.trxId = trxId;
    }

    public String getTrxStatusMapping() {
        return trxStatusMapping;
    }

    public void setTrxStatusMapping(String trxStatusMapping) {
        this.trxStatusMapping = trxStatusMapping;
    }
}
