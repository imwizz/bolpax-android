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
import android.widget.Toast;

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
 * Created by bimosektiw on 1/26/16.
 */
public class MerchantIssueDetailActivity extends AppCompatActivity {

    List<IssueHistory> issueHistory;
    @Bind(R.id.suspect) TextView suspectText;
    @Bind(R.id.amount) TextView amountText;
    @Bind(R.id.laststatus) TextView issueLastStatusText;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    @Bind(R.id.list_detail) ListView issueDetailText;
    @Bind(R.id.replyissue) Button replyissueButton;
    @Bind(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_detail);
        ButterKnife.bind(this);
        setToolbar();

        String json = DummyAPI.getJson(MerchantIssueDetailActivity.this ,R.raw.issue_detail);
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
        ListAdapter listAdapter = new IssueHistoryAdapter(MerchantIssueDetailActivity.this, issueHistory);
        issueDetailText.setAdapter(listAdapter);
        suspectText.setText(suspect);
        amountText.setText("Rp "+amount +" for "+ product);
        issueLastStatusText.setText(laststatus);
        replyissueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MerchantIssueDetailActivity.this, MerchantReportIssueActivity2.class);
                startActivity(i);
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
                Intent i = new Intent(MerchantIssueDetailActivity.this, MerchantHomeActivity.class);
                startActivity(i);
            }
        });
        toolbar.setTitle("");
        toolbarTitle.setText("BOLPAX");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_switch) {
            return true;
        }
        if (id == R.id.action_logout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
