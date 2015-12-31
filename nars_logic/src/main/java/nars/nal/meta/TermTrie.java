package nars.nal.meta;

import com.google.common.base.Joiner;
import com.gs.collections.impl.map.mutable.primitive.ObjectIntHashMap;
import nars.term.Term;
import org.magnos.trie.Trie;
import org.magnos.trie.TrieNode;
import org.magnos.trie.TrieSequencer;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import static com.sun.org.apache.xerces.internal.impl.xs.opti.SchemaDOM.indent;


/** indexes sequences of (a perfectly-hashable fixed number
 * of unique) terms in a magnos trie */
abstract public class TermTrie<K extends Term, V> {

    public final Trie<List<K>, V> trie;


    public void printSummary() {
        printSummary(trie.root);
    }


    public TermTrie(Collection<V> R) {
        super();

        ObjectIntHashMap<Term> conds = new ObjectIntHashMap<>();

        trie = new Trie(new TrieSequencer<List<K>>() {

            @Override
            public int matches(List<K> sequenceA, int indexA, List<K> sequenceB, int indexB, int count) {
                for (int i = 0; i < count; i++) {
                    K a = sequenceA.get(i + indexA);
                    K b = sequenceB.get(i + indexB);
                    if (!a.equals(b))
                        return i;
                }

                return count;
            }

            @Override
            public int lengthOf(List<K> sequence) {
                return sequence.size();
            }

            @Override
            public int hashOf(List<K> sequence, int index) {
                //return sequence.get(index).hashCode();

                Term pp = sequence.get(index);
                return conds.getIfAbsentPutWithKey(pp, (p) -> 1 + conds.size());
            }
        });

        R.forEach((Consumer<? super V>) this::index);
    }

    /** called for each item on insert */
    abstract public void index(V v);

    public static <A, B> void printSummary(TrieNode<List<A>,B> node) {

        node.forEach(n -> {
            List<A> seq = n.getSequence();

            int from = n.getStart();
            int to = n.getEnd();


            System.out.print(n.getChildCount() + "|" + n.getSize() + "  ");

            indent(from * 2);

            System.out.println(Joiner.on(", ").join( seq.subList(from, to)));

            printSummary(n);
        });

    }


}
