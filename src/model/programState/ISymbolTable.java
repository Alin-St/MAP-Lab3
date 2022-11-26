package model.programState;

import model.exceptions.InterpreterException;
import model.utility.MyIDictionary;
import model.values.IValue;

public interface ISymbolTable extends MyIDictionary<String, IValue> {
    @Override
    ISymbolTable deepCopy() throws InterpreterException;
}
