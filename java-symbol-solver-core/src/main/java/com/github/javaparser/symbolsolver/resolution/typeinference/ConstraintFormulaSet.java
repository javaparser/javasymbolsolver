/**
 * Copyright 2017 - The JavaParser Team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.javaparser.symbolsolver.resolution.typeinference;

import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Federico Tomassetti
 */
public class ConstraintFormulaSet {
    private List<ConstraintFormula> constraintFormulas;

    public ConstraintFormulaSet withConstraint(ConstraintFormula constraintFormula) {
        ConstraintFormulaSet newInstance = new ConstraintFormulaSet();
        newInstance.constraintFormulas.addAll(this.constraintFormulas);
        newInstance.constraintFormulas.add(constraintFormula);
        return newInstance;
    }

    private static final ConstraintFormulaSet EMPTY = new ConstraintFormulaSet();

    public static ConstraintFormulaSet empty() {
        return EMPTY;
    }

    private ConstraintFormulaSet() {
        constraintFormulas = new LinkedList<>();
    }

    /**
     * Takes a compatibility assertion about an expression or type, called a constraint formula, and reduces it to a
     * set of bounds on inference variables. Often, a constraint formula reduces to other constraint formulas,
     * which must be recursively reduced. A procedure is followed to identify these additional constraint formulas and,
     * ultimately, to express via a bound set the conditions under which the choices for inferred types would render
     * each constraint formula true.
     */
    public BoundSet reduce(TypeSolver typeSolver) {
        List<ConstraintFormula> constraints = new LinkedList<>(constraintFormulas);
        BoundSet boundSet = BoundSet.empty();
        while (constraints.size() > 0) {
            ConstraintFormula constraintFormula = constraints.remove(0);
            ConstraintFormula.ReductionResult reductionResult = constraintFormula.reduce(boundSet);
            constraints.addAll(reductionResult.getConstraintFormulas());
            boundSet.incorporate(reductionResult.getBoundSet(), typeSolver);
        }
        return boundSet;
    }

    public boolean isEmpty() {
        return constraintFormulas.isEmpty();
    }
}
