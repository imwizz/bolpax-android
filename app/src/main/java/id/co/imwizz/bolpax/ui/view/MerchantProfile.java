package id.co.imwizz.bolpax.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.entity.Profile;
import id.co.imwizz.bolpax.data.service.DummyAPI;

/**
 * Created by User on 08/01/2016.
 */
public class MerchantProfile extends AppCompatActivity {

    protected Context mContext;
    String email,name,phone;
    Integer balance;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    @Bind(R.id.tvname) TextView Name;
    @Bind(R.id.tvmail) TextView Balance;
    @Bind(R.id.tvcall) TextView Email;
    @Bind(R.id.tvbalance) TextView Call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_profile);
        ButterKnife.bind(this);

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

        String json = DummyAPI.getJson(MerchantProfile.this, R.raw.profile);
        Gson gson = new Gson();
        Profile profile = gson.fromJson(json, Profile.class);
        email = profile.getEmail();
        name = profile.getName();
        phone = profile.getPhone();
        balance = profile.getBalance();

        Name.setText(name);
        Email.setText(email);
        Call.setText(phone);
        Balance.setText("Rp. "+balance);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.profile:
                Intent i = new Intent(MerchantProfile.this, ProfileActivity.class);
                startActivity(i);

                return true;

            case R.id.create_store:
                Toast.makeText(MerchantProfile.this, "This Merchant Profile", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.quit:
                finish();

                return true;

            case R.id.action_settings:

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

}
