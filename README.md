<div align="center">

# DevRank 🚀

**Compare GitHub developers side-by-side — contributions, repos, stars, commits, and more.**

Built with Kotlin & Compose Multiplatform for Android and iOS.

[![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)](https://kotlinlang.org)
[![Compose Multiplatform](https://img.shields.io/badge/Compose%20Multiplatform-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)](https://www.jetbrains.com/lp/compose-multiplatform/)
[![Ktor](https://img.shields.io/badge/Ktor-087CFA?style=for-the-badge&logo=ktor&logoColor=white)](https://ktor.io)
[![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)](LICENSE)

[![Stars](https://img.shields.io/github/stars/Amit-Kundu-io/Dev-Rank?style=flat-square&color=yellow)](https://github.com/Amit-Kundu-io/Dev-Rank/stargazers)
[![Forks](https://img.shields.io/github/forks/Amit-Kundu-io/Dev-Rank?style=flat-square&color=blue)](https://github.com/Amit-Kundu-io/Dev-Rank/network/members)
[![Issues](https://img.shields.io/github/issues/Amit-Kundu-io/Dev-Rank?style=flat-square&color=orange)](https://github.com/Amit-Kundu-io/Dev-Rank/issues)
[![Last Commit](https://img.shields.io/github/last-commit/Amit-Kundu-io/Dev-Rank?style=flat-square)](https://github.com/Amit-Kundu-io/Dev-Rank/commits/main)

</div>

---

## 📖 About

**DevRank** is an open-source, cross-platform app that lets you compare two (or more) GitHub developers head-to-head. It pulls live data from the GitHub REST and GraphQL APIs to visualize contributions, repositories, stars, forks, commits, issues, pull requests, reviews, and followers — all in one clean, unified view.

Under the hood, it uses **MVI (Model-View-Intent)** for predictable, testable state management, and is built entirely with **Compose Multiplatform**, sharing one UI codebase across **Android** and **iOS**.

---

## ✨ Features

- 🔍 **Compare GitHub developers** side-by-side
- 📊 **Profile statistics** at a glance
- ⭐ **Repository stars & forks** comparison
- 📈 **Contributions & commits** tracking
- 🐛 **Issues & pull requests** breakdown
- ✅ **Pull request reviews** comparison
- 👥 **Followers** comparison
- 🔐 **GitHub OAuth** authentication
- 📱 **Android & iOS** support from a single codebase
- 🧠 **MVI-based** state management
- 🌐 **REST + GraphQL** API integration

---

## 🛠 Built With

| Technology | Usage |
|---|---|
| **Kotlin** | Core programming language |
| **Compose Multiplatform** | Shared, cross-platform UI |
| **Ktor** | Networking client |
| **MVI** | State management architecture |
| **GitHub REST API** | Profile & repository data |
| **GitHub GraphQL API** | Contribution data |

---

## 📱 Screenshots

<div align="center">

### 🏠 Home

<img src="https://raw.githubusercontent.com/Amit-Kundu-io/Images/main/WhatsApp%20Image%202026-03-22%20at%2011.20.33%20AM.jpeg" width="240" alt="DevRank home screen" />

### 💻 Cross-Platform (Android + iOS)

<img src="https://raw.githubusercontent.com/Amit-Kundu-io/Images/main/DevRank_Mac.jpg" width="750" alt="DevRank running on Android and iOS" />

</div>

---

## 🚀 Getting Started

### Prerequisites

- Android Studio (latest stable)
- JDK 17+
- Kotlin
- Compose Multiplatform plugin
- Git
- Xcode (for iOS builds, macOS only)

### Clone & Run

```bash
git clone https://github.com/Amit-Kundu-io/Dev-Rank.git
cd Dev-Rank
```

Open the project in Android Studio and let Gradle sync. Then:

- **Android:** run the `androidapp` configuration on an emulator or device
- **iOS:** open `iosApp/` in Xcode and run on a simulator or device (macOS required)

---

## 🗂 Project Structure

```
Dev-Rank/
├── androidapp/     # Android application module
├── composeApp/     # Shared Compose Multiplatform UI
├── database/       # Local persistence layer
├── feature/        # Feature modules (MVI)
├── iosApp/         # iOS application entry point
├── network/        # Ktor networking & API clients
├── theme/          # Shared design system / theming
└── utility/         # Common utilities & helpers
```

---

## 🤝 Contributing

Contributions are welcome! If you'd like to help improve DevRank:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m "Add amazing feature"`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

Feel free to open an [issue](https://github.com/Amit-Kundu-io/Dev-Rank/issues) for bugs, ideas, or feature requests.


---

<div align="center">

Made with ❤️ using Kotlin & Compose Multiplatform

⭐ If you like this project, consider giving it a star!

</div>
