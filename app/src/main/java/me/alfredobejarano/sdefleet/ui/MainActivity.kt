package me.alfredobejarano.sdefleet.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import me.alfredobejarano.sdefleet.R
import me.alfredobejarano.sdefleet.ui.compose.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { AppTheme { Content() } }
    }

    @Preview
    @Composable
    fun Content() = ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (title, composeButton, viewSystemButton) = createRefs()

        Text(
            text = stringResource(id = R.string.title_activity_main),
            modifier = Modifier.constrainAs(title) {
                linkTo(
                    start = parent.start,
                    end = parent.end,
                    top = parent.top,
                    bottom = parent.bottom,
                    verticalBias = 0.25f
                )
            }
        )

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(composeButton) {
                linkTo(
                    start = parent.start,
                    end = parent.end,
                    top = title.bottom,
                    bottom = parent.bottom,
                    verticalBias = 0.25f
                )
            }
        ) {
            Text(text = stringResource(id = R.string.compose))
        }

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(viewSystemButton) {
                linkTo(
                    start = parent.start,
                    end = parent.end,
                    top = composeButton.bottom,
                    bottom = parent.bottom,
                    verticalBias = 0.1f
                )
            }
        ) {
            Text(text = stringResource(id = R.string.view_system))
        }
    }
}

