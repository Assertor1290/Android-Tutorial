## Tasks and Back Stack  

**TASKS**  
* A ***task*** is a ***collection of activities*** that users interact with when performing a certain job. So when you tap the launcher icon for 
your app, what the system is actually doing is looking for a previously existing task (determined by the Intent and Activity it points to)
to resume — getting you back to exactly where you were. If no existing task is found, then a new task is created with your newly 
launched activity as the base activity on the task’s back stack.  
* The activities are ***arranged in a stack—(the back stack)***—in the order in which each activity is opened.  

***What happens when you click Home button ?***  
It puts the current task into the background, taking you back to your launcher.
While in the background, all the activities in the task are stopped, but the back stack for the task remains intact—
the task has simply lost focus while another task takes place.


<img src="https://user-images.githubusercontent.com/30290570/79079502-22426b80-7d2d-11ea-8b41-1fdae6fe7682.png">  

When the user touches an icon in the app launcher, then that app's task again comes to the foreground.  

***What happens when you navigate between Activities ?***    

Activities in the stack are never rearranged, only pushed and popped from the stack—pushed onto the stack when started by 
the current activity and popped off when the user leaves it using the Back button. As such, the back stack operates as a 
"last in, first out" object structure.  

<img src="https://user-images.githubusercontent.com/30290570/79079549-6b92bb00-7d2d-11ea-927d-66028b3706b9.png">  

**Single Activity Instantiated Multiple Times**
If an Activity is started from multiple Activities, then a new instance of that Activity is created and pushed on the stack 
rather than bringing the previous instance of that Activity to the top. As such, one activity in your app might be instantiated 
multiple times (even from different tasks). As such, if the user navigates backward using the Back button, each instance of 
the activity is revealed in the order they were opened (each with their own UI state). However, you can modify this behavior 
if you do not want an activity to be instantiated more than once.  

<img src="https://user-images.githubusercontent.com/30290570/79079613-d17f4280-7d2d-11ea-8d70-116fe07a03e9.png">  

**MANAGING TASKS**  

***Caution: Most apps should not interrupt the default behavior for activities and tasks.***  

**Defining launch modes**  
Launch modes allow you to define how a new instance of an activity is associated with the current task. You can define 
different launch modes in two ways:
***Using the manifest file and using intent flags***.  
The launchMode attribute specifies an instruction on how the activity should be launched into a task. There are four different 
launch modes you can assign to the launchMode attribute:  
* ***"standard"*** (the default mode)
The system creates a new instance of the activity in the task from which it was started and routes the intent to it. 
The activity can be instantiated multiple times, each instance can belong to different tasks, and one task can have multiple instances.  

<img src="https://user-images.githubusercontent.com/30290570/79079801-ea3c2800-7d2e-11ea-9cc8-0deccf1d0b6b.png">  

* ***"singleTop"|Intent.FLAG_ACTIVITY_SINGLE_TOP***
This launch mode stops different instances of the same activities from being stacked on top of one another.
If an instance of the activity already exists at the top of the current task, the system routes the intent to that 
instance through a call to its onNewIntent() method, rather than creating a new instance of the activity.  
For example, suppose a task's back stack consists of root activity A with activities B, C, and D on top (the stack is A-B-C-D; 
D is on top). An intent arrives for an activity of type D. If D has the default "standard" launch mode, a new instance of the 
class is launched and the stack becomes A-B-C-D-D. However, if D's launch mode is "singleTop", the existing instance of D 
receives the intent through onNewIntent(), because it's at the top of the stack—the stack remains A-B-C-D. However, if an 
intent arrives for an activity of type B, then a new instance of B is added to the stack, even if its launch mode is "singleTop".  

<pre>
<img src="https://user-images.githubusercontent.com/30290570/79080154-0b057d00-7d31-11ea-8ba2-3772318210dd.png" height="98%">  <img src="https://user-images.githubusercontent.com/30290570/79080158-0f319a80-7d31-11ea-9a99-c3ca448b601f.png" height="98%">  <img src="https://user-images.githubusercontent.com/30290570/79080249-4f911880-7d31-11ea-97e0-0a148f71424c.png">

</pre>  

* ***"singleTask"| flag — FLAG_ACTIVITY_NEW_TASK***  
The system creates a new task and instantiates the activity at the root of the new task. 
However, if an instance of the activity already exists in a separate task, the system routes the intent to the existing instance through a call to its onNewIntent() method, rather than creating a new instance. 
Only one instance of the activity can exist at a time.  
<img src="https://user-images.githubusercontent.com/30290570/79080370-e65dd500-7d31-11ea-8608-e1ce38621175.png">  
Your current activity has to have a taskAffinity value defined other than the default package name. Or else, even though you
have tagged it as “singleTask” it will still open it in the same Task. The affinity indicates which task an activity prefers to belong to.
By default, all the activities from the same app have an affinity for each other. So, by default, all activities in the same app
prefer to be in the same task.  
  
* ***"singleInstance"***
Same as "singleTask", except that the system doesn't launch any other activities into the task holding the instance. 

The activity is always the single and only member of its task; any activities started by this 
one open in a separate task.  
Example, In this example, Activity B will have a launch mode of Single Instance. Activity A in Task 1 launches Activity B. 
This makes Activity B launch in a new task, which is then put in the foreground. Activity B then launches Activity C. 
Since a Single Instance can be the only activity in a task, C is launched on top of Activity A in Task 1, and then Task 1 
comes to the foreground.  
<pre>
<img width="260px" height="290px" src="https://user-images.githubusercontent.com/30290570/79080477-df839200-7d32-11ea-9039-032ba9fd2ea2.png">    <img width="260px" height="290px" src="https://user-images.githubusercontent.com/30290570/79080481-e7dbcd00-7d32-11ea-8ddc-adf6dfd9853b.png">
<img width="260px" height="290px" src="https://user-images.githubusercontent.com/30290570/79080485-ec07ea80-7d32-11ea-87fc-afe92a6ab648.png">    <img width="260px" height="290px" src="https://user-images.githubusercontent.com/30290570/79080488-ee6a4480-7d32-11ea-8b7d-2057ed43193f.png">
</pre>
* ***FLAG_ACTIVITY_CLEAR_TOP***  
If the activity being started is already running in the current task, then instead of launching a new instance of that activity, all of the other activities on top of it are destroyed and this intent is delivered to the resumed instance of the activity (now on top), through onNewIntent()).
There is no value for the launchMode attribute that produces this behavior.  
<pre>
<img src="https://user-images.githubusercontent.com/30290570/79080491-f0cc9e80-7d32-11ea-9a61-c0739ef92ab4.png">   <img width="260px" height="290px" src="https://user-images.githubusercontent.com/30290570/79080492-f32ef880-7d32-11ea-8e31-4ce7cfc48005.png">
</pre>  

Whew! That was a long article. Hope you understood it :)






