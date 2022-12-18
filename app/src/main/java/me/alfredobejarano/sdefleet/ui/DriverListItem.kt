package me.alfredobejarano.sdefleet.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import me.alfredobejarano.sdefleet.R
import me.alfredobejarano.sdefleet.model.Shipment

@Preview
@Composable
fun DriverListItem(
    shipment: Shipment = Shipment(driver = "John Doe", address = "241 Mahogany Street Apt. 21")
) = AppTheme {
    var shipmentAddressVisible by remember {
        mutableStateOf(false)
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 16.dp)
            .clickable {
                shipmentAddressVisible = !shipmentAddressVisible
            },
    ) {
        val (icon, driverNameText, shipmentText) = createRefs()

        Icon(painter = painterResource(id = R.drawable.ic_truck),
            contentDescription = null,
            tint = MaterialTheme.colors.onPrimary,
            modifier = Modifier
                .background(shape = CircleShape, color = MaterialTheme.colors.primary)
                .padding(8.dp)
                .constrainAs(icon) {
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                        top = parent.top,
                        bottom = parent.bottom,
                        horizontalBias = 0f
                    )
                }
        )

        Text(
            text = shipment.driver,
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .constrainAs(driverNameText) {
                    linkTo(
                        start = icon.end,
                        end = parent.end,
                        top = icon.top,
                        bottom = if (shipmentAddressVisible) shipmentText.top else icon.bottom,
                        horizontalBias = 0f
                    )
                }
                .padding(horizontal = 16.dp)
        )

        AnimatedVisibility(
            visible = shipmentAddressVisible,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
            modifier = Modifier
                .constrainAs(shipmentText) {
                    linkTo(
                        start = icon.end,
                        end = parent.end,
                        top = driverNameText.bottom,
                        bottom = icon.bottom,
                        horizontalBias = 0f
                    )
                }
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = shipment.address,
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.subtitle2
            )
        }
    }
}