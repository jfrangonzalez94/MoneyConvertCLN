package xtremedesar.com.Data.Model

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass
open class NotaModel() : RealmModel {
    @PrimaryKey
    var _ID: String = ""

    @Required
    var _Titulo: String? = ""

    @Required
    var _Descripcion: String? = ""
}
