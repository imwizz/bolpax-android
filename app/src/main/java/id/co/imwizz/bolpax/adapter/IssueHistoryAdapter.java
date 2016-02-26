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
import id.co.imwizz.bolpax.data.entity.bolpax.response.IssueHistoryBolpax;

/**
 * Created by bimosektiw on 1/14/16.
 */
public class IssueHistoryAdapter extends ArrayAdapter<IssueHistoryBolpax> {

    private List<IssueHistoryBolpax> issueHistories;

    public IssueHistoryAdapter (Context context, List<IssueHistoryBolpax> issueHistories){
        super(context, R.layout.issue_detail_list, issueHistories);
        this.issueHistories = issueHistories;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater trxHistoryInflater = LayoutInflater.from(getContext());
        View customView = trxHistoryInflater.inflate(R.layout.issue_detail_list, parent, false);

        IssueHistoryBolpax trxHistory = getItem(position);

        TextView issuehistoryDate = (TextView) customView.findViewById(R.id.date);
        TextView issuehistoryMessage = (TextView) customView.findViewById(R.id.issuemessage);
        String fromAdmin = trxHistory.getFromAdmin();
        if (fromAdmin.contains("Y")){
            issuehistoryMessage.setTextColor(Color.parseColor("#2196F3"));
            issuehistoryMessage.setText(trxHistory.getMessage());
        }
        else{
            issuehistoryMessage.setTextColor(Color.parseColor("#000000"));
            issuehistoryMessage.setText(trxHistory.getMessage());
        }

        issuehistoryDate.setText(trxHistory.getTime());


        return customView;

    }
}
