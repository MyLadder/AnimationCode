package tokyo.tommy_kw.viewanimator;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
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
}
