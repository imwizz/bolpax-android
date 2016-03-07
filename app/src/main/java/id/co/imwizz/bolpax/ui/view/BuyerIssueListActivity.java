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
import id.co.imwizz.bolpax.adapter.IssueAdapter;
import id.co.imwizz.bolpax.data.BolpaxStatic;
import id.co.imwizz.bolpax.data.entity.bolpax.request.BuyerIssueRqs;
import id.co.imwizz.bolpax.data.entity.bolpax.response.LogoutRsp;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * This activity is used to display Issue in buyer transaction.
 *
 * @author Duway
 */
public class BuyerIssueListActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = BuyerIssueListActivity.class.getSimpleName();
    final Context context = this;

    protected Context mContext;
    private String phone,userid,token,nama;
    private BuyerIssueRqs issuelist;
    private List<BuyerIssueRqs> buyerIssueRqses;
    private Long bolpax,id,merchantId;
    private MenuItem createstore,switchtomerchant,buyername;

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.text_toolbar_title) TextView textToolbarTitle;
    @Bind(R.id.list_issue) ListView listIssue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issuelist);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);

        toolbar.setTitle("");

        textToolbarTitle.setText("BOLPAX");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BuyerIssueListActivity.this, BuyerHomeActivity.class);
                startActivity(i);
            }
        });

        bolpax = BolpaxStatic.getUserid();
        userid = bolpax.toString();
        token = BolpaxStatic.getToken();
        phone = BolpaxStatic.getPhonenumber();
        nama = BolpaxStatic.getFullname();
        merchantId = BolpaxStatic.getMerchantid();

        RestClient.getBolpax().getBuyerIssuelist(userid.toString(), new Callback<List<BuyerIssueRqs>>() {
                    @Override
                    public void success(List<BuyerIssueRqs> result, Response response) {

                        if(result==null){
                            Toast.makeText(BuyerIssueListActivity.this, "No Transaction Found", Toast.LENGTH_SHORT).show();
                        }else{
                        buyerIssueRqses = new ArrayList<BuyerIssueRqs>(result);
                        for (int i = 0; i < buyerIssueRqses.size(); i++) {
                            id = buyerIssueRqses.get(i).getIssueId();
                            String date = buyerIssueRqses.get(i).getIssueDate();
                            String status = buyerIssueRqses.get(i).getIssueLastStatus();
                            Double amount = buyerIssueRqses.get(i).getAmount();
                            String suspect = buyerIssueRqses.get(i).getSuspect();

                            ListAdapter issueListAdapter = new IssueAdapter(BuyerIssueListActivity.this, buyerIssueRqses);
                            listIssue.setAdapter(issueListAdapter);

                            listIssue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                public void onItemClick(AdapterView<?> parent, View view,
                                                        int position, long id) {
                                    issuelist = (BuyerIssueRqs) parent.getItemAtPosition(position);
                                    Intent myIntent = new Intent(BuyerIssueListActivity.this, BuyerIssueDetailActivity.class);
                                    myIntent.putExtra("issueId", (issuelist.getIssueId()));
                                    startActivity(myIntent);

                                }
                            });
                        }


                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getMessage());

                    }
                });


    }
    @OnItemClick(R.id.list_issue)
    void onItemClick(int position) {
        Intent i2 = new Intent(BuyerIssueListActivity.this,BuyerIssueDetailActivity.class);
        startActivity(i2);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        createstore = menu.findItem(R.id.create_store);
        switchtomerchant = menu.findItem(R.id.switchto_merchant);
        buyername = menu.findItem(R.id.profile);
        buyername.setTitle(nama.toString());
        if ( merchantId !=null){
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
                Intent i = new Intent(BuyerIssueListActivity.this, BuyerProfileActivity.class);
                startActivity(i);

                return true;

            case R.id.create_store:
                Intent i2 = new Intent(BuyerIssueListActivity.this, CreateStoreActivity.class);
                startActivity(i2);

                return true;
            case R.id.switchto_merchant:
                Intent i3 = new Intent(BuyerIssueListActivity.this, MerchantHomeActivity.class);
                startActivity(i3);

                return true;

            case R.id.quit:
                RestClient.getBolpax().getLogout(token, phone, new Callback<LogoutRsp>() {
                    @Override
                    public void success(LogoutRsp s, Response response) {

                        String success = s.getStatus();
                        if (success.contains("SUCCESS")) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("EXIT", true);
                            startActivity(intent);
                        } else {
                            Toast.makeText(BuyerIssueListActivity.this, "Failed Check your Network", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {



        }
    }
}
