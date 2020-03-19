package com.prabha.wekan.ui.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lynkdriver.lynk.factory.ViewModelFactory
import com.prabha.wekan.R
import com.prabha.wekan.ui.adapter.FuelStationAdapter
import com.prabha.wekan.ui.model.FuelStationsModel
import com.prabha.wekan.ui.viewmodel.FuelStationViewModel
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject


class FuelStationsListActivty :DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var  fuelStationViewModel : FuelStationViewModel
    private  var fuelStationAdapter:FuelStationAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(appbarlayout_tool_bar);
        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        fuelStationViewModel = ViewModelProviders.of(this, viewModelFactory).get(FuelStationViewModel::class.java)
        observeFuelStation()
    }

    private fun observeFuelStation() {
        fuelStationViewModel.observeFuelStationResponse().observe(this, Observer<ArrayList<FuelStationsModel>> { fuelStation ->
            progressBar.visibility = View.GONE
            recycler_view.visibility = View.VISIBLE
            no_list.visibility = View.GONE
            fuelStationAdapter = FuelStationAdapter(fuelStation)
            recycler_view.adapter = fuelStationAdapter
        })
        fuelStationViewModel.observeFuelStationErrorResponse().observe(this, Observer<String> { fuelStationAPIError->
            progressBar.visibility = View.GONE
            Toast.makeText(this@FuelStationsListActivty,"Error : " +fuelStationAPIError, Toast.LENGTH_LONG).show()
        })
        fuelStationViewModel.observeFuelStationNoDataFound().observe(this, Observer<String> { noData ->
            recycler_view.visibility = View.GONE
            no_list.visibility = View.VISIBLE
            no_list.text = noData
        })
    }

    @Override
    override fun onCreateOptionsMenu(menu : Menu) : Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val searchViewItem: MenuItem = menu.findItem(R.id.app_bar_search)
        val searchView: SearchView = searchViewItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return false;
        }
        override fun onQueryTextChange(searchText: String): Boolean {
           fuelStationAdapter?.getFilter()!!.filter(searchText)
            return false;
           }
        })
      return super.onCreateOptionsMenu(menu)
    }

}
