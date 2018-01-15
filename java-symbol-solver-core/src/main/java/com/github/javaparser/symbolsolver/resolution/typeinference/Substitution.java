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

import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Federico Tomassetti
 */
public class Substitution {

    private List<ResolvedTypeParameterDeclaration> typeParameterDeclarations;
    private List<ResolvedType> types;

    private final static Substitution EMPTY = new Substitution();

    public static Substitution empty() {
        return EMPTY;
    }

    public Substitution withPair(ResolvedTypeParameterDeclaration typeParameterDeclaration, ResolvedType type) {
        Substitution newInstance = new Substitution();
        newInstance.typeParameterDeclarations.addAll(this.typeParameterDeclarations);
        newInstance.types.addAll(this.types);
        newInstance.typeParameterDeclarations.add(typeParameterDeclaration);
        newInstance.types.add(type);
        return newInstance;

    }

    private Substitution() {
        this.typeParameterDeclarations = new LinkedList<>();
        this.types = new LinkedList<>();
    }

    public ResolvedType apply(ResolvedType originalType) {
        ResolvedType result = originalType;
        for (int i=0;i<typeParameterDeclarations.size();i++) {
            result = result.replaceTypeVariables(typeParameterDeclarations.get(i), types.get(i));
        }
        return result;
    }
}
