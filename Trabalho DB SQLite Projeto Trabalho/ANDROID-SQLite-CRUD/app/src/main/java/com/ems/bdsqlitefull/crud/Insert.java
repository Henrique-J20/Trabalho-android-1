package com.ems.bdsqlitefull.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.ems.bdsqlitefull.pojo.Livro;
import com.ems.bdsqlitefull.R;
import com.ems.bdsqlitefull.utils.Message;

public class Insert extends AppCompatActivity {
    EditText nome, autor, faixa, editora, genero;
    Button btInserir;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        // Abertura ou criação do Banco de Dados
        db = openOrCreateDatabase("db_livro", Context.MODE_PRIVATE, null);

        // Cria a tabela se não existir, senão carrega a tabela para uso
        db.execSQL("CREATE TABLE IF NOT EXISTS aluno(" +
                "nome INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "autor VARCHAR NOT NULL, " +
                "faixa VARCHAR NOT NULL, " +
                "editora VARCHAR NOT NULL, " +
                "genero VARCHAR NOT NULL);");

        // Mostra um botão na Barra Superior para voltar
        getSupportActionBar().setTitle("CRUD DB SQLite - Inserir");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        nome = findViewById(R.id.editNome);
        autor = findViewById(R.id.editAutor);
        faixa = findViewById(R.id.editFaixa);
        editora = findViewById(R.id.editEditora);
        genero = findViewById(R.id.editGenero);
        btInserir = findViewById(R.id.btInserir);

        btInserir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria um objeto Aluno para receber os dados
                Livro livro = new Livro();
                livro.setNome(nome.getText().toString());
                livro.setAutor(autor.getText().toString());
               // livro.setFaixa(faixa.getText().toString());
                livro.setFaixa(Integer.parseInt(faixa.getText().toString()));
                livro.setEditora(editora.getText().toString());
                livro.setGenero(genero.getText().toString());

                // Coleta os dados digitados nos campos
                ContentValues values = new ContentValues();
                values.put("nome", livro.getNome());
                values.put("autor", livro.getAutor());
                values.put("faixa", livro.getFaixa());
                values.put("editora", livro.getEditora());
                values.put("genero", livro.getGenero());


                // Insere os dados na tabela
                db.insert("livro", null, values);

                // Cria uma caixa de mensagem e mostra os dados incluídos
                Message message = new Message(Insert.this);
                message.show(
                        "Dados incluídos com sucesso!",
                        livro.getDados(),
                        R.drawable.ic_add);

                // Limpa os campos de entrada
                clearText();
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

    /**
     * Limpa os campos de entrada e fecha o teclado
     */
    public void clearText() {
        nome.setText("");
        autor.setText("");
        faixa.setText("");
        editora.setText("");
        genero.setText("");
        autor.requestFocus();

        // fecha o teclado virtual
        ((InputMethodManager) Insert.this.getSystemService(
                Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                getCurrentFocus().getWindowToken(), 0);
    }
}
