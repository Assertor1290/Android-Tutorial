package com.example.codejobscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void scheduleJob(View view) {
        ComponentName componentName=new ComponentName(this,ExampleJobService.class);
        JobInfo jobInfo=new JobInfo.Builder(12,componentName)
                .setRequiresCharging(true)
                //requires wifi
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                //Requires permission RECEIVE_BOOT_COMPLETED
                .setPersisted(true)
                .setPeriodic(15*60*1000)
                .build();

        JobScheduler jobScheduler=(JobScheduler)getSystemService(JOB_SCHEDULER_SERVICE);
        assert jobScheduler != null;
        int resultCode=jobScheduler.schedule(jobInfo);
        if (resultCode == JobScheduler.RESULT_SUCCESS) {
            Log.d(TAG, "Job scheduled");
        } else {
            Log.d(TAG, "Job scheduling failed");
        }
    }

    public void cancelJob(View view) {
        JobScheduler scheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
        assert scheduler != null;
        scheduler.cancel(12);
        Log.d(TAG, "Job cancelled");
    }
}
