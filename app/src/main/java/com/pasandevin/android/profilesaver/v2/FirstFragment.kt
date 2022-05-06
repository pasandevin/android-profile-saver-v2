package com.pasandevin.android.profilesaver.v2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.pasandevin.android.profilesaver.v2.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    var db = FirebaseFirestore.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textPersonName.setOnClickListener {
            binding.textPersonName.text = null
        }

        binding.textemail.setOnClickListener {
            binding.textemail.text = null
        }

        binding.textphone.setOnClickListener {
            binding.textphone.text = null
        }

        binding.registerbutton.setOnClickListener {
            saveData()
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun saveData() {

        //getting values from bindings
        val personName = binding.textPersonName.text.toString()
        val email = binding.textemail.text.toString()
        val phone = binding.textphone.text.toString()

        //creating a map
        var user = mapOf("personName" to personName, "email" to email, "phone" to phone)

        //storing the map in firestore
        db.collection("user").document("user1").set(user)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}