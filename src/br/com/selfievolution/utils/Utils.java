package br.com.selfievolution.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.ParseException;

public class Utils {

	public static boolean verificaConexao(Activity activity) {  
		boolean conectado;  
		ConnectivityManager conectivtyManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);  
		
		if (conectivtyManager.getActiveNetworkInfo() != null && conectivtyManager.getActiveNetworkInfo().isAvailable() && conectivtyManager.getActiveNetworkInfo().isConnected()) {  
			conectado = true;  
		} else {  
			conectado = false;  
		}
		
		return conectado;  
	}
	
	@SuppressLint("SimpleDateFormat")
	public static String formatDateTime(Context context, String timeToFormat) {

	    String finalDateTime = "";          

	    SimpleDateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	    Date date = null;
	    if (timeToFormat != null) {
	        try {
	            try {
					date = (Date) iso8601Format.parse(timeToFormat);
				} catch (java.text.ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        } catch (ParseException e) {
	            date = null;
	        }

	        if (date != null) {
	            long when = date.getTime();
	            int flags = 0;
	            flags |= android.text.format.DateUtils.FORMAT_SHOW_TIME;
	            flags |= android.text.format.DateUtils.FORMAT_SHOW_DATE;
	            flags |= android.text.format.DateUtils.FORMAT_ABBREV_MONTH;
	            flags |= android.text.format.DateUtils.FORMAT_SHOW_YEAR;

	            finalDateTime = android.text.format.DateUtils.formatDateTime(context,
	            when + TimeZone.getDefault().getOffset(when), flags);               
	        }
	    }
	    return finalDateTime;
	}	
	
	public static String decimalToDMS(double coord) {
	    String output, degrees, minutes, seconds;

	    double mod = coord % 1;
	    int intPart = (int)coord;

	    degrees = String.valueOf(intPart);

	    coord = mod * 60;
	    mod = coord % 1;
	    intPart = (int)coord;

	    minutes = String.valueOf(intPart);

	    coord = mod * 60;
	    intPart = (int)coord;

	    seconds = String.valueOf(intPart);

	    output = degrees + minutes + seconds;

	    return output;
	}	
	
}
