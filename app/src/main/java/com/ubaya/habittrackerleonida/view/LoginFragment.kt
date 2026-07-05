package com.ubaya.habittrackerleonida.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.ubaya.habittrackerleonida.R
import com.ubaya.habittrackerleonida.databinding.FragmentLoginBinding
import com.ubaya.habittrackerleonida.viewmodel.HabitViewModel


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: HabitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[HabitViewModel::class.java]

        binding.btnLogin.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()
            viewModel.checkLogin(username, password)
        }

        viewModel.loginStatusLD.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                val action = LoginFragmentDirections.actionLoginDashboardFragment()
                Navigation.findNavController(requireView()).navigate(action)

                viewModel.loginStatusLD.value = false
            } else {
                binding.txtUsernameLayout.error = "Username atau password salah"
                binding.txtPasswordLayout.error = "Username atau password salah"
            }
        }
    }

}