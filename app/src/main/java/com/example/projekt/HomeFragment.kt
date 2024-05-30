package com.example.projekt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import models.User
import network.ApiService
import network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var welcomeTextView: TextView
    private lateinit var loginButton: Button
    private lateinit var registerButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        welcomeTextView = view.findViewById(R.id.textView)
        loginButton = view.findViewById(R.id.loginButton)
        registerButton = view.findViewById(R.id.registerButton)

        loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        }

        registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_registerFragment)
        }

        fetchUserData()

        return view
    }

    private fun fetchUserData() {
        val apiService = RetrofitClient.instance.create(ApiService::class.java)
        apiService.getUser("jan.kowalski@example.com").enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val user = response.body()
                    user?.let {
                        welcomeTextView.text = "Witaj ${it.imie} ${it.nazwisko}"
                    }
                } else {
                    Toast.makeText(activity, "Nie znaleziono użytkownika", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(activity, "Błąd: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }
}