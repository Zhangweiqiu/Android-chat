package com.example.nit1107.nit1107;

import android.content.Intent;
import android.gesture.GestureUtils;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
import android.view.DragAndDropPermissions;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ashokvarma.bottomnavigation.ShapeBadgeItem;
import com.ashokvarma.bottomnavigation.TextBadgeItem;
import com.example.nit1107.nit1107.Adapter.ChatListAdapter;
import com.example.nit1107.nit1107.model.Friend;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.*;

public class ChatlistActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private BottomNavigationBar bottomNavigationBar;

    private TextBadgeItem textBadgeItem;

    private ShapeBadgeItem shapeBadgeItem;

    private ChatFragment chatFragment;

    private List<Friend> friendList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatlist);

        //初始化
        initFriend();
        ChatListAdapter adapter = new ChatListAdapter(ChatlistActivity.this,R.layout.head_item,friendList);
        ListView listView = findViewById(R.id.msg_list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ChatlistActivity.this,ChatFragment.class);
                startActivity(intent);
            }
        });


        //底部菜单栏
        bottomNavigationBar = findViewById(R.id.bottom_navigation_bar);
        textBadgeItem = new TextBadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.colorAccent);
        shapeBadgeItem = new ShapeBadgeItem()
                .setShapeColorResource(R.color.colorPrimary)
                .setGravity(Gravity.TOP|Gravity.END)
                .setHideOnSelect(false);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setBarBackgroundColor(R.color.black);
        bottomNavigationBar.addItem(new BottomNavigationItem(R.drawable.logo,"联系人"))
                .addItem(new BottomNavigationItem(R.drawable.logo,"新闻中心"))
                .setFirstSelectedPosition(0)
                .initialise();
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
              //  if (position)
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

        //上端导航
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);

        //右滑菜单
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                return true;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                makeText(this,"1", LENGTH_SHORT).show();
                break;
            case R.id.delete:
                makeText(this,"2", LENGTH_SHORT).show();
                break;
            case R.id.settings:
                makeText(this,"3", LENGTH_SHORT).show();
                break;
            default:
        }

        return true;
    }


    //初始化聊天列表
    private void initFriend(){
        for (int i = 0 ; i < 7; i++) {

            Friend friend1 = new Friend("张三", R.drawable.ic_menu);
            friendList.add(friend1);
            Friend friend2 = new Friend("李四", R.drawable.ic_menu);
            friendList.add(friend2);
            Friend friend3 = new Friend("王五", R.drawable.ic_menu);
            friendList.add(friend3);
            Friend friend4 = new Friend("柳六", R.drawable.ic_menu);
            friendList.add(friend4);
            Friend friend5 = new Friend("延期", R.drawable.ic_menu);
            friendList.add(friend5);
        }
    }
}


