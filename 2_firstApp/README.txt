Albert Li
Assignment 2
My First App

To complete the assignment, I used the Birthday Card example from Udacity, 
which helped me gain a basic understanding of XML, and added a button which
play music.

After going through the example, I decided to spice it up and added a button that would play 
birthday music (cover used: https://www.youtube.com/watch?v=7vQsBI6C8Qk), the button also stops 
the music when clicked on again. Not only did I gain more experience with buttons in XML, I 
also learned how to develope a functionality for a button using Java.

Some difficulties I faced was understanding how to get the app to play an mp3 file using MediaPlayer,
such as understanding how to create() a new MediaPlayer and its parameters. There was also an issue
where I could not play the music after starting and stopping it once, the issue turned out to be that
using stop() also deletes the MediaPlayer() object meaning there was no music to play with. The solution
I went with was to use pause() and seekTo(0) to get the desired result.
