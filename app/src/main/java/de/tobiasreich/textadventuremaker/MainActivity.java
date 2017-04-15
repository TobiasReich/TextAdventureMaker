package de.tobiasreich.textadventuremaker;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentManager;

import de.tobiasreich.textadventuremaker.data.DataManager;
import de.tobiasreich.textadventuremaker.editor.editor.EditorFragment;
import de.tobiasreich.textadventuremaker.player.chatGameType.PlayerChatFragment;
import de.tobiasreich.textadventuremaker.player.PlayerInitFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        ApplicationInteractionListener{

    private final String TAG = getClass().getSimpleName();

    private final FragmentManager fMan = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.storyEditor) {
            switchToStoryEditor();
        } else if (id == R.id.storyPlayer) {
            switchToStoryPlayer();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void switchToFragment(final Fragment fragment) {
        Log.d(TAG, "Switch to new Fragment : " + fragment + " TAG:" + fragment.getClass().getSimpleName());

        try {
            Handler handler = new Handler();
            handler.post(() -> {
                fMan.beginTransaction()
                        .replace(R.id.fragmentContainer, fragment)
                        //.replace(R.id.fragmentContainer, fragment, fragment.getClass().getSimpleName())
                        .addToBackStack(fragment.getClass().getSimpleName())
                        .commit();
                fMan.executePendingTransactions();
            });
        } catch (IllegalStateException e){
            Log.e(TAG, "IllegalStateException Can not perform this action after onSaveInstanceState");
        } catch (Exception e){
            Log.e(TAG, "Caught an Exception here!" + e);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        DataManager.getInstance().setActivity(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        boolean writeAccepted = false;
        if (requestCode == DataManager.PERMISSION_REQUEST_ACCESS_STORAGE){
            writeAccepted = grantResults[0]== PackageManager.PERMISSION_GRANTED;
        }

        if (writeAccepted){
            DataManager.getInstance().storeStoryOnDevice();
        }
    }


    private void switchToStoryEditor() {
        switchToFragment(new EditorFragment());
    }

    private void switchToStoryPlayer() {
        switchToFragment(new PlayerInitFragment());
    }

    private void switchToStoryChatPlayer() {
        switchToFragment(new PlayerChatFragment());
    }

    @Override
    public void openChatPlayer() {
        switchToStoryChatPlayer();
    }


}
