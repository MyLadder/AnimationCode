package tokyo.tommy_kw.viewanimator

import android.app.Activity
import android.view.View

/**
 * Created by tommy on 15/10/11.
 */
public fun <T : View> Activity.fv(id : Int) : T = findViewById(id) as T

