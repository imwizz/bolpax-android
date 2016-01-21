package id.co.imwizz.bolpax.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by User on 11/01/2016.
 */
public class TrxHistory {
    @SerializedName("time")
    @Expose
    private String time;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @SerializedName("status")
    @Expose
    private String status;
}
