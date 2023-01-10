package model.statements;

import model.exceptions.InterpreterException;
import model.programState.ProgramState;
import model.exceptions.ExpressionEvaluationException;
import model.expressions.IExpression;
import model.types.IType;
import model.utility.MyIDictionary;

public class PrintStatement implements IStatement {

    private final IExpression _expression;

    public PrintStatement(IExpression expression) {
        _expression = expression;
    }

    public String toString() {
        return "print(" + _expression.toString() + ")";
    }

    public ProgramState execute(ProgramState state) throws ExpressionEvaluationException {
        var output = state.getOutputStructure();
        var symbolTable = state.getSymbolTable();
        output.add(_expression.evaluate(symbolTable, state.getHeapTable()));
        return null;
    }

    @Override
    public PrintStatement deepCopy() {
        return new PrintStatement(_expression.deepCopy());
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> typeEnv) throws InterpreterException {
        _expression.typeCheck(typeEnv);
        return typeEnv;
    }
}
