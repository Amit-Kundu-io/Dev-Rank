# DevRank – GitHub OAuth Local Configuration & Git Workflow
Purpose: Keep the public GitHub repository clean while allowing local development with developer-specific GitHub OAuth configuration.

## 1. Public GitHubConfig.kt
Commit the template version once:
object GitHubConfig {

    const val CLIENT_ID = "YOUR_GITHUB_CLIENT_ID"
    const val CLIENT_SECRET = "YOUR_GITHUB_CLIENT_SECRET"

    const val AUTHORIZE_URL =
        "https://github.com/login/oauth/authorize"

    const val TOKEN_URL =
        "https://github.com/login/oauth/access_token"

    const val REDIRECT_URI =
        "devrank://oauth/callback"
}

## 2. Commit and Push the Template
git add "utility/src/commonMain/kotlin/com/kunduthchstudio/utility/GitHubConfig.kt"
git commit -m "Add GitHub OAuth configuration"
git push

## 3. Make the File Local-Only After the First Push
From the repository root (for example, M:\Dev-Rank), run:
git update-index --assume-unchanged "utility/src/commonMain/kotlin/com/kunduthchstudio/utility/GitHubConfig.kt"

## 4. Verify Git Is Ignoring Local Changes
Run the following command. A lowercase 'h' at the beginning means assume-unchanged is enabled:
git ls-files -v "utility/src/commonMain/kotlin/com/kunduthchstudio/utility/GitHubConfig.kt"
Expected:
h utility/src/commonMain/kotlin/com/kunduthchstudio/utility/GitHubConfig.kt

## 5. Change Local Configuration
You can now replace the placeholder values locally. Git should not show this tracked file as modified.
git status --short
Also verify the file is not listed in the output.

## 6. If Git Still Shows the File
Reset and apply the flag again:
git update-index --no-assume-unchanged "utility/src/commonMain/kotlin/com/kunduthchstudio/utility/GitHubConfig.kt"
git update-index --assume-unchanged "utility/src/commonMain/kotlin/com/kunduthchstudio/utility/GitHubConfig.kt"
git ls-files -v "utility/src/commonMain/kotlin/com/kunduthchstudio/utility/GitHubConfig.kt"

## 7. Re-enable Normal Tracking
If you intentionally want Git to detect changes to the file again:
git update-index --no-assume-unchanged "utility/src/commonMain/kotlin/com/kunduthchstudio/utility/GitHubConfig.kt"

## 8. Important Security Note
The assume-unchanged Git flag only hides local modifications from normal Git status. It does not make a client secret secure inside an Android or iOS application.
For a public Compose Multiplatform Android/iOS application, do not ship a real GitHub OAuth client secret in the application binary. Prefer an OAuth flow appropriate for public/native clients, such as Authorization Code with PKCE.

## 9. Recommended Open-Source Workflow
Public repository: keep only safe templates and source code. Local machine: use developer-specific configuration. Contributors: create/configure their own development credentials when required.
Note: The assume-unchanged approach is a local Git convenience. For a long-term open-source project, a dedicated ignored local configuration mechanism is generally cleaner than modifying a tracked file.