package model.programState;

import model.exceptions.InterpreterException;
import model.utility.MyIDictionary;
import model.values.StringValue;

import java.io.BufferedReader;

public interface IFileTable extends MyIDictionary<StringValue, BufferedReader> {
    @Override
    IFileTable deepCopy() throws InterpreterException;
}
