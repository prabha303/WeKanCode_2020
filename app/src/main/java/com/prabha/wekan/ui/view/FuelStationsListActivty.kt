package com.prabha.wekan.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lynkdriver.lynk.factory.ViewModelFactory
import com.prabha.wekan.R
import com.prabha.wekan.ui.adapter.FuelStationAdapter
import com.prabha.wekan.ui.model.FuelStationResponse
import com.prabha.wekan.ui.viewmodel.FuelStationViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class FuelStationsListActivty :DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var  fuelStationViewModel : FuelStationViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fuelStationViewModel = ViewModelProviders.of(this, viewModelFactory).get(FuelStationViewModel::class.java)
        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        fuelStationViewModel.getFuelData(100)
        progressBar.visibility = View.VISIBLE
        observeFuelStation()
    }


    private fun observeFuelStation() {

        fuelStationViewModel.observeFuelStationResponse().observe(this, Observer<FuelStationResponse> { fuelStation ->
            progressBar.visibility = View.GONE
            recycler_view.visibility = View.VISIBLE
            if (fuelStation.FuelStations?.size!!.compareTo(0) > 0 ) {
                val adapter = FuelStationAdapter(this,fuelStation.FuelStations!!)
                recycler_view.adapter = adapter
            }else {
                recycler_view.visibility = View.GONE
                no_list.visibility = View.VISIBLE
            }
        })

        fuelStationViewModel.observeFuelStationErrorResponse().observe(this, Observer<String> { fuelStationAPIError->
            progressBar.visibility = View.GONE
            Toast.makeText(this@FuelStationsListActivty,"Error : " +fuelStationAPIError, Toast.LENGTH_LONG).show()
        })


    }
}
