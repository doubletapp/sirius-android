package com.doubletapp.sirius.extensions

import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.widget.Toast

fun Fragment.toast(text: String) = Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
fun Fragment.toast(@StringRes id: Int) = Toast.makeText(context, id, Toast.LENGTH_SHORT).show()
