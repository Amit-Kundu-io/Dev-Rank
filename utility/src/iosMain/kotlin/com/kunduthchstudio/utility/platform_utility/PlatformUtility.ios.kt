package com.kunduthchstudio.utility.platform_utility

import platform.Foundation.NSDate
import platform.Foundation.timeIntervalSince1970

actual object PlatformUtility {
    actual fun currentTimeSeconds(): Long {
        return NSDate().timeIntervalSince1970.toLong()
    }
}