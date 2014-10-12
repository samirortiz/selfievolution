package br.com.selfievolution.controllers;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import br.com.selfievolution.utils.UnixCrypt;
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
    	
    	if(!checkEmail(email.getText().toString())){
    		
    		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
			builder.setTitle("Erro!");
			builder.setMessage("O formato de email é inválido");
			builder.create().show();

    	} else{

	    	usuario.setNome(nome.getText().toString());
	    	usuario.setEmail(email.getText().toString());
	    	
	    	//encriptação da senha
	    	String crypt = UnixCrypt.crypt(email.getText().toString(),senha.getText().toString());
	    	usuario.setSenha(crypt);

			switch (sexo.getCheckedRadioButtonId()) {

			    case R.id.radioM:
		    		usuario.setSexo("male");
			        break;
			    case R.id.radioF:
			    	usuario.setSexo("female");
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
	        	
	        	Intent i = new Intent(getActivity(), HomeActivity.class);
	        	getActivity().startActivity(i);
	        	
	        	getActivity().finish();
				
	    	}else{
	    		
	    		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
				builder.setTitle("Erro!");
				builder.setMessage("Email já está sendo utilizado!!");
				
				builder.create().show();
	    	}
    	}
    }

    
    public boolean checkEmail(String email){
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }
    
}
