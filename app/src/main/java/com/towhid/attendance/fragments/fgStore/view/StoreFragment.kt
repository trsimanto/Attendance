package com.towhid.attendance.fragments.fgStore.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.towhid.attendance.databinding.FragmentStoreBinding
import com.towhid.attendance.fragments.fgStore.adapter.recycler.RecyclerAdapterStore
import com.towhid.attendance.fragments.fgStore.model.Store
import com.towhid.attendance.fragments.fgStore.viewModel.StoreViewModel
import com.towhid.attendance.network.model.store.response.StoreRes
import com.towhid.attendance.toast.ToastShow
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StoreFragment : Fragment() {

    private lateinit var binding: FragmentStoreBinding
    private lateinit var mLayoutManager: LinearLayoutManager
    private val mStoreVM: StoreViewModel by viewModels()

    private lateinit var mRecyclerAdapterStore: RecyclerAdapterStore
    private var mStoreList: MutableList<Store> = ArrayList()
    private var page = 1
    var loading = true
    var pastVisibleItems:Int = 0
    var visibleItemCount:Int = 0
    var totalItemCount:Int = 0


    @Inject
    lateinit var toastShow: ToastShow

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStoreBinding.inflate(inflater)
        init()
        action()
        return binding.root
    }


    private fun init() {
        mLayoutManager = LinearLayoutManager(requireContext())
        mRecyclerAdapterStore = activity?.let { RecyclerAdapterStore( activity = it, mStoreList) }!!
        binding.rvStore.apply {
            layoutManager = mLayoutManager
            adapter = mRecyclerAdapterStore
        }
    }

    private fun action() {
        loadStore()
        pagination()
    }

    private fun pagination() {
        binding.rvStore.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    visibleItemCount = mLayoutManager.getChildCount()
                    totalItemCount = mLayoutManager.getItemCount()
                    pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition()
                    if (!loading) {
                        if (visibleItemCount + pastVisibleItems >= totalItemCount - 5) {
                            loading = true
                            loadStore()
                        }
                    }
                }
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadStore() {
        //live data bade egula ke ki dst flow diye maintain kora jaye? emn kisu maybe deksilam 
        mStoreVM.getStores(page = page).observe(viewLifecycleOwner) {
            when (it) {
                is StoreRes -> {
                    mStoreList.addAll(
                        it.data.map { data ->
                            Store(
                                name = data.name,
                                address = data.address,
                            )
                        },
                    )
                    mRecyclerAdapterStore.notifyDataSetChanged()
                    loading = false
                    page++
                }
                is Throwable -> {
                    toastShow.showInternetError(requireActivity(), it)
                    loading = false
                }
            }

        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            StoreFragment()
    }
}
