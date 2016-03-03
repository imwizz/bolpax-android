package id.co.imwizz.bolpax.data.entity.bolpax.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bimosektiw on 2/19/16.
 */
public class AddHistoryTrxRqs {
    @SerializedName("trxId")
    @Expose
    private String trxId;
    @SerializedName("trxStatusMapping")
    @Expose
    private List<IdRqs> trxStatusMapping;

    public String getTrxId() {
        return trxId;
    }

    public void setTrxId(String trxId) {
        this.trxId = trxId;
    }

    public List<IdRqs> getTrxStatusMapping() {
        return trxStatusMapping;
    }

    public void setTrxStatusMapping(List<IdRqs> trxStatusMapping) {
        this.trxStatusMapping = trxStatusMapping;
    }
}
