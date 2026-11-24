#include<stdio.h>

int main() {
    int i, j, n, c;
    float p[20], w[20], r[20], t, max = 0;
    printf("Capacity: "); scanf("%d", &c);
    printf("Items: "); scanf("%d", &n);
    printf("Profit & Weight:\n");
    for(i = 0; i < n; i++) scanf("%f%f", &p[i], &w[i]), r[i] = p[i]/w[i];

    // Sort by ratio descending
    for(i = 0; i < n - 1; i++)
        for(j = i + 1; j < n; j++)
            if(r[j] > r[i]) {
                t = r[i]; r[i] = r[j]; r[j] = t;
                t = w[i]; w[i] = w[j]; w[j] = t;
                t = p[i]; p[i] = p[j]; p[j] = t;
            }

    printf("\n%-10s%-10s%-10s%-15s\n", "Profit", "Weight", "Ratio", "Capacity Left");
    for(i = 0; i < n && c > 0; i++) {
        float used = (w[i] <= c) ? w[i] : c;
        float gain = (w[i] <= c) ? p[i] : (c / w[i]) * p[i];
        max += gain;
        c -= used;
        printf("%-10.2f%-10.2f%-10.2f%-15d\n", p[i], w[i], r[i], c);
    }

    printf("\nMax profit: %.2f", max);
    return 0;
}