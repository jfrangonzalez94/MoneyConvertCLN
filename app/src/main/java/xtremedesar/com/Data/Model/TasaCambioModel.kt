package xtremedesar.com.Data.Model

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass
open class TasaCambioModel() : RealmModel {
    @PrimaryKey
    var ID: String = ""

    @Required
    var TasaCambio: String? = ""

    @Required
    var Pais: String? = ""

    @Required
    var IDMoneda: String? = ""
}
