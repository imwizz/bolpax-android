package id.co.imwizz.bolpax.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import id.co.imwizz.bolpax.data.entity.bolpax.response.ProfileBolpax;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by User on 08/01/2016.
 */
public class BuyerHomeActivity extends AppCompatActivity implements View.OnClickListener {

    protected Context mContext;
    String email,name,phone,token,nama;
    Integer balance;
    Long userid;
    MenuItem createstore,switchtomerchant,buyername;
    @Bind(R.id.merchant) LinearLayout merchant;
    @Bind(R.id.transaction) LinearLayout transaction;
    @Bind(R.id.issue) LinearLayout issue;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_home);
        ButterKnife.bind(this);
        merchant.setOnClickListener(this);
        transaction.setOnClickListener(this);
        issue.setOnClickListener(this);
//        toolbar.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);

        toolbar.setTitle("");
        toolbarTitle.setText("BOLPAX");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BuyerHomeActivity.this, "This Home", Toast.LENGTH_SHORT).show();
            }
        });
        userid = BolpaxStatic.getUserid();
        token = BolpaxStatic.getToken();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        createstore = menu.findItem(R.id.create_store);
        switchtomerchant = menu.findItem(R.id.switchto_merchant);
        buyername = menu.findItem(R.id.profile);
        RestClient.getBolpax().getProfile(userid.toString(), token.toString(), new Callback<ProfileBolpax>() {
            @Override
            public void success(ProfileBolpax profileBolpax, Response response) {
                nama = profileBolpax.getFullname();
                buyername.setTitle(nama.toString());
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
        RestClient.getBolpax().getMerchantProfile(userid.toString(), token.toString(), new Callback<MerchantBolpax>() {
            @Override
            public void success(MerchantBolpax merchantBolpax, Response response) {
                if (merchantBolpax != null){
                    createstore.setVisible(false);
                } else {
                    switchtomerchant.setVisible(false);

                }
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
                Intent i = new Intent(BuyerHomeActivity.this, ProfileActivity.class);
                startActivity(i);

                return true;

            case R.id.create_store:
                Intent i2 = new Intent(BuyerHomeActivity.this, CreateStoreActivity.class);
                startActivity(i2);

                return true;
            case R.id.switchto_merchant:
                Intent i3 = new Intent(BuyerHomeActivity.this, MerchantHomeActivity.class);
                startActivity(i3);

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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.merchant:
                Intent i = new Intent(BuyerHomeActivity.this,MerchantList_Activity.class);
                startActivity(i);
                break;
            case R.id.transaction:
                Intent i2 = new Intent(BuyerHomeActivity.this,BuyerTransactionList.class);
                startActivity(i2);
                break;
            case R.id.issue:
                Intent i3 = new Intent(BuyerHomeActivity.this,BuyerIssueList.class);
                startActivity(i3);
                break;
        }

    }
}
