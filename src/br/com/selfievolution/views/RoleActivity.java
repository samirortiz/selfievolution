package br.com.selfievolution.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import br.com.selfievolution.R;
import br.com.selfievolution.controllers.RoleController;
import br.com.selfievolution.models.RoleModel;

public class RoleActivity extends Activity{

	private RoleModel model;
	private RoleController controller;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_role);
		
		controller = new RoleController(model, this);		
		
	}
	
    
    public void continuar(View v){
    	controller.continuar();
    }   
}
