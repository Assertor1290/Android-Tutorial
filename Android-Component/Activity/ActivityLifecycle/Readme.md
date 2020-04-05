## Activity Lifecycle

Activities in the Android are managed as an Activity stack. When an activity is started, it is placed at the top of the stack and becomes the running activity. The previous activity always remains below in the stack, and will not come to the foreground again until the new activity exists. I would suggest download the associated code to clearly see when which method is called.  

In the activity life cycle there are following methods:  
![Lifecycle](https://user-images.githubusercontent.com/30290570/78508364-f14dbe00-77a3-11ea-8bca-001cf1cf23df.png)

**onCreate():**  
•	Called when the activity is first created. All such operations are performed which should be done only once for the entire life of the activity.  
•	This is where you should do all of your normal static set up: create views, bind data to lists, etc.   
•	This method also provides you with a Bundle containing the activity’s previously frozen state, if there was one.   
•	Always followed by onStart().  
•	This method has a parameter savedInstanceState which is a bundle object. It contains activity’s previous saved state. If the activity has never existed before, the value of Bundle object is null.  

**onStart():**  

•	Called when the activity is becoming visible to the user.   
•	Followed by onResume() if the activity comes to the foreground, or onStop() if it becomes hidden.  
•	onCreate ( ) function is called only once but onStart( ) can be called multiple times , when activity enters the started state.  

**onResume():**

•	Called when the activity will start interacting with the user.   
•	At this point your activity is at the top of the activity stack, with user input going to it.   
•	Always followed by onPause().  
•	Your activity is in the resumed state when it is initialized, visible on screen, and ready to use. The resumed state is often called the running state, because it is in this state that the user is actually interacting with your app.  

**onPause():**  
•	Called as part of the activity lifecycle when an activity is going into the background, but has not (yet) been killed.   
•	The counterpart to onResume(). When activity B is launched in front of activity A, this callback will be invoked on A. B will not be created until A’s onPause() returns, so be sure to not do anything lengthy here.  
•	onPause( ) method can be used to release system resources, handles to sensors (like GPS), or any resources that may affect battery life while your activity is paused and the user does not need them.   
•	onPause( ) execution is very brief, and does not necessarily afford enough time to perform save operations. So onPause( ) should not be used to save application or user data, make network calls, or execute database transactions; such work may not complete before the method completes. Instead, you should perform heavy-load shutdown operations during onStop( ).  

**onStop():**  
•	Called when you are no longer visible to the user.   
•	You will next receive either onRestart(), onDestroy(), or nothing, depending on later user activity.  
•	In the onStop( ) method, the app should release or adjust resources that are not needed while the app is not visible to the user. It should be used to perform relatively CPU-intensive shutdown operations.  

**onRestart():**  
•	Called after your activity has been stopped, prior to it being started again. Always followed by onStart()  

**onDestroy():**  
•	The final call you receive before your activity is destroyed.   
•	This can happen either because the activity is finishing (someone called finish() on it, or because the system is temporarily destroying this instance of the activity to save space.   
•	You can distinguish between these two scenarios with the isFinishing() method.  
  
There are two more methods which are not part of Activity Lifecycle:-  
* onSaveInstanceState()  
* onRestoreInstanceState()  
These methods are used to save and retrieve values. The values are stored in form of key-value pair.  
These methods will be discussed in detail in next section.  

**TIP:- You might encounter difficulty in trying to see when activity goes from onPause() to onResume() instead of onStop. Try experimenting using split screen view :)**
