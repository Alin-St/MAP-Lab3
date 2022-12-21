package model.statements;

import model.programState.ProgramState;
import model.exceptions.InterpreterException;
import model.exceptions.StatementExecutionException;
import model.expressions.IExpression;
import model.types.IntType;
import model.types.StringType;
import model.values.IntValue;
import model.values.StringValue;

import java.io.IOException;

public class ReadFileStatement implements IStatement {

    private final IExpression _fileName;
    private final String _variableName;

    public ReadFileStatement(IExpression filename, String variableName) {
        _fileName = filename;
        _variableName = variableName;
    }

    @Override
    public String toString() {
        return "readFile(" + _fileName + ", " + _variableName + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        var symbolTable = state.getSymbolTable();
        var fileTable = state.getFileTable();

        if (!symbolTable.containsKey(_variableName))
            throw new StatementExecutionException("Variable not declared.");

        if (!symbolTable.get(_variableName).getType().equals(IntType.get()))
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

        symbolTable.put(_variableName, new IntValue(value));
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new ReadFileStatement(_fileName.deepCopy(), _variableName);
    }
}
