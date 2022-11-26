package controller;

import model.exceptions.InterpreterException;
import model.programState.ProgramState;
import model.exceptions.StatementExecutionException;
import model.statements.IStatement;
import model.utility.GarbageCollector;
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
        _repository.logProgramState("Initial program state:");

        while (!state.getExecutionStack().empty()) {
            state = oneStep(state);
            if (_displayFlag)
                System.out.println("Current program state:\n" + state.toString().indent(4));
            _repository.logProgramState("Current program state:");

            var newHeap = GarbageCollector.run(state.getHeapTable(), state.getSymbolTable());
            state.setHeapTable(newHeap);

            if (_displayFlag)
                System.out.println("Current program state (after garbage collector):\n" + state.toString().indent(4));
            _repository.logProgramState("Current program state (after garbage collector):");
        }
    }
}
