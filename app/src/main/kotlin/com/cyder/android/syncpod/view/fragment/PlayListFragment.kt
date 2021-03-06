package com.cyder.android.syncpod.view.fragment

import android.databinding.DataBindingUtil
import android.databinding.ObservableList
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cyder.android.syncpod.R
import com.cyder.android.syncpod.databinding.FragmentPlaylistBinding
import com.cyder.android.syncpod.databinding.ItemVideoBinding
import com.cyder.android.syncpod.view.adapter.BindingHolder
import com.cyder.android.syncpod.view.adapter.ObservableListAdapter
import com.cyder.android.syncpod.viewmodel.PlayListFragmentViewModel
import com.cyder.android.syncpod.viewmodel.VideoViewModel
import javax.inject.Inject


/**
 * Created by chigichan24 on 2018/04/16.
 */

class PlayListFragment : BaseFragment() {

    private lateinit var binding: FragmentPlaylistBinding
    @Inject lateinit var viewModel: PlayListFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentComponent.inject(this)
        bindViewModel(viewModel)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_playlist, container, false)
        binding.viewModel = viewModel
        viewModel.resources = activity!!.resources
        initRecyclerView()
        return binding.root
    }

    private fun initRecyclerView() {
        binding.playList.isNestedScrollingEnabled = false
        val adapter= PlayListAdapter(viewModel.videoViewModels)
        binding.playList.adapter = adapter
        binding.playList.layoutManager = LinearLayoutManager(activity)
    }

    inner class PlayListAdapter(list: ObservableList<VideoViewModel>) : ObservableListAdapter<VideoViewModel, BindingHolder<ItemVideoBinding>>(list) {
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BindingHolder<ItemVideoBinding>  = BindingHolder(parent, R.layout.item_video)

        override fun onBindViewHolder(holder: BindingHolder<ItemVideoBinding>?, position: Int) {
            val viewModel = getItem(position)
            val binding = holder?.binding
            binding?.viewModel = viewModel
        }

    }

    companion object {
        fun createInstance(): PlayListFragment = PlayListFragment()
    }
}