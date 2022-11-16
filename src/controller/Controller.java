package controller;

import model.exceptions.InterpreterException;
import model.ProgramState;
import model.exceptions.StatementExecutionException;
import model.statements.IStatement;
import repository.IRepository;

public class Controller {

    private final IRepository _repository;
    private boolean _displayFlag;

    public Controller(IRepository repository) {
        _repository = repository;
    }

    public IRepository getRepository() { return _repository; }

    public boolean getDisplayFlag() { return _displayFlag; }

    public void setDisplayFlag(boolean value) { _displayFlag = value; }

    public ProgramState oneStep(ProgramState state) throws InterpreterException {
        var stack = state.getExecutionStack();
        if (stack.empty())
            throw new StatementExecutionException("Stack is empty.");

        IStatement cs = stack.pop();
        return cs.execute(state);
    }

    public void allSteps() throws InterpreterException {

        ProgramState state = _repository.getCurrentProgram();
        if (_displayFlag)
            System.out.println("Initial program state:\n" + state.toString().indent(4));
        _repository.logProgramState();

        while (!state.getExecutionStack().empty()) {
            state = oneStep(state);
            if (_displayFlag)
                System.out.println("Current program state:\n" + state.toString().indent(4));
            _repository.logProgramState();
        }
    }
}
