package tokyo.tommy_kw.viewanimator

import android.animation.*
import android.app.Activity
import android.graphics.Color
import android.util.Log
import android.view.View
import android.view.animation.BounceInterpolator
import android.widget.Button
import android.widget.ImageView
import java.util.*

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
fun ImageView.animScaleX(from: Float, to:Float, duration: Long = DURATION)
        = ObjectAnimator.ofFloat(this, "scaleX", from, to).setDuration(duration).start()
fun ImageView.animScaleY(from: Float, to:Float, duration: Long = DURATION)
        = ObjectAnimator.ofFloat(this, "scaleY", from, to).setDuration(duration).start()
fun ImageView.anim01(degree:Double, distance:Float, duration: Long = DURATION) {
        val toX = (distance * Math.cos(Math.toRadians(degree))) as Float
        val toY = (distance * Math.sin(Math.toRadians(degree))) as Float
        val holderX = PropertyValuesHolder.ofFloat("translationX", 0f, toX)
        val holderY = PropertyValuesHolder.ofFloat("translationX", 0f, toY)
        val rotation = PropertyValuesHolder.ofFloat("rotation", 0f, 360f)
        ObjectAnimator.ofPropertyValuesHolder(this, holderX, holderY, rotation).setDuration(duration).start()
}

fun ImageView.anim02(degree:Double, distance:Float, duration: Long = DURATION) {
        val animList = ArrayList<Animator>()
        val alphaAnim = ObjectAnimator.ofFloat(this, "alpha", 0f, 1f)
        alphaAnim.setDuration(duration)
        animList.add(alphaAnim)

        val toX = (distance * Math.cos(Math.toRadians(degree))) as Float
        val toY = (distance * Math.sin(Math.toRadians(degree))) as Float

        val holderX = PropertyValuesHolder.ofFloat("translationX", 0f, toX)
        val holderY = PropertyValuesHolder.ofFloat("translationX", 0f, toY)
        val rotation = PropertyValuesHolder.ofFloat("rotation", 0f, 360f)

        val translationAnim = ObjectAnimator.ofPropertyValuesHolder(this, holderX, holderY, rotation)
        translationAnim.setDuration(duration)
        animList.add(translationAnim)
        val animSet = AnimatorSet()
        animSet.playSequentially(animList)
        animSet.start()
}

fun Button.animChangingTextColor(fromColor:Int, toColor:Int, duration:Long = DURATION) {
        val anim = ObjectAnimator.ofInt(this, "textColor", fromColor, toColor)
        anim.setDuration(duration)
        anim.setEvaluator(ArgbEvaluator())
        anim.setRepeatCount(ValueAnimator.INFINITE)
        anim.setRepeatMode(ValueAnimator.REVERSE)
        anim.start()
}

fun Button.animTranslation(from:Float, to:Float, repeatCount:Int, duration:Long = DURATION) {
        val anim = ObjectAnimator.ofFloat(this, "x", from, to)
        anim.setDuration(duration)
        anim.setRepeatCount(repeatCount)
        anim.setInterpolator(BounceInterpolator())
        anim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                        this@animTranslation.visibility = View.GONE
                }
        })
        anim.start()
}

