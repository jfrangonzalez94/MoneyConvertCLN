package xtremedesar.com.UI.View

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.thecode.aestheticdialogs.AestheticDialog
import com.thecode.aestheticdialogs.OnDialogClickListener
import io.realm.Realm
import www.sanju.todonotes.Interface.MonedaService
import xtremedesar.com.Application.MoneyApplication
import xtremedesar.com.Data.Model.MonedaConvertModel
import xtremedesar.com.Data.Model.MonedaModel
import xtremedesar.com.R
import xtremedesar.com.UI.Adapter.AdapterMoneda
import xtremedesar.com.databinding.ActivityMonedasBinding
import xtremedesar.com.databinding.AgregarRegistroMonedaBinding

class Monedas : AppCompatActivity(), AdapterMoneda.MonedaAdapterListener {

    private lateinit var _Binding: ActivityMonedasBinding
    private lateinit var _Realm: Realm
    lateinit var _Dialogo: Dialog
    private var _MonedaService: MonedaService = MonedaService()
    private lateinit var MonedaAdapter: AdapterMoneda

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _Binding = ActivityMonedasBinding.inflate(layoutInflater)
        setContentView(_Binding.root)
        supportActionBar?.setTitle("Lista de Monedas")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //INICIAR REALM
        _Realm = Realm.getDefaultInstance()

        CargarListaMonedas()

        _Binding.btnAgregar.setOnClickListener { onMonitoreoAgregar() }
    }

    private fun CargarListaMonedas() {
        _Dialogo = MoneyApplication._FunsHelper.CargarDialog(this)

        MonedaAdapter = AdapterMoneda(this, _MonedaService.getAllMonedas(_Realm), this)
        _Binding.RVListaMonedas.layoutManager = LinearLayoutManager(this)
        _Binding.RVListaMonedas.adapter = MonedaAdapter
        _Dialogo.dismiss()
    }

    private fun onMonitoreoAgregar() {
        val AlertDialog = AlertDialog.Builder(this)

        val _BindingInflaDialog = AgregarRegistroMonedaBinding.inflate(layoutInflater)
        AlertDialog.setView(_BindingInflaDialog.root)

        val ShowDialog = AlertDialog.show()
        AlertDialog.create()

        _BindingInflaDialog.TitFormInfo.setText("Agregar Información Moneda")

        _BindingInflaDialog.BtnFormGuardar.setOnClickListener {

            try {
                _MonedaService.addMoneda(
                    _Realm,
                    _BindingInflaDialog.etxtFormNombre.text.toString(),
                    _BindingInflaDialog.etxtFormPais.text.toString(),
                    _BindingInflaDialog.etxtFormSimbolo.text.toString()
                )

                MoneyApplication._FunsHelper.SuccessDialogSimple(
                    this@Monedas,
                    "Exito",
                    "Se Agregó Correctamente"
                )
            } catch (e: Exception) {
                MoneyApplication._FunsHelper.ErrorDialogSimple(
                    this@Monedas,
                    "Error",
                    "No Se Agregó Correctamente"
                )
            }

            ShowDialog.dismiss()
        }
    }

    override fun onMonedaDetalle(Posicion: Int, Moneda: MonedaModel?) {
        val _Intent = Intent(this, MonedasDetalle::class.java)
        _Intent.putExtra(
            "MonedaDetalle",
            MonedaConvertModel(Moneda?.ID, Moneda?.Nombre, Moneda?.Pais, Moneda?.Simbolo)
        )
        startActivity(_Intent)
    }

    override fun onMonedaEditar(Posicion: Int, Moneda: MonedaModel?) {
        val AlertDialog = AlertDialog.Builder(this)

        val _BindingInflaDialog = AgregarRegistroMonedaBinding.inflate(layoutInflater)
        AlertDialog.setView(_BindingInflaDialog.root)

        val ShowDialog = AlertDialog.show()
        AlertDialog.create()

        _BindingInflaDialog.TitFormInfo.setText("Editar Información Moneda")
        _BindingInflaDialog.etxtFormNombre.setText(Moneda?.Nombre)
        _BindingInflaDialog.etxtFormPais.setText(Moneda?.Pais)
        _BindingInflaDialog.etxtFormSimbolo.setText(Moneda?.Simbolo)

        _BindingInflaDialog.BtnFormGuardar.setOnClickListener {

            try {
                _MonedaService.updateMoneda(
                    _Realm,
                    Moneda!!.ID,
                    _BindingInflaDialog.etxtFormNombre.text.toString(),
                    _BindingInflaDialog.etxtFormPais.text.toString(),
                    _BindingInflaDialog.etxtFormSimbolo.text.toString()
                )

                MonedaAdapter.notifyDataSetChanged()

                MoneyApplication._FunsHelper.SuccessDialogSimple(
                    this@Monedas,
                    "Exito",
                    "Se Edito Correctamente"
                )
            } catch (e: Exception) {
                MoneyApplication._FunsHelper.ErrorDialogSimple(
                    this@Monedas,
                    "Error",
                    "No Se Edito Correctamente"
                )
            }

            ShowDialog.dismiss()
        }
    }

    override fun onMonedaEliminar(Posicion: Int, Moneda: MonedaModel?) {
        MoneyApplication._FunsHelper.WarningDialogConfirm(
            this,
            getString(R.string.app_name),
            "Estas realmente seguro de Eliminar este Registro?"
        ).setOnClickListener(object : OnDialogClickListener {
            override fun onClick(_Dialog: AestheticDialog.Builder) {
                try {
                    _MonedaService.deleteMoneda(
                        _Realm,
                        Moneda!!.ID
                    )

                    MonedaAdapter.notifyDataSetChanged()

                    MoneyApplication._FunsHelper.SuccessDialogSimple(
                        this@Monedas,
                        "Exito",
                        "Se Eliminó Correctamente"
                    )
                } catch (e: Exception) {
                    MoneyApplication._FunsHelper.ErrorDialogSimple(
                        this@Monedas,
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