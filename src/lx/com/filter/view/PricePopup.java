package lx.com.filter.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import lx.com.filter.R;
import lx.com.filter.adapter.PopupAdapter;
import lx.com.filter.vo.Vo;

public class PricePopup extends PopupWindow {

    private View contentView;
    private GridView grid;
    private TextView reset;
    private TextView ok;
    private PopupAdapter adapter;
    private List<Vo> data;

    public PricePopup(final Activity context, final List<Vo> data) {
        this.data = data;
        adapter = new PopupAdapter(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.popup, null);
        grid = (GridView) contentView.findViewById(R.id.grid);
        reset = (TextView) contentView.findViewById(R.id.reset);
        ok = (TextView) contentView.findViewById(R.id.ok);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                data.get(position).setChecked(!data.get(position).isChecked());
                for (int i = 0; i < data.size(); i++) {
                    if (i == position) {
                        continue;
                    }
                    data.get(i).setChecked(false);
                }
                Toast.makeText(context, data.get(position).getStr2(), Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged(data);
            }
        });

        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        this.setContentView(contentView);
        this.setWidth(w);
        this.setHeight(h);
        ColorDrawable dw = new ColorDrawable(00000000);
        this.setBackgroundDrawable(dw);
        this.setFocusable(true);
        this.setOutsideTouchable(false);
        this.update();

    }

    public void showPricePopup(View parent, final List<Vo> data) {
        if (!this.isShowing()) {
            this.showAsDropDown(parent);
            adapter.notifyDataSetChanged(data);
        } else {
            this.dismiss();
        }
    }

}
