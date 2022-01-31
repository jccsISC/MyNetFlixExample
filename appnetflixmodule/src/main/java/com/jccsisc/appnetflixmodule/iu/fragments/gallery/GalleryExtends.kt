package com.jccsisc.appnetflixmodule.iu.fragments.gallery

import com.bumptech.glide.Glide
import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.common.core.status.StatusRequestEnum
import com.jccsisc.appnetflixmodule.iu.fragments.gallery.adapter.GalleryAdapter
import com.jccsisc.appnetflixmodule.iu.fragments.gallery.previewe.AddDialogFragment.Companion.isUploadOk
import com.jccsisc.appnetflixmodule.iu.fragments.home.adapter.HomeAdapter
import com.jccsisc.appnetflixmodule.iu.fragments.home.model.HomeMovieResponse
import com.jccsisc.appnetflixmodule.iu.fragments.information.BottomSheetDetailFragment
import com.jccsisc.appnetflixmodule.iu.fragments.maps.model.UserLocation
import com.jccsisc.appnetflixmodule.utils.ConstantsObject
import com.jccsisc.appnetflixmodule.utils.DialogObject
import com.jccsisc.appnetflixmodule.utils.getUserLocation
import com.jccsisc.appnetflixmodule.utils.showToast
import com.jccsisc.appnetflixmodule.utils.showView

/****
 * Project: MyNetflixApp
 * From: com.jccsisc.appnetflixmodule.iu.fragments.gallery
 * Created by Julio Cesar Camacho Silva on 26/01/2022 at 22:32
 * More info: https://www.facebook.com/juliocesar.camachosilva/
 * All rights reserved 2022.
 ***/
fun GalleryFragment.initElements() {
    mBinding.apply {

        viewModel.requestDataGetLocations()

        fab.setOnClickListener { animateFab() }
        fabCamera.setOnClickListener { animateFab(); openCamera() }
        fabGallery.setOnClickListener { animateFab(); openGallery() }
    }
}

fun GalleryFragment.initObserversGallery() {
    mBinding.apply {
        var message = ""
        var response = false

        viewModel.responseListLocation.observe(viewLifecycleOwner, {
            when (it.statusRequest) {
                StatusRequestEnum.LOADING -> progress.showView()
                StatusRequestEnum.SUCCESS -> {
                    progress.showView(false)
                    it.successData?.let { userLocation ->
                        if (!userLocation.image.isNullOrEmpty()) {
                            list.add(userLocation)
                        }

                        val adapter = GalleryAdapter()
                        mBinding.rvGallery.adapter = adapter
                        adapter.submitList(list)

                        adapter.onItemClickListener = { movie ->

                        }
                    }
                    viewModel.responseListLocation.postValue(
                        GenericResponse(StatusRequestEnum.NONE)
                    )
                }
                StatusRequestEnum.FAILURE -> {
                    progress.showView(false)
                    it.errorData?.let { message->
                        DialogObject.showDialog(
                            requireActivity(),
                            message = message,
                            isOk = false,
                            textDialog = getString(R.string.error_message)
                        )
                    }
                }
                StatusRequestEnum.NONE -> progress.showView(false)
            }
        })

        viewModel.responseUploatLocation.observe(viewLifecycleOwner, {
            when (it.statusRequest) {
                StatusRequestEnum.LOADING -> progress.showView()
                StatusRequestEnum.SUCCESS -> {
                    progress.showView(false)
                    it.successData?.let {
                        DialogObject.showDialog(
                            requireActivity(),
                            getString(R.string.location_uploaded_successfully),
                            isOk = true,
                            isCancelable = false,
                            textDialog = getString(R.string.success_message)
                        )
                    }
                    viewModel.responseUploatLocation.postValue(
                        GenericResponse(StatusRequestEnum.NONE)
                    )
                }
                StatusRequestEnum.FAILURE -> {
                    progress.showView(false)
                    it.errorData?.let {
                        DialogObject.showDialog(
                            requireActivity(),
                            it,
                            isOk = false,
                            textDialog = getString(R.string.error_message)
                        )
                    }
                }
                StatusRequestEnum.NONE -> progress.showView(false)
            }
        })

        isUploadOk = { isOk, msj, eventPost ->
            if (isOk) {
                message = getString(R.string.successfully_upload)
                response = isOk

                uploadLocation(eventPost)
                /*getUserLocation {
                    if (it == null) return@getUserLocation

                    viewModel.requestUploatLocation(
                        RequestModel(
                            UserLocation(
                                latitud = it.latitude,
                                longitud = it.longitude,
                                image = eventPost.imageUrl
                            )
                        )
                    )
                }*/
            } else {
                message = msj
                response = isOk
            }

            DialogObject.showDialog(
                requireActivity(),
                message,
                isOk = response,
                textDialog = getString(R.string.success_message)
            )
        }
    }
}