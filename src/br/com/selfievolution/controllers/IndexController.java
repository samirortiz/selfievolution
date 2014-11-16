package br.com.selfievolution.controllers;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;
import android.content.SharedPreferences;
import br.com.selfievolution.models.IndexModel;
import br.com.selfievolution.views.HomeActivity;
import br.com.selfievolution.views.IndexActivity;

public class IndexController{
    
	private IndexActivity activity;
    private IndexModel model;
  
    
    public IndexController(IndexModel model, IndexActivity activity){
        this.activity = activity;
        this.model = model;

    }
 
    public IndexActivity getActivity() {
        return activity;
    }
 
    public IndexModel getModel() {
        return model;
    }    
    
    public void startApp(){
    	//testa a sessao - revisar
    	
    	SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("SelfieSession", 0); 
System.out.println(pref.getBoolean("logado", false));
    	if(pref.getBoolean("logado", false)){

    		Intent i = new Intent(getActivity(), HomeActivity.class);
    		activity.startActivity(i);

    	}
    }
    
}
