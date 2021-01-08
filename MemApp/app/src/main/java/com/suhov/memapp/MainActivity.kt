package com.suhov.memapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.unit.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "testText - hello compose!", modifier = Modifier.padding(16.dp))
                Spacer(modifier = Modifier.padding(top = 10.dp))

                Button(onClick = {
                    Toast.makeText(baseContext, "it's work!", Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = "Button Compose Test")
                }
            }
        }

    }
}