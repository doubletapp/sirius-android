package com.doubletapp.sirius.extensions

import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.widget.Toast

fun Fragment.toast(text: String) = Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
fun Fragment.toast(@StringRes id: Int) = Toast.makeText(context, id, Toast.LENGTH_SHORT).show()

fun Fragment.showFragment(container: Int,
                                   fragment: Fragment,
                                   tag: String,
                                   addToBackStack: Boolean) {
    if (this.activity?.supportFragmentManager?.findFragmentByTag(tag) == null) {
        if (addToBackStack) {
            this.activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(container, fragment, tag)
                    ?.addToBackStack(tag)
                    ?.commit()
        } else {
            this.activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(container, fragment, tag)
                    ?.commit()
        }
    }
}
