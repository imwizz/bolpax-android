package id.co.imwizz.bolpax.rest;


import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
/**
 * Created by bimosektiw on 2/3/16.
 */
public class RestClient {

    private static ApiService REST_CLIENT_BOLPAX;
    private static String ROOT_BOLPAX = "http://bolpax.mybluemix.net/";
    private static ApiService REST_CLIENT_MANDIRI;
    private static String ROOT_MANDIRI = "https://api.apim.ibmcloud.com/ex-icha-fmeirisidibmcom-ecash-be/sb/emoney/v1";

    static {
        setupRestClient();
    }

    public RestClient() {}

    public static ApiService getBolpax() {
        return REST_CLIENT_BOLPAX;
    }

    public static ApiService getMandiri() {
        return REST_CLIENT_MANDIRI;
    }

    private static void setupRestClient() {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(ROOT_BOLPAX).setClient(new OkClient(new OkHttpClient())).setLogLevel(RestAdapter.LogLevel.FULL);

        RestAdapter restAdapter = builder.build();
        REST_CLIENT_BOLPAX = restAdapter.create(ApiService.class);

        RestAdapter.Builder builderMandiri = new RestAdapter.Builder()
                .setEndpoint(ROOT_MANDIRI).setClient(new OkClient(new OkHttpClient())).setLogLevel(RestAdapter.LogLevel.NONE);

        RestAdapter restAdapterMandiri = builderMandiri.build();
        REST_CLIENT_MANDIRI = restAdapterMandiri.create(ApiService.class);

    }
}
