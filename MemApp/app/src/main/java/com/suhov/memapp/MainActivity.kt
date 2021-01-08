package com.suhov.memapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestTest()
        }
    }


    @Preview
    @Composable
    fun TestTest(){
        Example3()
        //Example2()
    }

    @Composable
    fun Example3(){
        ScrollableColumn(
                modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.LightGray)
        ) {
            Image(
                bitmap = imageFromResource(
                    res = resources,
                    resId = R.drawable.robo_vacuum_cleaner
                ),
                modifier = Modifier.height(300.dp),
                    contentScale = ContentScale.Crop,
            )
            Column(modifier = Modifier.padding(16.dp)
            ){
                Text(
                        text = "Robo",
                        style = TextStyle(
                                fontSize = 26.sp))
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Text(text = "2000 Pa")
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Text(text = "$150.00", style = TextStyle(
                        fontSize = 17.sp,
                color = Color(0x81AA00FF)))

            }
        }
    }

    @Composable
    fun Example2(){
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