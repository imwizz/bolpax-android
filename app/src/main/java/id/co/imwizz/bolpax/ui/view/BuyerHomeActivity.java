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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.BolpaxStatic;
import id.co.imwizz.bolpax.rest.Logout;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by User on 08/01/2016.
 */
public class BuyerHomeActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MerchantReportIssue2Activity.class.getSimpleName();

    protected Context mContext;
    private String email,name,phone,token,nama;
    private Long userid,merchantid;
    private MenuItem createstore,switchtomerchant,buyername;

    @Bind(R.id.text_merchant) LinearLayout linearMerchant;
    @Bind(R.id.linear_transaction) LinearLayout linearTransaction;
    @Bind(R.id.linear_issue) LinearLayout linearIssue;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.text_toolbar_title) TextView textToolbarTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_home);
        ButterKnife.bind(this);
        linearMerchant.setOnClickListener(this);
        linearTransaction.setOnClickListener(this);
        linearIssue.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);

        toolbar.setTitle("");
        textToolbarTitle.setText("BOLPAX");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BuyerHomeActivity.this, "This Home", Toast.LENGTH_SHORT).show();
            }
        });
        userid = BolpaxStatic.getUserid();
        token = BolpaxStatic.getToken();
        phone = BolpaxStatic.getPhonenumber();
        nama = BolpaxStatic.getFullname();
        merchantid = BolpaxStatic.getMerchantid();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
                RestClient.getBolpax().getLogout(token,phone,new Callback<Logout>() {
                    @Override
                    public void success(Logout s, Response response) {

                        String success = s.getStatus();
                        if(success.contains("SUCCESS")) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("EXIT", true);
                            startActivity(intent);
                        }else{
                            Toast.makeText(BuyerHomeActivity.this, "Failed Check your Network", Toast.LENGTH_SHORT).show();
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
            case R.id.text_merchant:
                Intent i = new Intent(BuyerHomeActivity.this,MerchantListActivity.class);
                startActivity(i);
                break;
            case R.id.linear_transaction:
                Intent i2 = new Intent(BuyerHomeActivity.this,BuyerTransactionListActivity.class);
                startActivity(i2);
                break;
            case R.id.linear_issue:
                Intent i3 = new Intent(BuyerHomeActivity.this,BuyerIssueListActivity.class);
                startActivity(i3);
                break;
        }

    }
}
