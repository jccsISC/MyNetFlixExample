package com.jccsisc.appnetflixmodule.iu.fragments.home

import com.bumptech.glide.Glide
import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.common.core.status.StatusRequestEnum
import com.jccsisc.appnetflixmodule.iu.fragments.home.adapter.HomeAdapter
import com.jccsisc.appnetflixmodule.iu.fragments.home.model.HomeMovieResponse
import com.jccsisc.appnetflixmodule.iu.fragments.information.BottomSheetDetailFragment
import com.jccsisc.appnetflixmodule.utils.ConstantsObject.now_playing
import com.jccsisc.appnetflixmodule.utils.ConstantsObject.popular
import com.jccsisc.appnetflixmodule.utils.ConstantsObject.top
import com.jccsisc.appnetflixmodule.utils.ConstantsObject.upcoming
import com.jccsisc.appnetflixmodule.utils.DialogObject
import com.jccsisc.appnetflixmodule.utils.showToast
import com.jccsisc.appnetflixmodule.utils.showView

fun HomeFragment.initElements() {
    mBinding.apply {
        viewModel.requestDataUpcoming(upcoming, RequestModel())

        btnPlay.setOnClickListener {
            viewModel.requestDataUpcoming(upcoming, RequestModel())
            shimmerFrameLayout.showView()
            shimmerFrameLayout.showShimmer(true)
            layoutRv.showView(false)
        }
    }
}

fun HomeFragment.initObserverMovies() {
    mBinding.apply {

        viewModel.responseListMoviesUpcoming.observe(viewLifecycleOwner, { data ->
            when (data.statusRequest) {
                StatusRequestEnum.SUCCESS -> {
                    data.successData?.let { it ->

                        val adapter = HomeAdapter()
                        rvUpcoming.adapter = adapter

                        val imageRandom = it.listMovies.random().poster_path
                        var information = HomeMovieResponse()
                        var facorite = HomeMovieResponse()

                        it.listMovies.forEach { movie ->
                            if (movie.poster_path == imageRandom) {
                                information = movie
                                facorite = movie
                            }
                        }

                        Glide.with(root.context)
                            .load(root.context.getString(R.string.url_img, imageRandom))
                            .centerCrop()
                            .into(mBinding.imgHome)

                        imgFavorite.setOnClickListener {
                            showToast("Se está trabajando en esta funcionalidad gracias por su comprensión")
                        }

                        imgHome.setOnClickListener {
                            BottomSheetDetailFragment(information).show(
                                requireActivity().supportFragmentManager,
                                BottomSheetDetailFragment::class.java.simpleName
                            )
                        }

                        imgInformation.setOnClickListener {
                            BottomSheetDetailFragment(information).show(
                                requireActivity().supportFragmentManager,
                                BottomSheetDetailFragment::class.java.simpleName
                            )
                        }

                        adapter.submitList(it.listMovies)

                        viewModel.requestDataPopular(popular, RequestModel())

                        adapter.onItemClickListener = { movie ->
                            BottomSheetDetailFragment(movie).show(
                                requireActivity().supportFragmentManager,
                                BottomSheetDetailFragment::class.java.simpleName
                            )
                        }
                        viewModel.responseListMoviesUpcoming.postValue(
                            GenericResponse(
                                StatusRequestEnum.NONE,
                                requestData = RequestModel()
                            )
                        )
                    }
                }
                StatusRequestEnum.FAILURE -> {
                    shimmerFrameLayout.showView(false)
                    shimmerFrameLayout.stopShimmer()

                    data.errorData?.let {
                        DialogObject.showDialog(
                            requireActivity(),
                            it,
                            isOk = false,
                            textDialog = getString(R.string.error_message)
                        )
                    }
                }
                StatusRequestEnum.NONE -> {
                    shimmerFrameLayout.showShimmer(false)
                    shimmerFrameLayout.stopShimmer()
                }
            }
        })

        viewModel.responseListMoviesPopular.observe(viewLifecycleOwner, { data ->
            when (data.statusRequest) {
                StatusRequestEnum.SUCCESS -> {
                    data.successData?.let { it ->

                        val adapter = HomeAdapter()
                        rvPopular.adapter = adapter

                        adapter.submitList(it.listMovies)

                        viewModel.requestDataTop(top, RequestModel())

                        adapter.onItemClickListener = { movie ->
                            BottomSheetDetailFragment(movie).show(
                                requireActivity().supportFragmentManager,
                                BottomSheetDetailFragment::class.java.simpleName
                            )
                        }
                        viewModel.responseListMoviesPopular.postValue(
                            GenericResponse(
                                StatusRequestEnum.NONE,
                                requestData = RequestModel()
                            )
                        )
                    }
                }
                StatusRequestEnum.FAILURE -> {
                    shimmerFrameLayout.showView(false)
                    shimmerFrameLayout.stopShimmer()

                    data.errorData?.let {
                        DialogObject.showDialog(
                            requireActivity(),
                            it,
                            isOk = false,
                            textDialog = getString(R.string.error_message)
                        )
                    }
                }
                StatusRequestEnum.NONE -> {
                    shimmerFrameLayout.showShimmer(false)
                    shimmerFrameLayout.stopShimmer()
                }
            }
        })

        viewModel.responseListMoviesTop.observe(viewLifecycleOwner, { data ->
            when (data.statusRequest) {
                StatusRequestEnum.SUCCESS -> {
                    data.successData?.let { it ->

                        val adapter = HomeAdapter()
                        rvTop.adapter = adapter

                        adapter.submitList(it.listMovies)

                        viewModel.requestDataNowPlaying(now_playing, RequestModel())

                        adapter.onItemClickListener = { movie ->
                            BottomSheetDetailFragment(movie).show(
                                requireActivity().supportFragmentManager,
                                BottomSheetDetailFragment::class.java.simpleName
                            )
                        }
                        viewModel.responseListMoviesTop.postValue(
                            GenericResponse(
                                StatusRequestEnum.NONE,
                                requestData = RequestModel()
                            )
                        )
                    }
                }
                StatusRequestEnum.FAILURE -> {
                    shimmerFrameLayout.showView(false)
                    shimmerFrameLayout.stopShimmer()

                    data.errorData?.let {
                        DialogObject.showDialog(
                            requireActivity(),
                            it,
                            isOk = false,
                            textDialog = getString(R.string.error_message)
                        )
                    }
                }
                StatusRequestEnum.NONE -> {
                    shimmerFrameLayout.showShimmer(false)
                    shimmerFrameLayout.stopShimmer()
                }
            }
        })

        viewModel.responseListMoviesNowPopular.observe(viewLifecycleOwner, { data ->
            when (data.statusRequest) {
                StatusRequestEnum.SUCCESS -> {
                    data.successData?.let { it ->
                        shimmerFrameLayout.showView(false)
                        shimmerFrameLayout.stopShimmer()
                        layoutRv.showView()

                        val adapter = HomeAdapter()
                        rvNowPlaying.adapter = adapter

                        adapter.submitList(it.listMovies)

                        adapter.onItemClickListener = { movie ->
                            BottomSheetDetailFragment(movie).show(
                                requireActivity().supportFragmentManager,
                                BottomSheetDetailFragment::class.java.simpleName
                            )
                        }
                        viewModel.responseListMoviesNowPopular.postValue(
                            GenericResponse(
                                StatusRequestEnum.NONE,
                                requestData = RequestModel()
                            )
                        )
                    }
                }
                StatusRequestEnum.FAILURE -> {
                    shimmerFrameLayout.showView(false)
                    shimmerFrameLayout.stopShimmer()

                    data.errorData?.let {
                        DialogObject.showDialog(
                            requireActivity(),
                            it,
                            isOk = false,
                            textDialog = getString(R.string.error_message)
                        )
                    }
                }
                StatusRequestEnum.NONE -> {
                    shimmerFrameLayout.showShimmer(false)
                    shimmerFrameLayout.stopShimmer()
                }
            }
        })
    }
}