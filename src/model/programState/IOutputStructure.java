package model.programState;

import model.exceptions.InterpreterException;
import model.utility.MyIList;
import model.values.IValue;

public interface IOutputStructure extends MyIList<IValue> {
    @Override
    IOutputStructure deepCopy() throws InterpreterException;
}
