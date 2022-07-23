package com.example.newsapppp.presentation.ui.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.newsapppp.R
import com.example.newsapppp.databinding.FragmentSettingsBinding
import com.example.newsapppp.presentation.ui.dialogs.NewNameDialog
import com.example.newsapppp.presentation.ui.main.MainFragmentViewModel
import com.example.newsapppp.presentation.utils.EGYPT
import com.example.newsapppp.presentation.utils.GERMANY
import com.example.newsapppp.presentation.utils.RUSSIA
import com.example.newsapppp.presentation.utils.USA
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.new_name_dialog.*

@AndroidEntryPoint
class SettingsFragment : Fragment() {
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPref = requireActivity().getSharedPreferences("Table", Context.MODE_PRIVATE)
        setCountryFlag()
        switchDayNight()
        reciveSwitchPosition()

        binding.imCountry.setOnClickListener {
            showPopup(im_country)
        }

        tvedit.setOnClickListener {
            NewNameDialog.showDialog(requireContext(), object : NewNameDialog.Listener{
                override fun onClick(name: String) {
                    tvUserName.text = name
                }

            },"")
        }
    }

    fun switchDayNight(){
        switchDayNight.setOnCheckedChangeListener { compoundButton, b ->
            if (b) {
                saveSwitch(0)
                saveSwitchPosition(false)
                AppCompatDelegate
                    .setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                saveSwitch(1)
                saveSwitchPosition(true)
                AppCompatDelegate
                    .setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }

    fun saveSwitch(switch: Int) {
        val editor = sharedPref.edit()
        editor.putInt("SWITCH_KEY", switch)
        editor.apply()
    }
    fun saveSwitchPosition(position:Boolean) {
        val editor = sharedPref.edit()
        editor.putBoolean("SWITCH_POSITION_KEY", position)
        editor.apply()
    }

    fun reciveSwitchPosition(){
        sharedPref = requireActivity().getSharedPreferences("Table", Context.MODE_PRIVATE)
        val switchPosition = sharedPref.getBoolean("SWITCH_POSITION_KEY", false )
        if (switchPosition){
            switchDayNight.isChecked = false
        }else{
            switchDayNight.isChecked = true
        }
    }

    fun saveCountry(res: String) {
        val editor = sharedPref.edit()
        editor.putString("country", res)
        editor.apply()
    }

    private fun showPopup(view: View) {
        val popup = PopupMenu(requireContext(), view)
        popup.inflate(R.menu.pop_up_menu)
        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

            when (item!!.itemId) {
                R.id.us -> {
                    saveCountry(USA)
                    binding.imCountry.setImageResource(R.drawable.usa)
                }
                R.id.ru -> {
                    saveCountry(RUSSIA)
                    binding.imCountry.setImageResource(R.drawable.russia)
                }
                R.id.germany -> {
                    saveCountry(GERMANY)
                    binding.imCountry.setImageResource(R.drawable.germany)
                }
                R.id.ukraine -> {
                    saveCountry(EGYPT)
                    binding.imCountry.setImageResource(R.drawable.egypt)
                }
            }
            true
        })
        popup.show()
//        val popupMen = PopupMenu::class.java.getDeclaredField("mPopup")
//        popupMen.isAccessible = true
//        val menu = popupMen.get(popup)
//        menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
//            .invoke(menu, true)
    }

    private fun setCountryFlag() {
        val countryCode =
            requireActivity().getSharedPreferences("Table", Context.MODE_PRIVATE)
                .getString("country", "")
        when (countryCode) {
            USA -> {
                binding.imCountry.setImageResource(R.drawable.usa)
            }
            GERMANY -> {
                binding.imCountry.setImageResource(R.drawable.germany)
            }

            RUSSIA -> {
                binding.imCountry.setImageResource(R.drawable.russia)
            }

            EGYPT -> {
                binding.imCountry.setImageResource(R.drawable.egypt)
            }
        }
    }
}
