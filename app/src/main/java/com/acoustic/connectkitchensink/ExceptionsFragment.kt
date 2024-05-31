/*
 * ExceptionFragment.kt
 *
 * Created by kimberlysweeney on 09-16-2022.
 *
 * Copyright (C) 2022 Acoustic, L.P. All rights reserved.
 *
 * NOTICE: This file contains material that is confidential and proprietary to
 * Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
 * industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
 * Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
 * prohibited.
 */

package com.acoustic.connectkitchensink

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.acoustic.connect.android.connectmod.Connect
import com.acoustic.connectkitchensink.databinding.FragmentExceptionsBinding

class ExceptionsFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentExceptionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentExceptionsBinding.inflate(inflater, container, false)

        val menuHost: MenuHost = host as MenuHost
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        val data = HashMap<String, String>()
        data["Foo"] = "Bar"

        val customButton = binding.contentExceptions.exceptionsCustomExceptionButton
        customButton.setOnClickListener {
            val value = data["Foo"]
            if (value != null) {
                Connect.logException(Exception("Custom Exception"))
            }
        }

        val arithmeticButton = binding.contentExceptions.exceptionsArithmeticExceptionButton
        arithmeticButton.setOnClickListener {
            try {
                val numerator = 12
                val denominator = 0
                val value = numerator / denominator
            } catch (e: Exception) {
                Connect.logException(e)
            }
        }

        val unhandledButton = binding.contentExceptions.exceptionsUnhandledExceptionButton
        unhandledButton.setOnClickListener {
            val value = "10.50".toInt()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.setGroupVisible(R.id.group_toolbars, false)
        menu.setGroupVisible(R.id.group_search, false)
        menu.setGroupVisible(R.id.group_main, false)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }

}
