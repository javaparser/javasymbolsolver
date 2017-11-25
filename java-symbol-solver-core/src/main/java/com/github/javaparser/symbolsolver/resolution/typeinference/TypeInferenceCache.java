/**
 * Copyright 2017 The JavaParser Team
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

import com.github.javaparser.ast.expr.LambdaExpr;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;

import java.util.*;

/**
 * @author Federico Tomassetti
 */
public class TypeInferenceCache {

    private static Map<TypeSolver, IdentityHashMap<LambdaExpr, Map<String, ResolvedType>>> typeForLambdaParameters = new HashMap<>();
    private static Map<TypeSolver, IdentityHashMap<LambdaExpr, List<InferenceVariable>>> inferenceVariables = new HashMap<>();

    public static void record(TypeSolver typeSolver, LambdaExpr lambdaExpr, String paramName, ResolvedType type) {
        if (!typeForLambdaParameters.containsKey(typeSolver)) {
            typeForLambdaParameters.put(typeSolver, new IdentityHashMap<>());
        }
        if (!typeForLambdaParameters.get(typeSolver).containsKey(lambdaExpr)) {
            typeForLambdaParameters.get(typeSolver).put(lambdaExpr, new HashMap<>());
        }
        typeForLambdaParameters.get(typeSolver).get(lambdaExpr).put(paramName, type);
    }

    public static Optional<ResolvedType> retrieve(TypeSolver typeSolver, LambdaExpr lambdaExpr, String paramName) {
        if (!typeForLambdaParameters.containsKey(typeSolver)) {
            return Optional.empty();
        }
        if (!typeForLambdaParameters.get(typeSolver).containsKey(lambdaExpr)) {
            return Optional.empty();
        }
        if (!typeForLambdaParameters.get(typeSolver).get(lambdaExpr).containsKey(paramName)) {
            return Optional.empty();
        }
        return Optional.of(typeForLambdaParameters.get(typeSolver).get(lambdaExpr).get(paramName));
    }

    public static void recordInferenceVariables(TypeSolver typeSolver, LambdaExpr lambdaExpr, List<InferenceVariable> _inferenceVariables) {
        if (!inferenceVariables.containsKey(typeSolver)) {
            inferenceVariables.put(typeSolver, new IdentityHashMap<>());
        }
        inferenceVariables.get(typeSolver).put(lambdaExpr, _inferenceVariables);
    }

    public static Optional<List<InferenceVariable>> retrieveInferenceVariables(TypeSolver typeSolver, LambdaExpr lambdaExpr) {
        if (!inferenceVariables.containsKey(typeSolver)) {
            return Optional.empty();
        }
        if (!inferenceVariables.get(typeSolver).containsKey(lambdaExpr)) {
            return Optional.empty();
        }
        return Optional.of(inferenceVariables.get(typeSolver).get(lambdaExpr));
    }
}
