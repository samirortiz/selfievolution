package br.com.selfievolution.views;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.selfievolution.R;
import br.com.selfievolution.models.AvaliacaoModel;

public class AdapterAlunosListView extends BaseAdapter
{
    private ArrayList<AlunoListView> itens;
 
    public AdapterAlunosListView(Context context, ArrayList<AlunoListView> itens)
    {
        //Itens que preencherão o listview
        this.itens = itens;

        LayoutInflater.from(context);
    }
 
    /**
     * Retorna a quantidade de itens
     *
     * @return
     */
    public int getCount()
    {
        return itens.size();
    }
 
    /**
     * Retorna o item de acordo com a posicao dele na tela.
     *
     * @param position
     * @return
     */
    public AlunoListView getItem(int position)
    {
        return itens.get(position);
    }
 
    /**
     * Sem implementação
     *
     * @param position
     * @return
     */
    public long getItemId(int position)
    {
        return position;
    }
 
    public View getView(int position, View convertView, ViewGroup parent){
        //Pega o item de acordo com a posção.
    	AlunoListView item = itens.get(position);
        //infla o layout para podermos preencher os dados
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.aluno_item_list, null);
        }        

        //atraves do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.

        ((TextView) convertView.findViewById(R.id.idAluno)).setText(String.valueOf(item.getId()));
        ((TextView) convertView.findViewById(R.id.nomeAluno)).setText(String.valueOf(item.getNome()));
        
        AvaliacaoModel model = new AvaliacaoModel(convertView.getContext());
        int count = model.countAvaliacoes(item.getId());
        
        ((TextView) convertView.findViewById(R.id.semAvaliacoes)).setText((count > 0 ? String.valueOf("Avaliações: "+count) : "Sem avaliações" ));
        
        if ( position % 2 == 0){
        	convertView.setBackgroundResource(R.drawable.listview_selector_even);
        }else{
	        convertView.setBackgroundResource(R.drawable.listview_selector_odd);
        }
        
        convertView.setBackgroundResource(R.drawable.rounded_corners);        
        
        return convertView;
    }
}
