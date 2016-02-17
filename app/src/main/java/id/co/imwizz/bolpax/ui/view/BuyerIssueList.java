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

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.adapter.IssueListAdapter;
import id.co.imwizz.bolpax.data.entity.bolpax.request.BuyerIssueListPojo;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by User on 08/01/2016.
 */
public class BuyerIssueList extends AppCompatActivity implements View.OnClickListener {

    protected Context mContext;
    String email,name,phone,merchants;
    private static final String TAG = BuyerIssueList.class.getSimpleName();
    Integer balance;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    @Bind(R.id.listviewIssue) ListView issue;
    List<BuyerIssueListPojo> buyerIssueListPojos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issuelist);
        ButterKnife.bind(this);
//        toolbar.setOnClickListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);

        toolbar.setTitle("");

        toolbarTitle.setText("BOLPAX");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BuyerIssueList.this, BuyerHomeActivity.class);
                startActivity(i);
            }
        });

        RestClient.getBolpax().getBuyerIssuelist("1", new Callback<List<BuyerIssueListPojo>>() {
                    @Override
                    public void success(List<BuyerIssueListPojo> result, Response response) {
                        buyerIssueListPojos = new ArrayList<BuyerIssueListPojo>(result);
                        for (int i = 0; i < buyerIssueListPojos.size(); i++) {
                            long id = buyerIssueListPojos.get(i).getIssueId();
                            String date = buyerIssueListPojos.get(i).getIssueDate();
                            String status = buyerIssueListPojos.get(i).getIssueLastStatus();
                            Double amount = buyerIssueListPojos.get(i).getAmount();
                            String suspect = buyerIssueListPojos.get(i).getSuspect();

                            ListAdapter issueListAdapter = new IssueListAdapter(BuyerIssueList.this, buyerIssueListPojos);
                            issue.setAdapter(issueListAdapter);


                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getMessage());

                    }
                });


//                String json = DummyAPI.getJson(BuyerIssueList.this, R.raw.issue_list);
//        Gson gson = new Gson();
//        IssueList[] issueList = gson.fromJson(json, IssueList[].class);
//
//        ListAdapter issueListAdapter = new IssueListAdapter(BuyerIssueList.this, issueList);
//        issue.setAdapter(issueListAdapter);
        issue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
//                issue = (IssueList) parent.getItemAtPosition(position);
                //catVal = Category.getCategoryName();
                //catVal = (Category)getIntent().getSerializableExtra("category");
                Intent myIntent = new Intent(BuyerIssueList.this, BuyerIssueDetailActivity.class);
//                myIntent.putExtra("amount", (transactionlist.getAmount()));
//                myIntent.putExtra("merchant", (transactionlist.getMerchant()));
//                myIntent.putExtra("date", (transactionlist.getTrxDate()));
//                myIntent.putExtra("status", (transactionlist.getTrxLastStatus()));
                startActivity(myIntent);

            }
        });





    }
    @OnItemClick(R.id.listviewIssue)
    void onItemClick(int position) {
        Intent i2 = new Intent(BuyerIssueList.this,BuyerIssueDetailActivity.class);
        startActivity(i2);
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
                Intent i = new Intent(BuyerIssueList.this, ProfileActivity.class);
                startActivity(i);

                return true;

            case R.id.create_store:
                Intent i2 = new Intent(BuyerIssueList.this, CreateStoreActivity.class);
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {



        }
    }
}
