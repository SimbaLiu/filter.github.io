package lx.com.filter.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import lx.com.filter.R;
import lx.com.filter.view.FilterPopupWindow;
import lx.com.filter.view.PricePopup;
import lx.com.filter.vo.Vo;

public class MainActivity extends Activity {

    private View main;
    private LinearLayout priceLayout;
    private LinearLayout filterLayout;
    private PricePopup pricePopup;
    private FilterPopupWindow popupWindow;
    private List<Vo> data = new ArrayList<Vo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        main = findViewById(R.id.main);
        priceLayout = (LinearLayout) findViewById(R.id.ranking_price);
        filterLayout = (LinearLayout) findViewById(R.id.ranking_filter);

        Vo vo1 = new Vo();
        vo1.setStr1("i3");
        vo1.setStr2("双核双线程");
        Vo vo2 = new Vo();
        vo2.setChecked(true);
        vo2.setStr1("i5");
        vo2.setStr2("双核四线程");
        Vo vo3 = new Vo();
        vo3.setStr1("i7");
        vo3.setStr2("四核八线程");
        data.add(vo1);
        data.add(vo2);
        data.add(vo3);
        // 价格点击监听
        priceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pricePopup = new PricePopup(MainActivity.this, data);
                pricePopup.showPricePopup(view, data);
            }
        });
        // 筛选点击监听
        filterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow = new FilterPopupWindow(MainActivity.this);
                popupWindow.showFilterPopup(main);
            }
        });
    }
}
