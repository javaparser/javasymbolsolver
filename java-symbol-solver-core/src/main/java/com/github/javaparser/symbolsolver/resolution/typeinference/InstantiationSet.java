/**
 * Copyright 2017 JavaParser Team.
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
public class InstantiationSet {

    private List<Instantiation> instantiations;

    public boolean allInferenceVariablesAreResolved(BoundSet boundSet) {
        throw new UnsupportedOperationException();
    }

    public static InstantiationSet empty() {
        return EMPTY;
    }

    private static final InstantiationSet EMPTY = new InstantiationSet();

    private InstantiationSet() {
        instantiations = new LinkedList<>();
    }

    public InstantiationSet withInstantiation(Instantiation instantiation) {
        InstantiationSet newInstance = new InstantiationSet();
        newInstance.instantiations.addAll(this.instantiations);
        newInstance.instantiations.add(instantiation);
        return newInstance;
    }

    public boolean isEmpty() {
        return instantiations.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstantiationSet that = (InstantiationSet) o;

        return instantiations.equals(that.instantiations);
    }

    @Override
    public int hashCode() {
        return instantiations.hashCode();
    }

    @Override
    public String toString() {
        return "InstantiationSet{" +
                "instantiations=" + instantiations +
                '}';
    }

    public ResolvedType apply(ResolvedType type) {
        for (Instantiation instantiation : instantiations) {
            type = type.replaceTypeVariables(instantiation.getInferenceVariable().getTypeParameterDeclaration(), instantiation.getProperType());
        }
        return type;
    }
}
