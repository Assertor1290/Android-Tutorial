## SAVE AND RESTORE INSTANCE  

Before I begin, I would like you to make some editText widget in your xml file of previous code( or you can use provided code, just comment out the extra part compared to previous code). Say, you have added an EditText to input random no. You entered the phone no. Now, rotate your phone. See what happens ?  

<pre>
<img height="30%" width="30%" src="https://user-images.githubusercontent.com/30290570/78509485-d2532a00-77ab-11ea-878b-de0e1f52cabe.jpg"/>      <img height="60%" width="60%" src="https://user-images.githubusercontent.com/30290570/78509489-da12ce80-77ab-11ea-8bd3-2197cdba889b.jpg"/>
</pre>  
As you can see the random number got erased when orientation changed.(Your device may produce different results). By default, when the screen is rotated your Activity is killed and restarted. To preserve orienatation, you can use two methods:-  
  
**onSaveInstanceState( )**  

*	onSaveInstanceState() called ***between Pause() and onStop().*** for devices targeting versions earlier than Build.VERSION_CODES_P (API 28). For applications starting from Build.VERSION_CODES_P, it will be called after onStop().   
*	***... onPause()-> onSaveInstanceState() -> onStop() -> onDestory().***  
*	As your activity begins to stop, the system calls the onSaveInstanceState( ) method so your activity can ***save current state information*** to an instance state bundle.  
*	To save additional instance state information for your activity, you must override onSaveInstanceState( ) and add key-value pairs to the bundle object that is saved in the event that your activity is destroyed unexpectedly.      
In simple terms, it saves your current state so that you can retrieve it later using onCreate() or onRestoreInstanceState() method.  

**onRestoreInstanceState( )**  

*	When your activity is recreated after it was previously destroyed, you can ***recover your saved instance state*** from the bundle that the system passes to your activity.   
*	Both the onCreate( ) and onRestoreInstanceState( ) callback methods receive the same bundle that contains the instance state information.  
*	This method is called ***between onStart() and onPostCreate(Bundle)*** when the activity is being re-initialized from a previously saved state.  
*	***onCreate()-> onStart()-> onRestoreInstanceState()-> onPostCreate(Bundle)***  

## NOTE  
* onRestoreInstanceState() is called when activity is recreated but only if :***it is killed by OS***. Such situation happen when:  
  * orientation of device changes  
  * there is another activity in front of yours and at some point the OS kills your activity in order to free memory(for example).  
So, if you are in your activity and you hit Back button on your device, activity is *finish()ed* and next time you start your app, it is started again but this time without saved state because you intentionally exited it when you hit Back button.  

* You can use both onCreate() and onRestoreInstanceState() to restore the state as both receive the same Bundle object. However, you have to check whether savedInstance is null or not in onCreate() but sytem calls onRestoreInstanceState() only if there is a savd state to restore, thus avoiding the need to check whether Bundle is null or not.  

* To save persistent data, use Shared Preferences.(*will be discussed in later sections*).
