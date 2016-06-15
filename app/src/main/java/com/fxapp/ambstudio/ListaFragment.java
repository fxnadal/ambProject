package com.fxapp.ambstudio;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;



public class ListaFragment extends Fragment {

    static final String ARRAYLIST="linta_informes";




    MyDBInformesAdapter dbAdapter;
    private ListView listView;
    private ArrayList listaInformes = new ArrayList();

    ListFragmentListener mCallback;

    public interface ListFragmentListener{
        public void onListSelected(int position);
    }


    public ListaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Llenar Arraylist para llenar el ListView posteriormente en el onCreateView
        if (getArguments() != null) {
            listaInformes= getArguments().getStringArrayList(ARRAYLIST);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_lista, container, false);


        return v;
    }



    public void onActivityCreated (Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,listaInformes);
        listView=(ListView)getView().findViewById(R.id.listView);
        listView.setOnItemClickListener(new nuestroListener());
        listView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (ListFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement ListFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    //Implementando el listener para nuestro listView
    //INNER CLASS
    //le pasamos los datos al activity
    private class nuestroListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            //String de la posicion clickada

            //Paso de informacion
            mCallback.onListSelected(position);
        }
    }

}
