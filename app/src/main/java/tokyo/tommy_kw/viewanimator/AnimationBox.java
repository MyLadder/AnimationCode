package tokyo.tommy_kw.viewanimator;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.CompoundButton;

/**
 * Created by tommy on 2016/05/17.
 */
public class AnimationBox extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int radius;
    private RectF rectF = new RectF();
    private RectF innerRectF = new RectF();
    private Path path = new Path();
    private float sweepAngle;
    private final double sin27 = Math.sin(Math.toRadians(27));
    private final double sin63 = Math.sin(Math.toRadians(63));
    private float hookStartY;
    private float baseLeftHookOffset;
    private float baseRightHookOffset;
    private float endLeftHookOffset;
    private float endRightHookOffset;
    private int size;
    private boolean checked = true;
    private float hookOffset;
    private float hookSize;
    private int innerCircleAlpha = 0xFF;
    private int strokeWidth = 2;
    private final int duration = 5000;
    private int strokeColor = Color.BLUE;
    private int circleColor = Color.WHITE;
    private final int defaultSize = 40;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;

    public AnimationBox(Context context) {
        super(context);
    }

    public AnimationBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimationBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AnimationBox(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void repare(AttributeSet attrs) {
        if (attrs == null) {
            strokeWidth = dipToInt(strokeWidth);
        } else {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.AnimationBox);
            strokeWidth = (int)ta.getDimension(R.styleable.AnimationBox_stroke_width, dipToInt(strokeWidth));
            strokeColor = ta.getColor(R.styleable.AnimationBox_stroke_color, strokeColor);
            circleColor = ta.getColor(R.styleable.AnimationBox_circle_color, circleColor);
            ta.recycle();
        }

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(strokeColor);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setChecked(!checked);
            }
        });
    }

    public void setChecked(boolean chk) {
        setChecked(chk, true);
    }

    public void setChecked(boolean chk, boolean animation) {
        if (chk == checked) {
            return;
        }

        checked = chk;
        if (animation) {
            startAnim();
        }
    }

    private void startAnim() {
        clearAnimation();
        if (checked) {
            startCheckedAnim();
        } else {
            startUnCheckedAnim();
        }
    }

    private int getAlphaColor(int color, int alpha) {
        alpha = alpha < 0 ? 0 : alpha;
        alpha = alpha > 255 ? 255 : alpha;
        return (color & 0x00FFFFFF) | alpha << 24;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        setChecked(checked, true);
    }

    public void setChecked(boolean chk, boolean animation) {
        if (chk == checked) {
            return;
        }

        checked = chk;
        if (animation) {
            startAnim();
        } else {
            if (checked) {
                innerCircleAlpha = 0xFF;
                sweepAngle = 0;
                hookOffset = hookSize + endLeftHookOffset - baseLeftHookOffset;
            } else {
                innerCircleAlpha = 0x00;
                sweepAngle = 360;
                hookOffset = 0;
            }
            invalidate();
        }
        if (onCheckedChangeListener != null) {
            onCheckedChangeListener.onCheckedChanged(checked);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void startCheckedAnim() {
        ValueAnimator animator = new ValueAnimator();
        final float hookMaxValue = hookSize - endLeftHookOffset - baseLeftHookOffset;
        final float circleMinFraction = (endLeftHookOffset - baseLeftHookOffset) / hookMaxValue;
        final float circleMaxValue = 360 / (1 - circleMinFraction);
        animator.setFloatValues(0, 1);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float circleFraction = animation.getAnimatedFraction();
                float fraction = 1 - circleFraction;
                hookOffset = fraction * hookMaxValue;
                if (circleFraction >= circleMinFraction) {
                    sweepAngle = (int) ((circleFraction - circleMinFraction) * circleMaxValue);
                } else {
                    sweepAngle = 0;
                }
                innerCircleAlpha = (int)(fraction * 0xFF);
                invalidate();
            }
        });
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(duration).start();
    }

    private void startUnCheckedAnim() {
    }

    private int dipToInt(int dip) {
        return (int)getContext().getResources().getDisplayMetrics().density * dip;
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }

    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener listener) {
        this.onCheckedChangeListener = listener;
    }

    public interface OnCheckedChangeListener {
        void onChange(boolean checked);
    }

}
