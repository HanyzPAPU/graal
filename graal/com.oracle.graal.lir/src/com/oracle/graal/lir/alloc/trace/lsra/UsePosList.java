/*
 * Copyright (c) 2015, 2015, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
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
package com.oracle.graal.lir.alloc.trace.lsra;

import com.oracle.graal.compiler.common.util.IntList;
import com.oracle.graal.lir.alloc.trace.lsra.TraceInterval.RegisterPriority;

/**
 * List of use positions. Each entry in the list records the use position and register priority
 * associated with the use position. The entries in the list are in descending order of use
 * position.
 *
 */
public final class UsePosList {

    private IntList list;

    /**
     * Creates a use list.
     *
     * @param initialCapacity the initial capacity of the list in terms of entries
     */
    public UsePosList(int initialCapacity) {
        list = new IntList(initialCapacity * 2);
    }

    private UsePosList(IntList list) {
        this.list = list;
    }

    /**
     * Splits this list around a given position. All entries in this list with a use position
     * greater or equal than {@code splitPos} are removed from this list and added to the returned
     * list.
     *
     * @param splitPos the position for the split
     * @return a use position list containing all entries removed from this list that have a use
     *         position greater or equal than {@code splitPos}
     */
    public static UsePosList splitAt(UsePosList ul, int splitPos) {
        int i = size(ul) - 1;
        int len = 0;
        while (i >= 0 && usePos(ul, i) < splitPos) {
            --i;
            len += 2;
        }
        int listSplitIndex = (i + 1) * 2;
        IntList childList = ul.list;
        ul.list = IntList.copy(ul.list, listSplitIndex, len);
        childList.setSize(listSplitIndex);
        UsePosList child = new UsePosList(childList);
        return child;
    }

    /**
     * Gets the use position at a specified index in this list.
     *
     * @param index the index of the entry for which the use position is returned
     * @return the use position of entry {@code index} in this list
     */
    public static int usePos(UsePosList ul, int index) {
        return ul.list.get(index << 1);
    }

    /**
     * Gets the register priority for the use position at a specified index in this list.
     *
     * @param index the index of the entry for which the register priority is returned
     * @return the register priority of entry {@code index} in this list
     */
    public static RegisterPriority registerPriority(UsePosList ul, int index) {
        return RegisterPriority.VALUES[ul.list.get((index << 1) + 1)];
    }

    public static void add(UsePosList ul, int usePos, RegisterPriority registerPriority) {
        assert ul.list.size() == 0 || usePos(ul, size(ul) - 1) > usePos;
        ul.list.add(usePos);
        ul.list.add(registerPriority.ordinal());
    }

    public static int size(UsePosList ul) {
        return ul.list.size() >> 1;
    }

    public static void removeLowestUsePos(UsePosList ul) {
        ul.list.setSize(ul.list.size() - 2);
    }

    public static void setRegisterPriority(UsePosList ul, int index, RegisterPriority registerPriority) {
        ul.list.set((index << 1) + 1, registerPriority.ordinal());
    }

    public static String toString(UsePosList ul) {
        StringBuilder buf = new StringBuilder("[");
        for (int i = size(ul) - 1; i >= 0; --i) {
            if (buf.length() != 1) {
                buf.append(", ");
            }
            RegisterPriority prio = registerPriority(ul, i);
            buf.append(usePos(ul, i)).append(" -> ").append(prio.ordinal()).append(':').append(prio);
        }
        return buf.append("]").toString();
    }
}
