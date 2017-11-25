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

import java.util.Comparator;

/**
 * @author Federico Tomassetti
 */
public class ClassComparator implements Comparator<Class<?>> {

    @Override
    public int compare(Class<?> o1, Class<?> o2) {
        int subCompare;
        subCompare = o1.getCanonicalName().compareTo(o2.getCanonicalName());
        if (subCompare != 0) return subCompare;
        subCompare = Boolean.compare(o1.isAnnotation(), o2.isAnnotation());
        if (subCompare != 0) return subCompare;
        subCompare = Boolean.compare(o1.isArray(), o2.isArray());
        if (subCompare != 0) return subCompare;
        subCompare = Boolean.compare(o1.isEnum(), o2.isEnum());
        if (subCompare != 0) return subCompare;
        subCompare = Boolean.compare(o1.isInterface(), o2.isInterface());
        if (subCompare != 0) return subCompare;
        return 0;
    }
}
