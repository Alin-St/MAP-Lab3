package model.types;

import model.utility.IDeepCopyable;
import model.values.IValue;

public interface IType extends IDeepCopyable {
    String toString();
    boolean equals(Object other);
    IValue defaultValue();
    IType deepCopy();
}
