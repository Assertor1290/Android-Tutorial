## SERVICES  
A Service is an application component that can ***perform long-running operations in the background***, and it doesn't provide a user interface.   
Another application component can start a service, and it continues to run in the background even if the user switches to another application.  
For example, a service can handle network transactions, play music, perform file I/O, or interact with a content provider, all from the background.  
These are the different types of services: 

* **Started Service**  
  * **Foreground**  
    * A foreground service performs some operation that is ***noticeable to the user***. For example, playing music or downloading file.   
    * Foreground services must ***display a Notification***.   
    * Foreground services continue running even when the user isn't interacting with the app.  

  * **Background**  
    * A background service performs an operation that ***isn't directly noticed*** by the user. For example, if an app used a service to compact its storage, syncing with server that would usually be a background service.  
    * Backgrouns services are subject to background execution limits from Android 8.0. Use ***JobScheduler*** instead of this.     

* **Bound**  
  * A service is bound when an application component binds to it by calling bindService().  
  * A bound service offers a ***client-server interface*** that allows components to interact with the service, send requests, receive results, and even do so across processes with interprocess communication (IPC).  
  * A bound service runs only as long as another application component is bound to it. 
  
* **Intent Service**  
  * An IntentService runs in the background independently from an activity and handles all the incoming work on a HandlerThread, 
  so we don’t have to take care of creating our own background thread in order to not block the UI thread.  
  * IntentService is subject to all the background execution limits imposed with Android 8.0 (API level 26). 
  Consider using ***WorkManager*** or ***JobIntentService***, which uses jobs instead of services when running on Android 8.0 or higher.  
  
***Caution:*** A service runs in the *main thread* of its hosting process; the service does not create its own thread and does not run in a separate process. 
If your service is going to perform any CPU-intensive work or blocking operations, such as MP3 playback or networking, 
you should create a new thread within the service to complete that work.  

**Basics**  
To create a service, you must create a subclass of ***Service*** or use one of its existing subclasses.   
In your implementation, you must override some callback methods.  
* ***onStartCommand()***  
  * The system invokes this method by calling ***startService()*** when another component (such as an activity) requests that the service be started.  
  * When this method executes, the service is started and can run in the background indefinitely.   
  * If you implement this, it is your responsibility to stop the service when its work is complete by calling ***stopSelf()*** or ***stopService().***   
  * There are three possible return values for this method:  
    * **START_STICKY**- Tells the system to create a fresh copy of the service, when sufficient memory is available, after it recovers from low memory. Here you will lose the results that might have computed before.
    * **START_NOT_STICKY**- tells the system not to bother to restart the service, even when it has sufficient memory.
    * **START_REDELIVER_INTENT**- tells the system to restart the service after the crash and also redeliver the intents that were present at the time of crash.
* ***onBind()***  
  * The system invokes this method by calling ***bindService()*** when another component wants to bind with the service.
  * In your implementation of this method, you must provide an interface that clients use to communicate with the service by ***returning an IBinder***. 
  * You must always implement this method; however, if you don't want to allow binding, you should return null.  
* ***onCreate()***  
  * The system invokes this method to perform one-time setup procedures when the service is initially created (before it calls either onStartCommand() or onBind()).
  *  If the service is already running, this method is not called.  
* ***onDestroy()***  
  * The system invokes this method when the service is no longer used and is being destroyed.
  * Your service should implement this to clean up any resources such as threads, registered listeners, or receivers.   

**NOTE**  
* If a component calls bindService() to create the service and onStartCommand() is not called, the service runs only as long as the component is bound to it.
* After the service is unbound from all of its clients, the system destroys it.
* The Android system stops a service only when memory is low and it must recover system resources for the activity that has user focus. 
* If the service is bound to an activity that has user focus, it's less likely to be killed; if the service is declared to run in the foreground, it's rarely killed.   

***Declaring a service in the manifest***  
To declare your service, add a <service> element as a child of the <application> element.  
<img src="https://user-images.githubusercontent.com/30290570/79805816-9a8bdb00-8384-11ea-8952-452c65cbacb7.png">    

***Caution:*** To ensure that your app is secure, always use an explicit intent when starting a Service.  

You can watch Coding in Flow tutorial for better understanding :)
