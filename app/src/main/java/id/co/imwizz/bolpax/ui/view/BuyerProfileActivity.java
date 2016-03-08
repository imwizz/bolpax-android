package id.co.imwizz.bolpax.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.BolpaxStatic;
import id.co.imwizz.bolpax.data.entity.bolpax.response.ProfileRsp;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * This activity is used to display Buyer Profile.
 *
 * @author Duway
 */
public class BuyerProfileActivity extends AppCompatActivity {

    private static final String TAG = BuyerProfileActivity.class.getSimpleName();
    protected Context mContext;
    private String email,name,phone, userid,token,balance;
    private Long bolpax;

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.text_toolbar_title) TextView textToolbarTitle;
    @Bind(R.id.text_name) TextView textName;
    @Bind(R.id.text_email) TextView textEmail;
    @Bind(R.id.text_phone) TextView textPhone;
    @Bind(R.id.text_balance) TextView textBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_profile);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BuyerProfileActivity.this, BuyerHomeActivity.class);
                startActivity(i);
            }
        });
        toolbar.setTitle("");
        textToolbarTitle.setText("BOLPAX");

        bolpax = BolpaxStatic.getUserid();
        userid = bolpax.toString();
        token = BolpaxStatic.getToken();
        phone = BolpaxStatic.getPhonenumber();


        RestClient.getBolpax().getProfile(userid.toString(), token.toString(), new Callback<ProfileRsp>() {
                    @Override
                    public void success(ProfileRsp profileRsp, Response response) {
                        name = profileRsp.getFullname();
                        email = profileRsp.getEmail();
                        phone = profileRsp.getPhone();
                        balance = profileRsp.getBalance();

                        textName.setText(name);
                        textEmail.setText(email);
                        textPhone.setText(phone);
                        textBalance.setText(balance);

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getMessage());
                    }
                });



    }

}
