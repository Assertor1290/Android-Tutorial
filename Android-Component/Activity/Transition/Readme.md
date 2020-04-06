## TRANSITION

Now, we will be dealing here with two questions:-  
### 1. Is onPause called when a dialog appears in an activity?  

The onPause() is called when your activity is no longer at the top of the activity stack. A Dialog by itself is not an Activity,
so will not replace the current Activity at the top of the stack, so **will not cause anything to pause.** 
Displaying the dialog-as-an-Activity will cause the new Activity to be on the top of the stack, pausing what previously was there.  



As you can see in the code, we have created an AlertDialog. It is not an Activity, so the current Activity does not pause.  

<img src="https://user-images.githubusercontent.com/30290570/78515025-ead63b00-77d1-11ea-9c3a-f64d9321dc4b.png"/>  
<p align="center">
<img width="30%" height="30%" src="https://user-images.githubusercontent.com/30290570/78515109-47d1f100-77d2-11ea-8454-a33e27c1bc06.jpg"/>  
</p>  


### 2. When you start another activity from one activity, what lifecycle methods get called, and in what sequence?  

When you start an activity, onPause() method of calling activity will be called. Then onCreate() , onStart() and onResume() method will
be called in sequence and then onStop() method of calling activity will be called. That means, the calling activity is in a transient
state between its onPause() and onStop() . Until and unless called activity is not fully visible to the user, onStop() method of 
calling activity won’t get called.  
<p align="center">
<img width="60%" height="60%" src="https://user-images.githubusercontent.com/30290570/78514949-86b37700-77d1-11ea-9f40-21972a64ffdd.png"/>  
</p>
The same process happens when you press the back button. At first, onPause() of child activity is called, Then, until the parent 
activity is not fully visible, i.e. onStart() and onResume() of parent are not called, onStop() of closing activity won’t get called. 
After that onStop() and onDestroy() method of child activity will be called.
