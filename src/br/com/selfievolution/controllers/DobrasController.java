package br.com.selfievolution.controllers;

import android.widget.EditText;
import br.com.selfievolution.objects.Dobras;
import br.com.selfievolution.R;
import br.com.selfievolution.models.DobrasModel;
import br.com.selfievolution.views.DobrasActivity;

public class DobrasController {
	private DobrasActivity activity;
	private DobrasModel model;
	Dobras dobras = new Dobras();

	public DobrasController(DobrasModel model, DobrasActivity activity) {
		this.model = model;
		this.activity = activity;
	}

	public DobrasActivity getActivity() {
		return activity;
	}

	public DobrasModel getModel() {
		return model;
	}

	public void salvar() {
		model = new DobrasModel(activity);
	}

	public float calcularSubescapular() {
		EditText sub1 = (EditText) activity
				.findViewById(R.id.editSubescapular1);
		EditText sub2 = (EditText) activity
				.findViewById(R.id.editSubescapular2);
		EditText sub3 = (EditText) activity
				.findViewById(R.id.editSubescapular3);

		float subescapular1 = Float.parseFloat(sub1.getText().toString());
		float subescapular2 = Float.parseFloat(sub2.getText().toString());
		float subescapular3 = Float.parseFloat(sub3.getText().toString());

		return (subescapular1 + subescapular2 + subescapular3) / 3;
	}

	public float calcularTricepes() {
		EditText tri1 = (EditText) activity.findViewById(R.id.editTriceps1);
		EditText tri2 = (EditText) activity.findViewById(R.id.editTriceps2);
		EditText tri3 = (EditText) activity.findViewById(R.id.editTriceps3);

		float triceps1 = Float.parseFloat(tri1.getText().toString());
		float triceps2 = Float.parseFloat(tri2.getText().toString());
		float triceps3 = Float.parseFloat(tri3.getText().toString());

		return (triceps1 + triceps2 + triceps3) / 3;
	}

	public float calcularPeitoral() {
		EditText pei1 = (EditText) activity.findViewById(R.id.editPeitoral1);
		EditText pei2 = (EditText) activity.findViewById(R.id.editPeitoral2);
		EditText pei3 = (EditText) activity.findViewById(R.id.editPeitoral3);

		float peitoral1 = Float.parseFloat(pei1.getText().toString());
		float peitoral2 = Float.parseFloat(pei2.getText().toString());
		float peitoral3 = Float.parseFloat(pei3.getText().toString());

		return (peitoral1 + peitoral2 + peitoral3) / 3;
	}

	public float calcularAxilarMedia() {
		EditText axi1 = (EditText) activity.findViewById(R.id.editAxilar1);
		EditText axi2 = (EditText) activity.findViewById(R.id.editAxilar2);
		EditText axi3 = (EditText) activity.findViewById(R.id.editAxilar3);

		float axilar1 = Float.parseFloat(axi1.getText().toString());
		float axilar2 = Float.parseFloat(axi2.getText().toString());
		float axilar3 = Float.parseFloat(axi3.getText().toString());

		return (axilar1 + axilar2 + axilar3) / 3;
	}

	public float calcularSupraIliaca() {
		EditText sup1 = (EditText) activity.findViewById(R.id.editSupraIliaca1);
		EditText sup2 = (EditText) activity.findViewById(R.id.editSupraIliaca2);
		EditText sup3 = (EditText) activity.findViewById(R.id.editSupraIliaca3);

		float supra1 = Float.parseFloat(sup1.getText().toString());
		float supra2 = Float.parseFloat(sup2.getText().toString());
		float supra3 = Float.parseFloat(sup3.getText().toString());

		return (supra1 + supra2 + supra3) / 3;
	}

	public float calcularAbdominal() {
		EditText abd1 = (EditText) activity.findViewById(R.id.editAbdominal1);
		EditText abd2 = (EditText) activity.findViewById(R.id.editAbdominal2);
		EditText abd3 = (EditText) activity.findViewById(R.id.editAbdominal3);

		float abdominal1 = Float.parseFloat(abd1.getText().toString());
		float abdominal2 = Float.parseFloat(abd2.getText().toString());
		float abdominal3 = Float.parseFloat(abd3.getText().toString());

		return (abdominal1 + abdominal2 + abdominal3) / 3;
	}

	public float calcularFemuralMedio() {
		EditText fem1 = (EditText) activity.findViewById(R.id.editFemural1);
		EditText fem2 = (EditText) activity.findViewById(R.id.editFemural2);
		EditText fem3 = (EditText) activity.findViewById(R.id.editFemural3);

		float femural1 = Float.parseFloat(fem1.getText().toString());
		float femural2 = Float.parseFloat(fem2.getText().toString());
		float femural3 = Float.parseFloat(fem3.getText().toString());

		return (femural1 + femural2 + femural3) / 3;
	}

	public float densidadeCorporal() {
		int idade = 0; // tem que ser a do usuário logado
		float soma = somaDobras();
		float resultado = 0;
		if (isMale()) {
			resultado = (float) (1.11200000 - (0.00043499 * soma + 0.00000055 * Math.pow(soma, 2)) - (0.0002882 * (idade)));
		} else {
			resultado = (float) (1.0970 - (0.00046971 * soma + 0.00000056 * Math.pow(soma, 2)) - (0.00012828 * (idade)));
		}
		return resultado;
	}

	public float somaDobras() {
		return calcularAbdominal() + calcularAxilarMedia()
				+ calcularFemuralMedio() + calcularPeitoral()
				+ calcularSubescapular() + calcularSupraIliaca()
				+ calcularTricepes();
	}

	/**
	 * tem que fazer as funcionalidades desse método, calculo para homem e
	 * mulher é diferente
	 */
	public boolean isMale() {
		return true;
	}

	public float percentualGordura() {
		float dc = densidadeCorporal();
		return (float) ((4.95 / dc) - 4.50) * 100;
	}
	
	public float imc(){
		float peso = 0;
		float altura = 0;
		
		return peso / (altura * altura);
	}
}
