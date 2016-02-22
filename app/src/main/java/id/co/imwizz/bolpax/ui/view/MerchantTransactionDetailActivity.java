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
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.adapter.TransactionHistoryAdapter;
import id.co.imwizz.bolpax.data.BolpaxStatic;
import id.co.imwizz.bolpax.data.entity.bolpax.response.TransactionDetailBolpax;
import id.co.imwizz.bolpax.data.entity.bolpax.response.TransactionHistoryBolpax;
import id.co.imwizz.bolpax.rest.History;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by bimosektiw on 1/22/16.
 */
public class MerchantTransactionDetailActivity extends AppCompatActivity implements View.OnClickListener {

    List<TransactionHistoryBolpax> trxHistory;
    @Bind(R.id.merchant) TextView merchantText;
    @Bind(R.id.amount) TextView amountText;
    @Bind(R.id.laststatus) TextView laststatusText;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    @Bind(R.id.list_detail) ListView trxDetailText;
    @Bind(R.id.reply) Button butShipment;
    @Bind(R.id.toolbar) Toolbar toolbar;
    Long  userid,merchantid,bolpax;
    String token,trxId;
    long trxid;
    private static final String TAG = MerchantTransactionDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_detail);
        Intent i = getIntent();
        trxid = i.getLongExtra("trxid",0);
        trxId = String.valueOf(trxid);
        ButterKnife.bind(this);
        butShipment.setOnClickListener(this);
        setToolbar();


        RestClient.getBolpax().getTransactionDetail(trxId, "merchant",new Callback<TransactionDetailBolpax>() {
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
                ListAdapter listAdapter = new TransactionHistoryAdapter(MerchantTransactionDetailActivity.this, trxHistory);
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

    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MerchantTransactionDetailActivity.this, MerchantHomeActivity.class);
                startActivity(i);
            }
        });
        toolbar.setTitle("");
        toolbarTitle.setText("BOLPAX");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_report) {
            Intent i = new Intent(MerchantTransactionDetailActivity.this, MerchantReportIssueActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.reply :
                bolpax = BolpaxStatic.getMerchantid();
                userid = BolpaxStatic.getMerchantid();
                token = BolpaxStatic.getToken();
                History history = new History();
                history.setTrxId(trxid);
                history.setTrxStatusMapping("{"+"id"+":"+"3}");
                RestClient.getBolpax().postMerchantIssueDtl(history, new Callback<String>() {

                    @Override
                    public void success(String string, Response response) {
                        Toast.makeText(getBaseContext(), "Report Submitted", Toast.LENGTH_LONG).show();
//                        Intent i = new Intent(MerchantTransactionDetailActivity.this, BuyerIssueDetailActivity.class);
//                        startActivity(i);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getMessage());

                    }
                });

//                Toast.makeText(BuyerReportIssueActivity2.this, "Report Submitted", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(BuyerReportIssueActivity2.this,BuyerIssueDetailActivity.class);
//                startActivity(i);
                break;

        }
    }
}