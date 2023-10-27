package com.daizzyinfo.food18.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.daizzyinfo.food18.R;
import com.daizzyinfo.food18.activities.MyCart;
import com.daizzyinfo.food18.databinding.FragmentAllItemsBottomSheetBinding;
import com.daizzyinfo.food18.viewModel.ProductList.ProductListResponse;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class AllItemsBottomSheet extends BottomSheetDialogFragment {
    private FragmentAllItemsBottomSheetBinding binding;
    private static final String TAG = AllItemsBottomSheet.class.getSimpleName();
    private String productId;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAllItemsBottomSheetBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        initOnClickListener();
        getData();

        return view;
    }

    private void getData() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            productId = arguments.getString("ProductId");
            Log.e(TAG, "ProductId: " + productId);
           }
    }

    private void initOnClickListener() {
        binding.txtBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyCart.class);
                startActivity(intent);
            }
        });

        binding.imgCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;

    }
}
