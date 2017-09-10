package com.example.nitishprasad.signin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class topPage extends AppCompatActivity {

    RecyclerView recyclerView;
    String []className = {"CSE A","CSE B","CSE C","CSE D","CSE E","CSE F","CSE G"};
    String []groupName = {"CSE A","CSE B","CSE C","CSE D","CSE E","CSE F","CSE G"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        recyclerView = (RecyclerView)findViewById(R.id.classList);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        classAdapter adapter = new classAdapter(topPage.this,className,groupName);
        recyclerView.setAdapter(adapter);
    }


    public  boolean onCreateOptionsMenu(Menu menu){

        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.signout,menu);

        return  true;
    }


    public boolean onOptionsItemSelected(MenuItem menuItem){

        super.onOptionsItemSelected(menuItem);

        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.AUTO_SIGNIN,MODE_PRIVATE);

        SharedPreferences.Editor edit = sharedPreferences.edit();

        edit.putString(MainActivity.USERNAME,"");
        edit.putString(MainActivity.PASSWORD,"");
        edit.putBoolean(MainActivity.IS_AUTO_SIGNIN,false);
        edit.commit();

        Intent intent = new Intent(topPage.this,MainActivity.class);
        startActivity(intent);
        finish();

        return true;
    }

    public void cardClick(View view){

        Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show();
    }

}
