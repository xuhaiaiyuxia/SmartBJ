package com.itheima.smart.beijing.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itheima.smart.beijing.activity.HomeActivity;

public abstract class BaseFragment extends Fragment {

    protected HomeActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity =(HomeActivity) getActivity();
        View view = initView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
        initEvent();
    }

    protected abstract View initView();
    protected abstract void initData();
    protected abstract void initEvent();

}
