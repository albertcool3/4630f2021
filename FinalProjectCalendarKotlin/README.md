# The Kotlin Calendar version
This is based on the following examples:
- [Crane example from Google] (https://github.com/android/compose-samples/tree/main/Crane)
- [Jetnode example from raywenderlich.com] (https://github.com/raywenderlich/jet-materials/tree/editions/1.1)w

## Routing in the app
The original example from Jet Node did not use the Routing API from the Jetpack library, instead it implements its own global Router object with its own data objects called currentScreens. This variable is monitored by Jetpack Compose. Whenever this variable changes, a recompose happens and a new screen is displayed. This demonstrates how routing works behind the scenes in Jetpack Compose, but lacks some of the features that the original navigation API possess. For example, it does not have the ability to pass an arguement from one screen to the next, or keep a history in the activity stack. But it still wins in simplicity. I also extended it so that it could track the previous screen and decide what to display based on what was in that screen. 
