package com.jccsisc.appnetflixmodule.iu.fragments.gallery.previewe

import android.app.Dialog
import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.databinding.FragmentDialogAddImageBinding
import com.jccsisc.appnetflixmodule.iu.fragments.gallery.previewe.model.EventPost
import com.jccsisc.appnetflixmodule.utils.ConstantsObject.COLL_IMAGES
import com.jccsisc.appnetflixmodule.utils.ConstantsObject.COLL_USER
import com.jccsisc.appnetflixmodule.utils.showView

/****
 * Project: MyNetflixApp
 * From: com.jccsisc.appnetflixmodule.iu.fragments.gallery.previewe
 * Created by Julio Cesar Camacho Silva on 27/01/2022 at 8:44
 * More info: https://www.facebook.com/juliocesar.camachosilva/
 * All rights reserved 2022.
 ***/
class AddDialogFragment(val image: Uri) : DialogFragment(), DialogInterface.OnShowListener {

    private var mBinding: FragmentDialogAddImageBinding? = null

    companion object {
        var isUploadOk: ((isOk: Boolean, message: String, eventPost: EventPost) -> Unit)? = null
        val storafeRef = FirebaseStorage.getInstance().reference.child(COLL_IMAGES)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        activity?.let { activity ->
            mBinding = FragmentDialogAddImageBinding.inflate(LayoutInflater.from(context))

            mBinding?.let {
                val builder = AlertDialog.Builder(activity, R.style.AlertDialogTheme)
                    .setView(it.root)

                val dialog = builder.create()
                dialog.setOnShowListener(this)

                return dialog
            }
        }

        return super.onCreateDialog(savedInstanceState)
    }

    override fun onShow(dialog: DialogInterface?) {
        val dialog = dialog as? AlertDialog
        dialog?.let {
            mBinding?.let { binding ->

                binding.imgImageDialog.setImageURI(image)

                binding.imgClose.setOnClickListener {
                    dismiss()
                }
                binding.btnUpload.setOnClickListener {
                    uploadImage(image)
                }
            }
        }
    }

    private fun uploadImage(image: Uri) {

        val eventPost = EventPost()
        eventPost.documentId = FirebaseFirestore.getInstance().collection(COLL_USER).document().id

        val storageRef = storafeRef
        image.let { image ->
            mBinding?.let { binding ->
                binding.progress.showView()
                val photoRef = storageRef.child(eventPost.documentId!!)
                photoRef.putFile(image)
                    .addOnSuccessListener {
                        it.storage.downloadUrl.addOnSuccessListener { downloadUrl ->

                            eventPost.isSuccess = true
                            eventPost.imageUrl = downloadUrl.toString()
                            isUploadOk?.invoke(true, "Ok", eventPost)
                            binding.progress.showView(false)
                            dismiss()
                        }
                    }
                    .removeOnFailureListener {
                        binding.progress.showView(false)

                        eventPost.isSuccess = false
                        isUploadOk?.invoke(true, it.message!!, eventPost)
                        dismiss()
                    }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}