package com.jccsisc.appnetflixmodule.iu.fragments.details.fragmentsPager.reviews

import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.common.core.status.StatusRequestEnum
import com.jccsisc.appnetflixmodule.iu.fragments.details.fragmentsPager.reviews.adapter.ReviewsAdapter
import com.jccsisc.appnetflixmodule.utils.DialogObject
import com.jccsisc.appnetflixmodule.utils.showView

fun ReviewsFragment.initElements() {
    mBinding.apply {
        viewModel.requestData(idMovie.toString(), RequestModel())
    }
}

fun ReviewsFragment.initObserversReviews() {
    mBinding.apply {
        viewModel.responseListReviews.observe(viewLifecycleOwner, { data ->
            when (data.statusRequest) {
                StatusRequestEnum.LOADING -> progress.showView()
                StatusRequestEnum.SUCCESS -> {
                    data.successData?.let { it ->
                        progress.showView(false)

                        txtTotalResults.text = getString(R.string.total_results, it.total_results.toString())

                        val adapter = ReviewsAdapter()
                        rvReviews.adapter = adapter
                        adapter.submitList(it.results)

                        adapter.onItemClickListener = { review ->

                        }

                        viewModel.responseListReviews.postValue(
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
                        DialogObject.showDialog(requireActivity(), it)
                    }
                }
                StatusRequestEnum.NONE -> progress.showView(false)
            }
        })
    }
}