package br.com.selfievolution.models;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.com.selfievolution.objects.Avaliacao;
import br.com.selfievolution.objects.Usuario;
import br.com.selfievolution.utils.DBHandler;

public class AvaliacaoModel {
	private SQLiteDatabase db;
	private String tableName = "avaliacoes";
	private String[] columns = new String[] { "id", "dobras", "idade", "peso" };
	
	public AvaliacaoModel(Context ctx) {
		db = DBHandler.getInstance(ctx).getWritableDatabase();
	}	
	
	public void close() {
		db.close();
	}

	public ContentValues selectAvaliacao(String id){

		Cursor c = db.rawQuery("SELECT id, dobras, idade, peso FROM avaliacoes " +
								"WHERE id = ?", new String[] {id});
		
		if(c.moveToFirst()){
			ContentValues cv = new ContentValues();
			cv.put("id", c.getInt(0));
			cv.put("dobras", c.getInt(1));
			cv.put("idade", c.getInt(2));
			cv.put("peso", c.getInt(3));

			return cv;
			
		}else{

			return null;
		}
	}	
	
	
	public ArrayList<Avaliacao> selectAll() {
		
    	Cursor c = db.query(tableName, columns, null, null, null, null, null);
    	
    	ArrayList<Avaliacao> list = new ArrayList<Avaliacao>();
    	
    	while (c.moveToNext()) {
    		
    		Avaliacao avaliacao = new Avaliacao();
    		avaliacao.setId(c.getInt(c.getColumnIndex("id")));
    		avaliacao.setDobras(c.getInt(c.getColumnIndex("dobras")));
    		avaliacao.setIdade(c.getInt(c.getColumnIndex("idade")));
    		avaliacao.setPeso(c.getInt(c.getColumnIndex("peso")));
    		
    		list.add(avaliacao);
    	}
    	
    	return list;
	}

	public boolean insert(Avaliacao avaliacao) {
		
		long rowId = 0;
		
		ContentValues cv = new ContentValues();
        cv.put("dobras", avaliacao.getDobras());
        cv.put("idade", avaliacao.getIdade());
        cv.put("peso", avaliacao.getPeso());
        rowId = db.insert(tableName, null, cv);
		
        return (rowId > 0);
	}

}
