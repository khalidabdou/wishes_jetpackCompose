package com.example.wishes_jetpackcompose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.disk.DiskCache
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ViewPager() {
    val context= LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .diskCache {
            DiskCache.Builder()
                .directory(context.cacheDir.resolve("image_cache"))
                .maxSizePercent(0.02)
                .build()
        }
        .build()


    val painter = rememberAsyncImagePainter(
        model = "http://wishesyougood.com/category/4650281_27188.gif",
        imageLoader = imageLoader,
        contentScale = ContentScale.Inside

    )
    Column() {
        HorizontalPager(
            modifier = Modifier.weight(9f),
            count = 10) { page ->
            Box(modifier = Modifier.fillMaxSize()){
                Image(
                    painter = painter, contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(MaterialTheme.colorScheme.primary)) {

        }
    }
   
}