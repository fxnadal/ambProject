package com.fxapp.ambstudio;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
public class CrearInformeFragment extends Fragment {
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

        //RELLENAMOS LA FECHA
        textViewDia=(TextView) view.findViewById(R.id.textViewDia);
        textViewDia.setText(String.valueOf(dia));
        textViewMes=(TextView) view.findViewById(R.id.textViewMes);
        textViewMes.setText(String.valueOf(mes));
        textViewAnyo=(TextView) view.findViewById(R.id.textViewAnyo);
        textViewAnyo.setText(String.valueOf(anyo));

        return view;///*/

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
