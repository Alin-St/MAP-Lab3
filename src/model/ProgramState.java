package model;

import model.exceptions.InterpreterException;
import model.statements.IStatement;
import model.utility.*;
import model.values.IValue;
import model.values.StringValue;

import java.io.BufferedReader;

public class ProgramState implements IDeepCopyable {

    private MyIStack<IStatement> _stack;
    private MyIDictionary<String, IValue> _symbolTable;
    private MyIList<IValue> _output;
    private MyIDictionary<StringValue, BufferedReader> _fileTable;

    public ProgramState(MyIStack<IStatement> stack, MyIDictionary<String, IValue> symbolTable, MyIList<IValue> output,
                        MyIDictionary<StringValue, BufferedReader> fileTable) {
        _stack = stack;
        _symbolTable = symbolTable;
        _output = output;
        _fileTable = fileTable;
    }

    public ProgramState(IStatement mainStatement) {
        this(new MyStack<>(IStatement.class, mainStatement),
                new MyDictionary<>(String.class, IValue.class),
                new MyList<>(IValue.class),
                new MyDictionary<>(StringValue.class, BufferedReader.class));
    }

    public MyIStack<IStatement> getExecutionStack() { return _stack; }
    public void setExecutionStack(MyIStack<IStatement> value) { _stack = value; }

    public MyIDictionary<String, IValue> getSymbolTable() { return _symbolTable; }
    public void setSymbolTable(MyIDictionary<String, IValue> value) { _symbolTable = value; }

    public MyIList<IValue> getOutputStructure() { return _output; }
    public void setOutputStructure(MyIList<IValue> value) { _output = value; }

    public MyIDictionary<StringValue, BufferedReader> getFileTable() { return _fileTable; }
    public void setFileTable(MyIDictionary<StringValue, BufferedReader> value) { _fileTable = value; }

    @Override
    public String toString() {
        return "Execution Stack:\n" +
                executionStackToString(_stack).indent(4) +
                "Symbol Table:\n" +
                symbolTableToString(_symbolTable).indent(4) +
                "Output:\n" +
                outputDataToString(_output).indent(4) +
                "File Table:\n" +
                fileTableToString(_fileTable).indent(4);
    }

    @Override
    public ProgramState deepCopy() throws InterpreterException {
        return new ProgramState(_stack.deepCopy(), _symbolTable.deepCopy(), _output.deepCopy(), _fileTable.deepCopy());
    }

    public static String executionStackToString(MyIStack<IStatement> stack) {
        try {
            var result = new StringBuilder();
            for (var statement : stack.toArrayList()) {
                if (!result.isEmpty())
                    result.append(" |\n");
                result.append(statement.toString());
            }
            if (result.isEmpty())
                return "Execution stack empty.";
            return result.toString();
        } catch (InterpreterException e) {
            e.printStackTrace();
            return "Unable to convert execution stack to String.";
        }
    }

    public static String symbolTableToString(MyIDictionary<String, IValue> table) {
        var result = new StringBuilder();
        for (var entry : table.toArrayList()) {
            if (!result.isEmpty())
                result.append("\n");
            result.append(entry.getKey()).append(" = ").append(entry.getValue().toString());
        }
        if (result.isEmpty())
            return "No Symbol declared.";
        return result.toString();
    }

    public static String outputDataToString(MyIList<IValue> output) {
        var result = new StringBuilder();
        for (var value : output.toArrayList()) {
            if (!result.isEmpty())
                result.append("\n");
            result.append("-> ").append(value.toString());
        }
        if (result.isEmpty())
            return "No output data.";
        return result.toString();
    }

    public static String fileTableToString(MyIDictionary<StringValue, BufferedReader> table) {
        var result = new StringBuilder();
        for (var entry : table.toArrayList()) {
            if (!result.isEmpty())
                result.append("\n");
            result.append(entry.getKey());
        }
        if (result.isEmpty())
            return "No file descriptor.";
        return result.toString();
    }
}
