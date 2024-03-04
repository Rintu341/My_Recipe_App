package com.example.myrecipeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun DetailsScreen(category: Category)
{
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            Text(text = category.strCategory, textAlign = TextAlign.Center)
            Image(
                painter = rememberAsyncImagePainter(category.strCategoryThumb), contentDescription = "${category.strCategory} Thimble",
                modifier = Modifier
                    .aspectRatio(1f)

            )
            Text(text = category.strCategoryDescription,
                modifier = Modifier
                .padding(4.dp)
                .verticalScroll(rememberScrollState())
                .aspectRatio(1f),
                textAlign = TextAlign.Justify
            )
    }
}

@Composable
@Preview(showBackground = true)
fun DetailsScreenPreview()
{
    DetailsScreen(category = Category("","","",""))
}