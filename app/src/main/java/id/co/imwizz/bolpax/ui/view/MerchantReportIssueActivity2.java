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
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.entity.bolpax.request.Report;
import id.co.imwizz.bolpax.rest.RestClient;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by bimosektiw on 1/27/16.
 */
public class MerchantReportIssueActivity2 extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.toolbar_title) TextView toolbarTitle;
    @Bind(R.id.subject_report)EditText subjectReport;
    @Bind(R.id.desc_report) EditText descReport;
    @Bind(R.id.subbut)Button subBut;
    String createSubjectReport,createDescReport;
    private static final String TAG = MerchantReportIssueActivity2.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_reportissue2);
        ButterKnife.bind(this);
        subBut.setOnClickListener(this);
        setToolbar();
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.ic_home_white_18dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MerchantReportIssueActivity2.this, MerchantHomeActivity.class);
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

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.subbut:
                createSubjectReport = subjectReport.getText().toString();
                createDescReport = descReport.getText().toString();
                Report report = new Report();
                report.setSubject(createDescReport);
                report.setDesc(createDescReport);
                report.setRole("merchant");
                report.setTrxId(1);
                RestClient.getBolpax().postBuyerReport(report, new Callback<String>() {

                    @Override
                    public void success(String string, Response response) {
                        Toast.makeText(getBaseContext(), "Report Submitted", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(MerchantReportIssueActivity2.this, MerchantTransactionDetailActivity.class);
                        startActivity(i);
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
