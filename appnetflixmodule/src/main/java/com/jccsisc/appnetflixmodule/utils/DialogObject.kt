package com.jccsisc.appnetflixmodule.utils

import android.app.Activity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.databinding.DialogGenericBinding

object DialogObject {

    fun showDialog(context: Activity, message: String) {

        val dialogView = context?.layoutInflater?.inflate(R.layout.dialog_generic, null, false)
        val dialog =
            MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme).setView(dialogView)
                .show()

        val bindingDialog = DialogGenericBinding.bind(dialogView!!)

        bindingDialog.apply {
            txtDialogMessage.text = message
            btnDialog.setOnClickListener {
                dialog.dismiss()
            }
        }
    }
}