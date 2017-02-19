package lx.com.filter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import lx.com.filter.R;
import lx.com.filter.vo.SaleAttributeNameVo;

/**
 * 属性listview的适配器
 */
public class GoodsAttrListAdapter extends BaseAdapter {

    private Context context;
    private List<SaleAttributeNameVo> data;

    public GoodsAttrListAdapter(Context context, List<SaleAttributeNameVo> data) {
        this.context = context;
        this.data = data;
    }

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
            v = View.inflate(context, R.layout.item_goods_attr_list, null);
            myView.name = (TextView) v.findViewById(R.id.attr_list_name);
            myView.img = (ImageView) v.findViewById(R.id.attr_list_img);
            myView.grid = (GridView) v.findViewById(R.id.attr_list_grid);
            myView.grid.setSelector(new ColorDrawable(Color.TRANSPARENT));
            v.setTag(myView);
        } else {
            myView = (MyView) v.getTag();
        }
        myView.name.setText(data.get(position).getName());
        final GoodsAttrsAdapter adapter = new GoodsAttrsAdapter(context);
        myView.grid.setAdapter(adapter);
        adapter.notifyDataSetChanged(data.get(position).isNameIsChecked(), data.get(position).getSaleVo());
        myView.img.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (data.get(position).isNameIsChecked()) {
                    ((ImageView) v).setImageResource(R.drawable.sort_common_up);
                } else {
                    ((ImageView) v).setImageResource(R.drawable.sort_common_down);
                }
                adapter.notifyDataSetChanged(data.get(position).isNameIsChecked(), data.get(position).getSaleVo());
                data.get(position).setNameIsChecked(!data.get(position).isNameIsChecked());
            }
        });
        myView.grid.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //设置当前选中的位置的状态为非。
                data.get(position).getSaleVo().get(arg2).setChecked(!data.get(position).getSaleVo().get(arg2).isChecked());
                for (int i = 0; i < data.get(position).getSaleVo().size(); i++) {
                    //跳过已设置的选中的位置的状态
                    if (i == arg2) {
                        continue;
                    }
                    data.get(position).getSaleVo().get(i).setChecked(false);
                }
                if (!data.get(position).isNameIsChecked()) {
                    myView.img.setImageResource(R.drawable.sort_common_up);
                } else {
                    myView.img.setImageResource(R.drawable.sort_common_down);
                }
                adapter.notifyDataSetChanged(!data.get(position).isNameIsChecked(), data.get(position).getSaleVo());
            }
        });
        return v;
    }

    static class MyView {
        public TextView name;
        public ImageView img;
        public GridView grid;
    }

}
