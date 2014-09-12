package br.com.selfievolution.models;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import br.com.selfievolution.objects.Usuario;
import br.com.selfievolution.utils.DBHandler;
import br.com.selfievolution.utils.UnixCrypt;

public class UsuarioModel {
	private SQLiteDatabase db;
	private String tableName = "usuarios";
	private String[] columns = new String[] { "id", "ds_nome", "ds_email", "ds_senha" };
	
	public UsuarioModel(Context ctx) {
		db = DBHandler.getInstance(ctx).getWritableDatabase();
	}	
	
	public void close() {
		db.close();
	}

	public int autenticacao(String usuario, String senha){

		String crypt = UnixCrypt.crypt(usuario,senha);

		Cursor c = db.rawQuery("SELECT id, ds_email, ds_senha FROM usuarios " +
								"WHERE ds_email = ? AND ds_senha = ?", new String[] {usuario, crypt});
		
		if(c.moveToFirst()){
			Log.d("Login", "Login de "+ usuario + " efetuado com sucesso!");

			return c.getInt(0);
			
		}else{

			return 0;
		}
	}

	public ContentValues selectUser(String id){

		Cursor c = db.rawQuery("SELECT id, ds_nome, ds_email FROM usuarios " +
								"WHERE id = ?", new String[] {id});
		
		if(c.moveToFirst()){
			ContentValues cv = new ContentValues();
			cv.put("id", c.getInt(0));
			cv.put("ds_nome", c.getString(1));
			cv.put("ds_email", c.getString(2));

			return cv;
			
		}else{

			return null;
		}
	}	
	
	public boolean checkUserByEmail(String email){

		Cursor c = db.rawQuery("SELECT id FROM usuarios " +
								"WHERE ds_email = ?", new String[] {email});
		
		if(c.moveToFirst()){

			return false;
			
		}else{

			return true;
		}
	}	
	
	public ArrayList<Usuario> selectAll() {
		
    	Cursor c = db.query(tableName, columns, null, null, null, null, null);
    	
    	ArrayList<Usuario> list = new ArrayList<Usuario>();
    	
    	while (c.moveToNext()) {
    		
    		Usuario usuario = new Usuario();
    		usuario.setId(c.getInt(c.getColumnIndex("id")));
    		usuario.setNome(c.getString(c.getColumnIndex("ds_nome_usuario")));
    		usuario.setSenha(c.getString(c.getColumnIndex("ds_senha")));
    		usuario.setEmail(c.getString(c.getColumnIndex("ds_email_usuario")));
    		
    		list.add(usuario);
    	}
    	
    	return list;
	}

	public boolean insert(Usuario usuario) {
		
		long rowId = 0;
		
		if(checkUserByEmail(usuario.getEmail())){
		
			ContentValues cv = new ContentValues();
	        cv.put("ds_nome", usuario.getNome());
	        cv.put("ds_email", usuario.getEmail());
	        cv.put("ds_senha", usuario.getSenha());

	        rowId = db.insert(tableName, null, cv);

		}
		
        return (rowId > 0);
	}

	public boolean update(Usuario usuario) {
		
		ContentValues cv = new ContentValues();
       // cv.put("descricao", produto.getDescricao());

        int result = db.update(tableName, cv, "id = ?", 
        		new String[] { String.valueOf(usuario.getId()) });
        
        return (result > 0);
	}
	
	public boolean delete(Usuario usuario) {
		
        int result = db.delete(
        		tableName, 
        		"id = ?",
        		new String[] { String.valueOf(usuario.getId()) });
        
        return (result > 0);
	}
}
