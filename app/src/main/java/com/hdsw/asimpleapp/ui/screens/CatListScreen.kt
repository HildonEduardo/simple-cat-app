package com.hdsw.asimpleapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.hdsw.asimpleapp.R
import com.hdsw.asimpleapp.data.model.Cat
import com.hdsw.asimpleapp.ui.components.CatImage
import com.hdsw.asimpleapp.ui.utils.DateUtils
import com.hdsw.asimpleapp.ui.viewmodels.CatListViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatListScreen(navController: NavHostController, viewModel: CatListViewModel = hiltViewModel()) {
    val cats by viewModel.cats.collectAsState(emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "List of Cats") }
            )
        }
    ) {innerPadding->
        LazyColumn(modifier = Modifier
            .padding(innerPadding)
            .fillMaxHeight()
            .fillMaxWidth()) {
            items(cats) { cat ->
                CatListItem(navController, cat = cat)
            }
        }
    }
}

@Composable
fun CatListItem(navController: NavHostController, cat: Cat) {
    ElevatedCard(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(modifier = Modifier
            .padding(4.dp)) {
            CatImage(cat)
            Column(
                modifier = Modifier
                    .clickable {
                        navController.navigate("cat/${cat.id}")
                    }
                    .padding(16.dp)
            ) {
                val owner = if ("null" == cat.owner ||cat.owner.isEmpty()) {
                    stringResource(id = R.string.owner_unknown)
                } else cat.owner

                Text(
                    text = stringResource(R.string.owner_label, owner),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = stringResource(R.string.tags, cat.tags.joinToString()),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = stringResource(R.string.created_at, DateUtils.formatDate(cat.createdAt)),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = stringResource(R.string.updated_at, DateUtils.formatDate(cat.createdAt)),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}

