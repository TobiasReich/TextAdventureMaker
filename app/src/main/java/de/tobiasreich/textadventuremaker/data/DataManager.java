package de.tobiasreich.textadventuremaker.data;

import android.Manifest;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import de.tobiasreich.textadventuremaker.storyObjects.Story;

/**
 *
 * Created by T on 13.04.2017. */
public class DataManager {

    private static final String TAG = DataManager.class.getSimpleName();
    private static final String APP_FOLDER_PATH = "AdventureMaker";
    private static final String ADVENTURES_PATH = "Adventures";
    private static final String ADVENTURES_SUFFIX = ".adv";

    private static final int BUFFER_SIZE = 512;

    public static final int PERMISSION_REQUEST_ACCESS_STORAGE = 1;

    public static Story story;

    private static DataManager instance;

    private AppCompatActivity activity;
    private Gson gson = new Gson();

    private DataManager() {
    }

    public static DataManager getInstance() {
        if (DataManager.instance == null) {
            DataManager.instance = new DataManager();
        }
        return DataManager.instance;
    }

    public void setActivity(AppCompatActivity activity){
        this.activity = activity;
    }

    /** Saves a story as a playable archive.
     * The file stored can be imported by others.
     *
     * Needed for creating playable adventures
     *
     * @param story
     */
    public void saveStory(Story story){
        DataManager.story = story;

        if (! isExternalStorageWritable()) {
            Log.e(TAG, "Error, storage is not writable.");
            Toast.makeText(activity, "Error! Storage is not writable", Toast.LENGTH_LONG).show();
            return;
        } else {
            Log.d(TAG, "Storage is writable.");
            Toast.makeText(activity, "Storage is writable", Toast.LENGTH_LONG).show();
            final String[] PERMISSIONS_STORAGE = {Manifest.permission.WRITE_EXTERNAL_STORAGE };
            //Asking request Permissions
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, PERMISSION_REQUEST_ACCESS_STORAGE);
        }
    }

    public void storeStoryOnDevice(){
        Log.d(TAG, "Saving story!");
        String storyAsJSON = gson.toJson(story);

        File path = getAlbumStoragePath();
        if (path.exists()) {
            Log.i(TAG, "Path exists");
        } else {
            Log.i(TAG, "Path does not exist!");
        }

        ensureFilePath(path);

        if (path.exists()) {
            Log.i(TAG, "Path exists");
        } else {
            Log.i(TAG, "Path does not exist!");
        }

        File file1 = new File(path, "game.txt");
        File file2 = new File(path, "game2.txt");
        File gameArchive = new File(path, "adventure" + ADVENTURES_SUFFIX);

        try (FileOutputStream stream = new FileOutputStream(file1)){
            stream.write(storyAsJSON.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "Exception while saving story" + e.getStackTrace());
        }

        try (FileOutputStream stream = new FileOutputStream(file2)){
            stream.write(storyAsJSON.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            Log.d(TAG, "Exception while saving story" + e.getStackTrace());
        }

        Log.d(TAG, "STORY AS STRING: \n" + storyAsJSON);

        Log.d(TAG, "Saved 2 files to Storage!");

        String[] files = new String[2];
        files[0] = file1.getAbsolutePath();
        files[1] = file2.getAbsolutePath();
        try {
            zip(files, gameArchive.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "Finished zipping!");
    }


    private void ensureFilePath(File path) {
        Log.d(TAG, "Trying to make Dirs " + path.getAbsolutePath());
        path.mkdirs();
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }


    public File getAlbumStoragePath() {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(APP_FOLDER_PATH), ADVENTURES_PATH);
        if (!file.mkdirs()) {
            Log.e(TAG, "Directory not created");
        }
        return file;
    }


    public void zip(String[] files, String zipFile) throws IOException {
        BufferedInputStream origin = null;
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile)));
        try {
            byte data[] = new byte[BUFFER_SIZE];

            for (int i = 0; i < files.length; i++) {
                FileInputStream fi = new FileInputStream(files[i]);
                origin = new BufferedInputStream(fi, BUFFER_SIZE);
                try {
                    ZipEntry entry = new ZipEntry(files[i].substring(files[i].lastIndexOf("/") + 1));
                    out.putNextEntry(entry);
                    int count;
                    while ((count = origin.read(data, 0, BUFFER_SIZE)) != -1) {
                        out.write(data, 0, count);
                    }
                }
                finally {
                    origin.close();
                }
            }
        }
        finally {
            out.close();
        }
    }

    public List<String> getPossibleGames(){
        List<String> adventures = new ArrayList<>();

        String path = Environment.getExternalStoragePublicDirectory(APP_FOLDER_PATH).toString() + "/" + ADVENTURES_PATH;
        Log.d(TAG, "Searching Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        Log.d(TAG, "Possible stories Size: "+ files.length);
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getName();
            Log.d(TAG, "checking file:" + fileName);
            if (fileName.endsWith(DataManager.ADVENTURES_SUFFIX))
                adventures.add(fileName);
        }
        Log.d(TAG, "Adventures: " + adventures.size());
        return adventures;
    }


}
