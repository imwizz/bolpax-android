package id.co.imwizz.bolpax.data;

/**
 * Created by bimosektiw on 2/11/16.
 */
public class BolpaxStatic {
    static Long userid, merchantid;
    static String token,merchantname;

    public static String getMerchantname() {
        return merchantname;
    }

    public static void setMerchantname(String merchantname) {
        BolpaxStatic.merchantname = merchantname;
    }

    public static Long getUserid() {
        return userid;
    }

    public static void setUserid(Long userid) {
        BolpaxStatic.userid = userid;
    }

    public static Long getMerchantid() {
        return merchantid;
    }

    public static void setMerchantid(Long merchantid) {
        BolpaxStatic.merchantid = merchantid;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        BolpaxStatic.token = token;
    }
}
