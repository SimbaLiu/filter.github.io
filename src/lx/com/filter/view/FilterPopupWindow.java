package lx.com.filter.view;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import lx.com.filter.R;
import lx.com.filter.adapter.GoodsAttrListAdapter;
import lx.com.filter.adapter.GoodsAttrsAdapter;
import lx.com.filter.vo.SaleAttributeNameVo;
import lx.com.filter.vo.SaleAttributeVo;

/**
 * 筛选商品属性选择的popupwindow
 */
public class FilterPopupWindow extends PopupWindow {
    private View contentView;
    private Context context;
    private View goodsNoView;

    private GridView serviceGrid;
    private ListView selectionList;
    private TextView filterReset;
    private TextView filterSure;
    private GoodsAttrListAdapter adapter;
    private GoodsAttrsAdapter serviceAdapter;
    private List<SaleAttributeNameVo> itemData;
    private List<SaleAttributeVo> serviceList;
    private String[] serviceStr = new String[]{"仅看有货", "促销", "手机专享"};

    /**
     * 商品属性选择的popupwindow
     */
    public FilterPopupWindow(final Activity context) {
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.popup_goods_details, null);
        goodsNoView = contentView.findViewById(R.id.popup_goods_noview);
        serviceGrid = (GridView) contentView.findViewById(R.id.yuguo_service);
        selectionList = (ListView) contentView.findViewById(R.id.selection_list);
        filterReset = (TextView) contentView.findViewById(R.id.filter_reset);
        filterSure = (TextView) contentView.findViewById(R.id.filter_sure);

        goodsNoView.setOnClickListener(new CancelOnClickListener());
        contentView.setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
                    dismiss();
                }
                return true;
            }
        });
        serviceList = new ArrayList<SaleAttributeVo>();
        for (int i = 0; i < serviceStr.length; i++) {
            SaleAttributeVo vo = new SaleAttributeVo();
            vo.setValue(serviceStr[i]);
            serviceList.add(vo);
        }
        serviceAdapter = new GoodsAttrsAdapter(context);
        serviceGrid.setAdapter(serviceAdapter);
        serviceAdapter.notifyDataSetChanged(true, serviceList);
        serviceGrid.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                //设置当前选中的位置的状态为非。
                serviceList.get(arg2).setChecked(!serviceList.get(arg2).isChecked());
                for (int i = 0; i < serviceList.size(); i++) {
                    //跳过已设置的选中的位置的状态
                    if (i == arg2) {
                        continue;
                    }
                    serviceList.get(i).setChecked(false);
                }
                serviceAdapter.notifyDataSetChanged(true, serviceList);
            }
        });

        itemData = new ArrayList<SaleAttributeNameVo>();
        adapter = new GoodsAttrListAdapter(context, itemData);
        selectionList.setAdapter(adapter);
        String str = "["
                + "{\"nameId\":\"V2QASD\",\"saleVo\":["
                + "{\"value\":\"2核\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"1\"},"
                + "{\"value\":\"4核\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"6核\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"8核\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"}"
                + "],\"name\":\"CPU\"},"
                + "{\"nameId\":\"V2QASD\",\"saleVo\":["
                + "{\"value\":\"全网通\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"移动4G\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"1\"},"
                + "{\"value\":\"电信4G\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"联通4G\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"}"
                + "],\"name\":\"网络制式\"},"
                + "{\"nameId\":\"V2QASD\",\"saleVo\":["
                + "{\"value\":\"OPPO\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"荣耀\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"苹果\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"1\"},"
                + "{\"value\":\"鸭梨\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"月饼\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"vivo\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"}"
                + "],\"name\":\"品牌\"},"
                + "{\"nameId\":\"V2QASD\",\"saleVo\":["
                + "{\"value\":\"音乐\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"1\"},"
                + "{\"value\":\"拍照\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"待机长\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"}"
                + "],\"name\":\"主打\"},"
                + "{\"nameId\":\"V2QLAH\",\"saleVo\":["
                + "{\"value\":\"4.5英寸\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"5英寸\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"5.5英寸\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"0\"},"
                + "{\"value\":\"6英寸\",\"goods\":null,\"goodsAndValId\":\"C6VOWQ\",\"checkStatus\":\"1\"}"
                + "],\"name\":\"尺寸\"}" + "]";
        JSONArray json = null;
        try {
            json = new JSONArray(str);
            refreshAttrs(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // 重置的点击监听，将所有选项全设为false
        filterReset.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                for (int i = 0; i < itemData.size(); i++) {
                    for (int j = 0; j < itemData.get(i).getSaleVo().size(); j++) {
                        itemData.get(i).getSaleVo().get(j).setChecked(false);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
        // 确定的点击监听，将所有已选中项列出
        filterSure.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String str = "";
                for (int i = 0; i < itemData.size(); i++) {
                    for (int j = 0; j < itemData.get(i).getSaleVo().size(); j++) {
                        if (itemData.get(i).getSaleVo().get(j).isChecked()) {
                            str = str + itemData.get(i).getSaleVo().get(j).getValue();
                        }
                    }
                }
                Toast.makeText(FilterPopupWindow.this.context, str, Toast.LENGTH_SHORT).show();
            }
        });

        this.setContentView(contentView);
        this.setWidth(LayoutParams.MATCH_PARENT);
        this.setHeight(LayoutParams.MATCH_PARENT);
        ColorDrawable dw = new ColorDrawable(00000000);
        this.setBackgroundDrawable(dw);
        this.setFocusable(true);
        this.setOutsideTouchable(false);
        this.update();

    }

    /**
     * 刷新商品属性
     *
     * @param json
     * @throws JSONException
     */
    public void refreshAttrs(JSONArray json) throws JSONException {
        itemData.clear();
        for (int i = 0; i < json.length(); i++) {
            SaleAttributeNameVo saleName = new SaleAttributeNameVo();
            JSONObject obj = (JSONObject) json.opt(i);
            saleName.setName(obj.getString("name"));
            List<SaleAttributeVo> list = new ArrayList<SaleAttributeVo>();
            net.sf.json.JSONArray array = new net.sf.json.JSONArray();
            array = net.sf.json.JSONArray.fromObject(obj.getString("saleVo"));
            for (int j = 0; j < array.size(); j++) {
                net.sf.json.JSONObject object = array.getJSONObject(j);
                SaleAttributeVo vo = new SaleAttributeVo();
                vo.setGoods(object.getString("goods"));
                vo.setValue(object.getString("value"));
                vo.setGoodsAndValId(object.getString("goodsAndValId"));
                if ("1".equals(object.getString("checkStatus"))) {
                    vo.setChecked(true);
                } else {
                    vo.setChecked(false);
                }
                list.add(vo);
            }
            saleName.setSaleVo(list);
            // 是否展开
            saleName.setNameIsChecked(false);
            itemData.add(saleName);
        }
        adapter.notifyDataSetChanged();
    }

    public class CancelOnClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    }

    public boolean onKeyDown(Context context, int keyCode, KeyEvent event) {
        this.context = context;
        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
            dismiss();
        }
        return true;
    }

    public void showFilterPopup(View parent) {
        if (!this.isShowing()) {
            this.showAsDropDown(parent);
        } else {
            this.dismiss();
        }
    }

}
