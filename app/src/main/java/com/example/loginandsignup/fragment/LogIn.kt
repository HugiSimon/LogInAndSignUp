package com.example.loginandsignup.fragment

import android.content.Intent
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.loginandsignup.ListUser
import com.example.loginandsignup.MainActivity
import com.example.loginandsignup.R
import com.example.loginandsignup.viewModels.ConnectionViewModel

class LogIn : Fragment() {
    private lateinit var connectionVW: ConnectionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        connectionVW = ViewModelProvider(requireActivity())[ConnectionViewModel::class.java]
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notAllChampsAreFilled()

        val tvSignUp2 = view.findViewById<TextView>(R.id.tvSignUp2)
        val etPseudo = view.findViewById<TextView>(R.id.etPseudo)
        val etPassword = view.findViewById<TextView>(R.id.etPassword)
        val clPseudo = view.findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.clPseudo)
        val clPassword = view.findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.clPassword)
        val btnLogIn = view.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnLogIn)

        val transitionDrawable = ContextCompat.getDrawable(requireContext(),
            R.drawable.transition_et
        ) as TransitionDrawable

        var btnActive = false

        tvSignUp2.setOnClickListener {
            (activity as MainActivity).signup()
        }

        etPseudo.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                clPseudo.background = transitionDrawable
                transitionDrawable.startTransition(150)
            } else {
                clPseudo.setBackgroundResource(R.drawable.shape_et)
            }
        }

        etPassword.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                clPassword.background = transitionDrawable
                transitionDrawable.startTransition(150)
            } else {
                clPassword.setBackgroundResource(R.drawable.shape_et)
            }
        }

        etPseudo.doAfterTextChanged() {
            connectionVW.setUsername(etPseudo.text.toString())
            if(!TextUtils.isEmpty(etPseudo.text) && !TextUtils.isEmpty(etPassword.text)) {
                if (!btnActive) {
                    allChampsAreFilled()
                    btnActive = true
                }
            } else {
                if (btnActive) {
                    notAllChampsAreFilled()
                    btnActive = false
                }
            }
        }

        etPassword.doAfterTextChanged() {
            connectionVW.setPassword(etPassword.text.toString())
            if(!TextUtils.isEmpty(etPseudo.text) && !TextUtils.isEmpty(etPassword.text)) {
                if (!btnActive) {
                    allChampsAreFilled()
                    btnActive = true
                }
            } else {
                if (btnActive) {
                    notAllChampsAreFilled()
                    btnActive = false
                }
            }
        }

        btnLogIn.setOnClickListener{
            (activity as MainActivity).changeActivity()
        }
    }

    fun allChampsAreFilled() {
        val btnLogIn = view?.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnLogIn)
        val transitionDrawableActive = ContextCompat.getDrawable(requireContext(),
            R.drawable.transition_btn_activ
        ) as TransitionDrawable
        btnLogIn?.background = transitionDrawableActive
        transitionDrawableActive.startTransition(200)

        btnLogIn?.isEnabled = true
    }

    fun notAllChampsAreFilled() {
        val btnLogIn = view?.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnLogIn)
        val transitionDrawableDesac = ContextCompat.getDrawable(requireContext(),
            R.drawable.transition_btn_desac
        ) as TransitionDrawable
        btnLogIn?.background = transitionDrawableDesac
        transitionDrawableDesac.startTransition(200)

        btnLogIn?.isEnabled = false
    }
}