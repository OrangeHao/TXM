package routeplan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.services.route.WalkStep;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import routeplan.R;
import routeplan.R2;
import routeplan.overlay.AMapUtil;

/**
 * created by czh on 2018-01-13
 */

public class WalkStepAdapter extends RecyclerView.Adapter{
    private Context mContext;
    List<WalkStep> mItemList = new ArrayList<WalkStep>();

    public WalkStepAdapter(Context context, List<WalkStep> list) {
        mContext = context;
        mItemList.add(new WalkStep());
        mItemList.addAll(list);
        mItemList.add(new WalkStep());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view= LayoutInflater.from(mContext).inflate(R.layout.item_bus_segment,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder=(ViewHolder)holder;

        final WalkStep item = mItemList.get(position);
        if (position == 0) {
            viewHolder.driveDirIcon.setImageResource(R.drawable.dir_start);
            viewHolder.driveLineName.setText("出发");
            viewHolder.splitLine.setVisibility(View.GONE);
        } else if (position == mItemList.size() - 1) {
            viewHolder.driveDirIcon.setImageResource(R.drawable.dir_end);
            viewHolder.driveLineName.setText("到达终点");
            viewHolder.splitLine.setVisibility(View.VISIBLE);
        } else {
            String actionName = item.getAction();
            int resID = AMapUtil.getDriveActionID(actionName);
            viewHolder.driveDirIcon.setImageResource(resID);
            viewHolder.driveLineName.setText(item.getInstruction());
            viewHolder.splitLine.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R2.id.bus_line_name)
        TextView driveLineName;
        @BindView(R2.id.bus_dir_icon)
        ImageView driveDirIcon;
        @BindView(R2.id.bus_seg_split_line)
        ImageView splitLine;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
