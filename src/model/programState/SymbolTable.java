package model.programState;

import model.exceptions.InterpreterException;
import model.utility.MyDictionary;
import model.values.IValue;

public class SymbolTable extends MyDictionary<String, IValue> implements ISymbolTable {
    public SymbolTable() {
        super(String.class, IValue.class);
    }

    /** Deep copy constructor.
     */
    public SymbolTable(SymbolTable other) throws InterpreterException {
        super(other);
    }

    @Override
    public SymbolTable deepCopy() throws InterpreterException {
        return new SymbolTable(this);
    }
}
