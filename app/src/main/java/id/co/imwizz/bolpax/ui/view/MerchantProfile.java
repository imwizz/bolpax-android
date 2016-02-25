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
public class MerchantProfile extends AppCompatActivity {
    private static final String TAG = MerchantProfile.class.getSimpleName();
    protected Context mContext;
    String email,name,phone,call,userid,token,balance,nama;
    MenuItem createstore,switchtomerchant,buyername;
    Long bolpax;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    @Bind(R.id.tvname) TextView Name;
    @Bind(R.id.tvmail) TextView Email;
    @Bind(R.id.tvcall) TextView Call;
    @Bind(R.id.tvbalance) TextView Balance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_profile);
        ButterKnife.bind(this);

        bolpax = BolpaxStatic.getUserid();
        userid = bolpax.toString();
        token = BolpaxStatic.getToken();
        phone = BolpaxStatic.getPhonenumber();

        RestClient.getBolpax().getMerchantProfile(userid.toString(), token.toString(), new Callback<MerchantBolpax>() {
            @Override
            public void success(MerchantBolpax merchantBolpax, Response response) {
//                User user = new User();
                name = merchantBolpax.getMerchantName();
                email = merchantBolpax.getUser().getEmail();
                call = merchantBolpax.getUser().getPhone();
                balance = merchantBolpax.getUser().getBalance();
                Name.setText(name.toString());
                Email.setText(email.toString());
                Call.setText(call.toString());
                Balance.setText("Rp. "+balance);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
            }
        });



    }

    public void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MerchantProfile.this,BuyerHomeActivity.class);
                startActivity(i);
            }
        });
        toolbar.setTitle("");
        toolbarTitle.setText("BOLPAX");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
                Intent i = new Intent(MerchantProfile.this, MerchantProfile.class);
                startActivity(i);

                return true;
            case R.id.switchto_merchant:
                Intent i3 = new Intent(MerchantProfile.this, BuyerHomeActivity.class);
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
                            Toast.makeText(MerchantProfile.this, "Failed Check your Network", Toast.LENGTH_SHORT).show();
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
