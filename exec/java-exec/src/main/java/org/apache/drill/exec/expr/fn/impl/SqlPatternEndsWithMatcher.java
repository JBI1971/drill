/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.drill.exec.expr.fn.impl;

public class SqlPatternEndsWithMatcher implements SqlPatternMatcher {
  final String patternString;
  CharSequence charSequenceWrapper;
  final int patternLength;

  public SqlPatternEndsWithMatcher(String patternString, CharSequence charSequenceWrapper) {
    this.charSequenceWrapper = charSequenceWrapper;
    this.patternString = patternString;
    this.patternLength = patternString.length();
  }

  @Override
  public int match() {
    int txtIndex = charSequenceWrapper.length();
    int patternIndex = patternLength;
    boolean matchFound = true; // if pattern is empty string, we always match.

    // simplePattern string has meta characters i.e % and _ and escape characters removed.
    // so, we can just directly compare.
    while (patternIndex > 0 && txtIndex > 0) {
      if (charSequenceWrapper.charAt(--txtIndex) != patternString.charAt(--patternIndex)) {
        matchFound = false;
        break;
      }
    }

    return (patternIndex == 0 && matchFound == true) ? 1 : 0;
  }

}
