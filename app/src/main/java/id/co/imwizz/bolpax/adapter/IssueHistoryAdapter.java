package id.co.imwizz.bolpax.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.entity.bolpax.response.IssueHistoryRsp;

/**
 * Created by bimosektiw on 1/14/16.
 */
public class IssueHistoryAdapter extends ArrayAdapter<IssueHistoryRsp> {

    private List<IssueHistoryRsp> issueHistories;

    public IssueHistoryAdapter (Context context, List<IssueHistoryRsp> issueHistories){
        super(context, R.layout.issue_detail_list, issueHistories);
        this.issueHistories = issueHistories;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater trxHistoryInflater = LayoutInflater.from(getContext());
        View customView = trxHistoryInflater.inflate(R.layout.issue_detail_list, parent, false);

        IssueHistoryRsp trxHistory = getItem(position);

        TextView textDate = (TextView) customView.findViewById(R.id.text_date);
        TextView textIssueMessage = (TextView) customView.findViewById(R.id.text_issue_message);
        String fromAdmin = trxHistory.getFromAdmin();
        if (fromAdmin.contains("Y")){
            textIssueMessage.setTextColor(Color.parseColor("#2196F3"));
            textIssueMessage.setText(trxHistory.getMessage());
        }
        else{
            textIssueMessage.setTextColor(Color.parseColor("#000000"));
            textIssueMessage.setText(trxHistory.getMessage());
        }

        textDate.setText(trxHistory.getTime());


        return customView;

    }
}
