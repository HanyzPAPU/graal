/*
 * Copyright (c) 2013, 2021, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package com.oracle.svm.hosted.phases;

import jdk.graal.compiler.java.BytecodeParser;
import jdk.graal.compiler.java.GraphBuilderPhase;
import jdk.graal.compiler.nodes.StructuredGraph;
import jdk.graal.compiler.nodes.ValueNode;
import jdk.graal.compiler.nodes.graphbuilderconf.GraphBuilderConfiguration;
import jdk.graal.compiler.nodes.graphbuilderconf.InlineInvokePlugin.InlineInfo;
import jdk.graal.compiler.nodes.graphbuilderconf.IntrinsicContext;
import jdk.graal.compiler.nodes.spi.CoreProviders;
import jdk.graal.compiler.phases.OptimisticOptimizations;
import jdk.graal.compiler.word.WordTypes;

import com.oracle.graal.pointsto.meta.AnalysisMethod;

import jdk.vm.ci.meta.ResolvedJavaMethod;

public class SubstrateGraphBuilderPhase extends SharedGraphBuilderPhase {

    public SubstrateGraphBuilderPhase(CoreProviders providers,
                    GraphBuilderConfiguration graphBuilderConfig, OptimisticOptimizations optimisticOpts, IntrinsicContext initialIntrinsicContext, WordTypes wordTypes) {
        super(providers, graphBuilderConfig, optimisticOpts, initialIntrinsicContext, wordTypes);
    }

    @Override
    protected BytecodeParser createBytecodeParser(StructuredGraph graph, BytecodeParser parent, ResolvedJavaMethod method, int entryBCI, IntrinsicContext intrinsicContext) {
        return new SubstrateBytecodeParser(this, graph, parent, method, entryBCI, intrinsicContext, false);
    }

    public static class SubstrateBytecodeParser extends SharedBytecodeParser {
        public SubstrateBytecodeParser(GraphBuilderPhase.Instance graphBuilderInstance, StructuredGraph graph, BytecodeParser parent, ResolvedJavaMethod method, int entryBCI,
                        IntrinsicContext intrinsicContext, boolean explicitExceptionEdges) {
            super(graphBuilderInstance, graph, parent, method, entryBCI, intrinsicContext, explicitExceptionEdges);
        }

        @Override
        protected InlineInfo tryInline(ValueNode[] args, ResolvedJavaMethod targetMethod) {
            InlineInfo inlineInfo = super.tryInline(args, targetMethod);
            if (inlineInfo == InlineInfo.DO_NOT_INLINE_WITH_EXCEPTION && targetMethod instanceof AnalysisMethod) {
                /*
                 * The target methods of intrinsified calls are still present in the image so their
                 * type is reachable. The methods are used as keys in the
                 * InvocationPlugins.resolvedRegistrations map reachable from
                 * SubstrateReplacements.snippetInvocationPlugins.
                 */
                AnalysisMethod aTargetMethod = (AnalysisMethod) targetMethod;
                aTargetMethod.getDeclaringClass().registerAsReachable("declared method " + aTargetMethod.getQualifiedName() + " is inlined");
            }
            return inlineInfo;
        }
    }
}
