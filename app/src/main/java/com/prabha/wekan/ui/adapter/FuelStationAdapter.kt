package com.prabha.wekan.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.prabha.wekan.R
import com.prabha.wekan.ui.model.FuelStationsModel
import java.util.ArrayList

class FuelStationAdapter(var fuelStationsModel : ArrayList<FuelStationsModel>): RecyclerView.Adapter<FuelStationAdapter.Companion.FuelStationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuelStationViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fuel_station_adapter, parent, false)
        return FuelStationViewHolder(view)
    }
    override fun onBindViewHolder(holder:FuelStationViewHolder, position: Int) {
        val docItem = fuelStationsModel.get(holder.adapterPosition)
        val title = docItem.ID.toString() + " - "  + docItem.StationName
        holder.fuel_station_name.text = title
        holder.access_days_time.text = docItem.AccessDaysTime
        holder.date_last_confirmed.text = docItem.DateLastConfirmed
        holder.facility_type.text = docItem.FacilityType
        holder.Intersection_directions.text = docItem.IntersectionDirections
        holder.street_address.text = docItem.StreetAddress
        val city = docItem.City + " - "  + docItem.Country
        holder.country.text = city
    }
    companion object {
        class FuelStationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val fuel_station_name : TextView = view.findViewById(R.id.fuel_station_name)
            val access_days_time : TextView = view.findViewById(R.id.access_days_time)
            val date_last_confirmed : TextView = view.findViewById(R.id.date_last_confirmed)
            val facility_type : TextView = view.findViewById(R.id.facility_type)
            val Intersection_directions : TextView = view.findViewById(R.id.intersection_directions)
            val street_address : TextView = view.findViewById(R.id.street_address)
            val country : TextView = view.findViewById(R.id.country)
        }
    }
    override fun getItemCount(): Int {
        return fuelStationsModel.size
    }
}