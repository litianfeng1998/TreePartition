package algo06;

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/*
"The same strategy explained above can also be applied to breadth-
first search, where for each visited node, we try to add it to the partition of its parent, and if that is full, to the partition of its previous
sibling. BFS is not main-memory friendly, as we need to see all of
the nodes in the document to perform proper breadth-first search.
We include it for reasons of completeness."

REMARK: for node u, it doesn't iterate all the u's son's weight,
which will cause much more partitions than the experiment in Paper.
Hyposis it iterate son's totalWeight instead of son's weight.

Due to "for reasons of completeness", leave this behind for now.
 */
public class PUREBFS {
    TreeNode root;
    int K;
    List<List<TreeNode>> result;
    List<TreeNode> part;
    int partsum = 0;
    public PUREBFS(TreeNode u, int k) {
        root = u;
        K = k;
        result = new ArrayList<>();
        part = new ArrayList<>();

        bfs();
        if (part.size() != 0) result.add(part);
    }
    public void bfs() {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        partsum += root.weight;
        //root.index = result.size();//
        part.add(root);
        TreeNode lastFather = null;
        while (!q.isEmpty()) {
            TreeNode u = q.poll();
            for (int i = 0; i < u.sons.size(); i++) {
                TreeNode v = u.sons.get(i);
                q.add(v);
                if (partsum + v.weight <= K && (lastFather == null || lastFather == u)) {
                    part.add(v);
                    partsum += v.weight;
                    lastFather = u;
                } else {
                    result.add(part);
                    part = new ArrayList<>();
                    part.add(v);
                    partsum = v.weight;
                    lastFather = u;
                }
            }

        }
    }

    public String getPartition() {
        StringBuilder ans = new StringBuilder();
        ans.append(String.valueOf(result.size()));
        ans.append("\n");
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++)
               // ans.append(result.get(i).get(j).label +" ");
            ans.append("\n");
        }
        return ans.toString();
    }

    public int getPartitionNumber() {
        return result.size();
    }
}
