package com.example.medica3



import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

import com.example.medica3.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var userName: String
    lateinit var userEmail: String
    private var currentFragmentTag: String? = null
    private lateinit var bottomNavigation: BottomNavigationView
    private val menuItems = arrayOf("HomeFragment", "ProfileFragment", "CommunityFragment", "ConversationFragment")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        bottomNavigation = binding.bottomNavigation
        replaceFragment(HomeFragment(), "HomeFragment")
        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(HomeFragment(), "HomeFragment")
                    true
                }
                R.id.navigation_profile -> {
                    replaceFragment(ProfileFragment(), "ProfileFragment")
                    true
                }

                R.id.navigation_inbox-> {
                    replaceFragment(ConversationFragment(), "ConversationFragment")
                    true
                }
                else -> false
            }
        }








    }





    private fun getIndexForFragment(fragmentTag: String): Int {
        return menuItems.indexOf(fragmentTag)
    }




    private fun replaceFragment(fragment: Fragment, tag: String) {
        if (currentFragmentTag != tag) {

            val transaction = supportFragmentManager.beginTransaction()
            val currentIndex = getIndexForFragment(currentFragmentTag ?: "")
            val targetIndex = getIndexForFragment(tag)
            currentFragmentTag = tag
            val directionTransition = if (currentIndex < targetIndex) {
                Pair(R.anim.animate_slide_left_enter, R.anim.animate_slide_left_exit)
            } else {
                Pair(R.anim.animate_slide_in_left, R.anim.animate_slide_out_right)
            }

            val (enterAnim, exitAnim) = directionTransition
            transaction.setCustomAnimations(enterAnim, exitAnim)
            transaction.replace(R.id.fragment_container, fragment, tag)
            transaction.commitAllowingStateLoss()
        }
    }



}
