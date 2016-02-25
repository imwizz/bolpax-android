package id.co.imwizz.bolpax.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.BolpaxStatic;
import id.co.imwizz.bolpax.data.entity.bolpax.response.MerchantBolpax;
import id.co.imwizz.bolpax.rest.Logout;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by User on 08/01/2016.
 */
public class MerchantHomeActivity extends AppCompatActivity implements View.OnClickListener{
    String email,name,phone,token,nama;
    private static final String TAG = MerchantHomeActivity.class.getSimpleName();
    Integer balance;
//    Long userid;
    MenuItem createstore,switchtomerchant,buyername;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    @Bind(R.id.transaction) LinearLayout transaction;
    @Bind(R.id.issue) LinearLayout issue;
    Long bolpax,merchantId,userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_home);
        ButterKnife.bind(this);
        setToolbar();
        userid = BolpaxStatic.getUserid();
        token = BolpaxStatic.getToken();
        phone = BolpaxStatic.getPhonenumber();

//        bolpax = BolpaxStatic.getUserid();
//        userid = bolpax.toString();
//        token = BolpaxStatic.getToken();

        RestClient.getBolpax().getMerchantProfile(userid.toString(), token.toString(), new Callback<MerchantBolpax>() {
            @Override
            public void success(MerchantBolpax merchantBolpax, Response response) {
                BolpaxStatic.setMerchantid(merchantBolpax.getMerchantId());
                BolpaxStatic.setMerchantname(merchantBolpax.getMerchantName());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
            }
        });

        transaction.setOnClickListener(this);
        issue.setOnClickListener(this);
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
        toolbar.setNavigationOnClickListener(this);
        toolbar.setTitle("");
        toolbarTitle.setText("BOLPAX");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.transaction:
                Intent i = new Intent(MerchantHomeActivity.this, MerchantTransactionList.class);
                startActivity(i);
                break;
            case R.id.issue:
                Intent myIntent = new Intent(MerchantHomeActivity.this, MerchantIssueList.class);
                startActivity(myIntent);
                break;
            case R.id.toolbar:
                Toast.makeText(MerchantHomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        menu.getItem(0).setIcon(R.drawable.ic_account);
        createstore = menu.findItem(R.id.create_store);
        switchtomerchant = menu.findItem(R.id.switchto_merchant);
        buyername = menu.findItem(R.id.profile);

        RestClient.getBolpax().getMerchantProfile(userid.toString(), token.toString(), new Callback<MerchantBolpax>() {
            @Override
            public void success(MerchantBolpax merchantBolpax, Response response) {
                nama = merchantBolpax.getMerchantName();
                buyername.setTitle(nama.toString());
                createstore.setVisible(false);
                switchtomerchant.setTitle("Switch To Buyer");
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.profile:
                Intent i = new Intent(MerchantHomeActivity.this, MerchantProfile.class);
                startActivity(i);

                return true;
            case R.id.switchto_merchant:
                Intent i3 = new Intent(MerchantHomeActivity.this, BuyerHomeActivity.class);
                startActivity(i3);

                return true;

            case R.id.quit:
                RestClient.getBolpax().getLogout(token, phone, new Callback<Logout>() {
                    @Override
                    public void success(Logout s, Response response) {

                        String success = s.getStatus();
                        if (success.contains("SUCCESS")) {
                            Intent intent = new Intent(getApplicationContext(), Login.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("EXIT", true);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MerchantHomeActivity.this, "Failed Check your Network", Toast.LENGTH_SHORT).show();
                        }


                    }

                    @Override
                    public void failure(RetrofitError error) {
//                        Log.e(TAG, error.getMessage());

                    }
                });

                return true;

            case R.id.action_settings:

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
