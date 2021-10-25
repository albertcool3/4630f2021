Homework Assignment 3
Albert Li

I created a Fragment for each category of questions in the assignment instructions.
A navigation bar is placed at the bottom to allow the user to switch between the fragments.

1. The Weather fragment:
  - One button for each sub-question
  - OpenWeather API used to get weather information
  - Small image in the left changes based on the selected city's weather condition
      - Supports most common weather conditions, does not support atmosphere related conditions

2. The Fact Fragment:
  - One button for each sub-question
  - Clicking on the button redirects the user to Google Maps 
    with the answer to the question loaded in
  - During testing with an AVD, Google Maps would only work when it had mobile service enabled
  
3. The Portfolio Fragment:
  - Numberpicker used with each item in the portfolio as a selectable option
  - Button is used to submit the selection from the Numberpicker
  - Webview at the bottom displays the selected option in Yahoo Finance
  
4. The Resume Fragment:
  - Displays Resume as an image
  - Button displays the Resume PDF 
  - PDF viewer library from: https://github.com/barteksc/AndroidPdfViewer
