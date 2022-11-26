import controller.Controller;
import model.programState.ProgramState;
import model.expressions.*;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.types.ReferenceType;
import model.types.StringType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.ReferenceValue;
import model.values.StringValue;
import repository.IRepository;
import repository.Repository;
import view.TextMenu;
import view.commands.ExitCommand;
import view.commands.RunExample;

public class Interpreter {

    @SuppressWarnings({"DuplicatedCode", "SpellCheckingInspection"})
    public static void main(String[] args) {

        IStatement ex1 = new CompoundStatement(
                new VariableDeclarationStatement("v", IntType.get()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))
                )
        );
        ProgramState prg1 = new ProgramState(ex1);
        IRepository repo1 = new Repository("log1.txt");
        repo1.setCurrentProgram(prg1);
        Controller ctr1 = new Controller(repo1);

        IStatement ex2 = new CompoundStatement(
                new VariableDeclarationStatement("a", IntType.get()),
                new CompoundStatement(
                        new VariableDeclarationStatement("b", IntType.get()),
                        new CompoundStatement(
                                new AssignmentStatement("a", new ArithmeticExpression(
                                        new ValueExpression(new IntValue(2)),
                                        new ArithmeticExpression(
                                                new ValueExpression(new IntValue(3)),
                                                new ValueExpression(new IntValue(5)),
                                                ArithmeticOperator.MULTIPLICATION
                                        ),
                                        ArithmeticOperator.ADDITION
                                )),
                                new CompoundStatement(
                                        new AssignmentStatement("b", new ArithmeticExpression(
                                                new VariableExpression("a"),
                                                new ValueExpression(new IntValue(1)),
                                                ArithmeticOperator.ADDITION
                                        )),
                                        new PrintStatement(new VariableExpression("b"))
                                )
                        )
                )
        );
        ProgramState prg2 = new ProgramState(ex2);
        IRepository repo2 = new Repository("log2.txt");
        repo2.setCurrentProgram(prg2);
        Controller ctr2 = new Controller(repo2);

        IStatement ex3 = new CompoundStatement(
                new VariableDeclarationStatement("a", BoolType.get()),
                new CompoundStatement(
                        new VariableDeclarationStatement("v", IntType.get()),
                        new CompoundStatement(
                                new AssignmentStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(
                                        new ConditionalStatement(
                                                new VariableExpression("a"),
                                                new AssignmentStatement("v", new ValueExpression(new IntValue(2))),
                                                new AssignmentStatement("v", new ValueExpression(new IntValue(3)))
                                        ),
                                        new PrintStatement(new VariableExpression("v"))
                                )
                        )
                )
        );
        ProgramState prg3 = new ProgramState(ex3);
        IRepository repo3 = new Repository("log3.txt");
        repo3.setCurrentProgram(prg3);
        Controller ctr3 = new Controller(repo3);

        IStatement ex4 = new CompoundStatement(
                new VariableDeclarationStatement("varf", StringType.get()),
                new CompoundStatement(
                        new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(
                                new OpenFileReaderStatement(new VariableExpression("varf")),
                                new CompoundStatement(
                                        new VariableDeclarationStatement("varc", IntType.get()),
                                        new CompoundStatement(
                                                new CompoundStatement(
                                                        new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                        new PrintStatement(new VariableExpression("varc"))
                                                ),
                                                new CompoundStatement(
                                                        new CompoundStatement(
                                                                new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                                new PrintStatement(new VariableExpression("varc"))
                                                        ),
                                                        new CloseFileReaderStatement(new VariableExpression("varf"))
                                                )
                                        )
                                )
                        )
                )
        );
        ProgramState prg4 = new ProgramState(ex4);
        IRepository repo4 = new Repository("log4.txt");
        repo4.setCurrentProgram(prg4);
        Controller ctr4 = new Controller(repo4);

        IStatement ex5 = new CompoundStatement(
                new CompoundStatement(
                        new PrintStatement(new RelationalExpression(
                                new ValueExpression(new IntValue(4)),
                                new ValueExpression(new IntValue(4)),
                                RelationalOperator.NOT_EQUALS
                        )),
                        new PrintStatement(new RelationalExpression(
                                new ValueExpression(new IntValue(4)),
                                new ValueExpression(new IntValue(5)),
                                RelationalOperator.NOT_EQUALS
                        ))
                ),
                new CompoundStatement(
                        new PrintStatement(new RelationalExpression(
                                new ValueExpression(new IntValue(4)),
                                new ValueExpression(new IntValue(4)),
                                RelationalOperator.LESS_THAN
                        )),
                        new PrintStatement(new RelationalExpression(
                                new ValueExpression(new IntValue(4)),
                                new ValueExpression(new IntValue(5)),
                                RelationalOperator.LESS_THAN
                        ))
                )
        );
        ProgramState prg5 = new ProgramState(ex5);
        IRepository repo5 = new Repository("log5.txt");
        repo5.setCurrentProgram(prg5);
        Controller ctr5 = new Controller(repo5);

        IStatement ex6 = new CompoundStatement(
                new VariableDeclarationStatement("v", IntType.get()),
                new CompoundStatement(
                        new AssignmentStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(
                                new WhileStatement(
                                        new RelationalExpression(
                                                new VariableExpression("v"),
                                                new ValueExpression(new IntValue(0)),
                                                RelationalOperator.GREATER_THAN
                                        ),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new AssignmentStatement("v", new ArithmeticExpression(
                                                        new VariableExpression("v"),
                                                        new ValueExpression(new IntValue(1)),
                                                        ArithmeticOperator.SUBTRACTION
                                                ))
                                        )
                                ),
                                new PrintStatement(new VariableExpression("v"))
                        )
                )
        );
        ProgramState prg6 = new ProgramState(ex6);
        IRepository repo6 = new Repository("log6.txt");
        repo6.setCurrentProgram(prg6);
        Controller ctr6 = new Controller(repo6);

        IStatement ex7 = new CompoundStatement(
                new VariableDeclarationStatement("v", new ReferenceType(IntType.get())),
                new CompoundStatement(
                        new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new VariableDeclarationStatement("a",
                                        new ReferenceType(new ReferenceType(IntType.get()))),
                                new CompoundStatement(
                                        new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(
                                                new HeapAllocationStatement("v",
                                                        new ValueExpression(new IntValue(30))),
                                                new CompoundStatement(
                                                        new PrintStatement(new HeapReadingExpression(
                                                                new HeapReadingExpression(
                                                                        new VariableExpression("a")))),
                                                        new AssignmentStatement("a", new ValueExpression(
                                                                new ReferenceType(new ReferenceType(IntType.get()))
                                                                        .defaultValue()))
                                                )
                                        )
                                )
                        )
                )
        );
        ProgramState prg7 = new ProgramState(ex7);
        IRepository repo7 = new Repository("log7.txt");
        repo7.setCurrentProgram(prg7);
        Controller ctr7 = new Controller(repo7);

        var menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
        menu.addCommand(new RunExample("4", ex4.toString(), ctr4));
        menu.addCommand(new RunExample("5", ex5.toString(), ctr5));
        menu.addCommand(new RunExample("6", ex6.toString(), ctr6));
        menu.addCommand(new RunExample("7", ex7.toString(), ctr7));
        menu.show();
    }
}
