package com.hdsw.asimpleapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
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
    navController: NavHostController
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Cute Cat Detail") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigateUp()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }

                }
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                val cat = viewModel.getCat(catId).collectAsState(
                    initial = Cat(
                        "", "", "",
                        emptyList(), ""
                    )
                )
                CatImage(
                    cat.value,
                    false
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Owner: ${cat.value.owner.ifEmpty { stringResource(id = R.string.owner_unknown) }}",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Tags: ${cat.value.tags.joinToString()}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Created At: ${DateUtils.formatDate(cat.value.createdAt)}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Updated At: ${DateUtils.formatDate(cat.value.updatedAt)}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

