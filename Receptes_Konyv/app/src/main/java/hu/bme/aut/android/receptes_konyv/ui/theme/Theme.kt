package hu.bme.aut.android.receptes_konyv.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import hu.bme.aut.android.receptes_konyv.data.datastore.Settings

private val DarkColorScheme = darkColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    onSecondary = Color.White,
    tertiary = Pink40,
    onPrimary = Color.White,
    background = BackgroundPurple,
)

private val LightColorScheme = lightColorScheme(
    primary = DarkBlue,
    secondary = Color.White,
    onSecondary = Color.Black,
    tertiary = Pink40,
    onPrimary = Color.White,
    background = WhiteishGrayish,
    /* Other default colors to override

    surface = Color(0xFFFFFBFE),


    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun Receptes_KonyvTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    val context = LocalContext.current
    val settings= Settings(context)
    val theme= settings.getAppTheme.collectAsState(initial = "light")
    var localTheme=theme.value


    val colorScheme = if (localTheme=="dark") DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun MyAppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()

    MaterialTheme(
        colorScheme = colorScheme,
        // shapes = ...,
        // typography = ...,
        content = content
    )
}