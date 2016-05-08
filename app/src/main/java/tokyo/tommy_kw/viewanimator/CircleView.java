package tokyo.tommy_kw.viewanimator;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by tommy on 2016/05/09.
 */
public class CircleView extends View {
    private static int START_COLOR = 0xFFFF1000;
    private static int END_COLOR = 0xFFFF2000;
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
