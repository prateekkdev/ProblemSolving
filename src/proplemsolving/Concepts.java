/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proplemsolving;

/**
 *
 * @author prateek.kesarwani
 */
/**
 * Backtracking(Could be done recursively or iteratively using stack) vs Branch
 * and Bound(Could be done using queues)
 *
 * Backtracking: [1] It is used to find all possible solutions available to the
 * problem. [2] It traverse tree by DFS(Depth First Search). [3] It realizes
 * that it has made a bad choice & undoes the last choice by backing up. [4] It
 * search the state space tree until it found a solution. [5] It involves
 * feasibility function.
 *
 * Branch-and-Bound: [1] It is used to solve optimization problem. [2] It may
 * traverse the tree in any manner, DFS or BFS. [3] It realizes that it already
 * has a better optimal solution that the pre-solution leads to so it abandons
 * that pre-solution. [4] It completely searches the state space tree to get
 * optimal solution. [5] It involves bounding function.
 *
 * 1 Branch-and-Bound algorithms can be applied only to optimization problems.
 * Backtracking is more often not applied to non-optimization.
 *
 * 2 Branch-and-bound generates nodes by Best-first rule. Backtracking generates
 * tree by depth-first rule.
 *
 * 3 Branch-and-bound involves feasible solution. Backtracking involves optimal
 * solution.
 *
 * A feasible solution is a point in the problem’s state-space that satisfies
 * all the problem’s constraints. An optimal solution is a feasible solution,
 * with the best value of the objective function.
 *
 *
 * Self balancing BST(RB Tree and AVL Tree)
 *
 * Top-Down: Recursive(Starting with fn we go down till f1 maybe and then build
 * our way up.
 *
 * Pros: - You can just focus on the nature of the recurrence - Comfortable to
 * code - Handy for large number of dimensions. - Easy to visualize the
 * overlapping sub-problems. - For example, consider the following recursion:
 * f(n) = f(n/2 + 1) + f(n/4) with some suitable base cases In this case, doing
 * bottom up would only waste time, however, doing top down would be a lot
 * better/faster.
 *
 * Cons: - Space optimization is not quite possible (Row-by-row DP: an example)
 * - Many optimizations hard to implement (Convex Hull Optimization, Knuth
 * Optimization) - Stack/Memory overflow due to recursion stacks - -
 * memorization table should be a global variable or should be passed between
 * functions (which could be complex and undesirable).
 *
 * Bottom-Up: Iterative(Starting with f1 maybe we build our way up to fn)
 *
 * Pros: -Time/space optimization can be implemented easier. -Bugs can be easier
 * to find. -Allows more freedom in variable/states manipulation
 *
 * Cons: -not natural to the recurrence -prone to segmentation faults (eg.
 * negative index array access) -base cases will cause more trouble than you
 * expected - Bottomup approach is nearly impossible to code if more than
 * three-dimension is required. - Would you write 10-nested loops for, if any,
 * DP with 10 dimensions?
 *
 * Note: If we are using stack based some method, than we could achieve same top
 * down approach(which is usually achieved via recursion) without using
 * recursion. This could be third approach, ie. recursive(top-down),
 * iterative(bottom-up), and stack based(bottom up).
 *
 * Old School dynamic programming is iterative also called Tabulation and is
 * bottom up. Memoization is recursive and top down.
 *
 * What is difference between memoization and dynamic programming? Memoization
 * is a term describing an optimization technique where you cache previously
 * computed results, and return the cached result when the same computation is
 * needed again.
 *
 * Dynamic programming is a technique for solving problems recursively and is
 * applicable when the computations of the subproblems overlap.
 *
 * Dynamic programming is typically implemented using tabulation, but can also
 * be implemented using memoization. So as you can see, neither one is a
 * "subset" of the other.
 *
 * A reasonable follow-up question is: What is the difference between tabulation
 * (the typical dynamic programming technique) and memoization?
 *
 * When you solve a dynamic programming problem using tabulation you solve the
 * problem "bottom up", i.e., by solving all related sub-problems first,
 * typically by filling up an n-dimensional table. Based on the results in the
 * table, the solution to the "top" / original problem is then computed.
 *
 * If you use memoization to solve the problem you do it by maintaining a map of
 * already solved sub problems. You do it "top down" in the sense that you solve
 * the "top" problem first (which typically recurses down to solve the
 * sub-problems).
 *
 * If all subproblems must be solved at least once, a bottom-up
 * dynamic-programming algorithm usually outperforms a top-down memoized
 * algorithm by a constant factor No overhead for recursion and less overhead
 * for maintaining table There are some problems for which the regular pattern
 * of table accesses in the dynamic-programming algorithm can be exploited to
 * reduce the time or space requirements even further If some subproblems in the
 * subproblem space need not be solved at all, the memoized solution has the
 * advantage of solving only those subproblems that are definitely required
 *
 * @author prateek.kesarwani
 */
public class Concepts {

}
