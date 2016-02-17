package id.co.imwizz.bolpax.data.entity.bolpax.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import id.co.imwizz.bolpax.data.entity.bolpax.request.User;

/**
 * Created by bimosektiw on 2/12/16.
 */
public class MerchantBolpax {
    @SerializedName("merchantId")
    @Expose
    private Long merchantId;
    @SerializedName("merchantName")
    @Expose
    private String merchantName;
    @SerializedName("profileBolpax")
    @Expose
    private ProfileBolpax profileBolpax;

    public ProfileBolpax getProfileBolpax() {
        return profileBolpax;
    }

    public void setProfileBolpax(ProfileBolpax profileBolpax) {
        this.profileBolpax = profileBolpax;
    }

    public Long getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

}
