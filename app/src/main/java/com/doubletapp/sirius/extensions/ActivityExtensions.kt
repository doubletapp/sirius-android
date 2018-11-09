package com.doubletapp.sirius.extensions

import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

fun AppCompatActivity.toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
fun AppCompatActivity.toast(@StringRes id: Int) = Toast.makeText(this, id, Toast.LENGTH_SHORT).show()

fun AppCompatActivity.showFragment(container: Int,
                                   fragment: Fragment,
                                   tag: String,
                                   addToBackStack: Boolean) {
    if (this.supportFragmentManager.findFragmentByTag(tag) == null) {
        if (addToBackStack) {
            this.supportFragmentManager
                .beginTransaction()
                .replace(container, fragment, tag)
                .addToBackStack(tag)
                .commit()
        } else {
            this.supportFragmentManager
                .beginTransaction()
                .replace(container, fragment, tag)
                .commit()
        }
    }
}