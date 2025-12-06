/***************************************************************
 * ABSTRACT FACTORY PATTERN IMPLEMENTATION
 * 
 * Purpose: Creates families of related objects without specifying
 *          their concrete classes. Ensures compatibility between
 *          MDA-EFSM, DataStore, and OutputProcessor components.
 * 
 * Responsibilities:
 * 1. Declare interface for creating all product families
 * 2. Let subclasses decide which concrete classes to instantiate
 * 3. Ensure created objects work together correctly
 ***************************************************************/
public abstract class AbstractFactory {
    public abstract MDA_EFSM createMDA_EFSM();
    public abstract DataStore createDataStore();
    public abstract OP createOutputProcessor();
}

class GP1Factory extends AbstractFactory {
    @Override
    public MDA_EFSM createMDA_EFSM() {
        return new MDA_EFSM();
    }
    
    @Override
    public DataStore createDataStore() {
        return new DS1();
    }
    
    @Override
    public OP createOutputProcessor() {
        return new OP1();
    }
}

class GP2Factory extends AbstractFactory {
    @Override
    public MDA_EFSM createMDA_EFSM() {
        return new MDA_EFSM();
    }
    
    @Override
    public DataStore createDataStore() {
        return new DS2();
    }
    
    @Override
    public OP createOutputProcessor() {
        return new OP2();
    }
}