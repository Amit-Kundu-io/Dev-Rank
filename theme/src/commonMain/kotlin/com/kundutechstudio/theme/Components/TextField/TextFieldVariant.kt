package com.kundutechstudio.theme.Components.TextField


enum class TextFieldVariant {
    Default,     // standard input — username, name
    Search,      // search bar style with icon prefix
    Password,    // masked input with show/hide toggle
    Success,     // green border — valid input confirmed
    Error,       // red border — validation failed
    Disabled,    // greyed out — non-editable
}