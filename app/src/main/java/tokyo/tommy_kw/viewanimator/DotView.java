package tokyo.tommy_kw.viewanimator;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tommy on 2016/05/15.
 */
public class DotView extends View {
    private static final int DOT_COUNT = 7;
    private static final int OUTER_DOT_POSITION_ANGLE = 51;

    private int COLOR_1 = 0xFFFFC107;
    private int COLOR_2 = 0xFFFF9800;
    private int COLOR_3 = 0xFFFF5722;
    private int COLOR_4 = 0xFFF44336;

    private int width = 0;
    private int height = 0;

    private final Paint[] circlePaints = new Paint[4];

    private int centerX;
    private int centerY;

    private float maxOuterDotRadius;
    private float maxInnerDotRadius;
    private float maxDotSize;

    private float currentProgress = 0;

    private float currentRadius1 = 0;
    private float currentDotSize1 = 0;

    private float currentDotSize2 = 0;
    private float currentRadius2 = 0;

    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    public DotView(Context context) {
        super(context);
        init();
    }

    public DotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        for (int i = 0; i < circlePaints.length; i++) {
            circlePaints[i] = new Paint();
            circlePaints[i].setStyle(Paint.Style.FILL);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;
        maxDotSize = 5;
        maxOuterDotRadius = w / 2 - maxDotSize * 2;
        maxInnerDotRadius = 0.0f * maxOuterDotRadius;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawOuterDotFrame(canvas);
        drawInnerDotFrame(canvas);
    }

    private void drawOuterDotFrame(Canvas canvas) {
    }

    private void drawInnerDotFrame(Canvas canvas) {

    }
}
