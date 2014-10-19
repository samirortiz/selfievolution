package br.com.selfievolution.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.selfievolution.objects.Avaliacao;
import br.com.selfievolution.utils.DBHandler;

public class AvaliacaoModel {
	private SQLiteDatabase db;
	private String tableName = "avaliacoes";
	private String[] columns = new String[] { "id", "id_aluno", "altura", "peso", "data" };
	
	public AvaliacaoModel(Context ctx) {
		db = DBHandler.getInstance(ctx).getWritableDatabase();
	}	
	
	public void close() {
		db.close();
	}

	public ContentValues selectAvaliacao(String id){

		Cursor c = db.rawQuery("SELECT id, altura, peso FROM avaliacoes " +
								"WHERE id = ?", new String[] {id});
		
		if(c.moveToFirst()){
			ContentValues cv = new ContentValues();
			cv.put("id", c.getInt(0));
			cv.put("altura", c.getInt(1));
			cv.put("peso", c.getInt(2));

			return cv;
			
		}else{

			return null;
		}
	}	
	
	public int countAvaliacoes(int id){

		Cursor c = db.rawQuery("SELECT count(id) FROM avaliacoes " +
								"WHERE id_aluno = ?", new String[] {String.valueOf(id)});
		
		if(c.moveToFirst()){

			return c.getInt(0);
			
		}else{

			return 0;
		}
	}
	
	
	public ArrayList<Avaliacao> selectAll() {
		
    	Cursor c = db.query(tableName, columns, null, null, null, null, null);
    	
    	ArrayList<Avaliacao> list = new ArrayList<Avaliacao>();
    	
    	while (c.moveToNext()) {
    		
    		Avaliacao avaliacao = new Avaliacao();
    		avaliacao.setId(c.getInt(c.getColumnIndex("id")));
    		avaliacao.setIdAluno(c.getInt(c.getColumnIndex("id_aluno")));
    		avaliacao.setAltura(c.getInt(c.getColumnIndex("altura")));
    		avaliacao.setPeso(c.getInt(c.getColumnIndex("peso")));
    		
    		list.add(avaliacao);
    	}
    	
    	c.close();
    	
    	return list;
	}

	public boolean insert(Avaliacao avaliacao) {
		
		long rowId = 0;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		String currentDate = sdf.format(new Date());
		
		ContentValues cv = new ContentValues();
        cv.put("id_aluno", avaliacao.getIdAluno());
        cv.put("altura", avaliacao.getAltura());
        cv.put("peso", avaliacao.getPeso());
        cv.put("data", currentDate);
        rowId = db.insert(tableName, null, cv);
		
        return (rowId > 0);
	}

	public ArrayList<Avaliacao> selectByAluno(int idAluno) {
		
		Cursor c = db.rawQuery("SELECT id, id_aluno, altura, peso, data FROM avaliacoes " +
				"WHERE id_aluno = ?", new String[] {String.valueOf(idAluno)});
    	
    	ArrayList<Avaliacao> list = new ArrayList<Avaliacao>();
    	
    	while (c.moveToNext()) {
    		
    		Avaliacao avaliacao = new Avaliacao();
    		avaliacao.setId(c.getInt(c.getColumnIndex("id")));
    		avaliacao.setIdAluno(c.getInt(c.getColumnIndex("id_aluno")));
    		avaliacao.setAltura(c.getDouble(c.getColumnIndex("altura")));
    		avaliacao.setPeso(c.getDouble(c.getColumnIndex("peso")));
    		avaliacao.setDate(c.getString(c.getColumnIndex("data")));
    		
    		list.add(avaliacao);
    	}
    	
    	c.close();
    	
    	return list;
	}

}
