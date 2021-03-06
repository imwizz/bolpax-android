package id.co.imwizz.bolpax.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.adapter.MerchantAdapter;
import id.co.imwizz.bolpax.data.BolpaxStatic;
import id.co.imwizz.bolpax.data.entity.bolpax.response.MerchantRsp;
import id.co.imwizz.bolpax.data.entity.bolpax.response.LogoutRsp;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * This activity is used to display Merchant List.
 *
 * @author Duway
 */
public class MerchantListActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MerchantListActivity.class.getSimpleName();
    protected Context mContext;
    MenuItem createstore,switchtomerchant,buyername;
    String email,name,phone,userid,token,nama;
    Integer balance;
    MerchantRsp merchants;
    Long bolpax,merchantid;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.text_toolbar_title) TextView toolbarTitle;
    @Bind(R.id.list_merchant) ListView listMerchant;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_list);
        ButterKnife.bind(this);
//        toolbar.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
        toolbar.setTitle("");
        toolbarTitle.setText("BOLPAX");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MerchantListActivity.this, BuyerHomeActivity.class);
                startActivity(i);
            }
        });

        bolpax = BolpaxStatic.getUserid();
        userid = bolpax.toString();
        token = BolpaxStatic.getToken();
        phone = BolpaxStatic.getPhonenumber();
        nama = BolpaxStatic.getFullname();
        merchantid = BolpaxStatic.getMerchantid();
        RestClient.getBolpax().getMerchantList(userid.toString(), new Callback<List<MerchantRsp>>() {
            @Override
            public void success(List<MerchantRsp> merchantRsps, Response response) {
                List<MerchantRsp> merchantBolpaxlist = new ArrayList<MerchantRsp>(merchantRsps);
                ListAdapter merchantlistAdapter = new MerchantAdapter(MerchantListActivity.this, merchantBolpaxlist);
                listMerchant.setAdapter(merchantlistAdapter);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
            }
        });


    }
    @OnItemClick(R.id.list_merchant)
    void onItemClick(AdapterView<?> parent, View view,
                     int position, long id) {
        merchants = (MerchantRsp) parent.getItemAtPosition(position);
        Intent myIntent = new Intent(MerchantListActivity.this, BuyerPaymentActivity.class);
        myIntent.putExtra("merchantId", (merchants.getMerchantId()));
        myIntent.putExtra("merchantName", (merchants.getMerchantName()));
        startActivity(myIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        createstore = menu.findItem(R.id.create_store);
        switchtomerchant = menu.findItem(R.id.switchto_merchant);
        buyername = menu.findItem(R.id.profile);
        buyername.setTitle(nama.toString());
        if ( merchantid !=null){
            createstore.setVisible(false);
        } else {
            switchtomerchant.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.profile:
                Intent i = new Intent(MerchantListActivity.this, BuyerProfileActivity.class);
                startActivity(i);

                return true;

            case R.id.create_store:
                Intent i2 = new Intent(MerchantListActivity.this, CreateStoreActivity.class);
                startActivity(i2);

                return true;
            case R.id.switchto_merchant:
                Intent i3 = new Intent(MerchantListActivity.this, MerchantHomeActivity.class);
                startActivity(i3);

                return true;

            case R.id.quit:
                RestClient.getBolpax().getLogout(token,phone,new Callback<LogoutRsp>() {
                    @Override
                    public void success(LogoutRsp s, Response response) {

                        String success = s.getStatus();
                        if(success.contains("SUCCESS")) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("EXIT", true);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MerchantListActivity.this, "Failed Check your Network", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getMessage());

                    }
                });



                return true;

            case R.id.action_settings:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {



        }
    }
}
