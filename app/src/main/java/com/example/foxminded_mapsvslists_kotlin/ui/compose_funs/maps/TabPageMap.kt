package com.example.foxminded_mapsvslists_kotlin.ui.compose_funs.maps

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.foxminded_mapsvslists_kotlin.R
import com.example.foxminded_mapsvslists_kotlin.ui.compose_funs.EnterSizeScreen
import com.example.foxminded_mapsvslists_kotlin.vm.MapViewModel

@Composable
fun TabPageMap(mapViewModel: MapViewModel = viewModel(factory = MapViewModel.Factory)) {

    val mapUiState by mapViewModel.uiState.collectAsState()

    when (mapUiState.waitingForUserInput) {
        true -> EnterSizeScreen(title = stringResource(id = R.string.map_title),
            textFieldValue = stringResource(id = R.string.tf_enter_value),
            onClickButton = { mapViewModel.calculate(it) },
            numberInTextFieldField = mapUiState.defaultInputCount)

        false -> if (mapUiState.calculation) MapResult(
            onBackClick = { mapViewModel.backToInputScreen() },
            resultIsReady = false
//                result = mapUiState.result
        ) else MapResult(
            onBackClick = { mapViewModel.backToInputScreen() },
            resultIsReady = true,
            result = mapUiState.result
        )
    }
}