package model.programState;

import model.exceptions.InterpreterException;
import model.utility.MyDictionary;
import model.values.StringValue;

import java.io.BufferedReader;

public class FileTable extends MyDictionary<StringValue, BufferedReader> implements IFileTable {
    public FileTable() {
        super(StringValue.class, BufferedReader.class);
    }

    /** Deep copy constructor.
     */
    public FileTable(FileTable other) throws InterpreterException {
        super(other);
    }

    @Override
    public FileTable deepCopy() throws InterpreterException {
        return new FileTable(this);
    }
}
