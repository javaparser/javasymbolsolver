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

import com.github.javaparser.resolution.types.ResolvedType;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Federico Tomassetti
 */
public class InferenceVariableSubstitution {

    private final static InferenceVariableSubstitution EMPTY = new InferenceVariableSubstitution();

    private List<InferenceVariable> inferenceVariables;
    private List<ResolvedType> types;

    public static InferenceVariableSubstitution empty() {
        return EMPTY;
    }

    private InferenceVariableSubstitution() {
        this.inferenceVariables = new LinkedList<>();
        this.types = new LinkedList<>();
    }

    public InferenceVariableSubstitution withPair(InferenceVariable inferenceVariable, ResolvedType type) {
        InferenceVariableSubstitution newInstance = new InferenceVariableSubstitution();
        newInstance.inferenceVariables.addAll(this.inferenceVariables);
        newInstance.types.addAll(this.types);
        newInstance.inferenceVariables.add(inferenceVariable);
        newInstance.types.add(type);
        return newInstance;
    }

}
