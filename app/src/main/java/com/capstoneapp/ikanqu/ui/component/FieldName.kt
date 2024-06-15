package com.capstoneapp.ikanqu.ui.component

import android.content.Context
import android.graphics.Canvas
import android.text.InputType
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class FieldName constructor(
    context: Context, attrs: AttributeSet
) : AppCompatEditText(context, attrs) {


    init {
        inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME or InputType.TYPE_CLASS_TEXT
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        hint = "Name"
        textAlignment = TEXT_ALIGNMENT_TEXT_START

    }
}