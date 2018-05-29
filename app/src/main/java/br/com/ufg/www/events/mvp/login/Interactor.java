package br.com.ufg.www.events.mvp.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.ufg.www.events.model.Login;

public class Interactor extends SQLiteOpenHelper implements Contract.Interactor {

    private static final String NOME_BANCO = "events.db";
    private static final String TABELA_LOGIN ="login";
    private static final int VERSAO =1;

    public Interactor(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table login "+TABELA_LOGIN+"("+
                     "user "+" text primary key"+
                     "password "+ " text";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean validadtedCredentials(String email){
        return email.contains("gmail");
    }

    @Override
    public void login(Login login) {
        if (validadtedCredentials(login.getLogin())){
            insere(login);
        }
    }

    public void insere(Login login){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("user", login.getLogin());
        dados.put("password", login.getPassword());
        db.insert(TABELA_LOGIN, null, dados);
    }

    public Login findLogin(){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "SELECT USER, PASSWORD FROM LOGIN";

        Cursor cursor = db.rawQuery(sql, null);

        if(cursor!=null){
            cursor.moveToFirst();
            Login user = new Login();
            user.setLogin(cursor.getString(cursor.getColumnIndex("user")));
            user.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            return user;
        } else{
            return null;
        }


    }

}
