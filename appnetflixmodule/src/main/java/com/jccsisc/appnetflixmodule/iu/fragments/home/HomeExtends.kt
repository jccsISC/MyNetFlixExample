package com.jccsisc.appnetflixmodule.iu.fragments.home

import com.bumptech.glide.Glide
import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.response.GenericResponse
import com.jccsisc.appnetflixmodule.common.core.status.StatusRequestEnum
import com.jccsisc.appnetflixmodule.iu.fragments.information.BottomSheetDetailFragment
import com.jccsisc.appnetflixmodule.iu.fragments.home.adapter.HomeAdapter
import com.jccsisc.appnetflixmodule.iu.fragments.information.model.InformationModel
import com.jccsisc.appnetflixmodule.utils.ConstantesObject.now_playing
import com.jccsisc.appnetflixmodule.utils.ConstantesObject.upcoming
import com.jccsisc.appnetflixmodule.utils.ConstantesObject.popular
import com.jccsisc.appnetflixmodule.utils.ConstantesObject.top
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

        /*   btnUploadImage.setOnClickListener {
               ABottomSheetOptionsImageFragment().show(requireActivity().supportFragmentManager, "imageoption")
           }
           btnOpenMap.setOnClickListener {
               findNavController().navigate(R.id.action_MMenuFragment_to_mapFragment)
           }*/

        /*val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true *//* enabled by default *//*) {
                override fun handleOnBackPressed() {
                    viewModel.apply {
                        when {
                            isUpComing.value == true ->  requireActivity().finish()
                            isPopular.value == true -> tabLayout.getTabAt(0)?.select()
                            isTop.value == true -> tabLayout.getTabAt(0)?.select()
                        }
                    }
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(this@initElements, callback)*/
    }
}

fun HomeFragment.initObserverMovies() {
    mBinding.apply {

        viewModel.responseListMoviesUpcoming.observe(viewLifecycleOwner, { data ->
            when (data.statusRequest) {
                StatusRequestEnum.SUCCESS -> {
                    data.successData?.let { it ->
                        /* shimmerFrameLayout.showView(false)
                         shimmerFrameLayout.stopShimmer()
                         layoutRv.showView()*/

                        val adapter = HomeAdapter()
                        rvUpcoming.adapter = adapter

                        val imageRandom = it.listMovies.random().poster_path
                        var information = InformationModel()
                        it.listMovies.forEach {
                            if (it.poster_path == imageRandom) {
                                information = InformationModel(
                                    it.poster_path,
                                    it.title,
                                    it.vote_average.toString(),
                                    it.original_language,
                                    it.overview ?: "No hay descripción"
                                )
                            }
                        }

                        Glide.with(root.context)
                            .load(root.context.getString(R.string.url_img, imageRandom))
                            .centerCrop()
                            .into(mBinding.imgHome)

                        imgFavorite.setOnClickListener {
                            showToast("Agregar a favoritos")
                        }

                        imgInformation.setOnClickListener {
                            BottomSheetDetailFragment(information).show(
                                requireActivity().supportFragmentManager,
                                "bottomSheetDialog"
                            )
                        }

                        adapter.submitList(it.listMovies)

                        viewModel.requestDataPopular(popular, RequestModel())

                        adapter.onItemClickListener = { movie ->
                            showToast("Click")
                            /* val action =
                                 MMenuFragmentDirections.actionMMenuFragmentToMDetailsFragment2(
                                     movie
                                 )
                             findNavController().navigate(action)*/
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
                        DialogObject.showDialog(requireActivity(), it)
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
//                StatusRequestEnum.LOADING -> shimmerFrameLayout.showShimmer(true)
                StatusRequestEnum.SUCCESS -> {
                    data.successData?.let { it ->
                        /*shimmerFrameLayout.showView(false)
                        shimmerFrameLayout.stopShimmer()
                        layoutRv.showView()*/

                        val adapter = HomeAdapter()
                        rvPopular.adapter = adapter

                        adapter.submitList(it.listMovies)

                        viewModel.requestDataTop(top, RequestModel())

                        adapter.onItemClickListener = { movie ->
                            showToast("Click")
                            /* val action =
                                 MMenuFragmentDirections.actionMMenuFragmentToMDetailsFragment2(
                                     movie
                                 )
                             findNavController().navigate(action)*/
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
                        DialogObject.showDialog(requireActivity(), it)
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
//                StatusRequestEnum.LOADING -> shimmerFrameLayout.showShimmer(true)
                StatusRequestEnum.SUCCESS -> {
                    data.successData?.let { it ->
                        /*shimmerFrameLayout.showView(false)
                        shimmerFrameLayout.stopShimmer()
                        layoutRv.showView()*/

                        val adapter = HomeAdapter()
                        rvTop.adapter = adapter

                        adapter.submitList(it.listMovies)

                        viewModel.requestDataNowPlaying(now_playing, RequestModel())

                        adapter.onItemClickListener = { movie ->
                            showToast("Click")
                            /* val action =
                                 MMenuFragmentDirections.actionMMenuFragmentToMDetailsFragment2(
                                     movie
                                 )
                             findNavController().navigate(action)*/
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
                        DialogObject.showDialog(requireActivity(), it)
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
//                StatusRequestEnum.LOADING -> shimmerFrameLayout.showShimmer(true)
                StatusRequestEnum.SUCCESS -> {
                    data.successData?.let { it ->
                        shimmerFrameLayout.showView(false)
                        shimmerFrameLayout.stopShimmer()
                        layoutRv.showView()

                        val adapter = HomeAdapter()
                        rvNowPlaying.adapter = adapter

                        adapter.submitList(it.listMovies)

                        adapter.onItemClickListener = { movie ->
                            showToast("Click")
                            /* val action =
                                 MMenuFragmentDirections.actionMMenuFragmentToMDetailsFragment2(
                                     movie
                                 )
                             findNavController().navigate(action)*/
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
                        DialogObject.showDialog(requireActivity(), it)
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