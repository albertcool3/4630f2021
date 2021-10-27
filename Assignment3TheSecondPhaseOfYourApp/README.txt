Homework Assignment 3
Albert Li

I created a Fragment for each category of questions in the assignment instructions.
A navigation bar is placed at the bottom to allow the user to switch between the fragments.

1. The Weather fragment:
  - OpenWeather API used to get weather information
    - Even OpenWeather API requires a key. This key was easier to obtain than one from Google Maps API.
  - One button for each sub-question
  - I studied Udacity's "Sunshine" Project and used their code with modifications.
    - I followed the AsyncTask to perform the OpenWeather API in a network thread and parse
      the JSON formatted texts using the org.json.JSONObject library. The UI thread displays 
      parsed JSON data in the onPostExecute() function.
    - In hinsight, I should have used a more modern library like Google's volley library
      which would have made the coding process much simpler
    - Followed the Sunshine project using the AsyncTask API, there was a sense of
      accomplishment running through my mind until I discovered that this API has been around
      since Android version 1 and is depreciated in Android 11. 
  - Displays temperature (in Celsius) and weather condition of the selected city  
  - Small image in the left changes based on the selected city's weather condition
      - Supports most common weather conditions, does not support atmosphere related conditions

2. The Fact Fragment:
  - One button for each sub-question  
  - I practiced Android "Intent", which is a communication method between Activities either in the same
    application or between applications. In this homework, the address is passed to a seperate app 
    (Most likely Google Maps on phones with the Google service installed)
  - I studied some code from an Android Development class from the University of Maryland in Coursera
  - Clicking on the button redirects the user to Google Maps with the answer to the question loaded in
  - During testing with an AVD, Google Maps would only work when it had mobile service enabled
  - I investigated using MapView to embed the Map in this fragment, but decided against it due to
    the inconvinient requirements to get an API key
  
3. The Portfolio Fragment:
  - The UI is inspired by the Android Class from CentraleSupélec in Coursera
  - Numberpicker used with each item in the portfolio as a selectable option
  - Button is used to submit the selection from the Numberpicker
  - Webview at the bottom displays the selected option in Yahoo Finance
  
4. The Resume Fragment:
  - Displays Resume as an image
  - Button displays the Resume PDF 
  - Intent used to activate the PDF viewer
  - PDF viewer library from: https://github.com/barteksc/AndroidPdfViewer
