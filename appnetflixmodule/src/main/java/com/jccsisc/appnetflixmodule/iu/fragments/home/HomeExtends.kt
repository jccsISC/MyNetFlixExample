package com.jccsisc.appnetflixmodule.iu.fragments.home

import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.jccsisc.appnetflixmodule.R
import com.jccsisc.appnetflixmodule.common.core.request.RequestModel
import com.jccsisc.appnetflixmodule.common.core.status.StatusRequestEnum
import com.jccsisc.appnetflixmodule.iu.fragments.home.adapter.HomeAdapter
import com.jccsisc.appnetflixmodule.utils.ConstantesObject.estreno
import com.jccsisc.appnetflixmodule.utils.ConstantesObject.now_playing
import com.jccsisc.appnetflixmodule.utils.ConstantesObject.popular
import com.jccsisc.appnetflixmodule.utils.ConstantesObject.top
import com.jccsisc.appnetflixmodule.utils.DialogObject
import com.jccsisc.appnetflixmodule.utils.showToast
import com.jccsisc.appnetflixmodule.utils.showView
import kotlin.random.Random
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun HomeFragment.initElements() {
    mBinding.apply {

        viewModel.requestData(estreno, RequestModel())
        /*viewModel.requestData(popular, RequestModel())
        viewModel.requestData(top, RequestModel())
        viewModel.requestData(now_playing, RequestModel())*/

        imgFavorite.setOnClickListener {
            showToast("Agregar a favoritos")
        }

        btnPlay.setOnClickListener {
            viewModel.requestData(estreno, RequestModel())
        }

        imgInformation.setOnClickListener {
            showToast("Mostras bottomSheet Con la info")
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
        viewModel.responseListMovies.observe(viewLifecycleOwner, { data ->
            when (data.statusRequest) {
                StatusRequestEnum.LOADING -> shimmerFrameLayout.showShimmer(true)
                StatusRequestEnum.SUCCESS -> {
                    data.successData?.let { it ->
                        shimmerFrameLayout.showView(false)
                        shimmerFrameLayout.stopShimmer()
                        layoutRv.showView()

                        val adapter = HomeAdapter()
                        rvPremiere.adapter = adapter
                        rvPopular.adapter = adapter
                        rvTop.adapter = adapter
                        rvNowPlaying.adapter = adapter

                        val imageRandom = it.listMovies.random().poster_path

                        Glide.with(root.context)
                            .load(root.context.getString(R.string.url_img, imageRandom))
                            .centerCrop()
                            .into(mBinding.imgHome)

                        adapter.submitList(it.listMovies)

                        adapter.onItemClickListener = { movie ->
                            showToast("Click")
                            /* val action =
                                 MMenuFragmentDirections.actionMMenuFragmentToMDetailsFragment2(
                                     movie
                                 )
                             findNavController().navigate(action)*/
                        }
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