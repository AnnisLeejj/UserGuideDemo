package com.lee.userguidedemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lee.user_guide.core.Controller;
import com.lee.user_guide.core.GuideBuilder;
import com.lee.user_guide.core.GuidePageBuilder;
import com.lee.user_guide.listener.OnGuideChangeListener;

public class MainActivity extends AppCompatActivity {
    TextView tvWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvWorld = findViewById(R.id.tvWorld);
        tvWorld.setOnClickListener(v -> {
            Toast.makeText(this, "高亮View被点击!", Toast.LENGTH_LONG).show();
        });
        initView();
    }

    public void initView() {
        GuideBuilder.with(this)
                .setGuideChangeListener(new OnGuideChangeListener() {
                    @Override
                    public void onShowed(Controller controller) {

                    }

                    @Override
                    public void onRemoved(Controller controller) {

                    }
                })
                .addGuidePage(GuidePageBuilder.newInstance()
                        .setView(R.layout.guide_view_first)
                        .setRemoveView(R.id.tbKnow)
                        .setTarget(tvWorld))
//                .addGuidePage(GuidePageBuilder.newInstance())
//                .addGuidePage(GuidePageBuilder.newInstance())
                .show();

    }
}