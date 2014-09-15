package br.com.selfievolution.controllers;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.EditText;
import android.widget.RadioGroup;
import br.com.selfievolution.R;
import br.com.selfievolution.models.UsuarioModel;
import br.com.selfievolution.objects.Usuario;
import br.com.selfievolution.views.HomeActivity;
import br.com.selfievolution.views.UsuarioActivity;

public class UsuarioController{
    
	private UsuarioActivity activity;
    private UsuarioModel model;
    
    public UsuarioController(UsuarioModel model, UsuarioActivity activity){
        this.activity = activity;
        this.model = model;
    }
 
    public UsuarioActivity getActivity() {
        return activity;
    }
 
    public UsuarioModel getModel() {
        return model;
    }    
    
    public void salvarUsuario(){
    	
    	Usuario usuario = new Usuario();
    	model = new UsuarioModel(activity);
    	
    	EditText nome = (EditText) activity.findViewById(R.id.nomeUsuario);
    	RadioGroup sexo = (RadioGroup) activity.findViewById(R.id.radioGroupSexo);
    	EditText email = (EditText) activity.findViewById(R.id.emailUsuario);
    	EditText senha = (EditText) activity.findViewById(R.id.senhaUsuario);
    	
    	usuario.setNome(nome.getText().toString());
    	usuario.setEmail(email.getText().toString());
    	usuario.setSenha(senha.getText().toString());
    	
		switch (sexo.getCheckedRadioButtonId()) {
		
	    case R.id.radioM:
    		usuario.setSexo("male");
	        break;
	    case R.id.radioF:
	    	usuario.setSexo("male");
	        break;
	}    	
    	
    	if(model.insert(usuario)){
    		
    		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setTitle("Sucesso!");
			builder.setMessage("Usuário cadastrado com sucesso!");
			builder.setPositiveButton("Entrar", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					Intent i = new Intent(activity.getApplicationContext(), HomeActivity.class);
					activity.startActivity(i);
					
				}
			});
			
			builder.create().show();

			//salvo os dados na session
        	SharedPreferences pref = activity.getApplicationContext().getSharedPreferences("SelfieSession", 0); // 0 - for private mode
        	Editor editor = pref.edit();
        	editor.putBoolean("logado", true);
        	editor.putString("nome", nome.getText().toString());
        	editor.commit();			
			
    	}else{
    		
    		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setTitle("Erro!");
			builder.setMessage("Email já está sendo utilizado!!");
			
			builder.create().show();
    	}
    }
    
}
