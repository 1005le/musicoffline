package com.example.admin.musicoffline;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.musicoffline.adapter.ListViewAdapter;
import com.example.admin.musicoffline.model.model;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSION_REQUEST = 1;

    ArrayList<String> arrayList;
    ListView listView;
    ArrayAdapter<String> adapter;

  //  ListView listView;
    ListViewAdapter lvadapter;
    String[] title;
    String[] description;
    int[] icon;
    List<model> arr = new ArrayList<model>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//         if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
//             if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)){
//                 ActivityCompat.requestPermissions(MainActivity.this,
//                         new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
//             }else{
//                 ActivityCompat.requestPermissions(MainActivity.this,
//                         new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION_REQUEST);
//             }
//         }else{
//             doStuff();
//         }
        /**
         * hien thi danh sách
         */
        ActionBar actionBar = getSupportActionBar();
      //  actionBar.setTitle("Items List");

        title = new String[]{"Battery", "Cpu", "Display", "Memory", "Sensor"};
        description = new String[]{"Battery detail...", "Cpu detail...", "Display detail...", "Memory detail...", "Sensor detail..."};
        icon = new int[]{R.drawable.a, R.drawable.dembuontinhle, R.drawable.tinhca, R.drawable.toithanhthoi, R.drawable.chuyennangmua};

        listView = findViewById(R.id.lvSong);

        for (int i =0; i<title.length; i++){
            model mod = new model(title[i], description[i], icon[i]);
            //bind all strings in an array
            arr.add(mod);
        }

        //pass results to listViewAdapter class
        lvadapter = new ListViewAdapter(MainActivity.this, arr);

        //bind the adapter to the listview
        listView.setAdapter(adapter);

    }

    /**
     * Hien thi menu search
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_view, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView)myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (TextUtils.isEmpty(s)){
                    lvadapter.filter("");
                    listView.clearTextFilter();
                }
                else {
                    lvadapter.filter(s);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.action_settings){
            //do your functionality here
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void doStuff() {
        listView = findViewById(R.id.lvSong);
        arrayList = new ArrayList<>();
        getMusic();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    public void getMusic(){
        ContentResolver contentResolver = getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor = contentResolver.query(songUri, null,null,null,null);

        if(songCursor != null && songCursor.moveToFirst()){
            int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songArtist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int songAlbum = songCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);
            int songPath = songCursor.getColumnIndex(MediaStore.Audio.Media.DATA);

            do{
                String currentTitle = songCursor.getString(songTitle);
                String currentArtist = songCursor.getString(songArtist);
                String currentAlbum = songCursor.getString(songAlbum);
                String currentPath = songCursor.getString(songPath);
                  arrayList.add(currentTitle+"\n" + currentArtist+"\n"+currentAlbum+"\n"+currentPath);

            }while(songCursor.moveToNext());
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case MY_PERMISSION_REQUEST: {
                if( grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                     doStuff();
                    }

                }else{
                    Toast.makeText(this, "No permission granted!", Toast.LENGTH_SHORT).show();
                 finish();
                }
                return;
            }
        }
    }
}
