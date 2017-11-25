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
package com.github.javaparser.symbolsolver.model.typesystem;

import com.github.javaparser.resolution.declarations.*;
import com.github.javaparser.resolution.types.*;
import com.github.javaparser.resolution.types.ResolvedType;

import java.util.Map;
import java.util.function.Function;

public class LazyType implements ResolvedType {
    private ResolvedType concrete;
    private Function<Void, ResolvedType> provider;

    public LazyType(Function<Void, ResolvedType> provider) {
        this.provider = provider;
    }

    private ResolvedType getType() {
        if (concrete == null) {
            concrete = provider.apply(null);
        }
        return concrete;
    }

    @Override
    public boolean isArray() {
        return getType().isArray();
    }

    @Override
    public int arrayLevel() {
        return getType().arrayLevel();
    }

    @Override
    public boolean isPrimitive() {
        return getType().isPrimitive();
    }

    @Override
    public boolean isNull() {
        return getType().isNull();
    }

    @Override
    public boolean isReference() {
        return getType().isReference();
    }

    @Override
    public boolean isReferenceType() {
        return getType().isReferenceType();
    }

    @Override
    public boolean isVoid() {
        return getType().isVoid();
    }

    @Override
    public boolean isTypeVariable() {
        return getType().isTypeVariable();
    }

    @Override
    public boolean isWildcard() {
        return getType().isArray();
    }

    @Override
    public ResolvedArrayType asArrayType() {
        return getType().asArrayType();
    }

    @Override
    public ResolvedReferenceType asReferenceType() {
        return getType().asReferenceType();
    }

    @Override
    public ResolvedTypeParameterDeclaration asTypeParameter() {
        return getType().asTypeParameter();
    }

    @Override
    public ResolvedTypeVariable asTypeVariable() {
        return getType().asTypeVariable();
    }

    @Override
    public ResolvedPrimitiveType asPrimitive() {
        return getType().asPrimitive();
    }

    @Override
    public ResolvedWildcard asWildcard() {
        return getType().asWildcard();
    }

    @Override
    public String describe() {
        return getType().describe();
    }

    @Override
    public ResolvedType replaceTypeVariables(ResolvedTypeParameterDeclaration tp, ResolvedType replaced,
                                             Map<ResolvedTypeParameterDeclaration, ResolvedType> inferredTypes) {
        return getType().replaceTypeVariables(tp, replaced, inferredTypes);
    }

    @Override
    public ResolvedType replaceTypeVariables(ResolvedTypeParameterDeclaration tp, ResolvedType replaced) {
        return getType().replaceTypeVariables(tp, replaced);
    }

    @Override
    public boolean isAssignableBy(ResolvedType other) {
        return getType().isAssignableBy(other);
    }
}
