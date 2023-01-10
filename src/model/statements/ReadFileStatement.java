package model.statements;

import model.programState.ProgramState;
import model.exceptions.InterpreterException;
import model.exceptions.StatementExecutionException;
import model.expressions.IExpression;
import model.types.IType;
import model.types.IntType;
import model.types.StringType;
import model.utility.MyIDictionary;
import model.values.IntValue;
import model.values.StringValue;

import java.io.IOException;

public class ReadFileStatement implements IStatement {

    private final IExpression _fileName;
    private final String _identifier;

    public ReadFileStatement(IExpression filename, String identifier) {
        _fileName = filename;
        _identifier = identifier;
    }

    @Override
    public String toString() {
        return "readFile(" + _fileName + ", " + _identifier + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        var symbolTable = state.getSymbolTable();
        var fileTable = state.getFileTable();

        if (!symbolTable.containsKey(_identifier))
            throw new StatementExecutionException("Variable not declared.");

        if (!symbolTable.get(_identifier).getType().equals(IntType.get()))
            throw new StatementExecutionException("Variable type is not an integer.");

        var fnv = _fileName.evaluate(symbolTable, state.getHeapTable());

        if (!fnv.getType().equals(StringType.get()))
            throw new StatementExecutionException("File name expression is not a string.");

        var fnsv = (StringValue)fnv;

        if (!fileTable.containsKey(fnsv))
            throw new StatementExecutionException("File not open.");

        var br = fileTable.get(fnsv);
        String line;

        try {
            line = br.readLine();
        } catch (IOException e) {
            throw new StatementExecutionException("IOException: " + e.getMessage());
        }

        int value = 0;

        if (line != null && !line.equals("")) {
            try {
                value = Integer.parseInt(line);
            }
            catch (Exception e) {
                throw new StatementExecutionException("Integer parse exception: " + e.getMessage());
            }
        }

        symbolTable.put(_identifier, new IntValue(value));
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new ReadFileStatement(_fileName.deepCopy(), _identifier);
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws InterpreterException {
        if (!_fileName.typeCheck(typeEnv).equals(StringType.get()))
            throw new InterpreterException("Filename is not a string.");
        if (!typeEnv.get(_identifier).equals(IntType.get()))
            throw new InterpreterException("Identifier must be an integer.");
        return typeEnv;
    }
}
