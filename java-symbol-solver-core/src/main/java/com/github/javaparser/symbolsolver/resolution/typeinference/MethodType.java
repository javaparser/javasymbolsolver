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

import com.github.javaparser.resolution.MethodUsage;
import com.github.javaparser.resolution.declarations.ResolvedTypeParameterDeclaration;
import com.github.javaparser.resolution.types.ResolvedType;

import java.util.List;

/**
 * A MethodType is an ordered 4-tuple consisting of:
 * 1. type parameters: the declarations of any type parameters of the method member.
 * 2. argument types: a list of the types of the arguments to the method member.
 * 3. return type: the return type of the method member.
 * 4. throws clause: exception types declared in the throws clause of the method member.
 *
 * See JLS 8.2
 *
 * @author Federico Tomassetti
 */
public class MethodType {
    private List<ResolvedTypeParameterDeclaration> typeParameters;
    private List<ResolvedType> formalArgumentTypes;
    private ResolvedType returnType;
    private List<ResolvedType> exceptionTypes;

    public static MethodType fromMethodUsage(MethodUsage methodUsage) {
        return new MethodType(methodUsage.getDeclaration().getTypeParameters(), methodUsage.getParamTypes(),
                methodUsage.returnType(), methodUsage.exceptionTypes());
    }

    public MethodType(List<ResolvedTypeParameterDeclaration> typeParameters, List<ResolvedType> formalArgumentTypes,
                      ResolvedType returnType,
                      List<ResolvedType> exceptionTypes) {
        this.typeParameters = typeParameters;
        this.formalArgumentTypes = formalArgumentTypes;
        this.returnType = returnType;
        this.exceptionTypes = exceptionTypes;
    }

    public List<ResolvedTypeParameterDeclaration> getTypeParameters() {
        return typeParameters;
    }

    public List<ResolvedType> getFormalArgumentTypes() {
        return formalArgumentTypes;
    }

    public ResolvedType getReturnType() {
        return returnType;
    }

    public List<ResolvedType> getExceptionTypes() {
        return exceptionTypes;
    }
}
