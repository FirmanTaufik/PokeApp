package firmanpoke.com.apps.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast


fun View.visible(){
    this.visibility = View.VISIBLE
}

fun View.gone(){
    this.visibility = View.GONE
}

fun String.showToast(context: Context){
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

inline fun <reified T : Activity> Context.startNewActivity(
    extras: Bundle? = null,
    flags: Int? = Intent.FLAG_ACTIVITY_NEW_TASK
) {
    val intent = Intent(this, T::class.java)
    extras?.let { intent.putExtras(it) }
    flags?.let { intent.flags = it }
    startActivity(intent)
}
