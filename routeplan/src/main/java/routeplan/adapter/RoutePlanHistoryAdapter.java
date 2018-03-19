package routeplan.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.amap.api.maps.model.Poi;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import routeplan.HistoryPoi;
import routeplan.R;
import routeplan.R2;

/**
 * created by czh on 2018-01-22
 */

public class RoutePlanHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<HistoryPoi.RouteRecord> models;
    private Context mContext;

    private final  int TYPE_HISTORY = 1003;
    private final  int TYPE_CLEAR = 1004;

    public RoutePlanHistoryAdapter(Context context, List<HistoryPoi.RouteRecord> list){
        mContext=context;
        models=list;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==models.size()){
            return TYPE_CLEAR;
        }else {
            return TYPE_HISTORY;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType==TYPE_HISTORY){
            view= LayoutInflater.from(mContext).inflate(R.layout.item_transfer_history,null);
            return new ViewHolder(view);
        }else if (viewType==TYPE_CLEAR){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_transfer_clear_history, null);
            return new ClearViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ClearViewHolder) {//清除历史
            ClearViewHolder clearViewHolder = (ClearViewHolder) holder;
            if (models.size() == 0) {
                clearViewHolder.itemView.setVisibility(View.GONE);
            } else {
                clearViewHolder.itemView.setVisibility(View.VISIBLE);
                clearViewHolder.clearHistoryTxt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new AlertDialog.Builder(mContext)
                                .setMessage(mContext.getString(R.string.route_plan_is_clear_history))
                                .setPositiveButton("Yes", (dialog, button) ->{
                                    HistoryPoi hp=new HistoryPoi(mContext);
                                    hp.Clear();
                                    models.clear();
                                    notifyDataSetChanged();
                                })
                                .setNegativeButton("Cancel", (dialog, button) ->{
                                    dialog.dismiss();
                                })
                                .show();
                    }
                });
            }
        }else if (holder instanceof ViewHolder){
            ViewHolder viewHolder=(ViewHolder)holder;
            final HistoryPoi.RouteRecord record = models.get(models.size()-1-position);
            final StringBuilder sb = new StringBuilder();
            sb.append(" ");
            sb.append(record.startPoi.getName());
            sb.append(" ");
            sb.append(record.targetPoi.getName());
            viewHolder.historyTxt.setText(sb.toString());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListner!=null){
                        mListner.itemOnclick(record);
                    }
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    new AlertDialog.Builder(mContext)
                            .setMessage(mContext.getString(R.string.route_plan_is_delete))
                            .setPositiveButton("Yes", (dialog, button) ->{
                                HistoryPoi hp=new HistoryPoi(mContext);
                                models.remove(record);
                                hp.updateHistoryPoi(models);
                                notifyDataSetChanged();
                            })
                            .setNegativeButton("Cancel", (dialog, button) ->{
                                dialog.dismiss();
                            })
                            .show();

                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return models.size()+1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R2.id.historyTxt)
        TextView historyTxt;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    static class ClearViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.clearHistoryTxt)
        TextView clearHistoryTxt;

        ClearViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private  HistoryItemOnclickListner mListner;
    public void setListner(HistoryItemOnclickListner listner){
        mListner=listner;
    }
    public static interface HistoryItemOnclickListner{
        public void itemOnclick(HistoryPoi.RouteRecord record);
    }
}
