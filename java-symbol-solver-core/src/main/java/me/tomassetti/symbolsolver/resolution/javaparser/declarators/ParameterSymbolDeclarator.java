package me.tomassetti.symbolsolver.resolution.javaparser.declarators;

import com.github.javaparser.ast.body.Parameter;
import me.tomassetti.symbolsolver.model.declarations.MethodDeclaration;
import me.tomassetti.symbolsolver.model.declarations.ValueDeclaration;
import me.tomassetti.symbolsolver.model.resolution.TypeSolver;
import me.tomassetti.symbolsolver.resolution.javaparser.declarations.JavaParserSymbolDeclaration;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ParameterSymbolDeclarator extends AbstractSymbolDeclarator<Parameter> {


    public ParameterSymbolDeclarator(Parameter wrappedNode, TypeSolver typeSolver) {
        super(wrappedNode, typeSolver);
    }

    @Override
    public List<ValueDeclaration> getSymbolDeclarations() {
        List<ValueDeclaration> symbols = new LinkedList<>();
        symbols.add(JavaParserSymbolDeclaration.parameter(wrappedNode, typeSolver));
        return symbols;
    }

    @Override
    public List<MethodDeclaration> getMethodDeclarations() {
        return Collections.emptyList();
    }
}
