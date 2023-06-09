package xtremedesar.com.UI.View

import android.app.Dialog
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.thecode.aestheticdialogs.AestheticDialog
import com.thecode.aestheticdialogs.OnDialogClickListener
import io.realm.Realm
import www.sanju.todonotes.Interface.MonedaService
import xtremedesar.com.Application.MoneyApplication.Companion._FunsHelper
import xtremedesar.com.Data.Model.MonedaModel
import xtremedesar.com.R
import xtremedesar.com.UI.Adapter.AdapterMoneda
import xtremedesar.com.databinding.ActivityMenuPrincipalBinding
import xtremedesar.com.databinding.AgregarRegistroMonedaBinding

class MenuPrincipal : AppCompatActivity(), AdapterMoneda.MonedaAdapterListener {

    private lateinit var _Binding: ActivityMenuPrincipalBinding
    lateinit var Toggle: ActionBarDrawerToggle
    private lateinit var _Realm: Realm
    private var _MonedaService: MonedaService = MonedaService()
    lateinit var _Dialogo: Dialog
    private lateinit var MonedaAdapter: AdapterMoneda


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _Binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(_Binding.root)
        supportActionBar?.setTitle("Bienvenido a CLN")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //INICIAR REALM
        _Realm = Realm.getDefaultInstance()

        Toggle = ActionBarDrawerToggle(this, _Binding.myDrawerLayout, 0, 0)
        _Binding.myDrawerLayout.addDrawerListener(Toggle)
        Toggle.syncState()

        _Binding.myDrawerView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_sistema_version -> {
                    _FunsHelper.InfoDialogSimple(
                        this,
                        getString(R.string.app_name),
                        "La Versión Actual en tu dispositivo es ${
                            packageManager.getPackageInfo(
                                this.packageName,
                                PackageManager.GET_ACTIVITIES
                            ).versionName
                        }"
                    )
                }

                R.id.nav_sistema_cerrar -> {
                    AntesdeSalir()
                }
            }
            true
        }

        CargarListaMonedas()

    }


    private fun CargarListaMonedas() {
        _Dialogo = _FunsHelper.CargarDialog(this)

        MonedaAdapter = AdapterMoneda(this, _MonedaService.getAllMonedas(_Realm), this)
        _Binding.appBarMain.RVListaMonedas.layoutManager = LinearLayoutManager(this)
        _Binding.appBarMain.RVListaMonedas.adapter = MonedaAdapter
        _Dialogo.dismiss()

        /*
        try {
            _MonedaService.addMoneda(_Realm, "Dolar", "USA", "$")
            _MonedaService.addMoneda(_Realm, "Euro", "España", "€")
            _MonedaService.addMoneda(_Realm, "Cordoba", "Nicaragua", "C$")

            _Dialogo.dismiss()
            _FunsHelper.SuccessDialogSimple(
                this@MenuPrincipal,
                "Exito",
                "Se Agregó Correctamente"
            )
        } catch (e: Exception) {
            _Dialogo.dismiss()
            _FunsHelper.ErrorDialogSimple(
                this@MenuPrincipal,
                "Error",
                "No Se Agregó Correctamente"
            )
        }
         */

    }

    private fun onMonitoreoAgregar() {
        val AlertDialog = AlertDialog.Builder(this)

        val _BindingInflaDialog = AgregarRegistroMonedaBinding.inflate(layoutInflater)
        AlertDialog.setView(_BindingInflaDialog.root)

        val ShowDialog = AlertDialog.show()
        AlertDialog.create()

        _BindingInflaDialog.TitFormVive.setText("Agregar Información Moneda")

        _BindingInflaDialog.BtnFormGuardar.setOnClickListener {
            val _Vivero = Registro(
                _Latitud,
                _Longitud,
                _BindingInflaDialog.etxtDescripFormVive.text.toString(),
                DepartamentoId,
                MunicipioId,
                ComunidadId,
                DelegacionId
            )
            AgregarFormViveros(_Vivero)
            ShowDialog.dismiss()
        }
    }

    override fun onMonedaDetalle(Posicion: Int, Moneda: MonedaModel?) {
    }

    override fun onMonedaEditar(Posicion: Int, Moneda: MonedaModel?) {
    }

    override fun onMonedaEliminar(Posicion: Int, Moneda: MonedaModel?) {
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (Toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        AntesdeSalir()
    }

    private fun AntesdeSalir() {
        _FunsHelper.WarningDialogConfirm(
            this,
            getString(R.string.app_name),
            getString(R.string.alert_3)
        ).setOnClickListener(object : OnDialogClickListener {
            override fun onClick(dialog: AestheticDialog.Builder) {
                finish()
            }
        }).show()
    }


}