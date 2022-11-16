package model.values;

import model.types.IType;
import model.utility.IDeepCopyable;

public interface IValue extends IDeepCopyable {
    IType getType();
    String toString();
    IValue deepCopy();
    boolean equals(Object other);
}
