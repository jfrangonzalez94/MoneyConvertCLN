package xtremedesar.com.UI.View

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.thecode.aestheticdialogs.AestheticDialog
import com.thecode.aestheticdialogs.OnDialogClickListener
import io.realm.Realm
import www.sanju.todonotes.Interface.MonedaService
import www.sanju.todonotes.Interface.TasaCambioService
import xtremedesar.com.Application.MoneyApplication.Companion._FunsHelper
import xtremedesar.com.R
import xtremedesar.com.databinding.ActivityMenuPrincipalBinding

class MenuPrincipal : AppCompatActivity() {

    private lateinit var _Binding: ActivityMenuPrincipalBinding
    lateinit var Toggle: ActionBarDrawerToggle
    private lateinit var _Realm: Realm
    private var _MonedaService: MonedaService = MonedaService()
    private var _TasaCambioServiceService: TasaCambioService = TasaCambioService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _Binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(_Binding.root)
        supportActionBar?.setTitle("Bienvenido a CLN")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        Toggle = ActionBarDrawerToggle(this, _Binding.myDrawerLayout, 0, 0)
        _Binding.myDrawerLayout.addDrawerListener(Toggle)
        Toggle.syncState()

        _Binding.myDrawerView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_moneda_lista -> {
                    startActivity(Intent(this, Monedas::class.java))
                }

                R.id.nav_moneda_calculadora -> {
                    startActivity(Intent(this, Calculadora::class.java))
                }

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

        //INICIAR REALM
        _Realm = Realm.getDefaultInstance()

        CargarContadores()
    }

    private fun CargarContadores() {
        _Binding.appBarMain.txtTotalmonedas.setText("${_MonedaService.getAllMonedas(_Realm).size}")
        _Binding.appBarMain.txtTotaltasacambio.setText(
            "${
                _TasaCambioServiceService.getAllTasaCambiosGen(
                    _Realm
                ).size
            }"
        )
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