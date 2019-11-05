package com.ems.bdsqlitefull.crud;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.ems.bdsqlitefull.R;
import com.ems.bdsqlitefull.pojo.Livro;
import com.ems.bdsqlitefull.pojo.Livro;

import java.util.ArrayList;

public class ListAll extends AppCompatActivity {
    ListView listViewALivro;
    ArrayList<Livro> livro = new ArrayList<>();
    ArrayAdapter<Livro> adaptador;
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Mostra um botão na Barra Superior para voltar
        getSupportActionBar().setTitle("CRUD DB SQLite - Listagem Geral");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        // Abreo banco de dados existente
        db = openOrCreateDatabase("db_livro", Context.MODE_PRIVATE, null);

        listViewALivro = findViewById(R.id.listagem);

        // Carrega os registros em ordem alfabética no ArrayList para anexar ao adaptador
        livro.clear();
        Cursor c = db.rawQuery("SELECT * FROM livro ORDER BY nome ASC", null);
        while (c.moveToNext()) {
            livro.add(new Livro(
              //      c.getString(0),
                    c.getString(1),
                    c.getString(2),
                    c.getInt(3),
                    c.getString(4),
                    c.getString(5)));
        }
        // Configura o adaptador
        adaptador = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                livro);


        // Anexa o adaptador à ListView
        listViewALivro.setAdapter(adaptador);

        listViewALivro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Livro livro = (Livro) listViewALivro.getItemAtPosition(position);
                Intent itLivro = new Intent(getApplicationContext(), Details.class);
                itLivro.putExtra("objLivro", livro);
                startActivity(itLivro);
            }
        });
    }

    // Configura o botão (seta) na ActionBar (Barra Superior)
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}