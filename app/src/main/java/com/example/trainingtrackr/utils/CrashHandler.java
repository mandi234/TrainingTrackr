package com.example.trainingtrackr.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


public class CrashHandler implements UncaughtExceptionHandler {

    /**
     * TAG
     */
    private static final String TAG = CrashHandler.class.getSimpleName();


    /**
     * System default UncaughtException processing class
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    /**
     * CrashHandler Example
     */
    private static CrashHandler INSTANCE = new CrashHandler();

    /**
     * Context object for program
     */
    private Context mContext;
    /**
     * Used to store device information and exception information
     */

    private Map<String, String> infos = new HashMap<String, String>();


    /**
     * Used to format the date as part of the log file name
     */
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");

    /**
     * Private construction method guarantees only one instance of CrashHandler
     */
    private CrashHandler() {
    }

    /**
     * Get CrashHandler instance, singleton mode
     */
    public static CrashHandler getInstance() {
        return INSTANCE;
    }

    /**
     * A.Initialization
     *
     * @param context
     */
    public void init(Context context) {

        mContext = context;
        //Get the system default UncaughtException processor

        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();

        //Set this CrashHandler as the program's default processor
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * B.When UncaughtException occurs, it goes into the function to handle it
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        System.out.println("thread =" + ex.getMessage());

        System.out.println("ex =" + ex);

        if (!handleException(ex) && mDefaultHandler != null) {
            //Let the default exception handler of the system handle if the user does not handle it
            mDefaultHandler.uncaughtException(thread, ex);
        } else {


            /**********************************************************************
             * First treatment method:
             *
             *  If an exception occurs, catch the exception and jump to the specified page.
             *
             ***********************************************************************/


            /**

             Intent intent = new Intent(mContext, ReportActivity.class);
             //Set the startup mode of the Activity to start a new task stack
             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             mContext.startActivity(intent);
             //Note: If this pops up here, it's because of problems with java and Android packages, killing the current process, and closing the current process
             Process.killProcess(Process.myPid());

             */


            /**********************************************************************
             * Second treatment method:
             *
             *  If an exception occurs, apply an automatic restart to enter the home or start page
             *
             ***********************************************************************/


         /*   Intent intent = new Intent(mContext, TrainingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());*/


            /**********************************************************************
             * The third treatment method:
             *
             * Toast display
             *
             ***********************************************************************/

            handleException(ex);
        }
    }

    /**
     * Customize error handling, collect error information and send error reports.
     *
     * @return true:Returns true if the exception information is handled; false otherwise.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }


        /**
         * Use Toast to display exception information
         */

        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "I'm sorry,Program Exception,About to quit.", Toast.LENGTH_LONG).show();
                Toast.makeText(mContext, ex.getMessage(), Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();

        //Collect device parameter information
        //collectDeviceInfo(mContext);
        //Save Log File
        //saveCrashInfo2File(ex);

        return true;
    }

    /**
     * Collect device parameter information
     *
     * @param ctx
     */
    public void collectDeviceInfo(Context ctx) {
        try {
            //Package Manager
            PackageManager pm = ctx.getPackageManager();
            //Get package information
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }

    /**
     * Save error information to file
     *
     * @param ex
     * @return Return file name for easy file transfer to server
     */
    private String saveCrashInfo2File(Throwable ex) {

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : infos.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            sb.append(key + "=" + value + "\n");
        }
        sb.append(ex.getMessage());
        //Log Output Error Log
        System.out.println("sb = " + sb.toString());

        //Store error log in SD card, commonly used in development, problems uploading saved files to server
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        ex.printStackTrace(printWriter);
        Throwable cause = ex.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        String result = writer.toString();
        sb.append(result);
        try {
            long timestamp = System.currentTimeMillis();
            String time = formatter.format(System.currentTimeMillis());
            String fileName = "crash-" + time + "-" + timestamp + ".log";
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/trainingtrackr/crash/";
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                FileOutputStream fos = new FileOutputStream(path + fileName);
                fos.write(sb.toString().getBytes());
                fos.close();
            }
            return fileName;
        } catch (Exception e) {
            Log.e(TAG, "an error occured while writing file...", e);
        }
        return null;
    }
}