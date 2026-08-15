import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    var body: some Scene {

        WindowGroup {

            ContentView()
                .onOpenURL { url in

                    print("DEV_RANK_AUTH: IOS CALLBACK RECEIVED")
                    print("DEV_RANK_AUTH: CALLBACK URL = \(url.absoluteString)")

                    OAuthCallbackBridge.shared.handle(url: url.absoluteString)
                }
        }
    }
}