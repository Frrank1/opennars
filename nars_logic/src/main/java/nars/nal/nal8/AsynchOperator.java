package nars.nal.nal8;

import com.google.common.collect.Lists;
import nars.Memory;
import nars.nal.Task;
import nars.nal.concept.Concept;
import nars.nal.term.Term;
import nars.op.io.Echo;

import java.util.List;

/**
 * Executes in the NAR's threadpool
 */
abstract public class AsynchOperator extends Operator {

    @Override
    public boolean execute(Operation op, Concept c, Memory memory) {

        memory.taskLater(new Runnable() {

            @Override
            public void run() {

                List<Task> feedback;
                try {
                    feedback = execute(op, memory);
                } catch (Exception e) {
                    feedback = Lists.newArrayList(new Echo(getClass(), e.toString()).newTask());
                    e.printStackTrace();
                }

                executed(op, feedback, memory);

            }
        });

        return true;
    }
}
