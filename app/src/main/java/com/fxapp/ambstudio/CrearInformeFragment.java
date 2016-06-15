package com.fxapp.ambstudio;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CrearInformeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CrearInformeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrearInformeFragment extends Fragment implements View.OnClickListener{

    // TODO: CALENDARIO..SACAMOS LA FECHA DE HOY
    final Calendar calendar=Calendar.getInstance();
    int hora = calendar.get(calendar.HOUR_OF_DAY);
    int minutos = calendar.get(calendar.MINUTE);
    int dia = calendar.get(calendar.DAY_OF_MONTH);
    int mes = calendar.get(calendar.MONTH)+1;
    int anyo = calendar.get(calendar.YEAR);
    Date date = calendar.getTime();

    // TEXTVIEWS
    TextView textViewDia;
    TextView textViewMes;
    TextView textViewAnyo;
    EditText editTextTrabajador;
    EditText editTextDuracion;
    EditText editTextLocalizacion;
    TimePicker timePickerHoraInicio;
    TimePicker timePickerHoraFin;
    Button boton;


    private String textTrabajador;
    private String textFecha;
    private String textDuracion;
    private String textLocalizacion;
    private String textHoraInicio;
    private String textHoraFin;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CrearInformeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CrearInformeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CrearInformeFragment newInstance(String param1, String param2) {
        CrearInformeFragment fragment = new CrearInformeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_crear_informe, container, false);
        //ASIGNAMOS VALORES
        editTextDuracion=(EditText) view.findViewById(R.id.editTextDuracion);
        editTextTrabajador=(EditText) view.findViewById(R.id.editTextTrabajador);
        editTextLocalizacion=(EditText) view.findViewById(R.id.editTextLocalizacion2);
        timePickerHoraInicio=(TimePicker) view.findViewById(R.id.timePicker);
        timePickerHoraFin=(TimePicker) view.findViewById(R.id.timePicker2);

        //RELLENAMOS LA FECHA
        textViewDia=(TextView) view.findViewById(R.id.textViewDia);
        textViewDia.setText(String.valueOf(dia));
        textViewMes=(TextView) view.findViewById(R.id.textViewMes);
        textViewMes.setText(String.valueOf(mes));
        textViewAnyo=(TextView) view.findViewById(R.id.textViewAnyo);
        textViewAnyo.setText(String.valueOf(anyo));

        // Implementamos el listener del boton



        return view;///*/

    }
    public void onActivityCreated (Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        boton =(Button) getActivity().findViewById(R.id.botonGuardar);
        boton.setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void onClick(View v) {

        textTrabajador=editTextTrabajador.getText().toString();
        textFecha=dia+"/"+mes+"/"+anyo;
        textDuracion=editTextDuracion.getText().toString();
        textLocalizacion=editTextLocalizacion.getText().toString();
        textHoraInicio=timePickerHoraInicio.getCurrentHour().toString()+":"+timePickerHoraInicio.getCurrentMinute().toString();
        textHoraFin=timePickerHoraFin.getCurrentHour().toString()+":"+timePickerHoraFin.getCurrentMinute().toString();

        //Llamamos al callback
        //Este a su vez pasa el mensaje al activity
        mListener.onFragmentInteraction(textTrabajador, textFecha, textDuracion,
                textLocalizacion, textHoraInicio, textHoraFin);
        System.err.println(textTrabajador+" : "+ textFecha+" : "+ textDuracion+" : "+
                textLocalizacion+" : "+ textHoraInicio+" : "+ textHoraFin);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) activity;
        } else {
            throw new RuntimeException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * ESTE ES EL INTERFAZ QUE EL ACTIVITY SUPERIOR DEBE IMPLEMENTAR
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String trabajador, String fecha, String duracion,
                                   String localizacion, String horaInicio, String horaFin);


    }
}
