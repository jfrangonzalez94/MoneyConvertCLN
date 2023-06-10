package xtremedesar.com.UI.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import xtremedesar.com.Data.Model.MonedaModel
import xtremedesar.com.databinding.ActivityCardSpinnerBinding


class AdapterSpinnerMoneda(
    _Context: Context,
    ListaSpinner: List<MonedaModel>,
    private val _LayoutInflater: LayoutInflater,
) :
    ArrayAdapter<MonedaModel>(_Context, 0, ListaSpinner) {

    private lateinit var _Binding: ActivityCardSpinnerBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {
        _Binding = ActivityCardSpinnerBinding.inflate(_LayoutInflater)

        val TipoSpinner = getItem(position)

        _Binding.tvSpinnerDescripcion.text = TipoSpinner?.Nombre

        return _Binding.root
    }
}