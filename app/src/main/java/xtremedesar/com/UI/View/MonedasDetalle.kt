package xtremedesar.com.UI.View

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.thecode.aestheticdialogs.AestheticDialog
import com.thecode.aestheticdialogs.OnDialogClickListener
import io.realm.Realm
import www.sanju.todonotes.Interface.MonedaService
import www.sanju.todonotes.Interface.TasaCambioService
import xtremedesar.com.Application.MoneyApplication.Companion._FunsHelper
import xtremedesar.com.Data.Model.MonedaConvertModel
import xtremedesar.com.Data.Model.MonedaModel
import xtremedesar.com.Data.Model.TasaCambioModel
import xtremedesar.com.R
import xtremedesar.com.UI.Adapter.AdapterSpinnerMoneda
import xtremedesar.com.UI.Adapter.AdapterTasaCambio
import xtremedesar.com.databinding.ActivityMonedasDetalleBinding
import xtremedesar.com.databinding.AgregarRegistroTasacambioBinding

class MonedasDetalle : AppCompatActivity(), AdapterTasaCambio.TasaCambioAdapterListener {

    private lateinit var _Binding: ActivityMonedasDetalleBinding
    private lateinit var _Realm: Realm
    private lateinit var _Dialogo: Dialog
    private var _MonedaService: MonedaService = MonedaService()
    private var _TasaCambioServiceService: TasaCambioService = TasaCambioService()
    private lateinit var _Moneda: MonedaConvertModel
    private lateinit var _MonedaSpinnerTemp: MonedaModel
    lateinit var TasaCambioAdapter: AdapterTasaCambio
    private lateinit var SpinnerAdapterMoneda: AdapterSpinnerMoneda

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _Binding = ActivityMonedasDetalleBinding.inflate(layoutInflater)
        setContentView(_Binding.root)

        _Moneda = (intent.getSerializableExtra("MonedaDetalle") as? MonedaConvertModel)!!
        supportActionBar?.setTitle("Detalle de Moneda ${_Moneda.Nombre}")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //INICIAR REALM
        _Realm = Realm.getDefaultInstance()

        //CARGAR CONTENIDO
        CargarListaTasaCambios()

        _Binding.btnAgregar.setOnClickListener { onTasaCambioAgregar() }
    }

    private fun CargarListaTasaCambios() {
        _Dialogo = _FunsHelper.CargarDialog(this)

        _Binding.tvTcambioNombre.setText(_Moneda.Nombre)
        _Binding.tvTcambioPais.setText(_Moneda.Pais)
        _Binding.tvTcambioSimbolo.setText(_Moneda.Simbolo)
        TasaCambioAdapter = AdapterTasaCambio(
            this,
            _TasaCambioServiceService.getAllTasaCambios(_Realm, _Moneda.ID.toString()),
            this
        )
        _Binding.RVListaDetalleMonedas.layoutManager = LinearLayoutManager(this)
        _Binding.RVListaDetalleMonedas.adapter = TasaCambioAdapter
        _Dialogo.dismiss()
    }

    private fun onTasaCambioAgregar() {
        val AlertDialog = AlertDialog.Builder(this)

        val _BindingInflaDialog = AgregarRegistroTasacambioBinding.inflate(layoutInflater)
        AlertDialog.setView(_BindingInflaDialog.root)

        val ShowDialog = AlertDialog.show()
        AlertDialog.create()

        _BindingInflaDialog.TitFormInfo.setText("Agregar Información Tasa de Cambio")
        SpinnerAdapterMoneda =
            AdapterSpinnerMoneda(this, _MonedaService.getAllMonedas(_Realm), layoutInflater)
        _BindingInflaDialog.spFormMoneda.adapter = SpinnerAdapterMoneda
        _BindingInflaDialog.spFormMoneda.setSelection(0)

        _BindingInflaDialog.spFormMoneda.setOnItemSelectedListener(object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View,
                i: Int,
                l: Long,
            ) {
                _MonedaSpinnerTemp = (adapterView?.getItemAtPosition(i) as MonedaModel)
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        })

        _BindingInflaDialog.BtnFormGuardar.setOnClickListener {

            try {
                _TasaCambioServiceService.addTasaCambio(
                    _Realm,
                    _BindingInflaDialog.etxtFormCambio.text.toString(),
                    "${_MonedaSpinnerTemp.Pais} - ${_MonedaSpinnerTemp.Simbolo}",
                    _Moneda.ID.toString()
                )

                CargarListaTasaCambios()

                _FunsHelper.SuccessDialogSimple(
                    this@MonedasDetalle,
                    "Exito",
                    "Se Agregó Correctamente"
                )
            } catch (e: Exception) {
                _FunsHelper.ErrorDialogSimple(
                    this@MonedasDetalle,
                    "Error",
                    "No Se Agregó Correctamente"
                )
            }

            ShowDialog.dismiss()
        }
    }

    override fun onTasaCambioEliminar(Posicion: Int, TasaCambio: TasaCambioModel?) {
        _FunsHelper.WarningDialogConfirm(
            this,
            getString(R.string.app_name),
            "Estas realmente seguro de Eliminar este Registro?"
        ).setOnClickListener(object : OnDialogClickListener {
            override fun onClick(_Dialog: AestheticDialog.Builder) {
                try {
                    _TasaCambioServiceService.deleteTasaCambio(
                        _Realm,
                        TasaCambio!!.ID
                    )

                    CargarListaTasaCambios()

                    _FunsHelper.SuccessDialogSimple(
                        this@MonedasDetalle,
                        "Exito",
                        "Se Eliminó Correctamente"
                    )
                } catch (e: Exception) {
                    _FunsHelper.ErrorDialogSimple(
                        this@MonedasDetalle,
                        "Error",
                        "No Se Eliminó Correctamente"
                    )
                }
                _Dialog.dismiss()
            }
        }).show()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


}