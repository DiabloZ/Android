package com.suhov.memappwocompose

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottom_sheet_layout.*
class StorageTrash () {
    var dialogIsOpen = false

    fun bottomSheetDialog(bottom_sheet_display: Button, fragment: Fragment){
        bottom_sheet_display.setOnClickListener{
            if (!dialogIsOpen) {
                Log.i("TAGTAGTAG", "dialogIsOpen: ${dialogIsOpen}")
                dialogIsOpen = true
                val bottomSheetDialog = BottomSheetDialog(fragment.requireContext(), R.style.ButtomSheetTheme)

                val sheetView = LayoutInflater.from(fragment.requireContext())
                    .inflate(R.layout.bottom_sheet_layout, fragment.bottom_sheet)
                bottomSheetDialog.setContentView(sheetView)
                bottomSheetDialog.show()

                bottomSheetDialog.setOnDismissListener {
                    dialogIsOpen = false
                }
            }
        }
    }

}