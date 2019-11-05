package com.ems.bdsqlitefull.crud;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.ems.bdsqlitefull.MainActivity;
import com.ems.bdsqlitefull.R;
import com.ems.bdsqlitefull.pojo.Livro;
import com.ems.bdsqlitefull.utils.Message;

public class EditRecord extends AppCompatActivity {

    EditText nome, autor, faixa, editora, genero;
    Button btSalvar;

    SQLiteDatabase db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // Mostra um botão na Barra Superior para voltar
        getSupportActionBar().setTitle("CRUD DB SQLite - Edição");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        nome = findViewById(R.id.nome);
        autor = findViewById(R.id.autor);
        faixa = findViewById(R.id.faixa);
        editora = findViewById(R.id.editora);
        genero = findViewById(R.id.genero);
        btSalvar = findViewById(R.id.btSalvar);

        final Intent itLivro = getIntent();
        final Livro livro = (Livro) itLivro.getExtras().getSerializable("objLivro");
        nome.setText(String.valueOf(livro.getNome()));
        autor.setText(livro.getAutor());
        faixa.setText(livro.getFaixa());
        editora.setText(livro.getEditora());
        genero.setText(livro.getGenero());

        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Coleta os dados digitados nos campos
                ContentValues values = new ContentValues();
                values.put("nome", nome.getText().toString());
                values.put("autor", autor.getText().toString());
                values.put("faixa", faixa.getText().toString());
                values.put("editora", editora.getText().toString());
                values.put("genero", genero.getText().toString());

                Livro novosDados = new Livro();
                novosDados.setNome(nome.getText().toString());
                novosDados.setAutor(autor.getText().toString());
                //novosDados.setFaixa(faixa.getText().);
                novosDados.setEditora(editora.getText().toString());
                novosDados.setGenero(genero.getText().toString());

                // Atualiza os dados na tabela
                db = openOrCreateDatabase("db_livro", Context.MODE_PRIVATE, null);
                db.execSQL("UPDATE livro SET " +
                        "nome='" + novosDados.getNome() + "'," +
                        "autor='" + novosDados.getAutor() + "'," +
                        "faixa='" + novosDados.getFaixa() + "'," +
                        "editora='" + novosDados.getEditora() + "'," +
                        "genero='" + novosDados.getGenero() + "' " +
                        "WHERE nome=" + livro.getNome()
                );

                // Cria uma caixa de mensagem e mostra os dados incluídos
                Message message = new Message(EditRecord.this);
                message.show(
                        "Dados do Livro Atualizados com Sucesso!",
                        novosDados.getDados(),
                        R.drawable.ic_add);
                ;
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
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