package xtremedesar.com.UI.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import xtremedesar.com.Data.Model.MonedaModel
import xtremedesar.com.Data.Model.TasaCambioModel
import xtremedesar.com.databinding.ActivityCardRecyclerviewItem3Binding


class AdapterSpinnerTasaCambio(
    _Context: Context,
    ListaSpinner: List<TasaCambioModel>,
    private val _LayoutInflater: LayoutInflater,
) :
    ArrayAdapter<TasaCambioModel>(_Context, 0, ListaSpinner) {

    private lateinit var _Binding: ActivityCardRecyclerviewItem3Binding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {
        _Binding = ActivityCardRecyclerviewItem3Binding.inflate(_LayoutInflater)

        val TipoSpinner = getItem(position)

        _Binding.tvRegistroNombre.text = TipoSpinner?.TasaCambio
        _Binding.tvRegistroPais.text = TipoSpinner?.Pais

        return _Binding.root
    }
}