package br.com.selfievolution.controllers;


import br.com.selfievolution.R;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.RadioGroup;
import br.com.selfievolution.models.RoleModel;
import br.com.selfievolution.views.RoleActivity;
import br.com.selfievolution.views.UsuarioActivity;

public class RoleController{
    
	private RoleActivity activity;
    private RoleModel model;
    
    public RoleController(RoleModel model, RoleActivity activity){
        this.activity = activity;
        this.model = model;
    }
 
    public RoleActivity getActivity() {
        return activity;
    }
 
    public RoleModel getModel() {
        return model;
    }    
    
    public void continuar(){
    	
    	SharedPreferences pref = getActivity().getApplicationContext().getSharedPreferences("SelfieSession", 0); // 0 - for private mode    	
    	Editor editor = pref.edit();
    	
    	RadioGroup role = (RadioGroup) getActivity().findViewById(R.id.role);

    	switch (role.getCheckedRadioButtonId()) {
		case R.id.academia:
	
			break;

		case R.id.aluno:
			break;
			
		case R.id.professor:
	    	editor.putInt("id_role", 3);			
			break;
		}

    	editor.commit();    	
    	
    	Intent i = new Intent(getActivity(), UsuarioActivity.class);
    	getActivity().startActivity(i);
    	
    }

}
