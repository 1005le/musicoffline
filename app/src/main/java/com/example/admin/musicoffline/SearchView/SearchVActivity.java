package com.example.admin.musicoffline.SearchView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.admin.musicoffline.R;

import java.util.ArrayList;

public class SearchVActivity extends AppCompatActivity {
    ListView listView;
    MyAppAdapter myAppAdapter;
    ArrayList<Post> postArrayList;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_v);
        listView = (ListView) findViewById(R.id.listView);

        postArrayList = new ArrayList<>();
        postArrayList.add(new Post("Dummy Title", "Dummy Sub Title"));
        postArrayList.add(new Post("Searchview in actionbar", "enjoy search functionality from actionbar in android"));
        postArrayList.add(new Post("Search in listview", "search feature that filter listview item"));
        postArrayList.add(new Post("Android Search Bar", "adding search feature in toolbar using appcompat library"));
        postArrayList.add(new Post("Android Studio SearchView example", "Android SearchView tutorial in android studio"));
        postArrayList.add(new Post("Android Tutorial", "Get latest android material with simple solution"));
        postArrayList.add(new Post("nkDroid tutorials", "A to Z Android tutorials at one place"));


        myAppAdapter = new MyAppAdapter(postArrayList, SearchVActivity.this);
        listView.setAdapter(myAppAdapter);

        toolbar = findViewById(R.id.toolBarMainActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Beauty Music");

    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate( R.menu.search_view, menu);

        MenuItem myActionMenuItem = menu.findItem( R.id.menu_search);
        final android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // listView.setVisibility(View.INVISIBLE);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String searchQuery) {
                myAppAdapter.filter(searchQuery.toString().trim());
                listView.invalidate();
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
