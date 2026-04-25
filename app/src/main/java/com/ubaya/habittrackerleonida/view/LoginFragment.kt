package com.ubaya.habittrackerleonida.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.ubaya.habittrackerleonida.R
import com.ubaya.habittrackerleonida.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

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

        binding.btnLogin.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()

            if(username == "student" && password == "123"){
                val action = LoginFragmentDirections.actionLoginDashboardFragment()
                Navigation.findNavController(it).navigate(action)
            } else{
                if (username != "student") {
                    binding.txtUsernameLayout.error = "Username salah"
                }
                if (password != "123") {
                    binding.txtPasswordLayout.error = "Password salah"
                }
            }
        }
    }

    companion object {

    }
}