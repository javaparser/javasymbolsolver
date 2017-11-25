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
package com.github.javaparser.symbolsolver.reflectionmodel.comparators;

import java.lang.reflect.Parameter;
import java.util.Comparator;

/**
 * @author Federico Tomassetti
 */
public class ParameterComparator implements Comparator<Parameter> {

    @Override
    public int compare(Parameter o1, Parameter o2) {
        int compareName = o1.getName().compareTo(o2.getName());
        if (compareName != 0) return compareName;
        int compareType = new ClassComparator().compare(o1.getType(), o2.getType());
        if (compareType != 0) return compareType;
        return 0;
    }
}
