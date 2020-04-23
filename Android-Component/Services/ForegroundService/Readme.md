## Foreground Services  
A foreground service performs some operation that is noticeable to the user. For example, an audio app would use a foreground service to 
play an audio track. Foreground services must display a Notification. 
Foreground services continue running even when the user isn't interacting with the app.  

**Creating a Foreground service**  
* A foreground service is created by extending Service class.  
* The class must provide a notification to the user in status bar. For API 26 or higher, you must create a Notification channel.  

  <img src="https://user-images.githubusercontent.com/30290570/79807938-f0af4d00-8389-11ea-9c95-2f2739d1bc61.png">  
  
* The notification must have a priority of PRIORITY_LOW or higher. Here is an example:  
  <img src="https://user-images.githubusercontent.com/30290570/79807614-17b94f00-8389-11ea-9679-34430e6f7354.png">  

* For Android 9( API 28) or higher, you must request ***FOREGROUND_SERVICE*** permission in manifest file.  
* To request that your service runs in the foreground, call ***startForeground()*** method. This method takes two parameters: an Integer that
uniquely identifies notification and the Notification for status bar. 

**Starting a Foreground service**  
* A foreground service is started by calling ***startService()*** (before API 26) or by calling ***startForegroundService()***(from API 26) 
and passing an Intent that specifies the service and includes any data for the service to use.
* The service receives this Intent in the onStartCommand() method.  

**Stopping a Foreground service**  
* Service should stop itself when its job is complete by calling ***stopSelf()***, or another component can stop it by calling ***stopService()***.  

<img width="280px" height="560px" src="https://user-images.githubusercontent.com/30290570/79808172-a5e20500-838a-11ea-9750-68a733b7401b.jpg">    <img width="280px" height="560px" src="https://user-images.githubusercontent.com/30290570/79808210-c3af6a00-838a-11ea-8985-9509f1f48792.jpg"  >    <img width="280px" height="560px" src="https://user-images.githubusercontent.com/30290570/79808236-d6c23a00-838a-11ea-860c-961c2136ac17.jpg">  

**TIP**  
* Use ***ContextCompat.startForegroundService(context,intent)*** to start your foreground service. It automatically checks whether API version is less than 26 or greater than or equal to 26.  
* You can see Running services in your mobile in Developer Options.
