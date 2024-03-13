package com.hdsw.asimpleapp.ui.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hdsw.asimpleapp.R
import com.hdsw.asimpleapp.data.model.Cat
import com.hdsw.asimpleapp.ui.components.CatImage
import com.hdsw.asimpleapp.ui.utils.DateUtils
import com.hdsw.asimpleapp.ui.viewmodels.CatDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CheckResult")
@Composable
fun CatDetailScreen(
    catId: String,
    viewModel: CatDetailViewModel = hiltViewModel(),
    onBackClicked: () -> Unit,
    orientation: Int? = LocalConfiguration.current.orientation
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.testTag("backButton"),
                title = { Text(text = "Cute Cat Detail") },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackClicked.invoke()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }

                }
            )
        }
    ) { _ ->
        val cat = viewModel.getCat(catId).collectAsState(
            initial = Cat(
                "", "", "",
                emptyList(), ""
            )
        )
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            PortraitCatDetail(cat.value)
        } else {
            LandscapeCatDetail(cat.value)
        }

    }
}

@Composable
private fun PortraitCatDetail(cat: Cat) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        val owner = if ("null" == cat.owner || cat.owner.isEmpty()) {
            stringResource(id = R.string.owner_unknown)
        } else cat.owner

        CatImage(
            cat,
            false
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Owner: $owner",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.testTag("ownerText")
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Tags: ${cat.tags.joinToString()}",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Created At: ${DateUtils.formatDate(cat.createdAt)}",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Updated At: ${DateUtils.formatDate(cat.updatedAt)}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun LandscapeCatDetail(cat: Cat) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
            .testTag("catDetailRow")
    ) {
        CatImage(
            cat,
            false
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(48.dp)
        ) {
            val owner = if ("null" == cat.owner || cat.owner.isEmpty()) {
                stringResource(id = R.string.owner_unknown)
            } else cat.owner
            Text(
                text = "Owner: $owner",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Tags: ${cat.tags.joinToString()}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Created At: ${DateUtils.formatDate(cat.createdAt)}",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Updated At: ${DateUtils.formatDate(cat.updatedAt)}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

