
# Term Paper for Comp.4630-Mobile App Development I

Albert Li

## Abstract
In this paper, I first outline what I learned in Android mobile app development. I will only discuss the technical parts in this paper. I fully understand the social and economical impacts from mobile computing weight far more than a programmer's myopic views. I briefly discuss one particular issue, the deprecated AsyncTask API and my quest to understand why. Using other topics that I have studied for my final project I had discovered more facts that were painful to swallow at first: a lot of what I have just learned have been outdated already. Lastly, I summarize what has been done correctly. And in hind sight, what I should have done at the beginning of the semester. Along the technical learning process, I went through a lot of ups and downs emotion wise. Even the journey is frustrating at times, it also motivates me to make changes and adapt to the new mobile app development paradigm.


## My Journey

### I am super excited!
- Udacity's introduction classes are very helpful. I learned how to use XML to express and design UI for Android. (I felt very comfortable about those layouts methods introduced in this class.)
- Learned some simple event handlers for button's touch event.
- Duplicated the Birthday Card App, embedded an audio file as a local resource and plays the "Happy Birthday" song if the user clicks on the button and pauses it after clicking the button again.
- I started Java back in high school, and dismissed Kotlin as the Swift clone with a concise syntax. But only later, I discovered I was not only biased but actually wrong, more on this later.

### I advanced to the next Udacity lesson
"Ud851" is the "Sunshine Project". It showcased a full featured weather app with a real world UI. Although the main emphasis is demonstrating how to fetch weather data using JSON API (by submitting http request with API defined query string to a fake weather server) running in a seperated thread other than the main UI thread. Android enforces the rule that any network related API must NOT run in the main UI thread in order to maintain responsive user interactions.

### I took advantage almost all available MOOC classes on Coursera.org and edx.org
I benefit a lot from:
1. Maryland University's class
- Android architecture
- a lot of useful and practical code examples about activity, intent/intent filter, broadcast receivers, services, a little bit 2D graphics API and 2D animation
2. Vanderbilt University's class
- Git for source code version control
- Android Linux internals(I also learned that most of the Android unique Linux kernel features are now in the current Linux kernel mainline while doing my Operating System related readings.)

### Sense of Accomplishment (very short lived)
My 2nd app used a lot of materials I learned:
- Botton Navigation UI toolbar menu working with Fragments
- Network Connectivity: Real weather data using open weather JSON API. (The Udacity example used a fake weather server)
- Web-view to embed yahoo stock query results (credit to coursera)
- intent to pass address to a map application(google map)
- using existing pdf library to handle PDF document.

### AsyncTask API is deprecated
But why? To me, it is most useful invention because I just learned it from Udacity in order to perform network I/O.(as well as from U Maryland's class on Coursera.com, it also presents a different method using threads together with posting messages and handlers.) I felt super proud under the assumption not only I understood it but also got it to work very well in my app. However, Android Studio later told me: please, don't use it. It may not be there in the future. [Ironically, Google is still teaching it to the new comers](https://developer.android.com/courses/fundamentals-training/toc-v2). I felt very puzzled and somewhat disappointed. I know tech landscapes change quickly. But this particular API was there in API level 1, why after so many years, at API 30, Google decided to deprecated it? Most explanations on the internet just chalk it to memory leak. That sounds serious. Should people discover this long time ago? Some posts showing code snippets demonstrating that the Activity instance was captured by the inner class (or its variant lambda), under certain conditions(network I/O exceptions).The network thread hold a reference count on the Activity instance prevents the GC to do its job.(Android life cycles come to play). But if/when the network thread eventually finishes or being killed, the reference count will be decreased and then there won't be so called "memory leak". I was not totally satisfied with those explanations until I found this particular [article by Vasiliy] (https://www.techyourchance.com/asynctask-deprecated/) The answer is not black or white, but nuanced. The author's views in summary:
- AsyncTask API makes multithreading more complex.
- Google's documentation is non-optimal. As a beginner, I have don't have too many opinions on Google's documentations, and mostly relied on MOOC classes. But still found it amusing that Google on one hand tells people stop using it, yet on the other hand still teaches people about it in its tutorials. Other entities like Udacity.com, Coursera.org, Edx.org, etc lagging behind is somewhat understandable, but Google itself is lacking consistency here.
- Excessive Complexity. In term of using generics. Maybe I should pat myself on the back for the effort to learn it.
- Inheritance abuse. It appears critics about object oriented programming are louder every day. The article author cited "Favor composition over inheritance" rule from Effective Java. But it seems that Jetpack Compose (the new UI framework, more on this later) also favors functional programming style over inheritance to solve issues around performances (laying out a large number of widgets/views with complex layout rules) and maintenance (complexity associated with inheritance base programming style).
- Reliability. I think the author had first hand experiences with different API Levels that would result in different behaviors, but that appears to just be bugs in the implementations.
- Concurrency Misconception, the author is again focusing on sub optimal documentation.

### Using a widget vs Designing a widget
In my project I need to present a calendar view to the user. I considered using the Calendar view from Android, but feel it is too limited as it only presents a monthly view and mainly serves as a day picker. I researched some open source libraries implementing calendar view. Their implementations are all similar architecture wise:
- Recycle View (an efficient way to display/scroll its contents, more commonly used for to host a list view but this case a grid layout. 1D array vs 2D array)
- Adapter (a requirement from the RecycleView)
- GridLayout (each day will be a cell in the GridLayout)
- Inflates a view holder in XML (Even it view itself is generated at runtime, it still needs a place holder)

### A Bigger Surprise
RecycleView related research was even a bigger surprise. The UI framework I just learned from Udacity/Coursera/Edx about Android app programming is now replaced by Jetpack Compose, a framework that emphasizes a lot less on object oriented programming style, but more on functional style. I guess the word "compose" has overloaded meanings here. (It can refers to functional style compose, but it can also view the composable functions that are the implementations of widgets that can layer on top each other in the graphics z-order.) Google can not just deprecated all the APIs related the old UI framework, there will be support to the old UI framework in the foreseeable future. But it appears people are moving to Jetpack Compose. It's not an API level change, it is a programming style/paradigm change.

### iOS eco-system has also under gone similar changes:
[193P 2020 Spring](https://cs193p.sites.stanford.edu/2020)
> The lectures for the Spring 2020 version of Stanford University's course CS193p (Developing Applications for iOS using SwiftUI) are archived here.  They are all now completely out of date and you'll want to go to the Spring 2021 version instead.

### Key reasons behind Jetpack Compose
- Inheritance based code is now identified as a performance and maintenance bottle neck in Android UI. Even mobile devices are becoming more powerful, the UI becomes even more demanding (demonstrated by the number of views/widgets in an app and its layout complexity). Jetpack Compose solves these issues by favoring compose over inheritance.
- Creating custom views/widget (different from using one) was hard in the traditional UI framework. Part of the reason is related to XML that is used to describe views. In Jetpack Compose, XML is completely gone.
- Promoting functional style (declarative) programming instead of imperative programming or even object oriented programming. It turns out that Dijkstra, an early critics on OO paradigm, was right. [Dijstra on OO] (https://www.quora.com/Why-did-Dijkstra-say-that-“Object-oriented-programming-is-an-exceptionally-bad-idea-which-could-only-have-originated-in-California-”)
- Focus on the so called source of truth (states management). In the old Android UI framework, the views/widgets itself manages its own states, which obviously was part of OO design that follows encapsulation. It is now considered a bad practice, as many bugs are proned to appear in an app where a state belongs to a widget and managed in many different places in other parts of the app because it is not clear what the source of truth is, who owns it, and who updates it. Yet another reason functional style is back in vogue, it is used for separation of the business logic and UI.
- In Jetpack Compose, the traditional UI widgets are replaced by composables, which are functions annotated with @Compasable. The UI is a function of data, will come alive when the states(data), modifiers(stylings related specifications) and callback functions (there will be a lot of lambdas as inputs) are passed into the function.
- Kotlin is the only language supported by Jetpack Compose. The @Compasable is not an annotation handle in Java pre-processor and needs Kotlin compiler level support. That might be part of reason Java is no long supported in Jetpack Compose. Google has been promoting Kotlin as the primary language for Android, but I was not convinced until now.

### I Flipped and Flopped
I feel the potential of Jetpack Compose, but I hesitated on leaving Java language, my comfort zone. So, I stuck with the old UI framework using RecyleView, with dynamic inflated GridView layout at runtime. There is an Adapter and along a lot of boiler plate type code and XML. I did spend some time experimenting on the Jetpack Compose UI framework. Currently, I think it's not only doable but also somewhat refreshing, so I decided to flip/flop back to the new framework just a few days ago.

### Compare RecycleView with LazyRow/Column
RecycleView in the old UI framework has its counter part in the new framework called LazyRow and LazyColumn. RecycleView relies on a layout manager, but the LazyRow and LazyColumn does not. (Or We can think they play the layout manager's role) RecycleView needs an Adapter to get the items to display. The content(item) inside of list is of the special type LazyItemScope. And to handle a user defined data type, an extension function is defined. Extension function is feature in Kotlin to make this newly defined function appears to be member function of an old class. To achieve the effects of a GridLayout in the old UI framework, LazyRow can be nested within a LazyColumn. Compare to RecycleView, the amount of boiler plate code is reduced.
                                                               
### Summary
Knowing Java proves to be a baggage for me. I should have started using Kotlin much sooner, even before this class. The UI framework for both iOS and Android app development changed significantly. Both are now favors functional programming style.

### References
- [Android Compose Tutorial] (https://developer.android.com/jetpack/compose/tutorial)
- [Get started with Jetpack Compose] (https://developer.android.com/jetpack/compose/documentation)
- [Android Summit] (https://www.youtube.com/watch?v=4R8-ggukUls&list=PLWz5rJ2EKKc9-BJh8Os6W6iQIp1gtOh2T)
- [Jetpack Compose by Tutorials, Balint T, et als] (https://www.raywenderlich.com)
- [193P Developing Applications for iOS using SwiftUI] (https://cs193p.sites.stanford.edu)
- [article by Vasiliy] (https://www.techyourchance.com/asynctask-deprecated/)
- [Dijstra on OO] (https://www.quora.com/Why-did-Dijkstra-say-that-“Object-oriented-programming-is-an-exceptionally-bad-idea-which-could-only-have-originated-in-California-”)

