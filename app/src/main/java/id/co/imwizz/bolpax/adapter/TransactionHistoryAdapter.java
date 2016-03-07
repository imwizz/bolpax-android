package id.co.imwizz.bolpax.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.entity.bolpax.response.TransactionHistoryRsp;

/**
 * This adapter is used to display transaction list history
 *
 * @author bimosektiw
 */
public class TransactionHistoryAdapter extends ArrayAdapter<TransactionHistoryRsp>{

    private List<TransactionHistoryRsp> trxHistories;

    public TransactionHistoryAdapter (Context context, List<TransactionHistoryRsp> trxHistories){
        super(context, R.layout.transaction_detail_list, trxHistories);
        this.trxHistories = trxHistories;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater trxHistoryInflater = LayoutInflater.from(getContext());
        View customView = trxHistoryInflater.inflate(R.layout.transaction_detail_list, parent, false);

        TransactionHistoryRsp trxHistory = getItem(position);

        TextView textDate = (TextView) customView.findViewById(R.id.text_date);
        TextView textStatus = (TextView) customView.findViewById(R.id.text_status);

        textDate.setText(trxHistory.getTime());
        textStatus.setText(trxHistory.getStatus());

        return customView;

    }
}
