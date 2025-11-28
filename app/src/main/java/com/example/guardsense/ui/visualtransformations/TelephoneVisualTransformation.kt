package com.example.guardsense.ui.visualtransformations

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class TelephoneVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 11) text.text.substring(0..10) else text.text
        var out = ""
        for (i in trimmed.indices) {
            if (i == 0) {
                out += '('
            }
            out += trimmed[i]
            if (i == 1) {
                out += ") "
            }
            if (i == 6) {
                out += '-'
            }
        }

        val telephoneOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 0) return offset
                if (offset <= 1) return offset + 1
                if (offset <= 6) return offset + 3
                if (offset <= 11) return offset + 4
                return 15
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 1) return 0
                if (offset <= 3) return offset - 1
                if (offset <= 5) return 2
                if (offset <= 10) return offset - 3
                if (offset <= 15) return offset - 4
                return 11
            }
        }

        return TransformedText(AnnotatedString(out), telephoneOffsetTranslator)
    }
}
