package br.com.selfievolution.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import br.com.selfievolution.R;


public class NoConnectionActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        //seto o layout
        setContentView(R.layout.no_conection);     

	}
    

    public void Login(View v){
    	Intent i = new Intent(getBaseContext(), IndexActivity.class);
    	startActivity(i);
    }
}
