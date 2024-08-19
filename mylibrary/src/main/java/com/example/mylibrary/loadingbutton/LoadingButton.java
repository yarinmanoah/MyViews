package com.example.mylibrary.loadingbutton;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.mylibrary.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.progressindicator.CircularProgressIndicator;

public class LoadingButton extends FrameLayout {

    private MaterialButton button;
    private MaterialCardView container;
    private CircularProgressIndicator progressIndicator;
    private String text;
    private int bgColor, strokeColor, textColor, progressColor;
    private float strokeSize, textSize, radiusSize;

    public LoadingButton(@NonNull Context context) {
        super(context);
        initView(null);
    }

    public LoadingButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public LoadingButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    public LoadingButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(attrs);
    }

    private void initView(AttributeSet attrs){
        LayoutInflater.from(getContext()).inflate(R.layout.loading_button, this);
        button = findViewById(R.id.button);
        container = findViewById(R.id.container);
        progressIndicator = findViewById(R.id.progress);

        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.LoadingButton,
                0, 0);

        try {
            text= typedArray.getString(R.styleable.LoadingButton_text);

            bgColor = typedArray.getColor(R.styleable.LoadingButton_button_background, ContextCompat.getColor(getContext(), android.R.color.white));
            strokeColor = typedArray.getColor(R.styleable.LoadingButton_button_stroke_color, ContextCompat.getColor(getContext(), android.R.color.transparent));
            textColor = typedArray.getColor(R.styleable.LoadingButton_button_text_color, ContextCompat.getColor(getContext(), android.R.color.black));
            progressColor = typedArray.getColor(R.styleable.LoadingButton_progress_color, ContextCompat.getColor(getContext(), android.R.color.black));
            strokeSize = typedArray.getDimension(R.styleable.LoadingButton_button_stroke_size,0f);
            textSize = typedArray.getDimension(R.styleable.LoadingButton_button_text_size,32f);
            radiusSize = typedArray.getDimension(R.styleable.LoadingButton_radius_size,container.getRadius());
            updateView();

        } finally {
            typedArray.recycle();
        }
    }

    private void updateView() {
        button.setText(text);
        container.setStrokeWidth((int) strokeSize);
        container.setBackgroundTintList(ColorStateList.valueOf(bgColor));
        container.setStrokeColor(ColorStateList.valueOf(strokeColor));
        button.setTextColor(textColor);
        progressIndicator.setIndicatorColor(progressColor);
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        container.setRadius(radiusSize);
    }

    public void startLoader() {
        progressIndicator.show();
        button.setVisibility(INVISIBLE);
        this.setEnabled(false);
    }

    public void stopLoader() {
        button.setVisibility(VISIBLE);
        progressIndicator.setVisibility(INVISIBLE);
        this.setEnabled(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (container != null) {
            LayoutParams params = new LayoutParams(w, h);
            params.gravity = Gravity.CENTER;
            container.setLayoutParams(params);
        }
    }

    public void setText(String text) {
        this.text = text;
        button.setText(text);
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
        container.setBackgroundTintList(ColorStateList.valueOf(bgColor));
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        container.setStrokeColor(ColorStateList.valueOf(bgColor));
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        button.setTextColor(textColor);
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
        this.progressIndicator.setIndicatorColor(progressColor);
    }

    public void setStrokeSize(float strokeSize) {
        this.strokeSize = strokeSize;
        container.setStrokeWidth((int) strokeSize);
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }

    public void setRadiusSize(float radiusSize) {
        this.radiusSize = radiusSize;
        container.setRadius(radiusSize);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        OnClickListener clickListener = new OnClickListener() {
            @Override
            public void onClick(View view) {
                l.onClick(view);
                startLoader();
            }
        };
        super.setOnClickListener(clickListener);

    }
}
