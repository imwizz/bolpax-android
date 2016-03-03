package id.co.imwizz.bolpax.ui.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.entity.bolpax.request.ReportRqs;
import id.co.imwizz.bolpax.data.entity.bolpax.response.IssueIdRsp;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by bimosektiw on 2/24/16.
 */
public class MerchantCreateReportActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = BuyerReportIssue2Activity.class.getSimpleName();
    private String createSubjectReport,createDescReport,subject,trxid;
    private Long trxId;

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.text_toolbar_title) TextView textToolbarTitle;
    @Bind(R.id.text_subject) TextView textSubject;
    @Bind(R.id.edit_description) EditText editDescription;
    @Bind(R.id.button_submit) Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_reportissue2);
        ButterKnife.bind(this);
        buttonSubmit.setOnClickListener(this);
        setToolbar();
        Intent i = getIntent();
        subject = i.getStringExtra("subject").toString();
        trxid = i.getStringExtra("trxid").toString();
        trxId = Long.valueOf(trxid);
        textSubject.setText(subject);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_report:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.button_submit:
                createSubjectReport = textSubject.getText().toString();
                createDescReport = editDescription.getText().toString();
                ReportRqs report = new ReportRqs();
                report.setSubject(createSubjectReport);
                report.setDesc(createDescReport);
                report.setRole("merchant");
                report.setTrxId(trxId);
                RestClient.getBolpax().postBuyerReport(report, new Callback<IssueIdRsp>() {
                    @Override
                    public void success(IssueIdRsp issueIdRsp, Response response) {
                        Intent i = new Intent(MerchantCreateReportActivity.this, MerchantIssueDetailActivity.class);
                        i.putExtra("issueId", issueIdRsp.getIssueId());
                        startActivity(i);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.e(TAG, error.getMessage());

                    }
                });

                break;

        }

    }
    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle("");
        textToolbarTitle.setText("BOLPAX");
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MerchantCreateReportActivity.this, MerchantHomeActivity.class);
                startActivity(i);
            }
        });

    }
}
