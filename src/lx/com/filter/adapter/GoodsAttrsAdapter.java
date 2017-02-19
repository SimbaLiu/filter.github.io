package lx.com.filter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lx.com.filter.R;
import lx.com.filter.vo.SaleAttributeVo;

/**
 * 子属性GridView的适配器
 */
public class GoodsAttrsAdapter extends BaseAdapter {

    private Context context;
    private List<SaleAttributeVo> data = new ArrayList<SaleAttributeVo>();

    public GoodsAttrsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        final MyView myView;
        if (v == null) {
            myView = new MyView();
            v = View.inflate(context, R.layout.item_goods_attrs, null);
            myView.attr = (TextView) v.findViewById(R.id.attr_name);
            v.setTag(myView);
        } else {
            myView = (MyView) v.getTag();
        }
        myView.attr.setText(data.get(position).getValue());
        /**
         * 根据选中状态来设置item的背景和字体颜色
         */
        if (data.get(position).isChecked()) {
            myView.attr.setBackgroundResource(R.drawable.goods_attr_selected_shape);
            myView.attr.setTextColor(Color.WHITE);
        } else {
            myView.attr.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
            myView.attr.setTextColor(Color.GRAY);
        }
        return v;
    }

    static class MyView {
        public TextView attr;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void notifyDataSetChanged(boolean isUnfold,
                                     final List<SaleAttributeVo> tempData) {
        if (tempData == null || 0 == tempData.size()) {
            return;
        }
        data.clear();
        // 如果是展开的，则加入全部data，反之则只显示3条
        if (isUnfold) {
            data.addAll(tempData);
        } else {
            data.add(tempData.get(0));
            data.add(tempData.get(1));
            data.add(tempData.get(2));
        }
        notifyDataSetChanged();
    }

}
