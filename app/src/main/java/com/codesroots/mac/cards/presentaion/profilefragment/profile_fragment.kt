package com.codesroots.mac.cards.presentaion.profilefragment

import android.content.Intent
import com.codesroots.mac.cards.R
import com.codesroots.mac.cards.presentaion.login.LoginActivity
import com.codesroots.mac.cards.presentaion.payment.Payment
import kotlinx.android.synthetic.main.menu_fragment.view.*


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.codesroots.mac.cards.DataLayer.helper.PreferenceHelper
import com.codesroots.mac.cards.DataLayer.usecases.checkUserLogin
import kotlinx.android.synthetic.main.contact_fragment.*
import kotlinx.android.synthetic.main.contact_fragment.view.*
import kotlinx.android.synthetic.main.menu_fragment.*
import org.jetbrains.anko.support.v4.makeCall
import android.net.Uri
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.codesroots.mac.cards.presentaion.MainActivity
import com.codesroots.mac.cards.presentaion.changepassword.changePassWord
import com.codesroots.mac.cards.presentaion.mainfragment.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.dialog_custom_view.*
import kotlinx.android.synthetic.main.profile.view.*


class ProfileFragment : Fragment() {
    lateinit var viewModel: MainViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,  savedInstanceState: Bundle? ): View? {

        val view= inflater.inflate(com.codesroots.mac.cards.R.layout.profile, container, false)


        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)


        viewModel.getcompanyData()
        viewModel.getMyBalance()

        viewModel.CompanyResponseLD?.observe(this , Observer {
            view.username.text = PreferenceHelper.getUsername()
        })

        viewModel.MyBalanceResponseLD?.observe(this , Observer {
            view.my_credit.text = it.account
        })

        view.profileCard.setOnClickListener {
            val phone = "07721499299"
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            startActivity(intent)
            }

        view.changepw.setOnClickListener {
            val homeIntent = Intent(context, changePassWord::class.java)
            ( context as MainActivity).startActivity(homeIntent)
        }


        view.logouttext.setOnClickListener {
            if (checkUserLogin(context!!)) {
                PreferenceHelper.setToken("",context)
                PreferenceHelper.setUserGroupId(0)
                PreferenceHelper.setUserId(0)

                Toast.makeText(context, "تم تسجيل خروجك", Toast.LENGTH_SHORT).show()

                val homeIntent = Intent(context, LoginActivity::class.java)
                ( context as MainActivity).startActivity(homeIntent)
            }else {
                val homeIntent = Intent(context, LoginActivity::class.java)
                ( context as MainActivity).startActivity(homeIntent)

            }
        }

        return view
    }

}
