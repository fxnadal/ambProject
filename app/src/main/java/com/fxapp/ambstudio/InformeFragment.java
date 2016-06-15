package com.fxapp.ambstudio;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class InformeFragment extends Fragment {

    static final String ARG_TRABAJADOR = "informeTrabajador";
    static final String ARG_NUM_OBRA = "informeNumObra";
    static final String ARG_FECHA_OBRA = "informeFecha";
    static final String ARG_DURACION = "informeDuracion";
    static final String ARG_LOCALIZACION = "informeLocalizacion";
    static final String ARG_HORA_INICIO = "informeHoraInicio";
    static final String ARG_HORA_FIN = "informeHoraFin";


    private String textTrabajador;
    private String textNumObra;
    private String textFecha;
    private String textDuracion;
    private String textLocalizacion;
    private String textHoraInicio;
    private String textHoraFin;

    TextView tViewTrabajador;
    TextView tViewFecha;
    TextView tViewDuracion;
    TextView tViewNumObra;
    TextView tViewLocalizacion;
    TextView tViewHoraInicio;
    TextView tViewHoraFin;


    public static InformeFragment newInstance(String trabajador, String numObra, String fechaObra, String duracion, String localizaacion, String horaInicio, String horaFin) {
        InformeFragment fragment = new InformeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TRABAJADOR, trabajador);
        args.putString(ARG_NUM_OBRA, numObra);
        args.putString(ARG_FECHA_OBRA, fechaObra);
        args.putString(ARG_DURACION, duracion);
        args.putString(ARG_LOCALIZACION, localizaacion);
        args.putString(ARG_HORA_INICIO, horaInicio);
        args.putString(ARG_HORA_FIN, horaFin);
        fragment.setArguments(args);
        return fragment;
    }

    //////////////////////////////////
    //////////////////////////////////
    //////////////////////////////////

    public InformeFragment() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            textTrabajador= getArguments().getString(ARG_TRABAJADOR);
            textNumObra= getArguments().getString(ARG_NUM_OBRA);
            textFecha= getArguments().getString(ARG_FECHA_OBRA);
            textDuracion= getArguments().getString(ARG_DURACION);
            textLocalizacion= getArguments().getString(ARG_LOCALIZACION);
            textHoraInicio= getArguments().getString(ARG_HORA_INICIO);
            textHoraFin= getArguments().getString(ARG_HORA_FIN);

        }
        System.out.println(textNumObra+" - "+textTrabajador+" - "+textFecha+" - "+textDuracion+" - "+textLocalizacion+" - "+textHoraInicio+" - "+textHoraFin);
    }

    @Override/**/
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_informe, container, false);







        System.out.println("ONCREATEVIEW: "+textNumObra+" - "+textTrabajador+" - "+textFecha+" - "+textDuracion+" - "+textLocalizacion+" - "+textHoraInicio+" - "+textHoraFin);
        return v;
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tViewTrabajador = (TextView) getActivity().findViewById(R.id.tVInformeTrabajador);
        tViewFecha = (TextView) getActivity().findViewById(R.id.tVInformeFechaObra);
        tViewDuracion = (TextView) getActivity().findViewById(R.id.tVInformeDuracion);
        tViewNumObra = (TextView) getActivity().findViewById(R.id.tVInformeNumObra);
        tViewLocalizacion = (TextView) getActivity().findViewById(R.id.tVInformeLocalizacion);
        tViewHoraInicio = (TextView) getActivity().findViewById(R.id.tVInformeHoraInicio);
        tViewHoraFin = (TextView) getActivity().findViewById(R.id.tVInformeHoraFin);

        tViewTrabajador.setText(textTrabajador);
        tViewFecha.setText(textFecha);
        tViewDuracion.setText(textDuracion);
        tViewNumObra.setText(textNumObra);
        tViewLocalizacion.setText(textLocalizacion);
        tViewHoraInicio.setText(textHoraInicio);
        tViewHoraFin.setText(textHoraFin);


    }
    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
