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
		
		super(context, "selfie.db", null, 1);
	}


	public static DBHandler getInstance(Context ctx) {		
		if (mInstance == null) {
		
			mInstance = new DBHandler(ctx.getApplicationContext());
		
		}
		
		return mInstance;
	}	

	@Override
	public void onCreate(SQLiteDatabase db) {

		
		Log.d("DBHandler", "criando tabela de roles...");

		StringBuilder str = new StringBuilder();

		str.append(" CREATE TABLE IF NOT EXISTS roles (");
		str.append(" id integer primary key autoincrement,");
		str.append(" role varchar(10));");		
		
		str.append(" INSERTO INTO roles (id, role) VALUES (1, 'Academia');");
		str.append(" INSERTO INTO roles (id, role) VALUES (2, 'Aluno');");
		str.append(" INSERTO INTO roles (id, role) VALUES (3, 'Professor');");
		db.execSQL(str.toString());        
		
		Log.d("DBHandler", "criando tabela de usuários...");

		StringBuilder strb1 = new StringBuilder();

		strb1.append(" CREATE TABLE IF NOT EXISTS alunos (");
		strb1.append(" id integer primary key autoincrement,");
		strb1.append(" nome varchar(100),");
		strb1.append(" email varchar(100),");
		strb1.append(" senha varchar(100),");
		strb1.append(" sexo varchar(100),");
		strb1.append(" data_nascimento date,");
		strb1.append(" email_treinador varchar(100),");
		strb1.append(" sync integer default 0)");

		db.execSQL(strb1.toString());        	
		
		StringBuilder strb10 = new StringBuilder();

		strb10.append(" CREATE TABLE IF NOT EXISTS treinadores (");
		strb10.append(" id integer primary key autoincrement,");
		strb10.append(" nome varchar(100),");
		strb10.append(" email varchar(100),");
		strb10.append(" senha varchar(100),");
		strb10.append(" sexo varchar(100),");
		strb10.append(" data_nascimento date,");
		strb10.append(" sync integer default 0)");

		db.execSQL(strb10.toString());		
		
		Log.d("DBHandler", "criando tabela de avaliacoes...");

		StringBuilder strb2 = new StringBuilder();

		strb2.append(" CREATE TABLE IF NOT EXISTS avaliacoes (");
		strb2.append(" id integer primary key autoincrement,");
		strb2.append(" id_aluno integer,");
		strb2.append(" data date,");
		strb2.append(" peso decimal(10,2),");
		strb2.append(" altura decimal(10,2),");
		strb2.append(" triceps decimal(10,2),");
		strb2.append(" peito decimal(10,2),");
		strb2.append(" subaxilar decimal(10,2),");
		strb2.append(" suescapular decimal(10,2),");
		strb2.append(" abdominal decimal(10,2),");
		strb2.append(" suprailiaca decimal(10,2),");
		strb2.append(" coxa decimal(10,2),");
		strb2.append(" imc decimal(10,2),");
		strb2.append(" gordura decimal(10,2))");
		
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
