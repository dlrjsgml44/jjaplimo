package com.dlrjsgml.makeview

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.dlrjsgml.makeview.databinding.FragmentAddBinding
import com.google.android.gms.common.api.Response
import retrofit2.Call
import retrofit2.Callback

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var binding: FragmentAddBinding
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater)
        apiService = ApiClient.instance.create(ApiService::class.java)
        binding.backback.setOnClickListener{
            requireActivity().supportFragmentManager.popBackStack()
        }
        binding.isDone.setOnClickListener{
            if((binding.plsTitle.text.isNotEmpty())and (binding.plsContent.text.isNotEmpty())){
                addData(Post(title = "${binding.plsTitle.text}", name = "이건희", contents = "${binding.plsContent.text}"))
                requireActivity().supportFragmentManager.popBackStack()
            }
            else{
                Toast.makeText(context, "글 다 채워주세요", Toast.LENGTH_SHORT).show()
            }
        }





        binding.plsContent.addTextChangedListener(object  : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.howmuchtext.text = "0"
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val userinput = binding.plsContent.text.toString()
                if (userinput.length ==0){
                    binding.howmuchtext.setTextColor(ContextCompat.getColor(requireContext(),R.color.black))
                }
                else{
                    binding.howmuchtext.setTextColor(ContextCompat.getColor(requireContext(),R.color.alimoyellow))
                }
                binding.howmuchtext.text = userinput.length.toString()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
        return binding.root
    }

    private fun addData(post: Post) {
        val call = apiService.addItems(post)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: retrofit2.Response<Void>) {
                if (response.isSuccessful) {
                    Log.d("PostFragment", "Data added successfully")
                } else {
                    Log.e("PostFragment", "Server error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("TAG", "onFailure" +
                        ": ", )
            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}