package com.example.practice.flo.data.ui.main.locker

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.practice.flo.data.ui.main.MainActivity
import com.example.practice.flo.data.ui.signin.LoginActivity
import com.example.practice.flo.databinding.FragmentStorageBinding
import com.google.android.material.tabs.TabLayoutMediator

class StorageFragment : Fragment() {
    lateinit var binding: FragmentStorageBinding
   private val information = arrayListOf("저장한 곡", "음악파일", "저장한 앨범")

    companion object {
        const val TAG: String = "로그"
        fun newInstance(): StorageFragment {
            return StorageFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStorageBinding.inflate(inflater, container, false)
        val lockerAdapter = LockerVpAdapter(this)
        binding.storageContentVp.adapter = lockerAdapter

        TabLayoutMediator(binding.storageContentTb, binding.storageContentVp)
        {
            tab, position ->
            tab.text = information[position]
        }.attach()

        binding.storageLoginText.setOnClickListener{
            startActivity(Intent(activity, LoginActivity::class.java))
        }
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        initViews()
    }

    private fun getJwt():Int{
        val spf = activity?.getSharedPreferences("auth",AppCompatActivity.MODE_PRIVATE)
        return  spf!!.getInt("jwt",0)
    }

    private fun initViews(){
        val jwt : Int = getJwt()
        if(jwt ==0){
            binding.storageLoginText.text="로그인"
            binding.storageLoginText.setOnClickListener{
                startActivity(Intent(activity, LoginActivity::class.java))
            }
        }
        else{
            binding.storageLoginText.text="로그아웃"
            binding.storageLoginText.setOnClickListener{
                //로그아웃 진행
                logout()
                startActivity(Intent(activity, MainActivity::class.java))
            }

        }

    }
    private fun logout(){
        val spf = activity?.getSharedPreferences("auth", AppCompatActivity.MODE_PRIVATE)
        val editor = spf!!.edit()
        editor.remove("jwt")
        editor.apply()
    }
}