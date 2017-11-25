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

import java.lang.reflect.Method;
import java.util.Comparator;

/**
 * @author Federico Tomassetti
 */
public class MethodComparator implements Comparator<Method> {

    @Override
    public int compare(Method o1, Method o2) {
        int compareName = o1.getName().compareTo(o2.getName());
        if (compareName != 0) return compareName;
        int compareNParams = o1.getParameterCount() - o2.getParameterCount();
        if (compareNParams != 0) return compareNParams;
        for (int i = 0; i < o1.getParameterCount(); i++) {
            int compareParam = new ParameterComparator().compare(o1.getParameters()[i], o2.getParameters()[i]);
            if (compareParam != 0) return compareParam;
        }
        int compareResult = new ClassComparator().compare(o1.getReturnType(), o2.getReturnType());
        if (compareResult != 0) return compareResult;
        return 0;
    }
}
