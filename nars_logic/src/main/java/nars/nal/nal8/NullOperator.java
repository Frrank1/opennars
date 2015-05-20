/*
 * Sample.java
 *
 * Copyright (C) 2008  Pei Wang
 *
 * This file is part of Open-NARS.
 *
 * Open-NARS is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * Open-NARS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Open-NARS.  If not, see <http://www.gnu.org/licenses/>.
 */
package nars.nal.nal8;

import nars.Global;
import nars.Memory;
import nars.nal.Task;

import java.util.Arrays;
import java.util.List;

/**
 *  A class used as a template for Operator definition.
 */
public class NullOperator extends SynchOperator {
    private final String name;

    
    /*public NullOperator() {
        this("^sample");
    }*/
    
    public NullOperator(String name) {
        super(name);
        this.name = name;
    }
    public NullOperator() {
        super();
        this.name = getClass().getSimpleName();
    }



    @Override
    protected List<Task> execute(Operation o, Memory memory) {
        if (Global.DEBUG) {
            memory.emit(getClass(),
                    //HACK
                    name,
                    o.arg()
            );
        }
        return null;
    }
}

