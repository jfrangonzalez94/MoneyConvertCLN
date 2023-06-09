package desarrollonica.com.ni.controlptypanama.Helper

import android.app.Activity
import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import com.thecode.aestheticdialogs.*
import java.util.*
import xtremedesar.com.R


class FunsHelper {

    //DIALOGO PARA CARGAR INFORMACIÓN
    fun CargarDialog(_Context: Activity): Dialog {
        val Dialogo = Dialog(_Context)
        Dialogo.setContentView(R.layout.upload_progress)
        if (Dialogo.window != null) {
            Dialogo!!.window!!.setBackgroundDrawable(ColorDrawable(0))
        }
        Dialogo.setCancelable(false)
        Dialogo.show()
        return Dialogo
    }

    fun InfoDialogSimple(
        _Context: Activity,
        TituloError: String,
        CadenaError: String,
    ) {
        AestheticDialog.Builder(
            _Context,
            DialogStyle.RAINBOW,
            DialogType.INFO,
        )
            .setTitle(TituloError)
            .setMessage(CadenaError)
            .setDuration(3000)
            .setAnimation(DialogAnimation.SHRINK)
            .show()
    }

    fun SuccessDialogSimple(
        _Context: Activity,
        TituloError: String,
        CadenaError: String,
    ) {
        AestheticDialog.Builder(
            _Context,
            DialogStyle.RAINBOW,
            DialogType.SUCCESS,
        )
            .setTitle(TituloError)
            .setMessage(CadenaError)
            .setDuration(2000)
            .setAnimation(DialogAnimation.SHRINK)
            .show()
    }

    fun ErrorDialogSimple(
        _Context: Activity,
        TituloError: String,
        CadenaError: String,
    ) {
        AestheticDialog.Builder(
            _Context,
            DialogStyle.RAINBOW,
            DialogType.ERROR,
        )
            .setTitle(TituloError)
            .setMessage(CadenaError)
            .setDuration(3000)
            .setAnimation(DialogAnimation.SHRINK)
            .show()
    }

    fun SuccessDialogConfirm(
        _Context: Activity,
        TituloError: String,
        CadenaError: String,
    ): AestheticDialog.Builder {
        return AestheticDialog.Builder(
            _Context,
            DialogStyle.FLAT,
            DialogType.SUCCESS,
        )
            .setTitle(TituloError)
            .setMessage(CadenaError)
            .setAnimation(DialogAnimation.SHRINK)
    }

    fun WarningDialogConfirm(
        _Context: Activity,
        TituloError: String,
        CadenaError: String,
    ): AestheticDialog.Builder {
        return AestheticDialog.Builder(
            _Context,
            DialogStyle.FLAT,
            DialogType.WARNING,
        )
            .setTitle(TituloError)
            .setMessage(CadenaError)
            .setAnimation(DialogAnimation.SHRINK)
    }

    fun ErrorDialogConfirm(
        _Context: Activity,
        TituloError: String,
        CadenaError: String,
    ): AestheticDialog.Builder {
        return AestheticDialog.Builder(
            _Context,
            DialogStyle.FLAT,
            DialogType.ERROR,
        )
            .setTitle(TituloError)
            .setMessage(CadenaError)
            .setAnimation(DialogAnimation.SHRINK)
    }


}