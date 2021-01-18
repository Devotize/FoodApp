package com.example.jetpackcoposewithrecepieapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.jetpackcoposewithrecepieapp.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    @Inject lateinit var someRandomString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: $someRandomString")


//        setContent {
//            ScrollableColumn(
//                    modifier = Modifier
//                            .fillMaxSize()
//                            .background(color = Color(0xFFF2F2F2))
//            ) {
//                Image(
//                    bitmap = imageFromResource(resources, R.drawable.happy_meal_small),
//                    modifier = Modifier.height(300.dp),
//                    contentScale = ContentScale.Crop
//                )
//                Column(modifier = Modifier.padding(16.dp)) {
//                    Row(
//                            modifier = Modifier
//                                    .fillMaxWidth(),
//                            horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        Text(
//                                text = "Happy Meal",
//                                style = TextStyle(
//                                        fontSize = TextUnit.Sp(26)
//                                ),
//
//                        )
//                        Text(
//                                text = "$5.99",
//                                style = TextStyle(
//                                        color = Color(0xFF85bb65),
//                                        fontSize = TextUnit.Sp(17)
//                                ),
//                                modifier = Modifier
//                                        .align(Alignment.CenterVertically)
//                        )
//                    }
//
//                    Spacer(modifier = Modifier.padding(top = 10.dp))
//                    Text(
//                        text = "800 Calories",
//                        style = TextStyle(
//                            fontSize = TextUnit.Sp(17)
//                        )
//                    )
//                    Spacer(modifier = Modifier.padding(top = 10.dp))
//                    Button(
//                            onClick = {},
//                            modifier = Modifier.align(Alignment.CenterHorizontally)
//                    ) {
//                        Text(text = "Order Now")
//                    }
//
//                }
//            }
//        }

    }
}