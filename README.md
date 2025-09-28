# DAA Assignment 1
Initial project setup (Maven + JUnit5)

# Design and Analysis of Algorithms – Assignment 1

## 1. Implemented Algorithms
- **MergeSort** – with insertion-sort cutoff and buffer reuse.
- **QuickSort** – randomized pivot, smaller-partition recursion.
- **Deterministic Select (Median of Medians)** – group-of-5 pivot strategy.
- **Closest Pair of Points (2D)** – divide-and-conquer with strip check.

All algorithms are instrumented with `Metrics` (comparisons, recursion depth, runtime) and emit `.csv` files through the CLI.

---

## 2. Architecture Notes
- **Metrics** class tracks:
    - comparisons → incremented at each `if` check.
    - recursion depth → updated on recursive entry/exit.
    - runtime → recorded per algorithm and written to CSV.
- Recursion depth for QuickSort is controlled by "smaller-first recursion".
- Closest Pair splits points by median-x, keeps y-sorted arrays, and checks only ~7 neighbors in the strip.

---

## 3. Recurrence Analysis
- **MergeSort**:  
  \(T(n) = 2T(n/2) + Θ(n)\) → Case 2 of Master Theorem → Θ(n log n).

- **QuickSort** (expected):  
  \(T(n) = T(n/2) + T(n/2) + Θ(n)\), but randomized pivot keeps expected depth O(log n) → Θ(n log n).

- **Deterministic Select (MoM5)**:  
  Recurrence: \(T(n) ≤ T(n/5) + T(7n/10) + Θ(n)\) → Θ(n).

- **Closest Pair of Points**:  
  \(T(n) = 2T(n/2) + Θ(n)\) → Θ(n log n).

---

## 4. Experimental Results
- **Time vs n**: measured using Metrics, output to `mergesort.csv`, `quicksort.csv`, `select.csv`, `closest.csv`.
- **Depth vs n**: QuickSort recursion depth matched ~2*log₂n.
- **Constant factors**: MergeSort slightly slower for small n due to buffer copy; QuickSort faster in practice due to cache.

(👉 Insert your plots/screenshots here: time vs n, depth vs n)

---

## 5. Summary
- Theory matches practice within constant factors.
- Deterministic Select grows linearly, but `Arrays.sort` often faster for small n due to library optimizations.
- Closest Pair verified against O(n²) brute force for small n, matched outputs.

---

## 6. Git Workflow
- `main` → stable releases.
- `feature/*` branches for each algorithm.
- `bench/jmh` → benchmark harness.
- `docs/report` → this README.
