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

/**
 * @author Federico Tomassetti
 */
public class ProperUpperBound {
    private InferenceVariable inferenceVariable;
    private ResolvedType properType;

    public ProperUpperBound(InferenceVariable inferenceVariable, ResolvedType properType) {
        this.inferenceVariable = inferenceVariable;
        this.properType = properType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProperUpperBound that = (ProperUpperBound) o;

        if (!inferenceVariable.equals(that.inferenceVariable)) return false;
        return properType.equals(that.properType);
    }

    @Override
    public int hashCode() {
        int result = inferenceVariable.hashCode();
        result = 31 * result + properType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ProperUpperBound{" +
                "inferenceVariable=" + inferenceVariable +
                ", properType=" + properType +
                '}';
    }

    public InferenceVariable getInferenceVariable() {
        return inferenceVariable;
    }

    public ResolvedType getProperType() {
        return properType;
    }
}
