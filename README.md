# iTunesTrackViewer
 iTunes Item Viewer App
 
 This app display a list of items
 * Featured item.(feature-movie)
 * Playlist items
 
 1. I separated the feature-movie list since the image size of the items is not the same with other items and to make it presentable when displayed on the ui.
 
 2. I used MVVM pattern because the app is using web and local service to fetch data. It is also good since I used Livedata for my persistent mechanism. And for a cleaner data processes.
 
 3. Shared preferences is used to persist the last screen where the user close the app.
 
 4. Added Room database to save the fetch data and use it as live data for persistent purposes.
 
   __As you can see when you clicked an item you will notice that the color of the text is darken compare to the items that is not yet viewed on the home list._

5. Since we are using a straightforward link for our web service. I used RxJava for the service for simpler code and for retry capabilities. Also Personally I found it more appropriate and cleaner. Retrofit will be best if we're gonna parameter to fetch data from the web service.

6. Some other Items does not have description so I labeled the as "No description available" but for the others, I display the descrption using html format.

7. I used Facebook Shimmer as place holder.
 

## Dependency
 * Material Design
 * Model-View-ViewModel
 * RxJava
 * Room
 * Glide
 * GSON
 * Facebook Shimmer
