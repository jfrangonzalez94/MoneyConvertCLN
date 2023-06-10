package www.sanju.todonotes.Interface

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.deleteFromRealm
import xtremedesar.com.Data.Model.MonedaModel
import xtremedesar.com.Data.Model.TasaCambioModel
import java.util.ArrayList
import java.util.UUID

class TasaCambioService : TasaCambioInterface {

    override fun getAllTasaCambios(_Realm: Realm, _IdMoneda: String): List<TasaCambioModel> {
        val _ListTasaCambio = _Realm.where(TasaCambioModel::class.java).findAll().toList().filter { it.IDMoneda == _IdMoneda }
        return _ListTasaCambio
    }

    override fun addTasaCambio(
        _Realm: Realm, _TasaCambio: String, _Pais: String, _IdMoneda: String
    ) {
        _Realm.executeTransaction { r: Realm ->
            val _TCambio = r.createObject(TasaCambioModel::class.java, UUID.randomUUID().toString())
            _TCambio.TasaCambio = _TasaCambio
            _TCambio.Pais = _Pais
            _TCambio.IDMoneda = _IdMoneda

            _Realm.insertOrUpdate(_TCambio)
        }
    }

    override fun updateTasaCambio(
        _Realm: Realm, _Id: String, _TasaCambio: String, _Pais: String, _IdMoneda: String
    ) {
        val _TCambio = _Realm.where(TasaCambioModel::class.java)
            .equalTo("ID", _Id)
            .findFirst()

        _Realm.executeTransaction {
            _TCambio?.TasaCambio = _TasaCambio
            _TCambio?.Pais = _Pais
            _TCambio?.IDMoneda = _IdMoneda
            _Realm.insertOrUpdate(_TCambio)
        }
    }

    override fun deleteTasaCambio(_Realm: Realm, _Id: String) {
        val _TCambio = _Realm.where(TasaCambioModel::class.java)
            .equalTo("ID", _Id)
            .findFirst()

        _Realm.executeTransaction {
            _TCambio!!.deleteFromRealm()
        }
    }

    override fun deleteAllTasaCambios(_Realm: Realm) {
        _Realm.executeTransaction { r: Realm ->
            r.delete(TasaCambioModel::class.java)
        }
    }
}