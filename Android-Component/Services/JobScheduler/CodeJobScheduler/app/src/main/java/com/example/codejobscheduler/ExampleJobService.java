package com.example.codejobscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.SystemClock;
import android.util.Log;

public class ExampleJobService extends JobService {
    private static final String TAG = "tag";
    private boolean jobCancelled=false;

    //This method is used to perform your tasks.
    @Override
    public boolean onStartJob(JobParameters params) {
        //It runs by default on UI thread. And therfore have to create own background thread
        Log.d(TAG, "onStartJob: ");
        doBackGroundWork(params);
        //If our task is short and can be executed in the scope of this method, return false
        //true indicates we are responsible for telling system when we are finished
        return true;
    }

    private void doBackGroundWork(final JobParameters params) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++)
                {
                    Log.d(TAG, "run: "+i);

                    if(jobCancelled)
                    {
                        return;
                    }

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
                Log.d(TAG, "Job Finished ");
                //tells system our job is complete
                jobFinished(params,false);
            }
        }).start();
    }

    //This method called when our job get cancelled
    //eg: we want our job to happen when connected to wifi and user turns off wifi

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job cancelled before completion ");
        //If dont want to retry false
        //If want to retry true
        jobCancelled=true;
        return true;
    }
}
