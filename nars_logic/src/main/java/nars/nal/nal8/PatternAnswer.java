package nars.nal.nal8;

import nars.Global;
import nars.Narsese;
import nars.Op;
import nars.task.Task;
import nars.term.Term;
import nars.term.transform.FindSubst;
import nars.term.transform.Subst;
import nars.util.data.random.XorShift1024StarRandom;

import java.util.List;
import java.util.function.Function;

/** responds to questions by inserting beliefs as answers */
public abstract class PatternAnswer implements Function<Task, List<Task>> {

    final XorShift1024StarRandom rng = new XorShift1024StarRandom(1);
    public final Term pattern;

    public PatternAnswer(String pattern) {
        this.pattern = Narsese.the().termRaw(pattern);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + '[' + pattern.toString() + ']';
    }

    @Override
    public List<Task> apply(Task questionTask) {

        FindSubst s = new FindSubst(Op.VAR_PATTERN, rng);
        if (s.next(pattern, questionTask.getTerm(), Global.UNIFICATION_POWER)) {
            List<Task> answers = run(questionTask, s);
            if (answers!=null)
                return process(questionTask, answers);
        }
        return null;
    }

    private List<Task> process(Task question, List<Task> answers) {
//        answers.forEach(a -> {
//            a.setParentTask(question);
//        });
        return answers;
    }

    public abstract List<Task> run(Task operationTask, Subst map1);
}
