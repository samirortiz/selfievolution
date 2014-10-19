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
	private String[] columns = new String[] { "id", "nome", "email", "senha", "sexo", "data_nascimento", "id_role", "id_professor" };
	
	public UsuarioModel(Context ctx) {
		db = DBHandler.getInstance(ctx).getWritableDatabase();
	}	
	
	public void close() {
		db.close();
	}

	public int autenticacao(String email, String senha){

		String crypt = UnixCrypt.crypt(email,senha);

		Cursor c = db.rawQuery("SELECT id, email, senha FROM usuarios " +
								"WHERE email = ? AND senha = ?", new String[] {email, crypt});
		
		if(c.moveToFirst()){
			Log.d("Login", "Login de "+ email + " efetuado com sucesso!");

			return c.getInt(0);
			
		}else{

			return 0;
		}
	}

	public ContentValues selectUser(String id){

		Cursor c = db.rawQuery("SELECT id, nome, email, sexo, data_nascimento, id_role FROM usuarios " +
								"WHERE id = ?", new String[] {id});
		
		if(c.moveToFirst()){
			ContentValues cv = new ContentValues();
			cv.put("id", c.getInt(0));
			cv.put("nome", c.getString(1));
			cv.put("email", c.getString(2));
			cv.put("sexo", c.getString(3));
			cv.put("data_nascimento", c.getString(4));
			cv.put("id_role", c.getString(5));

			return cv;
			
		}else{

			return null;
		}
	}	
	
	public boolean checkUserByEmail(String email){

		Cursor c = db.rawQuery("SELECT id FROM usuarios " +
								"WHERE email = ?", new String[] {email});
		
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
    		usuario.setNome(c.getString(c.getColumnIndex("nome")));
    		usuario.setSenha(c.getString(c.getColumnIndex("senha")));
    		usuario.setEmail(c.getString(c.getColumnIndex("email")));
    		usuario.setEmail(c.getString(c.getColumnIndex("data_nascimento")));
    		
    		list.add(usuario);
    	}
    	
    	return list;
	}

	public ArrayList<Usuario> selectByProfessor(Integer id) {

		Cursor c = db.rawQuery("SELECT u.id, u.nome FROM usuarios u WHERE id_professor = ?", new String[] {String.valueOf(id)});
    	
    	ArrayList<Usuario> list = new ArrayList<Usuario>();
    	
    	while (c.moveToNext()) {
    		
    		Usuario usuario = new Usuario();
    		usuario.setId(c.getInt(c.getColumnIndex("id")));
    		usuario.setNome(c.getString(c.getColumnIndex("nome")));
    		
    		list.add(usuario);
    	}
    	
    	return list;
	}	
	
	public long insert(Usuario usuario) {
		
		long rowId = 0;
		
		if(checkUserByEmail(usuario.getEmail())){
		
			ContentValues cv = new ContentValues();
	        cv.put("nome", usuario.getNome());
	        cv.put("email", usuario.getEmail());
	        cv.put("senha", usuario.getSenha());
	        cv.put("data_nascimento", usuario.getNascimento());
	        cv.put("id_role", usuario.getIdRole());
	        cv.put("id_professor", usuario.getIdProfessor());

	        rowId = db.insert(tableName, null, cv);

		}
		
        return rowId;
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
