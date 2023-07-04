package com.towhid.attendance.fragments.fgStore.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
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
    private val mStoreVM: StoreViewModel by viewModels()

    private lateinit var mRecyclerAdapterStore: RecyclerAdapterStore
    private var mStoreList: MutableList<Store> = ArrayList()
    private var page = 1

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
        mRecyclerAdapterStore = activity?.let { RecyclerAdapterStore( activity = it, mStoreList) }!!
        binding.rvStore.apply {
            val lm = LinearLayoutManager(requireContext())
            layoutManager = lm
            adapter = mRecyclerAdapterStore
        }
    }

    private fun action() {
        loadStore()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadStore() {
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
                }
                is Throwable -> {
                    toastShow.showInternetError(requireActivity(), it)
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
