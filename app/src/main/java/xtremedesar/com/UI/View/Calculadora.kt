package xtremedesar.com.UI.View

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import io.realm.Realm
import www.sanju.todonotes.Interface.MonedaService
import www.sanju.todonotes.Interface.TasaCambioService
import xtremedesar.com.Data.Model.MonedaModel
import xtremedesar.com.Data.Model.TasaCambioModel
import xtremedesar.com.R
import xtremedesar.com.UI.Adapter.AdapterSpinnerMoneda
import xtremedesar.com.UI.Adapter.AdapterSpinnerTasaCambio
import xtremedesar.com.databinding.ActivityCalculadoraBinding
import xtremedesar.com.databinding.ActivityMonedasBinding

class Calculadora : AppCompatActivity() {

    private lateinit var _Binding: ActivityCalculadoraBinding
    private lateinit var _Realm: Realm
    private var _MonedaService: MonedaService = MonedaService()
    private var _TasaCambioServiceService: TasaCambioService = TasaCambioService()
    private lateinit var _MonedaSpinnerTemp: MonedaModel
    private lateinit var _TasaCambioSpinnerTemp: TasaCambioModel
    private lateinit var SpinnerAdapterMoneda: AdapterSpinnerMoneda
    private lateinit var SpinnerAdapterTasaCambio: AdapterSpinnerTasaCambio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _Binding = ActivityCalculadoraBinding.inflate(layoutInflater)
        setContentView(_Binding.root)
        supportActionBar?.setTitle("Calculadora")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //INICIAR REALM
        _Realm = Realm.getDefaultInstance()

        CargarListaMonedas()
    }

    private fun CargarListaMonedas() {
        SpinnerAdapterMoneda =
            AdapterSpinnerMoneda(this, _MonedaService.getAllMonedas(_Realm), layoutInflater)
        _Binding.spFormMoneda.adapter = SpinnerAdapterMoneda
        _Binding.spFormMoneda.setSelection(0)

        _Binding.spFormMoneda.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View,
                i: Int,
                l: Long,
            ) {
                _MonedaSpinnerTemp = (adapterView?.getItemAtPosition(i) as MonedaModel)

                SpinnerAdapterTasaCambio =
                    AdapterSpinnerTasaCambio(
                        this@Calculadora,
                        _TasaCambioServiceService.getAllTasaCambios(_Realm, _MonedaSpinnerTemp.ID),
                        layoutInflater
                    )
                _Binding.spFormTasaCambio.adapter = SpinnerAdapterTasaCambio
                _Binding.spFormTasaCambio.setSelection(0)

                _Binding.etxtFormDineroCambiado.setText("")
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })

        _Binding.spFormTasaCambio.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View,
                i: Int,
                l: Long,
            ) {
                _TasaCambioSpinnerTemp = (adapterView?.getItemAtPosition(i) as TasaCambioModel)

                RealizarCalculo()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })

        _Binding.BtnFormCalcular.setOnClickListener {
            RealizarCalculo()
        }
    }

    private fun RealizarCalculo() {
        var CalculoMonto = _Binding.etxtFormCantidad.text.toString()
            .toFloat() * _TasaCambioSpinnerTemp.TasaCambio!!.toFloat()

        _Binding.etxtFormDineroCambiado.setText("Monto Final es ${CalculoMonto}")
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}