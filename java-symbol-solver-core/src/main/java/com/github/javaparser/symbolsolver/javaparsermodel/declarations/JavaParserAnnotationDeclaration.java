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
package com.github.javaparser.symbolsolver.javaparsermodel.declarations;

import com.github.javaparser.ast.body.AnnotationDeclaration;
import com.github.javaparser.ast.body.AnnotationMemberDeclaration;
import com.github.javaparser.resolution.declarations.*;
import com.github.javaparser.resolution.types.ResolvedReferenceType;
import com.github.javaparser.resolution.types.ResolvedType;
import com.github.javaparser.symbolsolver.logic.AbstractTypeDeclaration;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.github.javaparser.symbolsolver.javaparser.Navigator.getParentNode;

/**
 * @author Federico Tomassetti
 */
public class JavaParserAnnotationDeclaration extends AbstractTypeDeclaration implements ResolvedAnnotationDeclaration {

    private com.github.javaparser.ast.body.AnnotationDeclaration wrappedNode;
    private TypeSolver typeSolver;

    public JavaParserAnnotationDeclaration(AnnotationDeclaration wrappedNode, TypeSolver typeSolver) {
        this.wrappedNode = wrappedNode;
        this.typeSolver = typeSolver;
    }

    @Override
    public List<ResolvedReferenceType> getAncestors() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ResolvedFieldDeclaration> getAllFields() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<ResolvedMethodDeclaration> getDeclaredMethods() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isAssignableBy(ResolvedType type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isAssignableBy(ResolvedReferenceTypeDeclaration other) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasDirectlyAnnotation(String qualifiedName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getPackageName() {
        return Helper.getPackageName(wrappedNode);
    }

    @Override
    public String getClassName() {
        return Helper.getClassName("", wrappedNode);
    }

    @Override
    public String getQualifiedName() {
        String containerName = Helper.containerName(getParentNode(wrappedNode));
        if (containerName.isEmpty()) {
            return wrappedNode.getName().getId();
        } else {
            return containerName + "." + wrappedNode.getName();
        }
    }

    @Override
    public String getName() {
        return wrappedNode.getName().getId();
    }

    @Override
    public List<ResolvedTypeParameterDeclaration> getTypeParameters() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<ResolvedReferenceTypeDeclaration> containerType() {
        throw new UnsupportedOperationException("containerType is not supported for " + this.getClass().getCanonicalName());
    }

    @Override
    public List<ResolvedAnnotationMemberDeclaration> getAnnotationMembers() {
        return wrappedNode.getMembers().stream()
                .filter(m -> m instanceof AnnotationMemberDeclaration)
                .map(m -> new JavaParserAnnotationMemberDeclaration((AnnotationMemberDeclaration)m, typeSolver))
                .collect(Collectors.toList());
    }
}
