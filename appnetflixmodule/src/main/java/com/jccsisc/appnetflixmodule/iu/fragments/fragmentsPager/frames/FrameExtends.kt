package com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.frames

import androidx.navigation.fragment.findNavController
import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.common.core.status.StatusRequestEnum
import com.jccsisc.appnetflixmodule.iu.fragments.details.DetailsFragmentDirections
import com.jccsisc.appnetflixmodule.iu.fragments.fragmentsPager.frames.adapter.FrameImagesAdapter
import com.jccsisc.appnetflixmodule.utils.DialogObject
import com.jccsisc.appnetflixmodule.utils.showView

fun FrameFragment.initElements() {
    mBinding.apply {
        viewModel.requestData(idMovie.toString(), RequestModel())
    }
}

fun FrameFragment.initObserversFrame() {
    mBinding.apply {
        viewModel.responseListImages.observe(viewLifecycleOwner, { data ->
            when(data.statusRequest) {
                StatusRequestEnum.LOADING -> progress.showView()
                StatusRequestEnum.SUCCESS -> {
                    data.successData?.let { it ->
                        progress.showView(false)

                        val adapter = FrameImagesAdapter()
                        rvImages.adapter = adapter
                        adapter.submitList(it.backdrops)

                        adapter.onItemClickListener  = { movie ->
                            val action = DetailsFragmentDirections.actionDetailsFragmentToExpandFragment(movie.file_path)
                            findNavController().navigate(action)
                        }

                        viewModel.responseListImages.postValue(
                            GenericResponse(
                                StatusRequestEnum.NONE,
                                requestData = RequestModel()
                            )
                        )
                    }
                }
                StatusRequestEnum.FAILURE -> {
                    progress.showView(false)
                    data.errorData?.let {
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
    }
}