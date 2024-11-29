package hu.bme.aut.android.receptes_konyv.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NormalTextField(value:String,
                    label:String,
                    modifier: Modifier=Modifier,
                    onValueChange:(String)->Unit,
                    leadingIcon: @Composable (() -> Unit)? = null,
                    trailingIcon: @Composable (() -> Unit)? = null,
                    singleLine: Boolean=false,
                    enabled:Boolean=true,
                    shape:RoundedCornerShape= RoundedCornerShape(5.dp),
                    onDone: (KeyboardActionScope.() -> Unit)?,
                    ){


    TextField(value = value,
        onValueChange = onValueChange,
        label = {Text(text = label)},
        modifier = modifier.clip(shape),
        leadingIcon=leadingIcon,
        trailingIcon=trailingIcon,
        singleLine=singleLine,
        enabled=enabled,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = onDone
        ),
        colors = TextFieldDefaults.colors(
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedContainerColor = MaterialTheme.colorScheme.background
        ),
        shape=shape
    )
}
@Preview
@Composable
fun NormalTextFieldPreview(){
    Column(modifier = Modifier.fillMaxSize()) {
        NormalTextField(value = "Hi!", label = "", onValueChange = {}) {

        }
    }
}