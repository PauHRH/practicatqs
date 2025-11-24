package es.uab.tqs.mastermind;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import es.uab.tqs.mastermind.model.Aleatori;
import es.uab.tqs.mastermind.model.CodiSecret;
import es.uab.tqs.mastermind.model.MasterMindModel;
import es.uab.tqs.mastermind.model.MockAleatori;
import es.uab.tqs.mastermind.model.MockAleatoriConfiguracio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMasterMindControlador {
    
    MasterMindModel model;
    MasterMindVista vista;

    @BeforeEach
	void setUp() {
        vista = new MasterMindMockVista();
	}

    @Test
    void testIniciarPartida()
    {
        
    }
}
