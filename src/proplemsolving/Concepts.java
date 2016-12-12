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
 * @author prateek.kesarwani
 */
public class Concepts {

}
