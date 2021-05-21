package com.govind.admin.bodytrainer.HomePage;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.govind.admin.bodytrainer.R;
import com.github.siyamed.shapeimageview.CircularImageView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 15-Mar-19.
 */

public class DashBoardAdapter extends RecyclerView.Adapter<DashBoardAdapter.MyViewHolder> {

    private final OpenDahsboardActivity mOpenDahsboardActivity;
    private Context mContext;
    ArrayList<DashboardBean> dataList;

    public DashBoardAdapter(Context mContext, ArrayList<DashboardBean> dataList, OpenDahsboardActivity mOpenDahsboardActivity) {
        this.mContext = mContext;
        this.mOpenDahsboardActivity = mOpenDahsboardActivity;
        this.dataList = dataList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.dashboard_list_lyt, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final DashboardBean beanObj = dataList.get(position);
        holder.txtTitle.setText(beanObj.getTitle());
        holder.icon.setImageDrawable(beanObj.getMIcon());
        holder.lytAdmissionForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOpenDahsboardActivity != null) {
                    mOpenDahsboardActivity.openDahsboardActivity(beanObj);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList.size());
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.icon)
        CircularImageView icon;
        @BindView(R.id.txtTitle)
        TextView txtTitle;
        @BindView(R.id.lytAdmissionForm)
        RelativeLayout lytAdmissionForm;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OpenDahsboardActivity {
        public void openDahsboardActivity(DashboardBean dashboard);
    }
}
