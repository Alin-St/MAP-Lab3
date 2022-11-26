package model.programState;

import model.exceptions.InterpreterException;
import model.statements.IStatement;
import model.utility.IDeepCopyable;

public class ProgramState implements IDeepCopyable {

    private IExecutionStack _executionStack;
    private ISymbolTable _symbolTable;
    private IOutputStructure _outputStructure;
    private IFileTable _fileTable;
    private IHeapTable _heapTable;

    public ProgramState(IExecutionStack executionStack, ISymbolTable symbolTable, IOutputStructure outputStructure,
                        IFileTable fileTable, IHeapTable heapTable) {
        _executionStack = executionStack;
        _symbolTable = symbolTable;
        _outputStructure = outputStructure;
        _fileTable = fileTable;
        _heapTable = heapTable;
    }

    public ProgramState(IStatement mainStatement) {
        this(new ExecutionStack(mainStatement), new SymbolTable(), new OutputStructure(), new FileTable(),
                new HeapTable());
    }

    public IExecutionStack getExecutionStack() { return _executionStack; }
    public void setExecutionStack(IExecutionStack value) { _executionStack = value; }

    public ISymbolTable getSymbolTable() { return _symbolTable; }
    public void setSymbolTable(ISymbolTable value) { _symbolTable = value; }

    public IOutputStructure getOutputStructure() { return _outputStructure; }
    public void setOutputStructure(IOutputStructure value) { _outputStructure = value; }

    public IFileTable getFileTable() { return _fileTable; }
    public void setFileTable(IFileTable value) { _fileTable = value; }

    public IHeapTable getHeapTable() { return _heapTable; }
    public void setHeapTable(IHeapTable value) { _heapTable = value; }

    @Override
    public String toString() {
        return "Execution Stack:\n" +
                executionStackToString(_executionStack).indent(4) +
                "Symbol Table:\n" +
                symbolTableToString(_symbolTable).indent(4) +
                "Output:\n" +
                outputDataToString(_outputStructure).indent(4) +
                "File Table:\n" +
                fileTableToString(_fileTable).indent(4) +
                "Heap Table:\n" +
                heapTableToString(_heapTable).indent(4);
    }

    @Override
    public ProgramState deepCopy() throws InterpreterException {
        return new ProgramState(_executionStack.deepCopy(), _symbolTable.deepCopy(), _outputStructure.deepCopy(),
                _fileTable.deepCopy(), _heapTable.deepCopy());
    }

    public static String executionStackToString(IExecutionStack stack) {
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

    public static String symbolTableToString(ISymbolTable table) {
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

    public static String outputDataToString(IOutputStructure output) {
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

    public static String fileTableToString(IFileTable fileTable) {
        var result = new StringBuilder();
        for (var entry : fileTable.toArrayList()) {
            if (!result.isEmpty())
                result.append("\n");
            result.append(entry.getKey());
        }
        if (result.isEmpty())
            return "No file descriptor.";
        return result.toString();
    }

    public static String heapTableToString(IHeapTable heapTable) {
        var result = new StringBuilder();
        for (var entry : heapTable.toArrayList()) {
            if (!result.isEmpty())
                result.append("\n");
            result.append(entry.getKey()).append(": ").append(entry.getValue());
        }
        if (result.isEmpty())
            return "Heap is empty.";
        return result.toString();
    }
}
