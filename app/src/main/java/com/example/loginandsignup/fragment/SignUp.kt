package com.example.loginandsignup.fragment

import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import com.example.loginandsignup.MainActivity
import com.example.loginandsignup.R

class SignUp : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notAllChampsAreFilled()

        val tvSignIn2 = view.findViewById<TextView>(R.id.tvSignIn2)
        val etPseudo = view.findViewById<TextView>(R.id.etPseudo)
        val etPassword = view.findViewById<TextView>(R.id.etPassword)
        val etConfirmPassword = view.findViewById<TextView>(R.id.etConfirmPassword)
        val clPseudo = view.findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.clPseudo)
        val clPassword = view.findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.clPassword)
        val clConfirmPassword = view.findViewById<androidx.constraintlayout.widget.ConstraintLayout>(
            R.id.clConfirmPassword
        )

        val transitionDrawable = ContextCompat.getDrawable(requireContext(),
            R.drawable.transition_et
        ) as TransitionDrawable

        var btnActive = false

        tvSignIn2.setOnClickListener {
            (activity as MainActivity).login()
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

        etConfirmPassword.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                clConfirmPassword.background = transitionDrawable
                transitionDrawable.startTransition(150)
            } else {
                clConfirmPassword.setBackgroundResource(R.drawable.shape_et)
            }
        }

        etPseudo.doAfterTextChanged {
            if (etPseudo.text.toString().isNotEmpty() && etPassword.text.toString().isNotEmpty() && etConfirmPassword.text.toString().isNotEmpty()) {
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

        etPassword.doAfterTextChanged {
            if (etPseudo.text.toString().isNotEmpty() && etPassword.text.toString().isNotEmpty() && etConfirmPassword.text.toString().isNotEmpty()) {
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

        etConfirmPassword.doAfterTextChanged {
            if (etPseudo.text.toString().isNotEmpty() && etPassword.text.toString().isNotEmpty() && etConfirmPassword.text.toString().isNotEmpty()) {
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
    }

    fun allChampsAreFilled() {
        val btnSignUp = view?.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnSignUp)
        val transitionDrawableActive = ContextCompat.getDrawable(requireContext(),
            R.drawable.transition_btn_activ
        ) as TransitionDrawable
        btnSignUp?.background = transitionDrawableActive
        transitionDrawableActive.startTransition(200)

        btnSignUp?.isClickable = true
    }

    fun notAllChampsAreFilled() {
        val btnSignUp = view?.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnSignUp)
        val transitionDrawableDesac = ContextCompat.getDrawable(requireContext(),
            R.drawable.transition_btn_desac
        ) as TransitionDrawable
        btnSignUp?.background = transitionDrawableDesac
        transitionDrawableDesac.startTransition(200)

        btnSignUp?.isClickable = false
    }
}