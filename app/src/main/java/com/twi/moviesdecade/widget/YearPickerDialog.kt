package com.twi.moviesdecade.widget

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Html
import android.widget.NumberPicker
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.twi.moviesdecade.R
import java.util.*

class YearPickerDialog : DialogFragment() {

    private var listener: DatePickerDialog.OnDateSetListener? = null

    fun setListener(listener: DatePickerDialog.OnDateSetListener) {
        this.listener = listener
    }

    @SuppressLint("ResourceAsColor", "InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!, R.style.AlertDialogStyle)
        val inflater = activity!!.layoutInflater

        val cal = Calendar.getInstance()

        val dialog = inflater.inflate(R.layout.month_year_picker_dialog, null)
        val monthPicker = dialog.findViewById(R.id.picker_month) as NumberPicker
        val yearPicker = dialog.findViewById(R.id.picker_year) as NumberPicker

        monthPicker.minValue = 1
        monthPicker.maxValue = 12
        monthPicker.value = cal.get(Calendar.MONTH) + 1

        val year = cal.get(Calendar.YEAR)
        yearPicker.minValue = 1900
        yearPicker.maxValue = 3500
        yearPicker.value = year

        builder.setView(dialog).setPositiveButton(
            Html.fromHtml("<font color='#FF4081'>Ok</font>")
        ) { _, _ ->
            listener?.onDateSet(
                null,
                yearPicker.value,
                monthPicker.value,
                0
            )
        }.setNegativeButton(
            Html.fromHtml("<font color='#FF4081'>Cancel</font>")
        ) { _, _ -> this@YearPickerDialog.dialog?.cancel() }
        return builder.create()
    }
}
