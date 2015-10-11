package tokyo.tommy_kw.viewanimator

import android.animation.ObjectAnimator
import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.ImageView

/**
 * Created by tommy on 15/10/11.
 */
private val DURATION:Long = 3000

fun <T : View> Activity.fv(id : Int) : T = findViewById(id) as T

fun ImageView.anim(duration: Long = DURATION)
        = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f).setDuration(duration).start()
fun ImageView.animTranslationX(from: Float, to: Float, duration: Long = DURATION)
        = ObjectAnimator.ofFloat(this, "translationX", from, to).setDuration(duration).start()
fun ImageView.animTranslationY(from: Float, to: Float, duration: Long = DURATION)
        = ObjectAnimator.ofFloat(this, "translationY", from, to).setDuration(duration).start()
fun ImageView.animRotation(duration: Long = DURATION)
        = ObjectAnimator.ofFloat(this, "rotation", 0f, 360f).setDuration(duration).start()
