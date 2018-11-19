package ahocorasick;



import ahocorasick.trie.Emit;
import ahocorasick.trie.Trie;

import java.util.Collection;

public class demo {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.addKeyword("2");
        trie.addKeyword("4");
        trie.addKeyword("102");
        long t1 = System.nanoTime();
        Collection<Emit> emits = trie.parseText("102werwertrgsdgh");
        //Collection<Emit> emits = trie.parseText("asd");
        long t2 = System.nanoTime();
        System.out.println("time:" + (t2 - t1));
        System.out.println("isEmitsEmpty:"+emits.isEmpty());
        System.out.println("size:"+ emits.size());
        for (Emit temp : emits) {

            System.out.println(temp.getKeyword()+":"+temp.getStart()+" "+temp.getEnd());
        }
    }
}
