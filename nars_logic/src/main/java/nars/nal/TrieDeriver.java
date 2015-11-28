package nars.nal;

import nars.nal.meta.PreCondition;
import nars.nal.meta.RuleTrie;

/**
 * separates rules according to task/belief term type but otherwise involves significant redundancy we'll eliminate in other Deriver implementations
 */
public class TrieDeriver extends RuleTrie {


    public TrieDeriver() {
        this(Deriver.standard);
    }

    public TrieDeriver(DerivationRules rules) {
        super(rules);
    }

    @Override
    public final void forEachRule(RuleMatch match) {
        //System.out.println("\nstart: " + match);

        //match.start();
        for (RuleBranch r : root) {
            forEachRule(r, match);
        }
    }

    private final static void forEachRule(RuleBranch r, RuleMatch match) {

        for (PreCondition x : r.precondition) {

            if (!x.test(match)) {
                return;
            }

        }

        RuleBranch[] children = r.children;

        //if (children != null) {
            RuleMatch subMatch = new RuleMatch(match.subst.random);

            for (RuleBranch s : children) {
                match.save(subMatch);
                forEachRule(s, subMatch);
            }

        //}
    }


//    final static void run(RuleMatch m, List<TaskRule> rules, int level, Consumer<Task> t) {
//
//        final int nr = rules.size();
//        for (int i = 0; i < nr; i++) {
//
//            TaskRule r = rules.get(i);
//            if (r.minNAL > level) continue;
//
//            PostCondition[] pc = m.run(r);
//            if (pc != null) {
//                for (PostCondition p : pc) {
//                    if (p.minNAL > level) continue;
//                    ArrayList<Task> Lx = m.apply(p);
//                    if(Lx!=null) {
//                        for (Task x : Lx) {
//                            if (x != null)
//                                t.accept(x);
//                        }
//                    }
//                    /*else
//                        System.out.println("Post exit: " + r + " on " + m.premise);*/
//                }
//            }
//        }
//    }


}
