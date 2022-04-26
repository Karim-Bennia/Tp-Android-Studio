package com.karim.listview;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.preference.DialogPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
ImageView del;
    TextView itemName,itemQte;
    private ArrayList<Achat> listAchats;
    Button Add;
    EditText Input;
    EditText Qte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Add=(Button)findViewById(R.id.Add);
        Input=(EditText)findViewById(R.id.inputAchat);
        Qte=(EditText)findViewById(R.id.Quantite);
            listAchats =new ArrayList<Achat>();
           listAchats.add(new Achat("farine",10));

            ListView listAchatsView=(ListView)findViewById(R.id.listView);

            //final String Item=achat.toString();
            // listAchats.add(Item);
            final MyAdapter adapter =new MyAdapter(this,listAchats);
            listAchatsView.setAdapter(adapter);

            Add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!(Input.getText().toString().isEmpty() || Qte.getText().toString().isEmpty())) {
                        Double Quantite = Double.parseDouble((Qte.getText().toString()));
                        listAchats.add(new Achat(Input.getText().toString(), Quantite));
                        adapter.notifyDataSetChanged();
                        Input.setText("");
                        Qte.setText("");
                    }

                }
            });
       /* listAchatsView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                Toast.makeText(getApplicationContext(), "long clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
        });*/


registerForContextMenu(listAchatsView);



    }


    class MyAdapter extends ArrayAdapter<Achat> implements View.OnClickListener {
        private Activity context;
        private ArrayList<Achat> listAchats;

        public MyAdapter(Activity context, ArrayList<Achat> listAchats) {
            super(context, R.layout.ma_ligne, listAchats);
            this.context = context;
            this.listAchats = listAchats;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = context.getLayoutInflater();
                convertView = inflater.inflate(R.layout.ma_ligne, null);
            }
            TextView itemName = (TextView) convertView.findViewById(R.id.itemName);
            TextView itemQte = (TextView) convertView.findViewById(R.id.itemQte);

            itemName.setText(listAchats.get(position).getItem());
            itemQte.setText(String.valueOf(listAchats.get(position).getQte()));
            return convertView;
        }

        @Override
        public void onClick(View v) {
           int position =(Integer)v.getTag();
            if(v.getId()==R.id.deleteButton){
              //  listAchats.remove(position);
                //notifyDataSetChanged();

            }else{

            }
            this.notifyDataSetChanged();

        }}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater Inflater=getMenuInflater();
        Inflater.inflate(R.menu.first_menu, menu);
        return  true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item2:
                //listAchats.remove(position);
                //adapter.notifyDataSetChanged();


                return true;
            
            case R.id.item1:
                //Toast.makeText(this, "Vider La liste", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder Alert=new AlertDialog.Builder(this);
                Alert.setMessage("voulez-vous effacer le contenu de cette liste?");
                Alert.setTitle("Confirmation");

                Alert.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Are You Sur Ok ,Thank U", Toast.LENGTH_SHORT).show();
                       // listAchats.clear();


                    }
                });

                Alert.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "doing nothing", Toast.LENGTH_SHORT).show();
                    }
                });
                Alert.show();



            return true;

            default:
                AlertDialog.Builder dialog=new AlertDialog.Builder(this);
                dialog.setMessage("Achat app devloper par vous :/");
                dialog.setTitle("A propos");
                dialog.setNeutralButton("Ok", null);
                dialog.show();
                return true;
        }
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v,menuInfo);
        getMenuInflater().inflate(R.menu.contextmenu, menu);

    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Liste_1:
                Toast.makeText(MainActivity.this, "List1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Liste_2:
                Toast.makeText(MainActivity.this, "List2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Liste_3:
                Toast.makeText(MainActivity.this, "List3", Toast.LENGTH_SHORT).show();
                return true;
            default:
               return super.onContextItemSelected(item);

        }
    }
}

