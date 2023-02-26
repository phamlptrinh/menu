package com.example.samplemenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String msg;
    TextView txContext;
    ImageButton bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //option menu
        Toolbar my_toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(my_toolbar);

        //context menu
        txContext = (TextView) findViewById(R.id.txContext);
        registerForContextMenu(txContext);

        //popup menu
        bt = (ImageButton) findViewById(R.id.bt);
        bt.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getApplicationContext(), Msg.class);
        PopupMenu pm = new PopupMenu(this, view);
        pm.getMenuInflater().inflate(R.menu.popup_menu, pm.getMenu());
        pm.show();
        pm.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.Search:
                        msg = "This is Search Page Q";
                        intent.putExtra("msg", msg);
                        startActivity(intent);return true;

                    case R.id.Account:
                        msg = "This is Account Page <3:";
                        intent.putExtra("msg", msg);
                        startActivity(intent);return true;

                    default:
                        return false;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)//co the ko dung, thi dung phan mac dinh??
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent intent = new Intent(this, Msg.class);
        switch (item.getItemId())
        {
            case R.id.action_favorite:
                msg = "This is Favorite Page <3";
                intent.putExtra("msg", msg);
                startActivity(intent);
                return true;

            case R.id.Search:
                msg = "This is Search Page Q";
                intent.putExtra("msg", msg);
                startActivity(intent);return true;

            case R.id.Support:
                msg = "This is Support Page :)";
                intent.putExtra("msg", msg);
                startActivity(intent);return true;

            case R.id.Account:
                msg = "This is Account Page <3:";
                intent.putExtra("msg", msg);
                startActivity(intent);return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo(); //THUC CHIEN NHAT DINH DUNG
        TextView tx = (TextView) findViewById((int)info.id);
        /*int pos = info.position;
        String i = la.getItem(pos);*/

        switch (item.getItemId())
        {
            case R.id.Edit:
                txContext.setText("Context menu");
                return true;

            case R.id.action_favorite:
                tx.setTextSize(2,48);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
    //popup

}

/*
context_menu.xml

<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:id="@+id/delId"
        android:title="Delete"/>
    <item android:id="@+id/upId"
        android:title="Lowercase"/>
    <item android:id="@+id/lowId"
        android:title="Uppercase"/>
</menu>


strings.xml

<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="app_name">MainActivity</string>
    <string-array name="languages">
        <item>Python</item>
        <item>Java</item>
        <item>Ruby</item>
        <item>C++</item>
    </string-array>
</resources>


row.xml

<?xml version="1.0" encoding="utf-8"?>
<TextView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:padding="10dp"
    android:textSize="20sp">
</TextView>


private ListView lv;
    private ArrayAdapter<String> la;

    * Called when the activity is first created. */
   /* @Override
public void onCreate(Bundle savedInstanceState)
{
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    lv = (ListView)findViewById(R.id.lvId);
    String[] languages = getResources().getStringArray(R.array.languages);
    ArrayList<String> lst = new ArrayList<String>();
    lst.addAll(Arrays.asList(languages));

    la = new ArrayAdapter<String>(this, R.layout.row, lst);
    lv.setAdapter(la);
    registerForContextMenu(lv);
}

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();

        int pos = info.position;
        String i = la.getItem(pos);

        switch (item.getItemId())
        {
            case R.id.delId:
                la.remove(i);
                return true;

            case R.id.upId:
                String upln = i.toUpperCase();
                la.remove(i);
                la.insert(upln, pos);
                return true;

            case R.id.lowId:
                String lpln = i.toLowerCase();
                la.remove(i);
                la.insert(lpln, pos);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }


    //popup
    public void onClick(View v)
    {
        PopupMenu pm = new PopupMenu(this, v);
        pm.getMenuInflater().inflate(R.menu.popup_menu, pm.getMenu());
        pm.setOnMenuItemClickListener(this);
        pm.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item)
    {
        tv.setText(item.toString() + " selected");
        return true;
    }

*/