import Adjust
import Foundation

@objc public class AdjustBridge: NSObject {
    
    @objc public static func trackEvent(token: String) {
         let event = ADJEvent(eventToken: token)
         Adjust.trackEvent(event)
    }
    
    @objc public static func initialize(appToken: String, environment: String) {
         let config = ADJConfig(appToken: appToken, environment: environment)
         Adjust.appDidLaunch(config)
    }
}
