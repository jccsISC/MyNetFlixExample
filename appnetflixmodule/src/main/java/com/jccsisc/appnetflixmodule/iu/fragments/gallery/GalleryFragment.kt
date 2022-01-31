package com.jccsisc.appnetflixmodule.iu.fragments.gallery

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.databinding.FragmentGalleryBinding
import com.jccsisc.appnetflixmodule.iu.fragments.gallery.adapter.GalleryAdapter
import com.jccsisc.appnetflixmodule.iu.fragments.gallery.previewe.AddDialogFragment
import com.jccsisc.appnetflixmodule.iu.fragments.gallery.previewe.model.EventPost
import com.jccsisc.appnetflixmodule.iu.fragments.maps.model.UserLocation
import com.jccsisc.appnetflixmodule.utils.ConstantsObject
import com.jccsisc.appnetflixmodule.utils.DialogObject
import com.jccsisc.appnetflixmodule.utils.showToast
import com.jccsisc.appnetflixmodule.utils.showView
import java.text.SimpleDateFormat
import java.util.*


class GalleryFragment : Fragment(R.layout.fragment_gallery) {

    lateinit var mBinding: FragmentGalleryBinding
    val viewModel by viewModels<GalleryViewModel>()
    var imageUri: Uri? = null
    val db = FirebaseFirestore.getInstance()
    val list = mutableListOf<UserLocation>()
    private lateinit var firestoreListener: ListenerRegistration

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                imageUri = it.data?.data

                imageUri?.let {
                    activity?.let { it1 ->
                        AddDialogFragment(imageUri!!).show(
                            it1.supportFragmentManager,
                            AddDialogFragment::class.java.simpleName
                        )
                    }
                }
            }
        }
    var isOpen = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding = FragmentGalleryBinding.bind(view)
        super.onViewCreated(mBinding.root, savedInstanceState)

//        getLocationsRealtime()
        initElements()
        initObserversGallery()
    }

    fun animateFab() {
        mBinding.apply {
            val fabOpen = AnimationUtils.loadAnimation(requireContext(), R.anim.fab_open)
            val fabClose = AnimationUtils.loadAnimation(requireContext(), R.anim.fab_close)
            val rotateForward =
                AnimationUtils.loadAnimation(requireContext(), R.anim.fab_rotate_forward)
            val rotateBackward =
                AnimationUtils.loadAnimation(requireContext(), R.anim.fab_rotate_backward)
            if (isOpen) {
                fab.startAnimation(rotateForward)
                fabCamera.startAnimation(fabClose)
                fabGallery.startAnimation(fabClose)
                fabCamera.isClickable = false
                fabGallery.isClickable = false
                isOpen = false
            } else {
                fab.startAnimation(rotateBackward)
                fabCamera.startAnimation(fabOpen)
                fabGallery.startAnimation(fabOpen)
                fabCamera.isClickable = true
                fabGallery.isClickable = true
                isOpen = true
            }
        }
    }

    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        resultLauncher.launch(intent)
    }

    fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            resultLauncher.launch(intent)
        }catch (e: ActivityNotFoundException) {
            DialogObject.showDialog(
                requireActivity(),
                getString(R.string.no_app_found_to_take_the_photo),
                isOk = false,
                textDialog = getString(R.string.error_message)
            )
        }
    }

    fun uploadLocation(eventPost: EventPost) {

        val locationUser = UserLocation(
            latitud = 19.3266642,
            longitud = -99.2516524,
            image = eventPost.imageUrl
        )

        saveLocation(locationUser, eventPost)
    }

     private fun saveLocation(locationUser: UserLocation, eventPost: EventPost) {
        db.collection(ConstantsObject.COLL_USER)
            .document(eventPost.documentId!!)
            .set(locationUser)
            .addOnSuccessListener {
                activity?.let { activity ->
                    DialogObject.showDialog(
                        activity,
                        getString(R.string.location_uploaded_successfully),
                        isOk = true,
                        isCancelable = false,
                        textDialog = getString(R.string.success_message)
                    )
                }
            }
            .addOnFailureListener {
                activity?.let { activity ->
                    DialogObject.showDialog(
                        activity,
                        getString(R.string.your_location_could_not_be_uploaded),
                        isOk = false,
                        textDialog = getString(R.string.error_message)
                    )
                }
            }
    }
}
