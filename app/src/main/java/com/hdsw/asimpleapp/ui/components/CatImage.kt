package com.hdsw.asimpleapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.hdsw.asimpleapp.R
import com.hdsw.asimpleapp.data.local.Constants
import com.hdsw.asimpleapp.data.model.Cat
import com.skydoves.landscapist.glide.GlideImage
import com.skydoves.landscapist.glide.GlideRequestType
import com.skydoves.landscapist.glide.LocalGlideRequestBuilder
import kotlin.math.roundToInt

@SuppressLint("CheckResult")
@Composable
fun CatImage(
    cat: Cat,
    isThumbnail: Boolean = true,
) {
    val isGif = cat.tags.contains("gif")
    val glide = if (isGif) {
        Glide.with(LocalView.current)
            .asGif()
            .signature(ObjectKey(Constants.CAT_DETAIL_URL + cat.id))
            .run {
                if (isThumbnail) {
                    this.thumbnail(0.25f)
                    override(100.dp.value.roundToInt())
                } else {
                    override(400.dp.value.roundToInt())
                }
            }

    } else {
        Glide.with(LocalView.current)
            .asDrawable()
            .signature(ObjectKey(Constants.CAT_DETAIL_URL + cat.id))
            .run {
                if (isThumbnail) {
                    this.thumbnail(0.25f)
                    override(100.dp.value.roundToInt())
                } else {
                    override(400.dp.value.roundToInt())
                }
            }


    }
    CompositionLocalProvider(LocalGlideRequestBuilder provides glide) {
        GlideImage(
            imageModel = { Constants.CAT_DETAIL_URL + cat.id },
            glideRequestType = if (isGif) {
                GlideRequestType.GIF
            } else {
                GlideRequestType.DRAWABLE
            },
            requestOptions = { RequestOptions().timeout(35 * 1000).override(400) },
            modifier = Modifier
                .padding(1.dp)
                .size(if (isThumbnail) 100.dp else 400.dp),
            loading = {
                Box(modifier = Modifier.matchParentSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            },
            failure = {
                Image(
                    painterResource(R.drawable.image_error),
                    contentDescription = "",
                    modifier = Modifier.size(400.dp)
                )
            },
        )
    }
}

@Composable
@Preview
fun CatImagePreview() {
    val cat = Cat(
        "ZHrXPVRJniYPR6pp", "Wed Jun 01 2022 22:29:22 GMT+0000 (Coordinated UniversalTime)", "null",
        listOf("gifs","cute"), "Tue Oct 11 2022 07:52:32 GMT+0000 (Coordinated UniversalTime)"
    )
    CatImage(cat = cat, false)
}