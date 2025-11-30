package es.uab.tqs.mastermind.vista;

public class MockMasterMindVista extends MasterMindVista {

    public MockMasterMindVista()
    {
        super();
    }
    
    @Override
    public int recullLongitud()
    {
        return 4;
    }

    @Override
    public int recullMaxIntents()
    {
        return 5;
    }
}
