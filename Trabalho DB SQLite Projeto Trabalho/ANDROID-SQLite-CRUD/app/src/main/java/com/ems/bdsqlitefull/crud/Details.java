package com.ems.bdsqlitefull.crud;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ems.bdsqlitefull.R;
import com.ems.bdsqlitefull.pojo.Livro;

public class Details extends AppCompatActivity {
    Button btEditar;
    TextView nome, autor, faixa, curso, editora, genero;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Mostra um botão na Barra Superior para voltar
        getSupportActionBar().setTitle("CRUD DB SQLite - Detalhes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        nome = findViewById(R.id.nome);
        autor = findViewById(R.id.autor);
        faixa = findViewById(R.id.faixa);
        editora = findViewById(R.id.editora);
        genero = findViewById(R.id.genero);
        btEditar = findViewById(R.id.btSalvar);

        Intent itLivro = getIntent();
        final Livro livro = (Livro) itLivro.getExtras().getSerializable("objLivro");
        nome.setText(String.valueOf(livro.getNome()));
        autor.setText(livro.getAutor());
        faixa.setText(livro.getFaixa());
        editora.setText(livro.getEditora());
        genero.setText(livro.getGenero());

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editar = new Intent(getApplicationContext(), EditRecord.class);
                editar.putExtra("objAluno", livro);
                startActivity(editar);
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