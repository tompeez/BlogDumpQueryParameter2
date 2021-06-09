package de.hahn.blog.dumpqueryparameter2.model.adfbc.base;

import oracle.jbo.Variable;
import oracle.jbo.VariableValueManager;
import oracle.jbo.server.ViewDefImpl;
import oracle.jbo.server.ViewObjectImpl;

public class BaseViewObjectImpl extends ViewObjectImpl {
    public BaseViewObjectImpl(String string, ViewDefImpl viewDefImpl) {
        super(string, viewDefImpl);
    }

    public BaseViewObjectImpl() {
        super();
    }

    @Override
    protected void executeQueryForCollection(Object object, Object[] object2, int i) {
//        dumpQueryAndParameters(object2);
        dumpQueryAndParameters_new(object2);
        super.executeQueryForCollection(object, object2, i);
    }


    public void dumpQueryAndParameters_new(Object[] params) {
        // get the query in it's current state
        String lQuery = getQuery();
        //get Valriables
        VariableValueManager lEnsureVariableManager = ensureVariableManager();
        Variable[] lVariables = lEnsureVariableManager.getVariables();
        int lCount = lEnsureVariableManager.getVariableCount();
        lCount += params != null ? params.length : 0;
        // Dump query
        System.out.println("---query--- " + lQuery);
        // if variables found dump them
        if (lCount > 0) {
            System.out.println("---Variables:");
            for (int ii = 0; ii < lEnsureVariableManager.getVariableCount(); ii++) {
                Object lObject = lEnsureVariableManager.getVariableValue(lVariables[ii]);
                System.out.println("  --- Name: " + lVariables[ii].getName() + " Value: " +
                                   (lObject != null ? lObject.toString() : "null"));
            }

            // checking view link related parameters
            if (params != null && params.length > 0) {
                for (int jj = 0; jj < params.length; jj++) {
                    Object[] nvp = (Object[]) params[jj];
                    // avoid duplicate printout of hte variable. Don't print if already print via variable manager
                    if (lEnsureVariableManager.lookupVariable((String) nvp[0]) == null)
                        System.out.println("  --- Name: " + nvp[0] + " Value: " + (nvp[1] != null ? nvp[1] : "null"));
                }
            }
        }
    }

    public void dumpQueryAndParameters(Object[] params) {
        // get the query in it's current state
        String lQuery = getQuery();
        //get Valriables
        VariableValueManager lEnsureVariableManager = ensureVariableManager();
        Variable[] lVariables = lEnsureVariableManager.getVariables();
        int lCount = lEnsureVariableManager.getVariableCount();
        lCount += params != null ? params.length : 0;
        // Dump query
        System.out.println("+++query+++ " + lQuery);
        // if variables found dump them
        if (lCount > 0) {
            System.out.println("+++Variables:");
            for (int ii = 0; ii < lEnsureVariableManager.getVariableCount(); ii++) {
                Object lObject = lEnsureVariableManager.getVariableValue(lVariables[ii]);
                System.out.println("  +++ Name: " + lVariables[ii].getName() + " Value: " +
                                   (lObject != null ? lObject.toString() : "null"));
            }
        }
    }

}
