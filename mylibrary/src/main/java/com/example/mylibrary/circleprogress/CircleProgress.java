package com.example.mylibrary.circleprogress;

import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.example.mylibrary.R;

public class CircleProgress extends AppCompatTextView {

    private Paint arcPaint;
    private Paint circlePaint;
    private float angel = 0f;
    private int maxSteps = 0;
    private int currentStep = 0;
    private float strokeSize = 4f;

    private int circleBgColor = 0;
    private int strokeColor = 0;
    private ValueAnimator animator;

    public CircleProgress(Context context) {
        this(context, null);
    }

    public CircleProgress(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CircleProgress,
                0, 0
        );

        try {
            maxSteps = a.getInteger(R.styleable.CircleProgress_max_steps, 4);
            currentStep = a.getInteger(R.styleable.CircleProgress_current_step, currentStep);
            strokeSize = a.getDimension(R.styleable.CircleProgress_stroke_size, strokeSize);
            circleBgColor = a.getResourceId(R.styleable.CircleProgress_background_color, -1);
            strokeColor = a.getResourceId(R.styleable.CircleProgress_stroke_color, -1);
        } finally {
            a.recycle();
        }

        arcPaint = new Paint();
        arcPaint.setAntiAlias(true);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(strokeSize);
        arcPaint.setDither(true);
        arcPaint.setColor(ContextCompat.getColor(context, strokeColor));

        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(ContextCompat.getColor(context, circleBgColor));

        animator = new ValueAnimator();
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                angel = (Float) animation.getAnimatedValue("percentage");
                invalidate();
            }
        });
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        animateProgress();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();

        canvas.drawCircle(Math.abs(width / 2f), Math.abs(height / 2f), Math.abs(width / 2f), circlePaint);
        float strokeWidth = strokeSize / 2f;
        canvas.drawArc(
                0f + strokeWidth,
                strokeWidth,
                width - strokeWidth,
                height - strokeWidth,
                -90f, angel, false, arcPaint
        );
        super.onDraw(canvas);
        setGravity(Gravity.CENTER);
    }

    public void animateProgress() {
        animator.cancel();
        animator.setValues(PropertyValuesHolder.ofFloat("percentage", 0f, ((360f * currentStep) / maxSteps)));
        animator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int x = Math.min(widthMeasureSpec, heightMeasureSpec);
        if (x < 0) {
            int y = getMeasuredHeight();
            int z = getMeasuredWidth();
            int temp = Math.max(y, z);
            setMeasuredDimension(temp, temp);
        } else {
            setMeasuredDimension(x, x);
        }
    }

    public void setSteps(int currentStep, int maxSteps) {
        this.currentStep = currentStep;
        this.maxSteps = maxSteps;
        animateProgress();
    }

    public void setSteps(int currentStep) {
        setSteps(currentStep, this.maxSteps);
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        arcPaint.setColor(ContextCompat.getColor(getContext(), strokeColor));
        invalidate();
    }

    public void setCircleBgColor(int circleBgColor) {
        this.circleBgColor = circleBgColor;
        circlePaint.setColor(ContextCompat.getColor(getContext(), circleBgColor));
        invalidate();
    }
}
