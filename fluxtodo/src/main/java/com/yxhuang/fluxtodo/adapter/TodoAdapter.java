package com.yxhuang.fluxtodo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.yxhuang.fluxtodo.R;
import com.yxhuang.fluxtodo.data.DataType;
import com.yxhuang.fluxtodo.data.TodoData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yxhuang
 * Date: 2018/8/29
 * Description:
 */
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.VHolder> {

    private Context mContext;
    private List<TodoData> mDataList = new ArrayList<>();
    private ICheckboxSelect mCheckboxSelect;


    public TodoAdapter(Context context, List dataList){
        mContext = context;
        mDataList = dataList;
    }

    public void update(List dataList){
        mDataList = dataList;
        notifyDataSetChanged();
    }

    public void setCheckboxSelect(ICheckboxSelect select){
        mCheckboxSelect = select;
    }


    @NonNull
    @Override
    public TodoAdapter.VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_layout, parent, false);
        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.VHolder holder, final int position) {
        final TodoData data = mDataList.get(position);
        if (data != null){
            holder.tv_content.setText(data.getContent());
            if (data.getType() == DataType.COMPLETE){
                holder.checkbox.setChecked(true);
            } else {
                holder.checkbox.setChecked(false);
            }

            if (mCheckboxSelect != null){
                holder.checkbox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mCheckboxSelect.onSelected(position, data);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }


    static class VHolder extends RecyclerView.ViewHolder{
        CheckBox checkbox;
        TextView tv_content;


        public VHolder(View itemView) {
            super(itemView);

            checkbox =  itemView.findViewById(R.id.checkbox);
            tv_content = itemView.findViewById(R.id.tv_content);

        }
    }

    public interface ICheckboxSelect {
        void onSelected(int index, TodoData data);
    }
}
