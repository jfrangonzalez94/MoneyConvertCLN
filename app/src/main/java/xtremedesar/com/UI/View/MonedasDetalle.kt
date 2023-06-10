package xtremedesar.com.UI.View

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import www.sanju.todonotes.Interface.MonedaService
import www.sanju.todonotes.Interface.TasaCambioService
import xtremedesar.com.Data.Model.MonedaConvertModel
import xtremedesar.com.Data.Model.MonedaModel
import xtremedesar.com.R
import xtremedesar.com.databinding.ActivityMonedasBinding
import xtremedesar.com.databinding.ActivityMonedasDetalleBinding

class MonedasDetalle : AppCompatActivity() {

    private lateinit var _Binding: ActivityMonedasDetalleBinding
    private lateinit var _Realm: Realm
    lateinit var _Dialogo: Dialog
    private var _TasaCambioServiceService: TasaCambioService = TasaCambioService()
    lateinit var _Moneda: MonedaConvertModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _Binding = ActivityMonedasDetalleBinding.inflate(layoutInflater)
        setContentView(_Binding.root)

        _Moneda = (intent.getSerializableExtra("MonedaDetalle") as? MonedaConvertModel)!!
        supportActionBar?.setTitle("Detalle de Moneda ${_Moneda.Nombre}")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //INICIAR REALM
        _Realm = Realm.getDefaultInstance()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}