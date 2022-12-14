package com.example.wishes_jetpackcompose

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.wishes.jetpackcompose.data.entities.Page
import com.wishes.jetpackcompose.runtime.NavRoutes
import com.wishes.jetpackcompose.screens.ImagesFrom
import com.wishes.jetpackcompose.utlis.Const
import com.wishes.jetpackcompose.utlis.DEFAULT_RECIPE_IMAGE
import com.wishes.jetpackcompose.utlis.loadPicture
import com.wishes.jetpackcompose.viewModel.ImagesViewModel

import kotlinx.coroutines.ExperimentalCoroutinesApi


@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun ByCat(viewModel: ImagesViewModel, navHostController: NavHostController, catId:Int) {

    val context = LocalContext.current
    val scrollState = rememberLazyGridState()

    LaunchedEffect(Unit) {
        viewModel.getByCatRoom(catId)
    }

//    val imageLoader = ImageLoader.Builder(context)
//        .diskCache {
//            DiskCache.Builder()
//                .directory(context.cacheDir.resolve("image_cache"))
//                .maxSizePercent(0.02)
//                .build()
//        }
//        .build()

    val images =viewModel.imagesByCategory
    LazyVerticalGrid(
        state = scrollState,
        columns = GridCells.Adaptive(128.dp),

        content = {
            if (images.isEmpty()){
                items(10) {
                    repeat(10){
                        LoadingShimmerEffectImage()
                    }
                }
            }else{
                items(images.size) {
//                    val painter = rememberAsyncImagePainter(
//                        model = Const.directoryUpload + images[it].languageLable + "/" + images[it].image_upload,
//                        imageLoader = imageLoader,
//                        filterQuality= FilterQuality.Low
//
//                    )
                    val image = loadPicture(url =  Const.directoryUpload + images[it].languageLable + "/" + images[it].image_upload,
                        defaultImage = DEFAULT_RECIPE_IMAGE
                    ).value
                    image?.let { img ->
                        ImageItem(
                            img.asImageBitmap(),
                        ){
                            val page= Page(
                                page = it,
                                imagesList = ImagesFrom.ByCat.route,
                                cat =images[it].cat_id
                            )
                            navHostController.currentBackStackEntry?.savedStateHandle?.set(
                                key="page",
                                value = page
                            )
                            navHostController.navigate(NavRoutes.ViewPager.route)
                        }
                    }
                  /*  ImageItem( painter = painter){
                        val page= Page(
                            page = it,
                            imagesList = ImagesFrom.ByCat.route,
                            cat =images[it].cat_id
                        )
                        navHostController.currentBackStackEntry?.savedStateHandle?.set(
                            key="page",
                            value = page
                        )
                        navHostController.navigate(NavRoutes.ViewPager.route)
                    }*/
                }
            }
        })
}