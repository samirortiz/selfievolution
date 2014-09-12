package br.com.selfievolution.utils;

import java.io.File;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {
	
	private static DBHandler mInstance = null;

	private DBHandler(Context context) {
		
		super(context, Environment.getExternalStorageDirectory().getPath()+"/selfie.db", null, 1);
	}


	public static DBHandler getInstance(Context ctx) {
		if (mInstance == null) {
			mInstance = new DBHandler(ctx.getApplicationContext());
		}
		return mInstance;
	}	

	@Override
	public void onCreate(SQLiteDatabase db) {

		Log.d("DBHandler", "criando tabela de usuários...");

		StringBuilder strb1 = new StringBuilder();

		strb1.append(" CREATE TABLE IF NOT EXISTS usuarios (");
		strb1.append(" id integer primary key autoincrement,");
		strb1.append(" ds_nome varchar(100),");
		strb1.append(" ds_senha varchar(100),");
		strb1.append(" ds_email varchar(100))");

		db.execSQL(strb1.toString());        

		Log.d("DBHandler", "criando tabela de avaliacoes...");

		StringBuilder strb2 = new StringBuilder();

		strb2.append(" CREATE TABLE IF NOT EXISTS avaliacoes (");
		strb2.append(" id integer primary key autoincrement,");
		strb2.append(" dobras integer,");
		strb2.append(" idade integer,");
		strb2.append(" peso decimal(10,2))");
		
		db.execSQL(strb2.toString());        		
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int olderVersion, int newVersion){
		Log.d("DBHandler", "upgrade no banco... older: " + olderVersion + " -> new: " + newVersion);

		db.execSQL("");

	}

	public static boolean doesDatabaseExist(ContextWrapper context, String dbName) {
		File dbFile = context.getDatabasePath(dbName);
		return dbFile.exists();
	}

}
