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
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.adapter.MerchantIssueListAdapter;
import id.co.imwizz.bolpax.data.BolpaxStatic;
import id.co.imwizz.bolpax.data.entity.TransactionLIst;
import id.co.imwizz.bolpax.data.entity.bolpax.request.MerchantIssueListPojo;
import id.co.imwizz.bolpax.data.entity.bolpax.response.MerchantBolpax;
import id.co.imwizz.bolpax.rest.Logout;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by User on 08/01/2016.
 */
public class MerchantIssueList extends AppCompatActivity {

    protected Context mContext;
    String email,name,phone,merchants,userid,token,nama,merchantid;
    Integer balance;
    LinearLayout merchant;
    TransactionLIst transactionlist;
    MenuItem createstore,switchtomerchant,buyername;
    @Bind(R.id.listviewIssue) ListView issue;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    List<MerchantIssueListPojo> merchantIssueListPojos;
    Long bolpax;
    MerchantIssueListPojo issuelist;
    private static final String TAG = MerchantIssueList.class.getSimpleName();
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issuelist);
        ButterKnife.bind(this);
        setToolbar();

        bolpax = BolpaxStatic.getMerchantid();
        merchantid = bolpax.toString();
        userid = BolpaxStatic.getUserid().toString();
        token = BolpaxStatic.getToken();
        phone = BolpaxStatic.getPhonenumber();

        RestClient.getBolpax().getMerchantIssuelist(merchantid, new Callback<List<MerchantIssueListPojo>>() {
            @Override
            public void success(List<MerchantIssueListPojo> result, Response response) {
                if(result==null){
                    Toast.makeText(MerchantIssueList.this, "No Transaction Found", Toast.LENGTH_SHORT).show();
                }else{
                merchantIssueListPojos = new ArrayList<MerchantIssueListPojo>(result);
                for (int i = 0; i < merchantIssueListPojos.size(); i++) {
                    long id = merchantIssueListPojos.get(i).getIssueId();
                    String date = merchantIssueListPojos.get(i).getIssueDate();
                    String status = merchantIssueListPojos.get(i).getIssueLastStatus();
                    Double amount = merchantIssueListPojos.get(i).getAmount();
                    String suspect = merchantIssueListPojos.get(i).getSuspect();

                    ListAdapter issueListAdapter = new MerchantIssueListAdapter(MerchantIssueList.this, merchantIssueListPojos);
                    issue.setAdapter(issueListAdapter);
                }


                }

            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, error.getMessage());

            }
        });

//        String json = DummyAPI.getJson(MerchantIssueList.this, R.raw.issue_list);
//        Gson gson = new Gson();
////        JSONArray jsonArr = new JSONArray();
//
//        IssueList[] issueList = gson.fromJson(json, IssueList[].class);

//        String Test = merchant.getMerchant();
//        String[] catValues5 = new String[merchant.size() + 1];
//        for (int i = 0; i < merchant.size(); i++) {
//
//        }
//        ListAdapter issueListAdapter = new IssueListAdapter(MerchantIssueList.this, issueList);
//        issue.setAdapter(issueListAdapter);
//        MerchantListAdapter merchantlistAdapter = new MerchantListAdapter(MerchantList_Activity.this, merchant);
//        listView.setAdapter(merchantlistAdapter);

        issue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                issuelist = (MerchantIssueListPojo) parent.getItemAtPosition(position);
                Intent myIntent = new Intent(MerchantIssueList.this, MerchantIssueDetailActivity.class);
                myIntent.putExtra("issueId", (issuelist.getIssueId()));
                startActivity(myIntent);

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
                Intent i = new Intent(MerchantIssueList.this, MerchantHomeActivity.class);
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
                Intent i = new Intent(MerchantIssueList.this, MerchantProfile.class);
                startActivity(i);

                return true;
            case R.id.switchto_merchant:
                Intent i3 = new Intent(MerchantIssueList.this, BuyerHomeActivity.class);
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
                            Toast.makeText(MerchantIssueList.this, "Failed Check your Network", Toast.LENGTH_SHORT).show();
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
}
