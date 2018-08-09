package com.test.compl.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.compl.nerdlauncher.R;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NerdFragment extends Fragment {


    public static final String TAG = "NerdFragment";
    private RecyclerView mRecyclerView;
    private MyRecyclerAdapter mRecyclerAdapter;
    private PackageManager mPackageManager;
//    private

    public static NerdFragment newInstance() {
        NerdFragment fragment = new NerdFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPackageManager = getActivity().getPackageManager();
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nerd, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setAdapter();
        return view;
    }

    public class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTextView;
        private ImageView mLogo;
        private ResolveInfo mResolveInfo;

        public MyHolder(View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.app_name);
            mLogo = itemView.findViewById(R.id.app_icon);
            itemView.setOnClickListener(this);
        }

        public void bindData(ResolveInfo info) {
            mResolveInfo = info;
            String appName = info.loadLabel(mPackageManager).toString();
            mTextView.setText(appName);
            mLogo.setImageDrawable(info.loadIcon(mPackageManager));
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            ActivityInfo activityInfo = mResolveInfo.activityInfo;
            intent.setClassName(activityInfo.applicationInfo.packageName, activityInfo.name);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // 在一个新建的任务中打开Activity
            startActivity(intent);
        }
    }

    public class MyRecyclerAdapter extends RecyclerView.Adapter<MyHolder> {

        private List<ResolveInfo> mActivities;
        public MyRecyclerAdapter(List<ResolveInfo> resolveInfos) {
            mActivities = resolveInfos;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_app, parent, false);

            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.bindData(mActivities.get(position));
        }

        @Override
        public int getItemCount() {
            return mActivities.size();
        }
    }

    public void setAdapter() {
        final PackageManager pm = getActivity().getPackageManager();
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> mResloves = pm.queryIntentActivities(intent, PackageManager.MATCH_ALL);
        Collections.sort(mResloves, new Comparator<ResolveInfo>() {
            @Override
            public int compare(ResolveInfo o1, ResolveInfo o2) {
                return String.CASE_INSENSITIVE_ORDER.compare(o1.loadLabel(pm).toString(), o2.loadLabel(pm).toString());
            }
        });

        mRecyclerAdapter = new MyRecyclerAdapter(mResloves);
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }
}
