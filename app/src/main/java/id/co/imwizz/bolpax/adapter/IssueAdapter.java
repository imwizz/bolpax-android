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
import id.co.imwizz.bolpax.data.entity.bolpax.request.BuyerIssueRqs;

/**
 * This adapter is used to display issue list
 *
 * @author bimosektiw
 */

public class IssueAdapter extends ArrayAdapter<BuyerIssueRqs> {


    private List<BuyerIssueRqs> issueList;
    Context mContext;

    public IssueAdapter(Context context, List<BuyerIssueRqs> issueList) {
        super(context, R.layout.item_issue, issueList);
        this.issueList = issueList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater categoryInflater = LayoutInflater.from(getContext());
        View customView = categoryInflater.inflate(R.layout.item_issue, parent, false);

        BuyerIssueRqs category = getItem(position);

        TextView textDate = (TextView) customView.findViewById(R.id.text_date);
        TextView textStatus = (TextView) customView.findViewById(R.id.text_satus);
        TextView textNominal = (TextView) customView.findViewById(R.id.text_nominal);

        textDate.setText(category.getIssueDate());
        textStatus.setText(category.getIssueLastStatus());
        textStatus.setTextColor(Color.parseColor("#d36a04"));


        textNominal.setText(category.getSuspect());


        return customView;
    }
}
