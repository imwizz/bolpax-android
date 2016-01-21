package id.co.imwizz.bolpax.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import id.co.imwizz.bolpax.R;
import id.co.imwizz.bolpax.data.entity.TrxHistory;

/**
 * Created by bimosektiw on 1/12/16.
 */
public class TransactionHistoryAdapter extends ArrayAdapter<TrxHistory>{

    private List<TrxHistory> trxHistories;

    public TransactionHistoryAdapter (Context context, List<TrxHistory> trxHistories){
        super(context, R.layout.transaction_detail_list, trxHistories);
        this.trxHistories = trxHistories;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater trxHistoryInflater = LayoutInflater.from(getContext());
        View customView = trxHistoryInflater.inflate(R.layout.transaction_detail_list, parent, false);

        TrxHistory trxHistory = getItem(position);

        TextView trxhistoryDate = (TextView) customView.findViewById(R.id.date);
        TextView trxhistoryStatus = (TextView) customView.findViewById(R.id.trxstatus);

        trxhistoryDate.setText(trxHistory.getTime());
        trxhistoryStatus.setText(trxHistory.getStatus());

        return customView;

    }
}
