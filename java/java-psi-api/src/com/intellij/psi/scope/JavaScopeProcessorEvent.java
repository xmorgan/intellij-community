/*
 * Copyright 2000-2009 JetBrains s.r.o.
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
package com.intellij.psi.scope;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiModifier;
import com.intellij.psi.PsiModifierListOwner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author yole
 */
public class JavaScopeProcessorEvent implements PsiScopeProcessor.Event {
  private JavaScopeProcessorEvent() {
  }

  public static final JavaScopeProcessorEvent START_STATIC = new JavaScopeProcessorEvent();

  /**
   * An event issued by {@link com.intellij.psi.scope.util.PsiScopesUtil#treeWalkUp}
   * after {@link com.intellij.psi.PsiElement#processDeclarations} was called,
   * for each element in the hierarchy defined by a chain of {@link PsiElement#getContext()} calls.
   * The associated object is the {@link PsiElement} whose declarations have been processed.
   */
  public static final JavaScopeProcessorEvent EXIT_LEVEL = new JavaScopeProcessorEvent();

  public static final JavaScopeProcessorEvent CHANGE_LEVEL = new JavaScopeProcessorEvent();
  public static final JavaScopeProcessorEvent SET_CURRENT_FILE_CONTEXT = new JavaScopeProcessorEvent();

  public static boolean isEnteringStaticScope(@NotNull PsiScopeProcessor.Event event, @Nullable Object associated) {
    if (event == START_STATIC) return true;

    return event == EXIT_LEVEL &&
           associated instanceof PsiModifierListOwner && 
           ((PsiModifierListOwner)associated).hasModifierProperty(PsiModifier.STATIC);
  }
}
