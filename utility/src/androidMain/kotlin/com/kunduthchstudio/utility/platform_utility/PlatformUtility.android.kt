package com.kunduthchstudio.utility.platform_utility

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate

actual object PlatformUtility {
    actual fun currentTimeSeconds(): Long {
        return System.currentTimeMillis() / 1000
    }

}