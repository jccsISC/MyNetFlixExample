package com.jccsisc.appnetflixmodule.utils

import android.app.Activity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.databinding.DialogGenericBinding

object DialogObject {

    fun showDialog(
        context: Activity,
        message: String,
        isCancelable: Boolean = true,
        isOk: Boolean,
        textDialog: String,
        btnAcept: () -> Unit = {},
        btnNegate: () -> Unit = {},
        showBottomsPermissions: Boolean = false
    ) {

        val dialogView = context?.layoutInflater?.inflate(R.layout.dialog_generic, null, false)
        val dialog =
            MaterialAlertDialogBuilder(context, R.style.AlertDialogTheme).setView(dialogView)
                .setCancelable(isCancelable)
                .show()

        val bindingDialog = DialogGenericBinding.bind(dialogView!!)

        bindingDialog.apply {
            txtDialogMessage.text = message

            if (isOk) {
                imgDialogWarning.showView(false); imgDialogSuccess.showView()
                txtDialogMessage.text = textDialog
            } else {
                imgDialogWarning.showView(); imgDialogSuccess.showView(false)
                txtDialogMessage.text = textDialog
            }

            if (showBottomsPermissions) {
                contentButtomsPermissions.showView()
            } else contentButtomsPermissions.showView(false)

            btnDialog.setOnClickListener {
                dialog.dismiss()
            }

            btnAceptDialog.setOnClickListener {
                btnAcept()
                dialog.dismiss()
            }

            btnNegativeDialog.setOnClickListener {
                btnNegate()
                dialog.dismiss()
            }
        }
    }
}