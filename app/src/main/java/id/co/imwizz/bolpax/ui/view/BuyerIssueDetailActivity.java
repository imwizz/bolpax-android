package id.co.imwizz.bolpax.ui.view;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.adapter.IssueHistoryAdapter;
import id.co.imwizz.bolpax.data.entity.IssueDetail;
import id.co.imwizz.bolpax.data.entity.IssueHistory;
import id.co.imwizz.bolpax.data.service.DummyAPI;

/**
 * Created by bimosektiw on 1/14/16.
 */
public class BuyerIssueDetailActivity extends AppCompatActivity implements View.OnClickListener {

    List<IssueHistory> issueHistory;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    @Bind(R.id.suspect) TextView suspectText;
    @Bind(R.id.amount) TextView amountText;
    @Bind(R.id.laststatus) TextView issueLastStatusText;
    @Bind(R.id.list_detail) ListView issueDetailText;
    @Bind(R.id.replyissue) Button replayIssue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_detail);
        ButterKnife.bind(this);
        setToolbar();
        replayIssue.setOnClickListener(this);

        String json = DummyAPI.getJson(BuyerIssueDetailActivity.this ,R.raw.issue_detail);
        Gson gson = new Gson();
        IssueDetail issueDetail = gson.fromJson(json, IssueDetail.class);

        String suspect = issueDetail.getSuspect();
        String amount = issueDetail.getAmount();
        String product = issueDetail.getProduct();
        String laststatus = issueDetail.getIssueLastStatus();

        issueHistory = issueDetail.getIssueHistory();
        issueDetailText.setSelector(new ColorDrawable(0));
        String[] issue = new String[issueHistory.size() + 1];
        for (int i = 0; i < issueHistory.size(); i++) {
            issue[i] = issueHistory.get(i).getTime();
            issue[i] = issueHistory.get(i).getMessage();
        }
        ListAdapter listAdapter = new IssueHistoryAdapter(BuyerIssueDetailActivity.this, issueHistory);
        issueDetailText.setAdapter(listAdapter);
        suspectText.setText(suspect);
        amountText.setText("Rp "+amount +" for "+ product);
        issueLastStatusText.setText(laststatus);
    }

    private void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
        toolbarTitle.setText("BOLPAX");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BuyerIssueDetailActivity.this, BuyerHomeActivity.class);
                startActivity(i);
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
                Intent i = new Intent(BuyerIssueDetailActivity.this, ProfileActivity.class);
                startActivity(i);

                return true;

            case R.id.create_store:
                Intent i2 = new Intent(BuyerIssueDetailActivity.this, CreateStoreActivity.class);
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
            case R.id.replyissue :
                Intent i = new Intent(BuyerIssueDetailActivity.this,BuyerReportIssueActivity2.class);
                startActivity(i);
                break;

        }
    }
}
