package id.co.imwizz.bolpax.ui.view;

import android.content.Intent;
import android.graphics.Color;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.adapter.TransactionHistoryAdapter;
import id.co.imwizz.bolpax.data.entity.bolpax.request.AddHistoryTrxBolpax;
import id.co.imwizz.bolpax.data.entity.bolpax.request.Id;
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
    @Bind(R.id.reply)
    Button reply;
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
        refreshHistory();



    }
    private void refreshHistory(){
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
                if (laststatus.contains("Transaction complete")) {
                    laststatusText.setText(laststatus);
//                    laststatusText.setTextColor(Color.GREEN);
                    laststatusText.setTextColor(Color.parseColor("#49E845"));

                }else {
                    laststatusText.setText(laststatus);
                    laststatusText.setTextColor(Color.YELLOW);
                    laststatusText.setTextColor(Color.parseColor("#FFD426"));
                }

                if (trxHistory.size() == 3){
                    reply.setVisibility(View.VISIBLE);
                    reply.setText("Item Received");
                    reply.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getBaseContext(), "ditekan", Toast.LENGTH_LONG).show();
                            AddHistoryTrxBolpax addHistoryTrxBolpax = new AddHistoryTrxBolpax();
                            addHistoryTrxBolpax.setTrxId(trxId);
                            Id id = new Id();
                            id.setId(Long.valueOf(4));
                            Id id2 = new Id();
                            id2.setId(Long.valueOf(5));
                            Id id3 = new Id();
                            id3.setId(Long.valueOf(6));
                            List<Id> ids = new ArrayList<>();
                            ids.add(id);
                            ids.add(id2);
                            ids.add(id3);
                            addHistoryTrxBolpax.setTrxStatusMapping(ids);
                            RestClient.getBolpax().postAddHistoryTransaction(addHistoryTrxBolpax, new Callback<String>() {
                                @Override
                                public void success(String s, Response response) {
                                    refreshHistory();
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    Log.e(TAG, error.getMessage());
                                }
                            });
                        }
                    });
                } else {
                    reply.setVisibility(View.GONE);
                }
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
        getMenuInflater().inflate(R.menu.menu_report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_report:
                Intent i6 = new Intent(BuyerTransactionDetailActivity.this, BuyerReportIssueActivity.class);
                i6.putExtra("trxid",trxId);
                startActivity(i6);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
