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
import id.co.imwizz.bolpax.adapter.MerchantTransactionListAdapter;
import id.co.imwizz.bolpax.data.BolpaxStatic;
import id.co.imwizz.bolpax.data.entity.bolpax.request.MerchantTransactionListPojo;
import id.co.imwizz.bolpax.rest.Logout;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by User on 08/01/2016.
 */
public class MerchantTransactionListActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = BuyerTransactionListActivity.class.getSimpleName();
    protected Context mContext;
    MenuItem createstore,switchtomerchant,buyername;
    String email,name,phone,token,merchantId,nama,merchantName;
    Integer balance;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.text_toolbar_title) TextView textToolbarTitle;
    @Bind(R.id.list_transaction) ListView listTransaction;
    MerchantTransactionListPojo transactionlist;
    List<MerchantTransactionListPojo> merchantTransactionListPojos;
    final Context context = this;
    Long bolpax,userid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);

        toolbar.setTitle("");

        textToolbarTitle.setText("BOLPAX");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MerchantTransactionListActivity.this, MerchantHomeActivity.class);
                startActivity(i);
            }
        });

        bolpax = BolpaxStatic.getMerchantid();
        merchantId = bolpax.toString();
        userid = BolpaxStatic.getUserid();
        token = BolpaxStatic.getToken();
        phone = BolpaxStatic.getPhonenumber();
        merchantName = BolpaxStatic.getMerchantname();

        RestClient.getBolpax().getMerchantTransactionlist(merchantId.toString(), new Callback<List<MerchantTransactionListPojo>>() {
            @Override
            public void success(List<MerchantTransactionListPojo> result, Response response) {

                if(result==null){
                    Toast.makeText(MerchantTransactionListActivity.this, "No Transaction Found", Toast.LENGTH_SHORT).show();
                }else {
                    merchantTransactionListPojos = new ArrayList<MerchantTransactionListPojo>(result);
                    for (int i = 0; i < merchantTransactionListPojos.size(); i++) {
                        long id = merchantTransactionListPojos.get(i).getTrxId();
                        String date = merchantTransactionListPojos.get(i).getTrxDate();
                        String status = merchantTransactionListPojos.get(i).getTrxLastStatus();
                        Double amount = merchantTransactionListPojos.get(i).getAmount();
                        String merchant = merchantTransactionListPojos.get(i).getMerchant();
                        String buyer = merchantTransactionListPojos.get(i).getBuyer();
                        String product = merchantTransactionListPojos.get(i).getProduct();

                    }
                    ListAdapter transactionListAdapter = new MerchantTransactionListAdapter(MerchantTransactionListActivity.this, merchantTransactionListPojos);
                    listTransaction.setAdapter(transactionListAdapter);
                }

            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());

            }
        });
    }

    @OnItemClick(R.id.list_transaction)
    void onItemClick(AdapterView<?> parent, View view,
                     int position, long id) {
        transactionlist = (MerchantTransactionListPojo) parent.getItemAtPosition(position);
        Intent myIntent = new Intent(MerchantTransactionListActivity.this, MerchantTransactionDetailActivity.class);
        myIntent.putExtra("amount", (transactionlist.getAmount()));
        myIntent.putExtra("trxid", (transactionlist.getTrxId()));
        myIntent.putExtra("merchant", (transactionlist.getMerchant()));
        myIntent.putExtra("date", (transactionlist.getTrxDate()));
        myIntent.putExtra("status", (transactionlist.getTrxLastStatus()));
        myIntent.putExtra("product", (transactionlist.getProduct()));
        startActivity(myIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        createstore = menu.findItem(R.id.create_store);
        switchtomerchant = menu.findItem(R.id.switchto_merchant);
        buyername = menu.findItem(R.id.profile);
        buyername = menu.findItem(R.id.profile);
        buyername.setTitle(merchantName.toString());
        createstore.setVisible(false);
        switchtomerchant.setTitle("Switch To Buyer");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.profile:
                Intent i = new Intent(MerchantTransactionListActivity.this, MerchantProfileActivity.class);
                startActivity(i);

                return true;
            case R.id.switchto_merchant:
                Intent i3 = new Intent(MerchantTransactionListActivity.this, BuyerHomeActivity.class);
                startActivity(i3);

                return true;

            case R.id.quit:
                RestClient.getBolpax().getLogout(token, phone, new Callback<Logout>() {
                    @Override
                    public void success(Logout s, Response response) {

                        String success = s.getStatus();
                        if (success.contains("SUCCESS")) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("EXIT", true);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MerchantTransactionListActivity.this, "Failed Check your Network", Toast.LENGTH_SHORT).show();
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
