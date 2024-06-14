package com.dlrjsgml.makeview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dlrjsgml.makeview.databinding.FragmentHomeBinding
import com.google.android.material.appbar.AppBarLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@Suppress("DEPRECATION")
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //    GPT와 함께한 결과
    private lateinit var networkStatusHelper: NetworkMan
//


    lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }



    fun refreshFragment() {
        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_container, HomeFragment())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater)

        networkStatusHelper = NetworkMan(requireContext())
        networkStatusHelper.observe(viewLifecycleOwner, Observer { isConnected ->
            if (isConnected) {
                goInternet()
            }
        })

        binding.refresh.setOnRefreshListener {
            refreshFragment()
            binding.refresh.isRefreshing = false
        }
        binding.appbars.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            binding.refresh.isEnabled = verticalOffset == 0
        })

        val recyclerView = binding.rvViews
        recyclerView.adapter = AlimAdapter(emptyList())
        val GraderecyclerView = binding.rvGrade
        GraderecyclerView.adapter = GradeAdapter(emptyList())

//        binding.rvViews.adapter = AlimAdapter(contentLists)
//        binding.rvViews.layoutManager = LinearLayoutManager(context)


        binding.writeman.setOnClickListener {
            val fragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.main_container, AddFragment())
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }


        val dividerItemDecoration =
            DividerItemDecoration(binding.rvViews.context, LinearLayoutManager.VERTICAL)
        binding.rvViews.addItemDecoration(dividerItemDecoration)
        return binding.root
    }

    fun goInternet() {
        val recyclerView = binding.rvViews
        recyclerView.layoutManager = LinearLayoutManager(context)
        val apiService = ApiClient.instance.create(ApiService::class.java)

        apiService.getItems().enqueue(object : Callback<List<AlimData>> {
            override fun onResponse(
                call: Call<List<AlimData>>,
                response: Response<List<AlimData>>
            ) {
                if (response.isSuccessful) {
                    val itemList = response.body()!!
                    if (itemList.size == 0) {
                        binding.isInternet.isVisible = true
                    } else {
                        binding.isInternet.isVisible = false
                        recyclerView.adapter = AlimAdapter(itemList)
                    }

                } else {
                }
            }

            override fun onFailure(call: Call<List<AlimData>>, t: Throwable) {
                Toast.makeText(context, "네트워크 연결이 되지 않았습니다.", Toast.LENGTH_SHORT).show()
            }
        })


        val GraderecyclerView = binding.rvGrade
        GraderecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        apiService.getGrades().enqueue(object : Callback<List<GradeData>> {
            override fun onResponse(
                call: Call<List<GradeData>>,
                response: Response<List<GradeData>>
            ) {
                if (response.isSuccessful) {
                    val gradList = response.body()!!
                    if (gradList.size == 0) {
                        binding.maple.text = "먼가 오류가 있습니다."
                    } else {
                        binding.isInternet.isVisible = false
                        GraderecyclerView.adapter = GradeAdapter(gradList)
                        Log.d("TAG", "$gradList");
                    }

                }
            }

            override fun onFailure(call: Call<List<GradeData>>, t: Throwable) {
                Toast.makeText(context, "네트워크 연결이 되지 않았습니다.", Toast.LENGTH_SHORT).show()
                binding.maple.text = "먼가 오류가 있습니다."
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
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}