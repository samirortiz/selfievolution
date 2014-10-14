package br.com.selfievolution.models;

import br.com.selfievolution.utils.DBHandler;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DobrasModel {
	private SQLiteDatabase db;
	public DobrasModel(Context context){
		db = DBHandler.getInstance(context).getWritableDatabase();
	}
	
	public void close(){
		db.close();
	}
}
