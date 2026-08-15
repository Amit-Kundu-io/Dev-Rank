import SwiftUI

import SwiftUI
import Shared

@main
struct iOSApp: App {

    var body: some Scene {

        WindowGroup {

            ContentView()
            .onOpenURL { url in

                print("DEV_RANK_AUTH: iOS CALLBACK RECEIVED")

                print("DEV_RANK_AUTH: CALLBACK URL = \(url.absoluteString)")

                OAuthCallbackKt.handleOAuthCallback(url: url.absoluteString)
            }
        }
    }
}