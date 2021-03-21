package com.suhov.memappwocompose

import android.text.Editable
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.text.method.DigitsKeyListener
import android.widget.Button
import android.widget.EditText
import java.util.ArrayList

class MaskWatcher(defaultMask: String, private val editText: EditText, private val arrMask: ArrayList<MaskItem> = arrayListOf()) :
    TextWatcher {
    private var mask = defaultMask
    private var countryCode = "+7"
    private var isRunning = false
    private var isDeleting = false
    private var isNotRebuild = false
    private var needUpdate = false
    private var selectStart = 0
    private var selectRebuild = 0
    private var saveText = ""
    private var maskLength = 1
    private var defaultMaskLength = 1

    init {
        editText.filters = arrayOf<InputFilter>(
            InputFilter.LengthFilter(defaultMask.length)
        )
        editText.inputType = InputType.TYPE_CLASS_NUMBER
        editText.keyListener = DigitsKeyListener.getInstance("0123456789")

        editText.setOnFocusChangeListener { v, hasFocus ->

            editText.setText("+")
            editText.setSelection(1)
            //editText.requestFocus()

        }
        //editText.setText("+7 123 456-78-90")

    }

    override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {}
    override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {
        isDeleting = count > after

        if (isDeleting)
            selectStart = start
        if(isDeleting && mask[selectStart] != '#' && charSequence.length != 0 && selectStart != charSequence.length) {
            saveText = charSequence.toString()
            selectStart = start
        }
    }

    override fun afterTextChanged(editable: Editable) {
        maskLength = checkMaskLength(editable.toString())

        val editableLength = editable.length
        if(isDeleting && mask[selectStart] != '#' && editableLength != 0 && selectStart != editableLength && !isNotRebuild){
            selectStart = editText.selectionStart
            editText.setText(saveText)
            if (selectStart <= 0 && editableLength > 0 && countryCode.length > 2){
                editText.setSelection(selectStart + 1, countryCode.length)
            }
            else if (selectStart <= 1 && editableLength > 0){
                editText.setSelection(maskLength)
            } else {
                editText.setSelection(selectStart)
            }
        }

        if(editable.isEmpty()){
            editText.setText("+")
            editText.setSelection(1)
        }

        if(!isDeleting){
            setCodeCountry(editable.toString())
        }


        if (checkNeedRebuild(editable) || !maskIsValid()) {
            rebuildString(editable)
        }

        isNotRebuild = false
    }

    private fun checkMaskLength(toString: String): Int {
        for(i in toString.indices){
            if (mask[i] == ' '){
                return i
            }
        }
        return defaultMaskLength
    }

    private fun checkNeedRebuild(editable: Editable): Boolean {
        val checkContaints = editable.contains("--")  ||
                editable.contains("+-") ||
                editable.contains(" -") ||
                editable.contains("- ") ||
                editable.contains("-+") ||
                editable.contains("+ ") ||
                editable.contains(" +") ||
                editable.contains("  ")

        val checkLast = if(editable.isNotEmpty()){
            ((editable.last() == '+'  ||
                    editable.last() == '-'||
                    editable.last() == ' ') &&
                    editable.toString().length == mask.length)
        } else {
            false
        }

        return checkContaints || checkLast


    }

    private fun rebuildString(string: Editable) {
        if(!isNotRebuild && !isRunning) {
            isNotRebuild = true
            isRunning = true
            val clearString = getTrimString(string.toString())

            var newString = ""

            for (i in clearString.indices) {
                val editableLength = newString.length
                if (i < mask.length) {
                    if (mask[editableLength] != '#') {
                        newString += (mask[editableLength])
                    } else if (mask[editableLength - 1] != '#') {
                        newString += mask[editableLength]
                    }
                }
                newString += clearString[i]
            }

            selectRebuild = editText.selectionStart

            editText.setText(newString)
            if (selectStart == selectRebuild) {
                editText.setSelection(selectRebuild)
            } else {
                if (selectRebuild < editText.length() && checkCursor(selectRebuild) && !isDeleting) {
                    editText.setSelection(selectRebuild + 1)
                }
                else if (selectRebuild < editText.length()&& checkNeedRebuild(string)) {
                    editText.setSelection(selectRebuild)
                }else if (selectRebuild < editText.length() ) {
                    editText.setSelection(selectRebuild)
                }else {
                    editText.setSelection(editText.length())
                }
            }

            isRunning = false
            needUpdate = true
            return
        }
    }



    fun getTrimString(string: String = ""): String {
        var tempString = ""
        tempString =
            if (string == "")
                editText.text.toString()
            else
                string

        return string
            .replace(" ", "")
            .replace("-", "")
            .replace("+", "")
    }


    private fun checkCursor(numRebuild: Int): Boolean {
        return if (numRebuild - 1 >= 0)
            mask[numRebuild-1] != '#'
        else
            false
    }

    private fun maskIsValid(): Boolean{
        val value = editText.text.toString()
        for(i in value.indices){
            if (mask[i] != '#' && mask[i] != value[i])
                return false
        }
        return true
    }

    private fun setCodeCountry(string: String) {
        var code = ""
        var length = 16

        if (string.length >= 2)
            code = string[0] + string[1].toString()

        if (arrMask.isNotEmpty() && code.isNotEmpty())
            arrMask.forEach{ itemMask ->
                itemMask.initialString.forEach { codeMask ->
                    if (code == codeMask) {
                        mask = itemMask.mask
                        countryCode = itemMask.countryCode
                        setLengthEditText(itemMask.mask.length)
                        if (string == codeMask){
                            editText.setText(itemMask.countryCode + " ")
                            setCursorLastPosition()
                        }
                    }
                }
            }
    }

    private fun setCursorLastPosition(){
        editText.setSelection(editText.length())
    }

    private fun setLengthEditText(length: Int){
        editText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(length))
    }

    data class MaskItem (val nameMask:String,val countryCode:String, val mask:String, val initialString:Array<String>)
}

class CustomMaskWatcherTelephonyLogic(defaultMask: String, private val editText: EditText, private val button:Button, private val arrMask: ArrayList<MaskItem> = arrayListOf()) :
    TextWatcher {
    private var mask = defaultMask
    private var countryCode = "+7"
    private var isRunning = false
    private var isDeleting = false
    private var isNotRebuild = false
    private var needUpdate = false
    private var selectStart = 0
    private var selectRebuild = 0
    private var saveText = ""
    private var maskLength = 1
    private var defaultMaskLength = 1

    init {
        editText.filters = arrayOf<InputFilter>(
            InputFilter.LengthFilter(defaultMask.length)
        )
        editText.inputType = InputType.TYPE_CLASS_NUMBER
        editText.keyListener = DigitsKeyListener.getInstance("0123456789")
        editText.setText("+")
        editText.setSelection(1)
        editText.requestFocus()
        button.isEnabled = false

        if (BuildConfig.DEBUG) {
            editText.setText("+7 555 555-55-55")
            setCursorLastPosition()
            button.isEnabled = true
        }
    }

    override fun onTextChanged(charSequence: CharSequence, start: Int, before: Int, count: Int) {}
    override fun beforeTextChanged(charSequence: CharSequence, start: Int, count: Int, after: Int) {
        isDeleting = count > after

        if (isDeleting)
            selectStart = start
        if (isDeleting && mask[selectStart] != '#' && charSequence.isNotEmpty() && selectStart != charSequence.length) {
            saveText = charSequence.toString()
            selectStart = start
        }
    }

    override fun afterTextChanged(editable: Editable) {
        maskLength = checkMaskLength(editable.toString())

        val editableLength = editable.length
        if (isDeleting && mask[selectStart] != '#' && editableLength != 0 && selectStart != editableLength && !isNotRebuild) {
            selectStart = editText.selectionStart
            editText.setText(saveText)
            if (selectStart <= 0 && editableLength > 0 && countryCode.length > 2) {
                editText.setSelection(selectStart + 1, countryCode.length)
            } else if (selectStart <= 1 && editableLength > 0) {
                editText.setSelection(maskLength)
            } else {
                editText.setSelection(selectStart)
            }
            return
        }

        if (editable.isEmpty()) {
            editText.setText("+")
            editText.setSelection(1)
        }

        if (!isDeleting) {
            setCodeCountry(editable.toString())
        }


        if (checkNeedRebuild(editable) || !maskIsValid()) {
            rebuildString(editable)
            return
        }

        checkLengthString(editable.toString())
        isNotRebuild = false
    }

    private fun checkLengthString(code: String) {
        val test = code.length == mask.length
        button.isEnabled = test
    }


    private fun checkMaskLength(toString: String): Int {
        for (i in toString.indices) {
            if (mask[i] == ' ') {
                return i
            }
        }
        return defaultMaskLength
    }

    private fun checkNeedRebuild(editable: Editable): Boolean {
        val checkContaints = editable.contains("--") ||
                editable.contains("+-") ||
                editable.contains(" -") ||
                editable.contains("- ") ||
                editable.contains("-+") ||
                editable.contains("+ ") ||
                editable.contains(" +") ||
                editable.contains("  ")

        val checkLast = if (editable.isNotEmpty()) {
            ((editable.last() == '+' ||
                    editable.last() == '-' ||
                    editable.last() == ' ') &&
                    editable.toString().length == mask.length)
        } else {
            false
        }

        return checkContaints || checkLast


    }

    private fun rebuildString(string: Editable) {
        if (!isNotRebuild && !isRunning) {
            isNotRebuild = true
            isRunning = true
            val clearString = getTrimString(string.toString())

            var newString = ""

            for (i in clearString.indices) {
                val editableLength = newString.length
                if (mask.length != newString.length)
                    if (i < mask.length) {
                        if (mask[editableLength] != '#') {
                            newString += (mask[editableLength])
                        } else if (mask[editableLength - 1] != '#') {
                            newString += mask[editableLength]
                        }
                    }
                newString += clearString[i]
            }

            selectRebuild = editText.selectionStart

            editText.setText(newString)
            if (selectStart == selectRebuild) {
                editText.setSelection(selectRebuild)
            } else {
                if (selectRebuild < editText.length() && checkCursor(selectRebuild) && !isDeleting) {
                    editText.setSelection(selectRebuild + 1)
                } else if (selectRebuild < editText.length() && checkNeedRebuild(string)) {
                    editText.setSelection(selectRebuild)
                } else if (selectRebuild < editText.length()) {
                    editText.setSelection(selectRebuild)
                } else {
                    editText.setSelection(editText.length())
                }
            }

            checkLengthString(newString)
            isRunning = false
            needUpdate = true
            return
        }
    }

    fun getTrimString(string: String = ""): String {
        val tempString: String =
            if (string == "")
                editText.text.toString()
            else
                string

        return tempString
            .replace(" ", "")
            .replace("-", "")
            .replace("+", "")
    }


    private fun checkCursor(numRebuild: Int): Boolean {
        return if (numRebuild - 1 >= 0)
            mask[numRebuild-1] != '#'
        else
            false
    }

    private fun maskIsValid(): Boolean{
        val value = editText.text.toString()
        for(i in value.indices){
            if (mask[i] != '#' && mask[i] != value[i])
                return false
        }
        return true
    }

    private fun setCodeCountry(string: String) {
        var code = ""

        if (string.length >= 2)
            code = string[0] + string[1].toString()

        if (arrMask.isNotEmpty() && code.isNotEmpty())
            arrMask.forEach{ itemMask ->
                itemMask.initialString.forEach { codeMask ->
                    if (code == codeMask) {
                        mask = itemMask.mask
                        countryCode = itemMask.countryCode
                        setLengthEditText(itemMask.mask.length)
                        isNotRebuild = false
                        if (string == codeMask){
                            editText.setText(itemMask.countryCode + " ")
                            setCursorLastPosition()
                        }
                    }
                }
            }
    }

    private fun setCursorLastPosition(){
        editText.setSelection(editText.length())
    }

    private fun setLengthEditText(length: Int){
        editText.filters = arrayOf<InputFilter>(InputFilter.LengthFilter(length))
    }

    data class MaskItem (val nameMask:String,val countryCode:String, val mask:String, val initialString:Array<String>)
}