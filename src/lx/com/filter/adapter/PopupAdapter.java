package lx.com.filter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lx.com.filter.R;
import lx.com.filter.vo.Vo;

/**
 *
 */
public class PopupAdapter extends BaseAdapter {

    private Context context;
    private List<Vo> data = new ArrayList<Vo>();

    public PopupAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item, null);
            viewHolder.tv1 = (TextView) view.findViewById(R.id.tv1);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (data != null && data.size() > 0) {
            viewHolder.tv1.setText(data.get(i).getStr1());
            if (data.get(i).isChecked()){
                viewHolder.tv1.setBackgroundResource(R.drawable.goods_attr_selected_shape);
                viewHolder.tv1.setTextColor(Color.WHITE);
            } else {
                viewHolder.tv1.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
                viewHolder.tv1.setTextColor(Color.GRAY);
            }
        }
        return view;
    }

    static class ViewHolder {
        public TextView tv1;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void notifyDataSetChanged(List<Vo> tempData) {
        if (tempData == null || tempData.size() == 0) {
            return;
        }
        data.clear();
        data.addAll(tempData);
        notifyDataSetChanged();
    }
}
