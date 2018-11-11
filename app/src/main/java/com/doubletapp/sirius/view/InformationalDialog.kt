package com.doubletapp.sirius.view

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.doubletapp.sirius.R
import kotlinx.android.synthetic.main.dialog_informational.*

class InformationalDialog : DialogFragment() {

    companion object {
        const val TAG = "InformationalDialog"
        fun newInstance(): InformationalDialog {
            val fragment = InformationalDialog()
            fragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.BackgroundlessDialog)
            fragment.isCancelable = false
            return fragment
        }
    }

    lateinit var listener: () -> Unit

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.dialog_informational, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val decorView = requireActivity().window.decorView
        var width = decorView.width
        val height = decorView.height
        width -= requireContext().resources.getDimensionPixelSize(R.dimen.informational_dialog_margin) * 2
        val dialogWindow = dialog.window
        dialogWindow?.setLayout(width, height)
        informational_dialog_button.setOnClickListener {
            dismiss()
            listener.invoke()
        }
    }

    fun setOKListener(unit: () -> Unit): DialogFragment {
        listener = unit
        return this
    }

    override fun show(manager: FragmentManager, tag: String) {
        val transaction = manager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
        super.show(transaction, tag)
    }
}