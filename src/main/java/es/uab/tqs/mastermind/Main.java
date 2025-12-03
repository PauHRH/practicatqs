package es.uab.tqs.mastermind;

import es.uab.tqs.mastermind.vista.MasterMindVista;
import es.uab.tqs.mastermind.vista.RealMasterMindVista;
import es.uab.tqs.mastermind.model.AleatoriReal;
import es.uab.tqs.mastermind.model.Aleatori;
import es.uab.tqs.mastermind.model.MasterMindModel;
import es.uab.tqs.mastermind.controlador.MasterMindControlador;

public class Main {
    public static void main(String[] args) {
        Aleatori alt = new AleatoriReal();
        MasterMindVista vista = new RealMasterMindVista();
        MasterMindModel model = new MasterMindModel(alt);
        MasterMindControlador controlador = new MasterMindControlador(model, vista);

        controlador.iniciarPartida();
    }
}