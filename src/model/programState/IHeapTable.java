package model.programState;

import model.exceptions.InterpreterException;
import model.utility.MyIDictionary;
import model.values.IValue;

public interface IHeapTable extends MyIDictionary<Integer, IValue> {
    @Override
    IHeapTable deepCopy() throws InterpreterException;

    int getFreeAddress();
}
