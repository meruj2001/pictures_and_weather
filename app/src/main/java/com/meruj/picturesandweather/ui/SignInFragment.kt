package com.meruj.picturesandweather.ui

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.meruj.picturesandweather.R
import com.meruj.picturesandweather.ui.viewmodel.SignInViewModel
import com.meruj.picturesandweather.util.Status
import kotlinx.android.synthetic.main.fragment_accaunt.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.regex.Pattern

class SignInFragment : Fragment() {
    private val PASSWORD_PATTERN: Pattern = Pattern.compile(
        "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                ".{6,}" +               //at least 4 characters
                "$"
    );

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_accaunt, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_signin.setOnClickListener {
            loadWeatherData(view)
        }
    }

    private fun validateEmail(): Boolean {
        val emailInput: String = login.text.toString().trim()

        if (emailInput.isEmpty()) {
            login.error = "Field can't be empty"
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            login.error = "Please enter a valid email address"
            return false;
        } else {
            login.error = null
            return true;
        }
    }

    private fun validatePassword(): Boolean {
        val passwordInput: String = password.text.toString().trim()
        if (passwordInput.isEmpty()) {
            password.error = "Field can't be empty"
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.error = "Password too weak"
            return false;
        } else {
            password.error = null;
            return true;
        }
    }

    private fun loadWeatherData(view: View) {
        // Checking validation
        if (!validateEmail() || !validatePassword()) {
            return
        }

        val viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
        viewModel.getWeather().observe(viewLifecycleOwner, Observer {
            GlobalScope.launch(Dispatchers.Main) {

                when (it.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        it.data?.let {
                            val info = resources.getString(R.string.weather_info)
                            val snackbarInfo = info.format(
                                it.name,
                                it.main.temp.toString(),
                                it.clouds.all.toString(),
                                it.main.humidity.toString()
                            )

                            Snackbar.make(
                                view,
                                snackbarInfo,
                                Snackbar.LENGTH_LONG

                            ).show()
                        }
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Toast.makeText(activity, "Something went wrong", Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }
}