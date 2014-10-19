package br.com.selfievolution.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import br.com.selfievolution.R;
import br.com.selfievolution.controllers.RoleController;
import br.com.selfievolution.controllers.UsuarioController;
import br.com.selfievolution.models.RoleModel;
import br.com.selfievolution.models.UsuarioModel;

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
