package com.zhadko.tips.ui.loginScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.zhadko.tips.R
import com.zhadko.tips.databinding.LoginFragmentBinding
import com.zhadko.tips.ui.profileScreen.ProfileFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

const val phoneNumberFromUkraineLength = 13
const val plugErrorCode = -1

class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding
    private val loginViewModel by viewModel<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Sign in"
        loginViewModel.userNameLiveData.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, ProfileFragment()).commit()
            }
        }

        loginViewModel.errorMessageLiveData.observe(viewLifecycleOwner) {
            if (it != plugErrorCode) {
                when (it) {
                    401 -> Toast.makeText(
                        requireContext(),
                        getString(R.string.account_does_not_exist_message),
                        Toast.LENGTH_LONG
                    ).show()

                    499 -> Toast.makeText(
                        requireContext(),
                        getString(R.string.internet_connection_error_message),
                        Toast.LENGTH_LONG
                    ).show()

                    else -> Toast.makeText(
                        requireContext(),
                        getString(R.string.general_error_message),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        binding.continueButton.setOnClickListener {
            val phoneNumber = binding.phoneNumberEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            if (phoneNumber.isEmpty() && password.isEmpty()) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.empty_fields_message),
                    Toast.LENGTH_LONG
                ).show()
            } else {
                if (phoneNumber.length != phoneNumberFromUkraineLength) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.incorrect_phone_number_message),
                        Toast.LENGTH_LONG
                    ).show()

                } else {
                    loginViewModel.login(phoneNumber, password)
                }
            }
        }
    }

}