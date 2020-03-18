package com.prabha.wekan.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.prabha.wekan.R
import com.prabha.wekan.ui.model.FuelStationsModel
import java.util.ArrayList

class FuelStationAdapter(var context: Context, var fuelStationsModel : ArrayList<FuelStationsModel>): RecyclerView.Adapter<FuelStationAdapter.Companion.AddressViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fuel_station_adapter, parent, false)
        return AddressViewHolder(view)
    }
    override fun onBindViewHolder(holder:AddressViewHolder, position: Int) {
        val docItem = fuelStationsModel.get(holder.adapterPosition)
        holder.fuel_station_text.text = docItem.StationName

    }
    companion object {
        class AddressViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val fuel_station_text : TextView = view.findViewById(R.id.fuel_station)
        }
    }
    override fun getItemCount(): Int {
        return fuelStationsModel.size
    }
}