package model.programState;

import model.exceptions.InterpreterException;
import model.utility.MyList;
import model.values.IValue;

public class OutputStructure extends MyList<IValue> implements IOutputStructure {
    public OutputStructure() {
        super(IValue.class);
    }

    /** Deep copy constructor.
     */
    public OutputStructure(OutputStructure other) throws InterpreterException {
        super(other);
    }

    @Override
    public OutputStructure deepCopy() throws InterpreterException {
        return new OutputStructure(this);
    }
}
