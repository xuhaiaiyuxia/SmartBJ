package com.itheima.smart.beijing.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itheima.smart.beijing.R;
import com.itheima.smart.beijing.page.BasePage;
import com.itheima.smart.beijing.pojo.NewsCenterData;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/20.
 */

public class LeftFragment extends BaseFragment {


    private  List<NewsCenterData.Data> data = new ArrayList<>();
    private MyAdapter mMyAdapter;
    private ListView mLv;

    @Override
    protected View initView() {
        /*TextView textView = new TextView(mActivity);
        textView.setText("left");
        textView.setTextSize(30);
        textView.setTextColor(Color.RED);*/
        mLv = new ListView(mActivity);
        mLv.setPadding(20,90,0,0);
        mLv.setDividerHeight(0);
        mLv.setBackgroundColor(Color.BLACK);
        mLv.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mMyAdapter = new MyAdapter();
        mLv.setAdapter(mMyAdapter);
        return mLv;
    }

    @Override
    protected void initData() {

    }

    public void setOnLeftMenuSelectedListener(OnLeftMenuSelectedListener onLeftMenuSelectedListener) {
        mOnLeftMenuSelectedListener = onLeftMenuSelectedListener;
    }

    private OnLeftMenuSelectedListener mOnLeftMenuSelectedListener;
    public interface OnLeftMenuSelectedListener {
        void selected(Integer index);
    }


    @Override
    protected void initEvent() {
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view,
                                    int position, long id) {
                int childCount = mLv.getChildCount();
                for (int i=0;i<childCount;i++) {
                    View viewChild = mLv.getChildAt(i);
                    viewChild.setEnabled(false);
                }
                view.setEnabled(true);
             /*   mActivity.getSlidingMenu().toggle();
                BasePage selectPage = mActivity.getRightFragment().getSelectPage();
                selectPage.selectPage(position);*/
                if (mOnLeftMenuSelectedListener!=null) {
                    mOnLeftMenuSelectedListener.selected(position);
                }
            }
        });
    }

    public void setMenuData(List<NewsCenterData.Data> menuData) {
        data = menuData;
        mMyAdapter.notifyDataSetChanged();
        Log.d("LeftFragment", menuData.get(0).title);
    }

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return data.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView view = null;
            if (convertView != null) {
                view = (TextView) convertView;
            } else {
                view = (TextView) View.inflate(mActivity, R.layout.item_left_fragment_textview, null);
            }
            String title = data.get(position).title;
            view.setText(title);
            if (position==0) {
                view.setEnabled(true);
            }
            return view;
        }
    }

}
