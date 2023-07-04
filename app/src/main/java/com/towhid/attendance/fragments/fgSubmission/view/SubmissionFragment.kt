package com.towhid.attendance.fragments.fgSubmission.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.towhid.attendance.databinding.FragmentSubmissionBinding
import com.towhid.attendance.fragments.fgSubmission.viewModel.SubmissionViewModel
import com.towhid.attendance.network.model.attendance.response.AttendanceRes
import com.towhid.attendance.toast.ToastShow
import com.towhid.attendance.utils.Utils.removeFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SubmissionFragment : Fragment() {

    private lateinit var binding: FragmentSubmissionBinding
    private val mSubmissionVM: SubmissionViewModel by viewModels()

    @Inject
    lateinit var toastShow: ToastShow

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubmissionBinding.inflate(inflater)
        init()
        action()
        return binding.root
    }

    private fun action() {
        binding.btSubmit.setOnClickListener {
            if (binding.etName.text.toString().trim().isEmpty()) {
                binding.etName.error = "name can't be empty"
                return@setOnClickListener

            } else if (binding.etUserId.text.toString().trim().isEmpty()) {
                binding.etUserId.error = "user Id can't be empty"
                return@setOnClickListener
            }
            submit(
                name = binding.etName.text.toString(),
                uid = binding.etUserId.text.toString(),
                latitude = 90.0,
                longitude = 23.0
            )




        }
    }

    private fun init() {

    }


    private fun submit(
        name: String,
        uid: String,
        latitude: Double,
        longitude: Double,
    ) {
        mSubmissionVM.submission(
            name = name,
            uid = uid,
            latitude = latitude,
            longitude = longitude
        ).observe(viewLifecycleOwner) {
            when (it) {
                is AttendanceRes -> {
                    toastShow.toast(it.user_message)
                    removeFragment(requireActivity(), this)

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
            SubmissionFragment()
    }
}