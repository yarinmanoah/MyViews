package com.example.mylibrary.initialsindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.core.widget.TextViewCompat;

import com.bumptech.glide.Glide;
import com.example.mylibrary.R;

public class InitialsIndicator extends FrameLayout {

    private final GradientDrawable gradient = new GradientDrawable();
    private TextView textView;
    private ImageView imageView;

    public InitialsIndicator(@NonNull Context context) {
        this(context, null);
    }

    public InitialsIndicator(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InitialsIndicator(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBackground(attrs);
        textView.setGravity(Gravity.CENTER);
    }


    private void initBackground(@Nullable AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.initials_indicator, this);
        textView = this.findViewById(R.id.text);
        imageView = this.findViewById(R.id.image);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.InitialsIndicator, 0, 0);
        int shapeIndex = a.getInteger(
                R.styleable.InitialsIndicator_indicatorShape,
                InitialsIndicatorShape.valueOf(InitialsIndicatorShape.CIRCLE.name()).ordinal()
        );

        int textColor = a.getColor(
                R.styleable.InitialsIndicator_indicator_text_color,
                ContextCompat.getColor(getContext(), R.color.black));


        int backgroundColor = a.getColor(
                R.styleable.InitialsIndicator_indicator_background_color,
                ContextCompat.getColor(getContext(), R.color.white));

        String text = a.getString(
                R.styleable.InitialsIndicator_indicator_text);

        int textStyleResId = a.getResourceId(R.styleable.InitialsIndicator_indicator_text_style, -1);
        if (textStyleResId != -1) {
            TextViewCompat.setTextAppearance(textView, textStyleResId);
        }

        float textSize = a.getDimension(R.styleable.InitialsIndicator_indicator_text_size, 16f);
        textView.setTextSize(textSize);

        setShape(InitialsIndicatorShape.values()[shapeIndex]);
        setBackgroundColor(backgroundColor);
        textView.setText(text);
        textView.setTextColor(textColor);
        super.setBackground(gradient);
        a.recycle();
    }

    public void setInitialsText(String text, int howManyFirstWords) {
        if (text != null) {
            textView.setText(getInitials(text, howManyFirstWords));
        } else {
            textView.setText("");
        }
       imageView.setVisibility(GONE);
    }

    public void setImageUrl(String url) {
        if (gradient.getShape() == GradientDrawable.OVAL) {
            Glide.with(this)
                    .load(url)
                    .centerCrop()
                    .circleCrop()
                    .into(imageView);
        } else {
            Glide.with(this)
                    .load(url)
                    .centerCrop()
                    .into(imageView);
        }

        textView.setVisibility(GONE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (widthMeasureSpec > heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
            gradient.setSize(widthMeasureSpec, widthMeasureSpec);

        } else {
            super.onMeasure(heightMeasureSpec, heightMeasureSpec);
            gradient.setSize(heightMeasureSpec, heightMeasureSpec);

        }

    }


    @Override
    public void setBackgroundColor(int color) {
        gradient.setColor(color);
    }

    public void setShape(InitialsIndicatorShape shape) {
        gradient.setShape(
                shape == InitialsIndicatorShape.CIRCLE ? GradientDrawable.OVAL : GradientDrawable.RECTANGLE
        );
    }

    public void setTextStyle(@StyleRes int resId) {
        TextViewCompat.setTextAppearance(textView, resId);
    }

    public static String getInitials(String input, int howManyFirstWords) {
        if (input == null) {
            return "AB";
        }

        StringBuilder initials = new StringBuilder();
        String[] words = input.split(" ");

        int count = 0;
        for (String word : words) {
            if (word.isBlank()) {
                continue;
            }
            initials.append(word.toUpperCase().charAt(0));
            count++;
            if (count == howManyFirstWords) {
                break;
            }
        }

        return initials.length() > 0 ? initials.toString() : "AB";
    }

}