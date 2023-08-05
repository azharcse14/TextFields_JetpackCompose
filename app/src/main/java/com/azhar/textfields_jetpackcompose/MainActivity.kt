package com.azhar.textfields_jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.Visibility
import com.azhar.textfields_jetpackcompose.ui.theme.TextFieldsJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextFieldsJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BuildTextFiled()
                }
            }
        }
    }
}

@Composable
fun BuildTextFiled(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        SimpleTextField()
        LabelAndPlaceHolder()
        TextFieldWithInputType()
        OutLineTextFieldSample()
        TextFieldWithIcons()
        TextFieldWithDoubleIcons()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTextField() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = text,
        onValueChange = { newText ->
            text = newText
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabelAndPlaceHolder() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        label = { Text(text = "Your Label") },
        placeholder = { Text(text = "Your Placeholder/Hint") },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithInputType() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(
        value = text,
        label = { Text(text = "Number Input Type") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { it ->
            text = it
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutLineTextFieldSample() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        label = { Text(text = "Enter Your Name") },
        onValueChange = {
            text = it
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun OutLineColorTextFieldSample() {
    var value by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = value,
        onValueChange = { newText ->
            value = newText
        },
        label = { Text(text = "Name") },
        placeholder = { Text(text = "Enter your name") },
        colors = TextFieldDefaults.outlinedTextFieldColors(
//            backgroundColor = Color.Green.copy(alpha = 0.4f),
            cursorColor = Color.Yellow
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithIcons() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    return OutlinedTextField(
        value = text,
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
        //trailingIcon = { Icon(imageVector = Icons.Default.Add, contentDescription = null) },
        onValueChange = {
            text = it
        },
        label = { Text(text = "Email address") },
        placeholder = { Text(text = "Enter your e-mail") },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithDoubleIcons() {
    // Creating a variable to store password
    var password by remember { mutableStateOf("") }

    // Creating a variable to store toggle state
    var passwordVisible by remember { mutableStateOf(false) }

    // Create a Text Field for giving password input
    return TextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Password") },
        singleLine = true,
        placeholder = { Text("Password") },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val image = if (passwordVisible)
                Icons.Filled.KeyboardArrowLeft
            else Icons.Filled.ArrowForward

            // Localized description for accessibility services
            val description = if (passwordVisible) "Hide password" else "Show password"

            // Toggle button to hide or display password
            IconButton(onClick = {passwordVisible = !passwordVisible}){
                Icon(imageVector  = image, description)
            }
        }
    )

}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TextFieldsJetpackComposeTheme {
        BuildTextFiled()
    }
}