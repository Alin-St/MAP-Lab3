package model.garbageCollector;

import model.programState.HeapTable;
import model.programState.IHeapTable;
import model.programState.ISymbolTable;
import model.values.ReferenceValue;

import java.util.HashSet;
import java.util.List;

public class ConservativeGarbageCollector {

    public static IHeapTable run(IHeapTable heapTable, List<ISymbolTable> symbolTables) {
        var result = new HeapTable();
        var keepAddressSet = new HashSet<Integer>();

        for (var symbolTable : symbolTables) {
            for (var entry : symbolTable.toArrayList()) {
                if (entry.getValue() instanceof ReferenceValue refVal && !keepAddressSet.contains(refVal.getAddress())) {
                    addRecursively(refVal.getAddress(), keepAddressSet, heapTable);
                }
            }
        }

        for (var entry : heapTable.toArrayList()) {
            if (keepAddressSet.contains(entry.getKey()))
                result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }

    private static void addRecursively(Integer address, HashSet<Integer> keepAddressSet, IHeapTable heapTable) {
        keepAddressSet.add(address);

        var val = heapTable.get(address);

        if (val instanceof ReferenceValue refVal && !keepAddressSet.contains(refVal.getAddress()))
            addRecursively(refVal.getAddress(), keepAddressSet, heapTable);
    }
}
