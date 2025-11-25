import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {
    }
}

struct ContentView: View {
    init(){
        AdjustBridge.initialize(appToken: "bgxygfj4r18g", environment: "sandbox")
    }
    var body: some View {
        ComposeView()
            .ignoresSafeArea()
    }
}



