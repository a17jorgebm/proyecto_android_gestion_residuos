package com.example.proyectoresiduoscompose.presentation.ui.signatureScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyectoresiduoscompose.R
import com.example.proyectoresiduoscompose.app.host.bottomNavItems

@Composable
fun SignatureScreen(
    navController: NavController
){
    var selectedBottomNavItem by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        containerColor = Color.Yellow, //para que o menu estea flotando
        topBar = {
            Row(
                Modifier
                    .background(Color(0xFF1D1D1D))
                    .padding(16.dp)
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                                contentDescription = "Atrás",
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .aspectRatio(1f),
                                tint = Color.White
                            )
                        }

                        Text(
                            text = "Talleres Juan Antornio SL...",
                            color = Color.White
                        )
                    }
                }
            }
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF292928),
                modifier = Modifier
                    .background(Color(0xFF292928))
            ) {
                bottomNavItems.forEachIndexed{ i, item ->
                    NavigationBarItem(
                        selected = false,
                        onClick = {
                            selectedBottomNavItem=i
                            navController.navigate(item.route)
                        },
                        icon = {
                            Icon(
                                imageVector = item.unselectedIcon,
                                contentDescription = item.title,
                                tint = Color.White
                            )
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF1D1D1D))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                DrawSignatureZone()
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFF0625C))
                        .clickable {
                            navController.popBackStack()
                        }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.cancel),
                        contentDescription = "Cross",
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(1f)
                            .padding(0.dp, 0.dp, 15.dp, 0.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.cancel),
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFD8FF7E))
                        .clickable {
                            //se guardaria la firma como array de bytes
                            navController.popBackStack()
                        }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.check),
                        contentDescription = "Cross",
                        modifier = Modifier
                            .fillMaxHeight()
                            .aspectRatio(1f)
                            .padding(0.dp, 0.dp, 15.dp, 0.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.ready),
                    )
                }
            }
        }
    }
}

@Composable
fun DrawSignatureZone(){
    val lines = remember {
        mutableStateListOf<Line>()
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(15.dp))
            .pointerInput(true) {
                detectDragGestures { change, dragAmount ->
                    change.consume()

                    val line = Line(
                        start = change.position - dragAmount,
                        end = change.position
                    )

                    lines.add(line)
                }
            }
    ){
        drawRect(color = Color.White)
        lines.forEach{ line ->
            drawLine(
                color = line.color,
                start = line.start,
                end = line.end,
                strokeWidth = line.strokeWith.toPx(),
                cap = StrokeCap.Round
            )
        }
    }
}

data class Line(
    val start: Offset,
    val end: Offset,
    val color: Color = Color.Black,
    val strokeWith: Dp = 2.dp
)