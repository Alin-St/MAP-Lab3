package model.statements;

import model.programState.ProgramState;
import model.exceptions.InterpreterException;
import model.exceptions.StatementExecutionException;
import model.expressions.IExpression;
import model.types.IType;
import model.types.StringType;
import model.utility.MyIDictionary;
import model.values.StringValue;

import java.io.BufferedReader;
import java.io.FileReader;

public class OpenFileReaderStatement implements IStatement {

    private final IExpression _filename;

    public OpenFileReaderStatement(IExpression filename) {
        _filename = filename;
    }

    @Override
    public String toString() {
        return "openRFile(" + _filename.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        var symbolTable = state.getSymbolTable();
        var fileTable = state.getFileTable();
        var fnv = _filename.evaluate(symbolTable, state.getHeapTable());

        if (!fnv.getType().equals(StringType.get()))
            throw new StatementExecutionException("Argument of an open file reader statement is not a string.");

        StringValue fnsv = (StringValue)fnv;
        if (fileTable.containsKey(fnsv))
            throw new StatementExecutionException("Already opened a file reader with the same name.");

        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(fnsv.getValue()));
        } catch (Exception e) {
            throw new StatementExecutionException("IOException: " + e.getMessage());
        }

        fileTable.put(fnsv, br);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new OpenFileReaderStatement(_filename.deepCopy());
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws InterpreterException {
        if (!_filename.typeCheck(typeEnv).equals(StringType.get()))
            throw new InterpreterException("Filename is not a string.");
        return typeEnv;
    }
}
