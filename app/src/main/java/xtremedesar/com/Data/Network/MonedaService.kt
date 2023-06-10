package www.sanju.todonotes.Interface

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.deleteFromRealm
import xtremedesar.com.Data.Model.MonedaModel
import java.util.ArrayList
import java.util.UUID

class MonedaService : MonedaInterface {

    override fun getAllMonedas(_Realm: Realm): List<MonedaModel> {
        val _ListMoneda = _Realm.where(MonedaModel::class.java).findAll().toList()
        return _ListMoneda
    }

    override fun addMoneda(_Realm: Realm, _Nombre: String, _Pais: String, _Simbolo: String) {
        _Realm.executeTransaction { r: Realm ->
            val _Moneda = r.createObject(MonedaModel::class.java, UUID.randomUUID().toString())
            _Moneda.Nombre = _Nombre
            _Moneda.Pais = _Pais
            _Moneda.Simbolo = _Simbolo

            _Realm.insertOrUpdate(_Moneda)
        }
    }

    override fun updateMoneda(
        _Realm: Realm,
        _Id: String,
        _Nombre: String,
        _Pais: String,
        _Simbolo: String
    ) {
        val _Moneda = _Realm.where(MonedaModel::class.java)
            .equalTo("ID", _Id)
            .findFirst()

        _Realm.executeTransaction {
            _Moneda?.Nombre = _Nombre
            _Moneda?.Pais = _Pais
            _Moneda?.Simbolo = _Simbolo
            _Realm.insertOrUpdate(_Moneda)
        }
    }

    override fun deleteMoneda(_Realm: Realm, _Id: String) {
        val _Moneda = _Realm.where(MonedaModel::class.java)
            .equalTo("ID", _Id)
            .findFirst()

        _Realm.executeTransaction {
            _Moneda!!.deleteFromRealm()
        }
    }

    override fun deleteAllMonedas(_Realm: Realm) {
        _Realm.executeTransaction { r: Realm ->
            r.delete(MonedaModel::class.java)
        }
    }
}