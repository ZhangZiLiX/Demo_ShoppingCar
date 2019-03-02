package com.example.administrator.demo_shoppingcar.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.demo_shoppingcar.R;

/**
 * Author : 张自力
 * Created on time.
 *
 * 圈子
 */

public class CircleFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.circle_fragment,null);

        return view;
    }
}
