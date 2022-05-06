package com.pasandevin.android.profilesaver.v2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.pasandevin.android.profilesaver.v2.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    var db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        loadData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun loadData() {
        db.collection("user").document("user1").get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    binding.textlabelname.text = document.data?.get("personName").toString()
                    binding.textlabelemail.text = document.data?.get("email").toString()
                    binding.textlabelphone.text = document.data?.get("phone").toString()
                }
            }
            .addOnFailureListener { exception ->
                println("error retrieving data")
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}