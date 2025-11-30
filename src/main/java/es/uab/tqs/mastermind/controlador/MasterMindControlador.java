package es.uab.tqs.mastermind.controlador;

import es.uab.tqs.mastermind.model.MasterMindModel;
import es.uab.tqs.mastermind.vista.MasterMindVista;

public class MasterMindControlador {

    private MasterMindModel model;
    private MasterMindVista view;
    
    public MasterMindControlador(MasterMindModel model, MasterMindVista view)
    {
    	this.model = model;
    	this.view = view;
    }

    public void iniciarPartida() {

    }
}
