package id.co.imwizz.bolpax.data.entity.mandiri;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bimosektiw on 2/4/16.
 */
public class LoginMandiri {
    @SerializedName("msisdn")
    @Expose
    private String msisdn;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("token")
    @Expose
    private String token;

    @Override
    public String toString() {
        return "LoginMandiri{" +
                "msisdn='" + msisdn + '\'' +
                ", status='" + status + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
