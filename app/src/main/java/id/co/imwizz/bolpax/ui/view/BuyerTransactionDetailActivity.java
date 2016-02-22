package id.co.imwizz.bolpax.ui.view;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.adapter.TransactionHistoryAdapter;
import id.co.imwizz.bolpax.data.entity.bolpax.response.TransactionDetailBolpax;
import id.co.imwizz.bolpax.data.entity.bolpax.response.TransactionHistoryBolpax;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by bimosektiw on 11/01/2016.
 */
public class BuyerTransactionDetailActivity extends AppCompatActivity {

    private static final String TAG = BuyerTransactionDetailActivity.class.getSimpleName();
    List<TransactionHistoryBolpax> trxHistory;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    @Bind(R.id.merchant) TextView merchantText;
    @Bind(R.id.amount) TextView amountText;
    @Bind(R.id.laststatus) TextView laststatusText;
    @Bind(R.id.list_detail) ListView trxDetailText;
    String trxId;
    long trxid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_detail);
        Intent i = getIntent();
        trxid = i.getLongExtra("trxid",0);
        trxId = String.valueOf(trxid);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
        toolbar.setTitle("");

        toolbarTitle.setText("BOLPAX");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BuyerTransactionDetailActivity.this, BuyerHomeActivity.class);
                startActivity(i);
            }
        });



        RestClient.getBolpax().getTransactionDetail(trxId,"buyer", new Callback<TransactionDetailBolpax>() {
            @Override
            public void success(TransactionDetailBolpax transactionDetailBolpax, Response response) {
                String merchant = transactionDetailBolpax.getMerchant();
                String amount = transactionDetailBolpax.getAmount();
                String product = transactionDetailBolpax.getProduct();
                String laststatus = transactionDetailBolpax.getTrxLastStatus();

                trxHistory = transactionDetailBolpax.getTrxHistory();
                trxDetailText.setSelector(new ColorDrawable(0));
                String[] trx = new String[trxHistory.size() + 1];
                for (int i = 0; i < trxHistory.size(); i++) {
                    trx[i] = trxHistory.get(i).getTime();
                    trx[i] = trxHistory.get(i).getStatus();
                }
                ListAdapter listAdapter = new TransactionHistoryAdapter(BuyerTransactionDetailActivity.this, trxHistory);
                trxDetailText.setAdapter(listAdapter);
                merchantText.setText(merchant);
                amountText.setText("Rp "+amount +" for "+ product);
                laststatusText.setText(laststatus);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());
            }
        });
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
                Intent i = new Intent(BuyerTransactionDetailActivity.this, ProfileActivity.class);
                startActivity(i);

                return true;

            case R.id.create_store:
                Intent i2 = new Intent(BuyerTransactionDetailActivity.this, CreateStoreActivity.class);
                startActivity(i2);

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
