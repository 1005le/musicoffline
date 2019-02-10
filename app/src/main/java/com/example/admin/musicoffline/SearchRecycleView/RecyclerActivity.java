package com.example.admin.musicoffline.SearchRecycleView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;

import com.example.admin.musicoffline.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {
  Toolbar toolbarRecycleView;
   RecyclerView recyclerView;
    List<Item> arrayListItem = new ArrayList<>();
    ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        arrayListItem.add(new Item("HonDa", R.drawable.dauphu));
        arrayListItem.add(new Item("HonDa2", R.drawable.dauphu));
        arrayListItem.add(new Item("HonDa3", R.drawable.dauphu));
        arrayListItem.add(new Item("HonDa4", R.drawable.dauphu));
        arrayListItem.add(new Item("HonDa5", R.drawable.dauphu));
        arrayListItem.add(new Item("HonDa6", R.drawable.dauphu));
        arrayListItem.add(new Item("HonDa7", R.drawable.dauphu));
        arrayListItem.add(new Item("HonDa8", R.drawable.dauphu));
        arrayListItem.add(new Item("HonDa9", R.drawable.dauphu));
        arrayListItem.add(new Item("HonDa10", R.drawable.dauphu));
        arrayListItem.add(new Item("HonDa11", R.drawable.dauphu));

        init();

        //Drag and Drop

    }

    private void init() {
        toolbarRecycleView = findViewById(R.id.toolBarMainRecycler);
        recyclerView = findViewById(R.id.recycleDauPhu);
        recyclerView.setHasFixedSize(true);


        setSupportActionBar(toolbarRecycleView);
         getSupportActionBar().setTitle("Hello");
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        itemAdapter = new ItemAdapter(RecyclerActivity.this, arrayListItem);
        recyclerView.setAdapter(itemAdapter);

        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(divider);

        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder dragged, RecyclerView.ViewHolder target) {

                int position_dragged = dragged.getAdapterPosition();
                int position_target = target.getAdapterPosition();

                Collections.swap(arrayListItem, position_dragged, position_target);

                itemAdapter.notifyItemMoved(position_dragged, position_target);

                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

            }
        });
        helper.attachToRecyclerView(recyclerView);

    }
    @Override
    public boolean onCreateOptionsMenu( Menu menu) {
        getMenuInflater().inflate( R.menu.search_view, menu);

        final MenuItem myActionMenuItem = menu.findItem( R.id.menu_search);
        final android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(!searchView.isIconified()){
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String searchQuery) {
                final List<Item>filterModel = filter(arrayListItem, searchQuery);
               itemAdapter.getFilte(filterModel);
                return true;
            }
        });
        return true;
    }

    private List<Item> filter(List<Item>listItem, String query){

        query = query.toLowerCase();
        final List<Item>filterModel = new ArrayList<>();

        for( Item item :listItem){
            final String text = item.getName().toLowerCase();
            if( text.startsWith(query)){
                filterModel.add(item);
            }
        }
        return filterModel;
    }


}
